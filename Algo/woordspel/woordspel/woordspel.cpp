#include <iostream>
#include "graaf.h"

using namespace std;

int main()
{
	cout << "Geef een woord: " << endl;
	string woord;
	cin >> woord;
	Graaf<GERICHT> graaf;
	graaf.voegKnoopToe();
	graaf.voegKnoopToe();
	graaf.voegKnoopToe();
	graaf.voegKnoopToe();
	graaf.voegKnoopToe();
	graaf.voegVerbindingToe(0, 2);
	graaf.voegVerbindingToe(0, 4);
	graaf.voegVerbindingToe(1, 2);
	graaf.voegVerbindingToe(3, 2);
	
	
	//cout << graaf<<endl<<endl;
	
	/*vector<int> po = graaf.postOrder();

	for (int i = 0; i < po.size(); i++){
		cout << i << " -> " << po[i] << endl;
	}*/
	graaf.samenhangZoeken();

	cin.ignore();
	cin.get();
	return 0;
}

