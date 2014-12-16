#ifndef _SOCKET_H_
#define _SOCKET_H_

#include <iostream>
#include <sstream>
#include <string>
#include <stdexcept>
#include <sys/socket.h>
#include <sys/types.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <netdb.h>
#include <cstring>
#include <cerrno>

template <typename T>
std::string to_string(const T &x) 
{
	std::ostringstream os;
	os << x;
	return os.str();
}

struct NetwerkExceptie: public std::runtime_error
{
	NetwerkExceptie(const std::string &s) 
	: std::runtime_error(s)
	{}
};

//Maakt een IPv4 socket-adres aan de hand van gegeven 'hostnaam' (bv. "localhost" of "www.example.net") en 'poort'.
//Het derde argument is SOCK_STREAM of SOCK_DGRAM
// throws NetwerkExceptie bij problemen.
sockaddr_in maak_adres_ipv4(const std::string &servernaam, short poort, int socktype)
{
    int status;
    struct addrinfo hints;
    struct addrinfo *res,*p;  // will point to the results
    char ipstr[INET_ADDRSTRLEN];

    memset(&hints, 0, sizeof hints); // make sure the struct is empty
    hints.ai_family = AF_INET;     // don't care IPv4 or IPv6
    hints.ai_socktype = socktype; // TCP stream sockets

    if ((status = getaddrinfo(servernaam.c_str(), to_string(poort).c_str(), &hints, &res)) != 0) {
        throw NetwerkExceptie(std::string("Problemen met maak_adres_ipv4(): ")+gai_strerror(status));
    }
    for(p=res;p!=NULL;p=p->ai_next){
        struct sockaddr_in *ipv4=(struct sockaddr_in *)p->ai_addr;
        void *addr = &(ipv4->sin_addr);
        inet_ntop(AF_INET, addr, ipstr, INET_ADDRSTRLEN);        
        std::cout << ipstr << std::endl;
    }
    struct sockaddr_in result = *(struct sockaddr_in *)res->ai_addr;
    freeaddrinfo(res);
    return result;
}

//idem voor ipv6
sockaddr_in6 maak_adres_ipv6(const std::string &servernaam, short poort, int socktype)
{
    struct addrinfo hints, *res, *p;
    int status;
    char ipstr[INET6_ADDRSTRLEN];
    memset(&hints, 0, sizeof hints);
    hints.ai_family = AF_INET6;
    hints.ai_socktype = socktype;
    
    if ((status = getaddrinfo(servernaam.c_str(), to_string(poort).c_str(), &hints, &res)) != 0){
        throw NetwerkExceptie(std::string("Problemen met maak_adres_ipv6(): ")+gai_strerror(status));
    }
    
    for(p = res; p!=NULL; p = p->ai_next){
        struct sockaddr_in6 *ipv6 = (struct sockaddr_in6 *)p->ai_addr;       
        void * addr = &ipv6->sin6_addr;        
        inet_ntop(AF_INET6, addr, ipstr, INET6_ADDRSTRLEN);        
        std::cout << ipstr << std::endl; 
        
    } 
	//zelfde opm ivm freeaddrinfo
    return *(struct sockaddr_in6 *)res->ai_addr;
}


// Maakt een standaard tcp-socket (IPv4 en streaming) en geeft descriptor terug. 
// throws NetwerkExceptie indien dit niet lukt.
int maak_tcp_socket()
{
    int fd = socket(PF_INET,SOCK_STREAM,IPPROTO_TCP);
    if(fd < 0)
        throw NetwerkExceptie("Problemen met socket(): "+std::string(strerror(errno)));
    return fd;
}

// Sluit de socket met descriptor sd.	
// throws NetwerkExceptie indien dit niet lukt.
void sluit(int sd)
{
    //if(close(sd) < 0)
      //  throw NetwerkExceptie("Problemen met sluit(): "+std::string(strerror(errno)));
    //close(sd);
}


