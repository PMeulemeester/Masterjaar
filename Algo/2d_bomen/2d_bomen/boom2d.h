#ifndef __BOOM2D_H
#define __BOOM2D_H
#include <cstdlib>

#include <iostream>
#include <iomanip>
#include "punt2.h"

using namespace std;

class Bknoop;

class Boom2d{
	public:
		Boom2d():k(0){}
		~Boom2d(){
			delete k;
		}
		void voegtoe(const punt2 & punt);
		void voegtoe(const punt2& p, int d, Bknoop* o);
		bool bevat_punt(const punt2 &p);
		Bknoop* zoek(const punt2 &p);
		Bknoop* k;
		void print();
};

class Bknoop{
public:
	int diepte;
	punt2 data;
	Boom2d links, rechts;
	Bknoop* ouder;
	Bknoop(int _diepte) :ouder(0), diepte(_diepte){}
	Bknoop(int _diepte, const punt2& _data) :ouder(0), data(_data), diepte(_diepte){}
	void print_ouder();
};

void Bknoop::print_ouder(){
	cout << "ouder: " << ouder->data << endl;
}

void Boom2d::voegtoe(const punt2& punt){
	voegtoe(punt, 0, k);
}

void Boom2d::voegtoe(const punt2& punt, int d, Bknoop *o){
	if (k == 0){
		k = new Bknoop(d, punt);
		k->ouder = o;
	}
	else{
		if (k->diepte % 2 == 0){
			if (punt.x<k->data.x){
				k->links.voegtoe(punt, ++d, k);
			}
			else{
				k->rechts.voegtoe(punt, ++d, k);
			}
		}
		else{
			if (punt.y<k->data.y){
				k->links.voegtoe(punt, ++d, k);
			}
			else{
				k->rechts.voegtoe(punt, ++d, k);
			}
		}
	}
}

bool Boom2d::bevat_punt(const punt2 & punt){
	return zoek(punt)!=0 ? true : false;
}
Bknoop* Boom2d::zoek(const punt2 & p){
	if (k == 0){
		return 0;
	}
	else{
		if (p == k->data){
			return k;
		}
		else{
			if (k->diepte % 2 == 0){
				if (p.x<k->data.x){
					return k->links.zoek(p);
				}
				else{
					return k->rechts.zoek(p);
				}
			}
			else{
				if (p.y<k->data.y){
					return k->links.zoek(p);
				}
				else{
					return k->rechts.zoek(p);
				}
			}
		}
	}
}

void Boom2d::print(){

	if (k != 0){
		cout << "diepte: " << k->diepte << " punt: " << k->data << endl;
		//cout << "punt: " << k->data << endl;
		if (k->ouder != 0){
			k->print_ouder();
		}
		k->links.print();
		k->rechts.print();

	}
}


#endif