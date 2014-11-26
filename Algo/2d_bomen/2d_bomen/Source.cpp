#include <iostream>
#include "boom2d.h"

using namespace std;

int main(){
	Boom2d boom;
	boom.voegtoe(punt2(50, 50));
	boom.voegtoe(punt2(25, 75));
	boom.voegtoe(punt2(75, 25));
	boom.voegtoe(punt2(45, 70));
	boom.voegtoe(punt2(10, 80));
	boom.voegtoe(punt2(70, 20));
	boom.voegtoe(punt2(70, 30));
	
	cout<< (boom.bevat_punt(punt2(70, 32))==1 ? "true" : "false" )<<endl;

	cin.get();
	return 0;
}