// Maakt een IPv4 server-adres dat luistert op de gegeven 'poort' (op alle netwerkinterfaces, TCP socket).
// Kan je gebruik maken van de methode maak_adres_ipv4, of moet je manueel een struct invullen?
sockaddr_in maak_server_adres_ipv4(short poort)
{
    sockaddr_in servaddr;
    memset(&servaddr, 0, sizeof (servaddr));
    servaddr.sin_family = AF_INET;
    servaddr.sin_addr.s_addr = INADDR_ANY;
    servaddr.sin_port = htons(poort);      //htons!   
    return servaddr;
}

// idem voor ipv6
sockaddr_in6 maak_server_adres_ipv6(short poort)
{
    sockaddr_in6 servaddr;
    memset(&servaddr, 0, sizeof servaddr);
    servaddr.sin6_family = AF_INET6;
    servaddr.sin6_addr = in6addr_any;
    servaddr.sin6_port = htons(poort);
    return servaddr;  
}

//configureer de socket zodat het adres kan hergeberuikt worden
void set_hergebruik_adres(int fd){
    int yes = 1;
    setsockopt(fd, SOL_SOCKET, SO_REUSEADDR, &yes, sizeof(yes));
}

// Laat server-socket (descriptor sd) luisteren op de gegeven poort (max. 10 connecties). 
// IPv4, TCP sockets
// throws NetwerkExceptie indien er een fout optreedt.
void verbind_en_luister(int sd, short poort) 
{ 
    sockaddr_in servaddr = maak_server_adres_ipv4(poort);
    if(bind(sd,(struct sockaddr *)&servaddr,sizeof(servaddr)) < 0)
        throw NetwerkExceptie("Problemen met bind: "+std::string(strerror(errno)));
    
    if(listen(sd,10) < 0)
        throw NetwerkExceptie("Problemen met listen: "+std::string(strerror(errno)));
}

// Laat server-socket (descriptor sd) wachten op een client die wil connecteren.
// Return de socketdescriptor van de client.
// throws NetwerkExceptie indien er een fout optreedt.
int accepteer(int sd) 
{
    sockaddr_in cliaddr;
    unsigned int len = sizeof(cliaddr);
    int connfd = accept(sd,(sockaddr *) &cliaddr, &len);
    if(connfd < 0)
        throw NetwerkExceptie("Problemen met accepteer: "+std::string(strerror(errno)));
    return connfd;
}


//Probeert client-socket (descriptor sd) te connecteren met gegeven adres
// throws NetwerkExceptie indien er een fout optreedt.
void connecteer(int fd, const sockaddr_in & adres){
    if(connect(fd, (struct sockaddr *) &adres,sizeof(adres)) < 0){
        throw NetwerkExceptie("Problemen met connect : "+std::string(strerror(errno)));
    }
}

//idem, maar op servernaam
void connecteer(int fd, const std::string &servernaam, short poort)
{
    connecteer(fd, maak_adres_ipv4(servernaam, poort,SOCK_STREAM));
}

// Probeert string 's' te schrijven naar socket met descriptor 'sd'.
// Return aantal effectief uitgeschreven.
// throws NetwerkExceptie indien er een fout optreedt. 
int schrijf(int fd, const std::string &s) 
{
    int noWritten = send(fd,s.c_str(),s.length(),0);
    if(noWritten < 0)
        throw NetwerkExceptie("Problemen met send: "+std::string(strerror(errno)));
    return noWritten;
}

// Probeert 'buflen' karakters in te lezen in 'buffer' uit socket met descriptor 'sd'.
// Return aantal effectief ingelezen.
// throws NetwerkExceptie indien er een fout optreedt. 
int lees(int fd, char *buffer, int buflen)
{
    int noRecv = recv(fd,buffer,buflen,0);    
    if(noRecv < 0)
        throw NetwerkExceptie("Problemen met recv: "+std::string(strerror(errno)));
    return noRecv;
}

#endif