/* 
 * File:   main.cpp
 * Author: pieterm
 *
 * Created on 14 december 2014, 13:13
 */

#include <cstdlib>
#include <iostream>
#include "socket.h"

using namespace std;

/*
 * 
 */
int main(int argc, char** argv) {
    short serverPort = 12345;
    std::string invoer;
    int bufferLength = 1000;
    char buffer [bufferLength];
    int noRecv = 0;
    
    int clientSocket = maak_tcp_socket();
    cout<<clientSocket<<endl;
    set_hergebruik_adres(clientSocket);
    connecteer(clientSocket,"localhost",serverPort);
    std::cout << "Please enter text" << std::endl;    
    getline(std::cin,invoer);
    while(!invoer.empty()){
        schrijf(clientSocket,invoer);
        if((noRecv = lees(clientSocket,buffer,bufferLength)) > 0){
                std::cout << std::string(buffer,noRecv) << std::endl;            
                getline(std::cin,invoer);    
        }
        else
            break;
    }
      
    sluit(clientSocket);
    return 0;
}

