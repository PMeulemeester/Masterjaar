#include <iostream>

#include "Zoekboom.h"

using namespace std;

int main(){
	Zoekboom<int,int> z;

	z.voegtoe(10, 10);
	z.voegtoe(3, 3);
	z.voegtoe(12, 12);
	z.voegtoe(5, 5);
	z.voegtoe(2, 2);
	z.voegtoe(20, 20);
	z.voegtoe(4, 4);
	z.voegtoe(9, 9);

	cout << z << endl << endl;

	z.verwijder(3);

	cout << z << endl << endl;

	cin.get();
	return 0;
}