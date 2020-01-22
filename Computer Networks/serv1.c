#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <netinet/in.h>
#include <sys/types.h>
#include <sys/socket.h>

int main(int argc, char *argv[])
{
	//dhlwseis metablhtwn
	int sockfd, nsockfd,port, client_leng;
	struct sockaddr_in server_addr, client_addr;
	char buffer[1024];
	if(argc < 2 )
	{
		printf("Error, no port\n");
		exit(1);
	}
	
	sockfd=socket(AF_INET, SOCK_STREAM, 0); //SETUP OF THE SOCKET

	if (sockfd<0 )
	{
		printf("Opening Socket: Error!\n");
		exit(1);
	}

	port=atoi(argv[1]);

	memset(&server_addr, '\0', sizeof(server_addr));
	server_addr.sin_family=AF_INET;
	server_addr.sin_addr.s_addr=INADDR_ANY;
	server_addr.sin_port=htons(port);

	if(bind(sockfd, (struct sockaddr *) &server_addr, sizeof(server_addr)) < 0 )
	{	printf("Bind: Error!\n");
		exit(1);}
	
	listen(sockfd,5);

	client_leng = sizeof(client_addr);


	for(;;){
		nsockfd=accept(sockfd, (struct sockaddr *)&client_addr, &client_leng);
		if(nsockfd<0)
		{
		printf("Accept: Error!\n");
		exit(0);
		}
		for(;;){
		send(nsockfd,buffer,strlen(buffer) + 1, 0);
		recv(nsockfd,buffer, 50,0);
		break;
		}
		close(nsockfd);	
		
		
		}
	return 0;
}
