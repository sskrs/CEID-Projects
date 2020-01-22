#include <stdio.h>
#include <string.h>
#include <pthread.h>
#include <stdlib.h>
#include <unistd.h>
#include "display.h"
#include <sys/wait.h>
#include <sys/types.h>

/*dhmiourgia pinaka apo id nhmatwn megethous 2*/
pthread_t id[2];

pthread_mutex_t lock_mutex;
char print1,print2;
int pthread_create(pthread_t *thread, const pthread_attr_t *attr, void *(*start_routine) (void *), void *arg);


void* prwtomhnyma(void *arg);
void* deyteromhnyma(void *arg);


int main(void)
{
                  
        pthread_mutex_lock(&lock_mutex);

        print1 = pthread_create(&(id[0]),NULL, &prwtomhnyma,NULL);
        print2 = pthread_create(&(id[1]),NULL, &deyteromhnyma,NULL);

        pthread_mutex_unlock(&lock_mutex);

        pthread_join(id[0], NULL);
        pthread_join(id[1], NULL);
        pthread_mutex_destroy(&lock_mutex);
        return 0;
}

void* prwtomhnyma(void *arg)
{
	int i;
	for (i=0;i<10;i++){
		pthread_mutex_lock(&lock_mutex);
       		display("Hello world\n");
		pthread_mutex_unlock(&lock_mutex);              
  

	}

	return NULL;
}
        

void* deyteromhnyma(void *arg){
	int i;        
	for (i=0;i<10;i++){
		pthread_mutex_lock(&lock_mutex);     
		display("Kalimera kosme\n");
		pthread_mutex_unlock(&lock_mutex);
	}
	return NULL;
}
