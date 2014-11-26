/* 
 * File:   RzwBoom.h
 * Author: pieterm
 *
 * Created on 7 oktober 2014, 11:19
 */

#ifndef RZWBOOM_H
#define	RZWBOOM_H

#include <cstdlib>
#include <iostream>
using std::ostream;
using std::max;
using std::min;
using std::pair;
#include <queue>


template <class T,class D>
class Rzwboom;

template <class T,class D>
class Rzwknoop;


template <class T,class D>
class Rzwboom{
friend class Rzwknoop<T,D>;
const static int rood,zwart;
public:
    Rzwboom<T,D>(){
        k=0;
    }
    ~Rzwboom<T,D>(){
        delete k;
    }
//bottom-up toevoegen, geen duplicaten toegestaan.
//geeft false als de sleutel al in de boom zat.
//geldigeKleur=false: knoop wordt toegevoegd zonder op kleur te letten.
    bool voegtoe(const T&,const D&,bool geldigeKleur=true );
//volgende functie geeft false als de sleutel niet kan verwijderd
//worden omdat hij niet in de boom zit.
    bool verwijder(const T&);
    D* zoekdata(const T&);
    int diepte();
	pair<int, int> min_en_max_diepte();
// << schrijft uit in level order.
	void kleur();
	void kleur(int wortelMin,  int hoogte);
    friend ostream& operator<<(ostream& os,Rzwboom<T,D>& b){
        b.schrijf(os);
        return os;
    }
//-1 is ongeldige boom; zwartediepte kan dus ook gebruikt worden
//om boom te controleren.
    int zwartediepte();
    int zwartediepte2();
//geeft de voorganger als de linkerboom niet leeg is
    Rzwboom<T,D>* ondervoorganger(Rzwknoop<T,D>*);
    bool ouderOK();
	bool is_kleurbaar();
private:
    void schrijf(ostream&);
    int geefkleur(){
        if (k!=0)
           return k->rzw;
        else
            return zwart;
    }
//Dit hoort op het eerste gezicht bij de klasse Rzwknoop.
//Maar dit kan niet omdat kn een nulpointer mag zijn
    void schrijf(ostream& os ,Rzwknoop<T,D>* kn);
//alleen roteer oproepen als de operatie kan!
    void roteer(bool naarlinks);
//maakzwart maakt de knoop in plaats->k zwart, en laat plaats wijzen naar
//volgende boom die een probleem kan opleveren bij bottom-up toevoegen.
    void maakzwart(Rzwboom<T,D>*& plaats);
//zoekknoop: postconditie: plaats wijst naar knoop met gezochte sleutel,
//                           of naar lege boom die naar die knoop zou wijzen als hij bestond
//                         ouder heeft waarde die in het ouderveld van de knoop staat/zou moeten staan
    void zoekknoop(const T&,Rzwboom<T,D>*& plaats,Rzwboom<T,D>*& ouder);

//gegevensveld
    Rzwknoop<T,D>* k;
};
template <class T,class D>
const int Rzwboom<T,D>::rood=0;
template <class T,class D>
const int Rzwboom<T,D>::zwart=1;

template <class T,class D>
class Rzwknoop{
    friend class Rzwboom<T,D>;
    public:
        T sl;
        D data;
        int rzw;
        Rzwboom<T,D>* ouder;//wijst naar de boom die naar de ouderknoop wijst
        Rzwboom<T,D> links,rechts;
        Rzwknoop<T,D>(const T& _sl,const D& _data):
                ouder(0),sl(_sl),data(_data),rzw(Rzwboom<T,D>::zwart){}
};

template <class T,class D>
void Rzwboom<T,D>::roteer(bool naarlinks){
    Rzwknoop<T,D>* op;
    Rzwknoop<T,D>* neer=k;
    if (naarlinks){
        op=k->rechts.k;
        if (neer->links.k!=0)
            neer->links.k->ouder=&(op->links);
        neer->rechts.k=op->links.k;
        if (op->links.k!=0){
            Rzwknoop<T,D>* kind=op->links.k;
            kind->ouder=&(op->links);
            if (kind->links.k!=0)
                kind->links.k->ouder=&(neer->rechts);
            if (kind->rechts.k!=0)
                kind->rechts.k->ouder=&(neer->rechts);
        }
        if (op->rechts.k!=0)
            op->rechts.k->ouder=this;
        op->links.k=neer;
    }
    else{
        op=k->links.k;
        if (neer->rechts.k!=0)
            neer->rechts.k->ouder=&(op->rechts);
        neer->links.k=op->rechts.k;
        if (op->links.k!=0)
            op->links.k->ouder=this;
        if (op->rechts.k!=0)
            op->rechts.k->ouder=&(op->rechts);
        if (op->rechts.k!=0){
            Rzwknoop<T,D>* kind=op->rechts.k;
            kind->ouder=&(op->rechts);
            if (kind->links.k!=0)
                kind->links.k->ouder=&(neer->links);
            if (kind->rechts.k!=0)
                kind->rechts.k->ouder=&(neer->links);
        }
        op->rechts.k=neer;
    }
    k=op;
    op->ouder=neer->ouder;
    neer->ouder=this;
}



