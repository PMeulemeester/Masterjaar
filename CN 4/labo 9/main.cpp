/* 
 * File:   main.cpp
 * Author: pieterm
 *
 * Created on 30 november 2014, 15:31
 */
#include <iostream>
#include <cstdlib>
#include "socket.h"


using namespace std;

/*
 * 
 */
int main(int argc, char** argv) {
    maak_adres_ipv6("www.v6.facebook.com",0,0);
    char str[INET_ADDRSTRLEN];
    //cout<<inet_ntop(AF_INET,&(s.sin_addr),str,INET_ADDRSTRLEN)<<endl;
    return 0;
}

