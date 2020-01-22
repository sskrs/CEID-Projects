#include <sys/types.h>
#include <sys/wait.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <unistd.h>
#include <string.h>
int main(int argc, char *argv[])  {
    struct sockaddr_in addr;
    int socketfd, nsocketfd;
    char buffer[1024];
    int result, i, val, pid, newread, child_proc;
    
	
    if (argc < 2) { 
    	printf("%s port\n", argv[0] ); 
	return -1;
    }

    if(argc <3) {
	printf("children process missing\n");
	return -1;
    }

    if (argc > 1)   {
       child_proc = atoi(argv[2]);
    }
    
    socketfd = socket(AF_INET, SOCK_STREAM, 0);
    val = 1;
    
    if(setsockopt(socketfd, SOL_SOCKET, SO_REUSEADDR, &val, sizeof(val)) <0)
    {
        perror("preforking\n");
        return 0;
    }
    
    addr.sin_family = AF_INET;
    addr.sin_port = htons(atoi(argv[1]));
    addr.sin_addr.s_addr = INADDR_ANY;
    
    if(bind(socketfd, (struct sockaddr *) &addr, sizeof(addr))<0)
    {
        perror("preforking\n");
        return 0;
    }
    
    if(listen(socketfd, 5) < 0)
    {
        perror("perforking");
        return 0;
    }
    
for (i = 0; i< child_proc; i++) {
if ((pid = fork()) == 0)    {
            for(;;)  {
                nsocketfd = accept(socketfd, NULL, NULL);
                newread = recv(nsocketfd, buffer, 50, 0);
                buffer[newread] = '\0';
                send(socketfd, buffer, newread, 0);
                close(nsocketfd);
                
            }
        }
    }
    
wait(NULL);    
}
