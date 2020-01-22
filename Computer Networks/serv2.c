#include <stdio.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <netinet/in.h>
#include <sys/wait.h>
#include <signal.h>
#include <unistd.h>
#include <string.h>
#include <stdlib.h>

void sigchld_handler(int signo)
{
	while(waitpid(-1,NULL,WNOHANG) >0);
}
int main(int argc,  char*argv[])
{
	struct sockaddr_in server_addr;
	int sockfd, nsockfd, newread,pid,val;
	char buffer[1024];

	if(argc < 2){
		printf("need port\n");
	exit (1);
	}
	sockfd=socket(AF_INET,SOCK_STREAM, 0);
	val =1;
	if(setsockopt(sockfd,SOL_SOCKET, SO_REUSEADDR,&val, sizeof(val))<0 ){
		perror("error!:*******\n");
		return 0;
	}

	server_addr.sin_family =AF_INET;
	server_addr.sin_port=htons(atoi(argv[1]));
	server_addr.sin_addr.s_addr=INADDR_ANY;
	if(bind(sockfd, (struct sockaddr*)&server_addr, sizeof(server_addr))<0){
		perror("error!:*******\n");
		return 0;
	}


	if (listen(sockfd,5)<0){
		perror("error!:******\n");
		return 0;
	}

	signal(SIGCHLD, sigchld_handler);
	for(;;){
		nsockfd=accept(sockfd,NULL,NULL);
		if((pid=fork()==0)){
		close(sockfd);
		newread=(recv(nsockfd,buffer,50,0));
		buffer[newread]='\0';
		send(nsockfd,buffer,newread,0);
		close(nsockfd);
		exit(0);
	}
	close(nsockfd);
	}
}


