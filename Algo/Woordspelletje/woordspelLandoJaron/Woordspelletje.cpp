#include <iostream>
#include "graaf.h"
using namespace std;

int main() {
	cout<<"Geef een woord: "<<endl;
	string woord;
	cin>>woord;
	Graaf<GERICHT> graaf;
	graaf.voegKnoopToe();
	graaf.voegKnoopToe();
	graaf.voegKnoopToe();
	graaf.voegKnoopToe();
	graaf.voegKnoopToe();
	graaf.voegVerbindingToe(0,2);
	graaf.voegVerbindingToe(0,4);
	graaf.voegVerbindingToe(1,2);
	graaf.voegVerbindingToe(3,2);
	cout<<graaf;
	
	

	Graaf<GERICHT> omgekeerde;
	omgekeerde = graaf.keerOm();
	cout<<omgekeerde.aantalKnopen();
	cout<<omgekeerde;
	
	
	
	cout << endl << endl;
	graaf.samenhangZoeken();
	cin>>woord;
	/*for(int i=0;i<graaf.componentNummers.size();i++){
		cout << graaf.componentNummers[i]<<" ";
	}*/
	return 0;
}
