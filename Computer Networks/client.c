#include <stdio.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <netdb.h> 
#include <unistd.h>
#include <string.h>
#include <stdlib.h>
#include <arpa/inet.h>
#include <string.h>

int main(int argc, char *argv[]) 
{
	char buffer[1024];
        int sockfd, portnumber;
   	struct sockaddr_in serv_addr;
	struct hostent *server;
  
   	if (argc < 3) {
      		printf("Error: no hostname and port\n");
      		exit(0);
   	}
	
   	portnumber = atoi(argv[2]);
   
   	//setup tou socket
   	sockfd = socket(AF_INET, SOCK_STREAM, 0);
   
   	if (sockfd < 0) {
      		perror("ERROR opening socket");
      		exit(1);
   	}
	
   	server = gethostbyname(argv[1]);
   
   	if (server == NULL) {
      		printf("ERROR, no such host exists!\n");
      		exit(0);
   	}
   
   	bzero((char *) &serv_addr, sizeof(serv_addr));
   	serv_addr.sin_family = AF_INET;
   	bcopy((char *)server->h_addr, (char *)&serv_addr.sin_addr.s_addr, server->h_length);
   	serv_addr.sin_port = htons(portnumber);
   
   	//sundesh me server
   	if (connect(sockfd, (struct sockaddr*)&serv_addr, sizeof(serv_addr)) < 0) {
    		  perror("Connect******Error!\n");
     		 exit(1);
   	}
   
   
   	printf("Enter the msg: \n");
   	bzero(buffer,1024);
   	fgets(buffer,50,stdin);
   
   	//stelnoume mhnuma ston server
   	if(write(sockfd, buffer, strlen(buffer))< 0) {
      		perror("ERROR writing to socket");
      		exit(1);
   	}
   
   	bzero(buffer,1024);
   	if (read(sockfd, buffer, 50)< 0) {
      		perror("ERROR reading from socket");
        	exit(1);
   	}
	return 0;
}
