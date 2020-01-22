#include <string.h>
#include <fcntl.h>
#include <sys/wait.h>
#include <ctype.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>
#include <errno.h>

int parse_command(char *command, char *delim, char ***argvp);
int execute(char* args[]) ;

int main() {
  for(;;) { 
  pid_t shell_pid = getpid();
  //prompt. 
  printf("$");
  //input tou user
  char* input = NULL;
  size_t input_size = 0;
  //sto input eisagetai auto p grafei o xrhsths (stdin) 
  getline(&input, &input_size, stdin);
  /*h metablhth locat entopizei to shmeio pou yparxei '\n' (new line) sto string tou input kai epistrefei arithmo shmeioy pou brethike */
   char* locat = strchr(input, (int)'\n');
  *locat = '\0';
  // Xeirismos builtin entolwn: exit
  char** builtin;
  parse_command(input, " ", &builtin); //klhsh synarthshs parse_command
  /*elegxos ena sth prwth thesh tou pinaka argument (builtin) uparxei to exit sthn prwth thesh!*/
  if (builtin[0] != NULL) { 
     if (strcmp(builtin[0], "exit") == 0) { exit(0); } }
  free(builtin);
  // dhhmiougia paidiou gia na xeiristei t input tou xrhsth
  pid_t pid = fork();
  int status;
  if (pid != 0) { // Gonios-pateras
  wait(NULL);} 
  else if (pid == 0 ) {  // Child process	
     char** argv = NULL;
     parse_command(input, " \n", &argv);
     execute(argv); } // Child should kill itself			
  }
}
  //klhsh sunarthshs execute h opoia pairnei san orisma an array of arguments
  int execute(char* args[]) {
    if ((execvp(args[0],args)) < 0) {
      return 0;
    }
    return 1;
  }

/* dhmiourgia  argument array tou (*arvp) gia ta tokens sto s ta opoia diaxwrizontai apo delimiters
 Return -1 on error h ton arithmo twn tokens .
 */
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
        strcpy(tok, new_command);
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
