#include <iostream>
#include "rzwboom.h"
#include <stack>

using namespace std;

int main(){
	Rzwboom<int, int> b;
	b.voegtoe(3, 3,false);
	b.voegtoe(10, 10,false);
	b.voegtoe(12, 12,false);
	b.voegtoe(6, 6,false);
	b.voegtoe(1, 1,false);
	b.voegtoe(18, 18, false);
	b.voegtoe(9, 9,false);
	b.voegtoe(4, 4,false);
	cout << b << endl<<endl<<endl;

	//cout<<(b.is_kleurbaar_test()==1 ? "true" : "false" )<<endl;

	b.roteer(true);

	cout << b << endl << endl;

	stack<int> st;
	st.push(5);
	st.push(10);
	st.push(2);

	while (!st.empty()){
		cout << st.top() << endl;
		st.pop();
	}

	/*b.voegtoe(20, 20, false);
	
	cout << (b.is_kleurbaar_test() == 1 ? "true" : "false") << endl;

	b.voegtoe(0, 0, false);
	//b.voegtoe(2, 2, false);

	cout << (b.is_kleurbaar_test() == 1 ? "true" : "false") << endl;
	*/
	cin.get();
	return 0;
}