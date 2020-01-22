#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include "display.h"
#include <stdio.h>
#include <sys/ipc.h>
#include <stdlib.h>
#include <pthread.h>

/*dhlwsh buffer,condition variables, mutexes kai synarthsewn me t mhnymata*/
pthread_mutex_t lock_mutex;
pthread_cond_t cond_var;
void *prwtomhnyma();
void *deyteromhnyma();
int buffer = 0;
#define BUFFER_READY 19

int main()
{
	pthread_t thread1, thread2;
	
	pthread_create( &thread1, NULL, &prwtomhnyma, NULL);
	pthread_create( &thread2, NULL, &deyteromhnyma, NULL);
	
	pthread_join( thread1, NULL);
	pthread_join( thread2, NULL);
	
	pthread_mutex_destroy(&lock_mutex);
	return 0;
}

void *prwtomhnyma()
{
	for(;;) {
   		/*kleidwma mutex kai perimenoume gia signal na eleutherwthei to mutex*/
   		pthread_mutex_lock( &lock_mutex );
   		if ( buffer % 2 != 0 ) {
			pthread_cond_wait( &cond_var, &lock_mutex );
   		}
   		buffer++;
		display("ab");
   		pthread_cond_signal( &cond_var );
   		if ( buffer >= BUFFER_READY ) {
     			pthread_mutex_unlock( &lock_mutex );
     			return(NULL);
   		}
   		pthread_mutex_unlock( &lock_mutex );
 	}
}


void *deyteromhnyma()
{
  	for(;;) {
  	 	/*kleidwma mutex kai perimenoume gia signal na eleutherwthei to mutex*/
  		pthread_mutex_lock( &lock_mutex );
  		if ( buffer % 2 == 0 ) {
		    	pthread_cond_wait( &cond_var, &lock_mutex );
  		}	
  		buffer++;
  		display("cd\n");
		pthread_cond_signal( &cond_var );
  		if( buffer >= BUFFER_READY ) {
 			pthread_mutex_unlock( &lock_mutex );
    			return(NULL);
  		}
  		pthread_mutex_unlock( &lock_mutex );
 	}
}