template <class T,class D>
void Rzwboom<T,D>::zoekknoop(const T& sl,Rzwboom<T,D>*& plaats,
                                         Rzwboom<T,D>*& ouder){
    plaats=this;
    ouder=0;
    while (plaats->k!=0 && plaats->k->sl!=sl){
        ouder=plaats;
        if (sl<plaats->k->sl)
            plaats=&(plaats->k->links);
        else
            plaats=&(plaats->k->rechts);
    }
}
        
template <class T,class D>
D* Rzwboom<T,D>::zoekdata(const T& sl){
    Rzwboom<T,D>* plaats;
    Rzwboom<T,D>* ouder;//dummy, eigenlijk overbodig bij zoekdata
    zoekknoop(sl,plaats,ouder);
    if (plaats->k==0)
        return 0;
    else return &(plaats->k->data);
}

template <class T,class D>
void Rzwboom<T,D>::maakzwart(Rzwboom<T,D>*& plaats){
    Rzwboom<T,D>* kind=plaats;
    Rzwboom<T,D>* ouder=kind->k->ouder;
    Rzwboom<T,D>* grootouder=ouder->k->ouder;
    if (grootouder->k->links.geefkleur()==rood && grootouder->k->rechts.geefkleur()==rood){
        grootouder->k->rzw=rood;
        grootouder->k->links.k->rzw=zwart;
        grootouder->k->rechts.k->rzw=zwart;
    }
    else {//geval 1
        bool ouderlinks=(ouder==&(grootouder->k->links));
        bool kindlinks=(kind==&(ouder->k->links));
        if (kindlinks!=ouderlinks){//geval 1b
            if (ouderlinks)
                grootouder->k->links.roteer(true);
            else
                grootouder->k->rechts.roteer(false);
        };
        //geval 1a
        grootouder->k->rzw=rood;
        ouder->k->rzw=zwart;
        grootouder->roteer(!ouderlinks);
    }
    plaats=grootouder;
}

//bottom up
template <class T,class D>
bool Rzwboom<T,D>::voegtoe(const T& sl,const D& data, bool geldigeKleur){
    Rzwboom<T,D>* plaats;
    Rzwboom<T,D>* ouder;
    zoekknoop(sl,plaats,ouder);
    bool nietgevonden=plaats->k==0;
    if (nietgevonden){
        plaats->k=new Rzwknoop<T,D>(sl,data);
        plaats->k->ouder=ouder;
//noot: ouder wijst nooit naar een ledige boom.
        if (geldigeKleur){
            bool dubbelrood=(ouder!=0 && ouder->k->rzw==rood);
            while (dubbelrood){
                if (sl==18){
                    std::cout<<"plaats->k->sl== "<<plaats->k->sl;
                    std::cout<<"ouder->k->sl== "<<ouder->k->sl;
                    std::cout<<'\n';
                }
                maakzwart(plaats);
                assert(plaats->k!=0);
                ouder=plaats->k->ouder;
                dubbelrood=(plaats->k->rzw==rood && ouder !=0
                                              && ouder->k->rzw==rood);
            }
            k->rzw=zwart;//eventueel wortel zwart maken.
        }
    }
    return nietgevonden;
}

template <class T,class D>
int Rzwboom<T,D>::zwartediepte2(){
    if(k==0){
        return 0; 
    }else{
    	int hulp=0;
        if(k->rzw==zwart){
            hulp=1;
        }
		
        int linkerdiepte=k->links.zwartediepte();
        
        if(linkerdiepte==-1){
        	return -1;
        }
        
		int rechterdiepte=k->rechts.zwartediepte();

        
        
        if(linkerdiepte!=rechterdiepte){
            return -1;
        }else{
            return rechterdiepte+hulp;
        }
    }
}

