#include <errno.h>
#include <string.h>
#include <fcntl.h> 
#include <sys/wait.h>
#include <ctype.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>

int parse_command(char *command, char *delim, char ***argvp);
int execute(char* args[]) ;

//kurio programma
int main() {
  for(;;) {
  pid_t shell_pid = getpid();
  //prompt. 
  printf("$");
  // Pairnoume to input tou xrhsth apo to stdin.
  char* input = NULL;
  size_t input_size = 0;
  getline(&input, &input_size, stdin);
  /*h metablhth locat entopizei to shmeio pou yparxei '\n' (new line) sto string tou input kai epistrefei arithmo shmeioy pou brethike */
  char* locat = strchr(input, (int)'\n');
  *locat = '\0';
  
  //builtins entoles
  //..cd kai h exit
  char** builtin;
  parse_command(input, " ", &builtin);
  if (builtin[0] != NULL) {
  	if (strcmp(builtin[0], "exit") == 0)
  	exit(0);
  	else if (strcmp(builtin[0], "cd") == 0) {
  	chdir(builtin[1]);
  	continue; }
  }
  free(builtin);
  pid_t pid = fork();
  int status;
  if (pid != 0) { // Parent
  wait(NULL); } 
  else { // diergasia paidi
     char* command = NULL;
     int in, out;
     char kind;
     int i = strlen(input);
     for (; i > 0; i--) {
       if (input[i-1] == '|') {
         command = input + i;
         kind = input[i-1];
         input[i-1] = '\0';
         if (kind == '|') {
           int fd[2]; // 0 - read, 1 - write
           pipe(fd);
           pid_t newpid = fork();
           if (newpid == 0) {
           // Ayto einai to paidi, phgaine aristera
           //..kai to  stdout tou prepei na einai stou patera to stdin
           dup2(fd[1], STDOUT_FILENO);
           continue;
           }
           else { // Gonios, deksi meros
           dup2(fd[0], STDIN_FILENO);
           close(fd[1]); //  sunnexomena STDIN
           wait(&status);        				
           char** argv;
           parse_command(command, " ", &argv);
           execute(argv);
           }
         }
       }
     }
     //edw eimaste afotou dn exoume piping
     char** argv = NULL;
     parse_command(input, " \n", &argv);
     execute(argv); // Child prepei na kanei  kill ton eauto tou
  }
  // Eleutherwsh mnhmhs apo thn getline(). 
 free(input);
  } 
}
  
  //klhsh sunarthshs execute
  int execute(char* args[]) {
    int err = execvp(args[0], args);
    if (err < 0) {
     return 0; }
    return 1;
  } 

  /* dhmiourgia  argument array tou (*arvp) gia ta tokens sto s ta opoia diaxwrizontai apo delimiters
  Return -1 on error h ton arithmo twn tokens .*/
  int parse_command(char *command, char *delim, char ***argvp)
  {
    char *tok;
    char *new_command;
    int number_of_tokens;
    int i;
    // new_command h arxh tou string meta to "perasma" twn arxikwn delimiters 
    new_command = command + strspn(command, delim);
    /* dhmiourgia xwrou-mnhmhs gia to antigrafo tou new_command mesa sto tok */
    if ((tok = calloc(strlen(new_command) + 1, sizeof(char))) == NULL) {
      *argvp = NULL;
      number_of_tokens = -1;
    } 
    else {                     /* metrhma tou arithmou tokens sth metablhth new_commnad */
      strcpy(tok, new_command);
      if (strtok(tok, delim) == NULL)
      number_of_tokens = 0;
      else
      for (number_of_tokens = 1; strtok(NULL, delim) != NULL; number_of_tokens++);  
      // dhmiourgia pinaka arguments pou na periexei pointers sta tokens
      if ((*argvp = calloc(number_of_tokens + 1, sizeof(char *))) == NULL) {
        free(tok);
        number_of_tokens = -1;
      } 
      else {            /* eisagwgh pointers to tokens mesa ston pinaka */
        if (number_of_tokens > 0) {
        //antigrafei thn sumboloseira new_command sthn pinaka tok kai epistrefetai h timh tou tok
        strcpy(tok, new_command);
        /*xwrizei to tok se tokens pou to kathena oriotheteitai apo xarakthres pou exoun oristei apo to delim*/
        **argvp = strtok(tok, delim);
        for (i = 1; i < number_of_tokens + 1; i++)
        *((*argvp) + i) = strtok(NULL, delim);
        } 
      else {
        **argvp = NULL;
        free(tok);
      }
      }
    }   
  //epistrefei ton arithmo twn tokens	
  return number_of_tokens;
  }
