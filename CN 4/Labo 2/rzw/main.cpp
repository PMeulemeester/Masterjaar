/* 
 * File:   main.cpp
 * Author: pieterm
 *
 * Created on 7 oktober 2014, 11:13
 */

#include <cstdlib>
#include <assert.h> 
#include "RzwBoom.h"

using namespace std;

/*
 * 
 */
int main(int argc, char** argv) {
    

	Rzwboom<int, int> f;

	f.voegtoe(7,0, false);
	f.voegtoe(5,0, false);
	f.voegtoe(10,0, false);
	f.voegtoe(3,0, false);
	f.voegtoe(6,0, false);
	f.voegtoe(9,0, false);
	f.voegtoe(12,0, false);
	f.voegtoe(2,0, false);
	f.voegtoe(4,0, false);
	f.voegtoe(8,0, false);
	f.voegtoe(11,0, false);
	f.voegtoe(1,0, false);

	cout << f << endl << endl;

	f.kleur();

	cout << f << endl << endl;


	return 0;
}