template <class T, class D>
int Rzwboom<T,D>::diepte() {
	int ld = 0;
	int rd = 0;
	if (k != 0) {
		ld = k->links.diepte();
		rd = k->rechts.diepte();
	}
	return (ld < rd ? rd : ld) + 1;
}

template <class T, class D>
pair<int, int> Rzwboom<T,D>::min_en_max_diepte() {
	pair<int,int> ret = std::make_pair(0,0);
	if (k != 0) {
		pair<int,int> ld = k->links.min_en_max_diepte();
		pair<int,int> rd = k->rechts.min_en_max_diepte();

		ret.first = min(ld.first, rd.first) + 1;
		ret.second = max(ld.second, rd.second) + 1;
	}
	return ret;
}

template<class T, class D>
void Rzwboom<T,D>::kleur(){
	int min = min_en_max_diepte().first;
	if(k != 0){
		k->links.kleur(min,1);
		k->rechts.kleur(min,1);
	}
}

template<class T, class D>
void Rzwboom<T,D>::kleur(int wortelMin, int hoogte){
	if(k != 0){
		if(k->ouder->k->rzw==zwart){
			int minL = k->links.min_en_max_diepte().first+1;	//+1 omdat we de huidige knoop er niet bijtellen
			int minR = k->rechts.min_en_max_diepte().first+1;
			int min = ( minL > minR ? minR : minL);
			if(min + hoogte > wortelMin){
				k->rzw=rood;
			}			
		}
		k->links.kleur(wortelMin,hoogte+1);
		k->rechts.kleur(wortelMin,hoogte+1);
	}
}


template <class T,class D>
void Rzwboom<T,D>::schrijf(ostream& os){
    if (k!=0){
       std::queue<Rzwknoop<T,D>*, std::deque<Rzwknoop<T,D>*> > q;
       q.push(k);
       while(!q.empty()){
           Rzwknoop<T,D>* nu=q.front();
           schrijf(os,nu);
           os<<" links: ";
           schrijf(os,nu->links.k);
           os<<" rechts: ";
           schrijf(os,nu->rechts.k);
           os<<" ouder: ";
           if (nu->ouder==0)
               schrijf(os,0);
           else
               schrijf(os,nu->ouder->k);
           os<<std::endl;
           if (nu->links.k!=0)
               q.push(nu->links.k);
           if (nu->rechts.k!=0)
               q.push(nu->rechts.k);
           q.pop();
       }
    }
    else{
        schrijf(os,k);
    }
}

template <class T,class D>
void Rzwboom<T,D>::schrijf(ostream& os ,Rzwknoop<T,D>* kn){
    if (kn!=0)
        os<<
        (kn->rzw==rood?
            "rood ":"zwart")
        <<"("<<kn->sl<<","<<kn->data<<")";
    else
        os<<"---";
}

template <class T, class D>
int Rzwboom<T,D>::zwartediepte() { //we tellen alle zwarte knopen, inclusief het fictieve blad, maar zonder de wortel
	int ld = 0;
	int isWortel = 0;
	if (k != 0) {
		ld = k->links.zwartediepte();
		if (k->ouder == 0) { isWortel = 1; } //Bij zwartediepte moet de wortel niet meegeteld worden. De wortel heeft geen ouder, en voor hem trekken we 1 af van de gehele diepte
	}

	int zelf = 0;
	if (geefkleur() == zwart) {
		zelf = 1;
	}

	return ld + zelf - isWortel;
}

template <class T, class D>
bool Rzwboom<T,D>::is_kleurbaar() {
	//Voor elke boom moeten de min- en maxdiepte van linkerdeelboom overlappen met de min- en maxdiepte van de rechter deelboom
	
	//min en max L en R deelboom ophalen
	//als overlap tussen minL <-> maxL, minL <-> maxL: in orde
	//anders, als minL ==  maxL en minR == maxR en verschil is <= factor 2: in orde

	if (k != 0) {
		pair<int,int> L = k->links.min_en_max_diepte();
		pair<int,int> R = k->rechts.min_en_max_diepte();

		if ((L.first <= R.first && L.second >= R.first) || (R.first <= L.first && R.second >= L.first)) { //is er overlap?
			//return true;
			return k->links.is_kleurbaar() && k->rechts.is_kleurbaar();
		}
		else if (L.first == L.second && R.first == R.second) { //zijn min/max dan gelijk?
			return (L.first*2 >= R.first || L.first/2 <= R.first); //als ze factor 2 schelen dan is ie kleurbaar
		}
		else {
			return false;
		}
	}
	else {
		return true;
	}
}

#endif	/* RZWBOOM_H */

