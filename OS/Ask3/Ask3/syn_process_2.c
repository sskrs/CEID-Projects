#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include "display.h"
#include <stdio.h>
#include <time.h>
#include <sys/ipc.h>
#include <sys/sem.h>
#include <stdlib.h>

union semun  
{  
    int val;  
    struct semid_ds *buf;  
    unsigned short *array;  
    struct seminfo *__buf;  
};

int main()
{
  int i;
  int semset_id;
  int semset_id2;
  union semun sem_val;
 /*h domh union dhmioyrgei 3 pedia,sem_num,sem_op,sem_fl
  *sem_flg=0 
  *sem_num deixnei tn semaphore pou xrhsimopoioume
  *sem_op an einai -1 exoume down()
  *an einai +1 exoume up()
 */ 
  struct sembuf semoperation;
  
  /*dhmioyrgia semaphores*/ 
  //1h semaphore
  semset_id=semget(IPC_PRIVATE,1,0600);
  //2h semaphore
  semset_id2=semget(IPC_PRIVATE,1,0600);
  
  if (fork())
  {
   /*arxikopoihsh semaphore*/
   semoperation.sem_num = 0;
   semoperation.sem_op = 1;
   semoperation.sem_flg = 0; 
   semop(semset_id,&semoperation,1); 
   for (i=0;i<10;i++){
   semoperation.sem_num = 0;
   semoperation.sem_op = -1;
   semoperation.sem_flg = 0;
   semop(semset_id, &semoperation, 1);
   display("ab");
   semoperation.sem_num = 0;
   semoperation.sem_op = 1;
   semoperation.sem_flg = 0;
   semop(semset_id2, &semoperation, 1);
}
  wait(NULL);
  }
  else
  {
    for (i=0;i<10;i++){
	semoperation.sem_num = 0;
        semoperation.sem_op = -1;
        semoperation.sem_flg = 0;
        semop(semset_id2, &semoperation, 1);
      display("cd\n");
        semoperation.sem_num = 0;
	semoperation.sem_op = 1;
	semoperation.sem_flg = 0;
	semop(semset_id, &semoperation, 1);  
}}
/*apodeusmeush mnhmhs pou katalambanoyn oi semaphores*/
semctl(semset_id, 0, IPC_RMID); 
semctl(semset_id2, 0, IPC_RMID); 
  return 0;
}
