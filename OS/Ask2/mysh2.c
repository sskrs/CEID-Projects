#include <string.h>
#include <fcntl.h>
#include <sys/wait.h>
#include <ctype.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>
#include <errno.h>

//dhlwsh sunarthsewn kai statherwn 
int process(char **argvp);
int cd(char **argvp);
int shell_exit(char **argvp);
int execute(char **argvp);
char *input_user(void);
char **parse_command(char *command);
#define buffersize  1024
#define TOK_inputSIZE 64 
#define DELIM  " \n\r\t\a"


int  main()
{ 
 char *command;
 char **argvp;
 //mpainoume ousiastika sto shell
 for(;;){
   //promt
   printf("$");
   //diabasma grammhs xrhsth
   command = input_user();
   //analush grammhs
   argvp = parse_command(command);
   //ektelesh grammhs
    execute(argvp);
   if ((strcmp(argvp[0], "exit"))== 0) {exit(0);}
   } 
   return 1;
 }
 
 //dhlwsh cd,exit,kai klhsh antistoixwn sunarthsewn
 char *builtin[] = {"cd", "exit"};
 int (*synarthseis[]) (char **) = { &cd, &shell_exit };
 int numberbuiltins() {
  return sizeof(builtin) / sizeof(char *);}
 int cd(char **argvp)
 {
  if (argvp[1] == 0) {
  } else {
    if (chdir(argvp[1]) != 0) {
      perror("");
    }
  }
  return 1;
 }
 int shell_exit(char **argvp){return 0;}
//telos dhlwsewn

char *input_user(void)
{
  int input_size = buffersize;
  int i = 0;
  char *buffer_input = malloc(sizeof(char) * input_size);
  int line;
  if (!buffer_input) { exit(1); }
  for(;;) {
    //elegxos me thn getchar gia kathe xarakthra tou input
    line = getchar();
    if (line == '\n' || line == EOF) {
      buffer_input[i] = '\0';
      return buffer_input;
    } else {
      buffer_input[i] = line;
    }
    i++;
    //kanoume epanakoathorismo mnhmhns an kseperasame to megethos tou input
    if (i >= input_size) {
      input_size += buffersize;
      buffer_input = realloc(buffer_input, input_size);
      if (!buffer_input) { exit(1); }
    }
  }
}
//analush entolhs pou eisagetai apo tn xrhsth
char **parse_command(char *input)
{
  int input_size = TOK_inputSIZE, i = 0;
  char **tokenspin = malloc(input_size * sizeof(char*));
  char *tok;
  if (!tokenspin){exit(1);}
  tok = strtok(input,DELIM);
  while (tok != NULL) {
    tokenspin[i] = tok;
    i++;
    if (i >= input_size) {
      input_size += TOK_inputSIZE;
      tokenspin = realloc(tokenspin, input_size * sizeof(char*));
      if (!tokenspin) {exit(1);}
    }
   tok = strtok(NULL, DELIM);
  }
  tokenspin[i] = NULL;
  return tokenspin;
}

//synarthsh pou einai ypeuthinh gia diergasia_paidi 
int process(char **argvp){
{
int status,pid;
 pid = fork();
    if (pid == 0) {
      // diergasia-paidi
      if (execvp(argvp[0], argvp) == -1) {
       perror("fork");}
      exit(1);}
    else if (pid < 0) {
    perror("");
   }
   else if(wait(&status)!= pid){
    perror(""); exit(1);}
}
return 1;}


// kanei ektelesh tou programmatos h ths antistoixhs buildin entolhs: cd or exit
int execute(char **argvp)
{
  int i;
  if (argvp[0] == NULL) {
    // an dwthei keno dld h argvp einai O
    return 1; }
  //epanalhpsh gia tis buildin entoles
  for (i=0;i < numberbuiltins();i++){
    if (strcmp(argvp[0], builtin[i]) == 0) {
      return (*synarthseis[i])(argvp); }
 }
  return process(argvp);
}
