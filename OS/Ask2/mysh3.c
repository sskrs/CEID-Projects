#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>
#include <errno.h>
#include <string.h>
#include <fcntl.h> // file descriptors
#include <sys/wait.h>
#include <ctype.h>

int parse_command(char *command, char *delim, char ***argvp);
int execute(char* argvp[]) ;

int main() 
  {
  for(;;) {
    pid_t shell_pid = getpid();
    //prompt. 
    printf("$");
    //input tou user apo to stdin.
    char* input = NULL;
    size_t input_size = 0;
    getline(&input, &input_size, stdin);
    /*h metablhth locat entopizei to shmeio pou yparxei '\n' (new line) sto string tou input kai epistrefei arithmo shmeioy pou brethike */
    char* locat = strchr(input, (int)'\n');
    *locat = '\0';
    // Handle builtins
    char** builtin;
    parse_command(input, " ", &builtin);
    if (builtin[0] != NULL) {
      if (strcmp(builtin[0],"exit")==0){exit(0);} 
      else if (strcmp(builtin[0], "cd") == 0) {
        chdir(builtin[1]);
        continue; } }
    free(builtin);
    // dhmiourgia diergasias paidi gia na xeiristei to input tou xrhsth.
    pid_t pid = fork();
    int status;
    if (pid != 0) { // Pateras
    wait(NULL); } 
    else { // paidi diergasia
      char* command = NULL;
      int in, out;
      char delim;
      int i = strlen(input);
      for (; i > 0; i--) {
        if (input[i-1] == '|' ) {
          command = input + i;
          delim = input[i-1];
          input[i-1] = '\0';
          if (delim == '|') {
            int fd[2]; // 0 - read, 1 - write
            pipe(fd);
            pid_t newpid = fork();
            if (newpid == 0) {
              // aristero paidi kai to  stdout prepei na einai st patera to stdin.
              dup2(fd[1], STDOUT_FILENO); //STDOUT_FILENO = 1
              continue; }
            else { // Pateras, de3i meros
              dup2(fd[0], STDIN_FILENO); //STDIN_FILENO = 0
              close(fd[1]); //
              char** argv;
              parse_command(command, " ", &argv);
              execute(argv);}
          }
        }
      }
      char** argv = NULL;
      parse_command(input, " \n", &argv);
      execute(argv); // Paid kill ton eauto tou
    }
    //eleutherwsh mnhmhs
    free(input);
    }
  }
    

    int execute(char* argvp[]) {
    if ((execvp(argvp[0],argvp)) < 0) {
      return 0;}
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
      /* metrhma tou arithmou tokens sth metablhth new_commnad */ 
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
