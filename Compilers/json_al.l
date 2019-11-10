%{
	#include "json_ab.tab.h"  
	#include <stdio.h>
	#include <stdlib.h>
	#include <string.h>
	char *temp;
	char *stemp;
	    int lineNumber = 1;

%}
%option noyywrap
%option yylineno
month_name ("Jan"|"Feb"|"Mar"|"Apr"|"May"|"Jun"|"Jul"|"Aug"|"Sep"|"Oct"|"Nov"|"Dec")
day_name (Sun|Mon|Tue|Wen|Thu|Fri|Sat)
digit [0-9]
esc[\"]
other [\/.-:_@#^$<>!,?&*%+=]
name [a-z,A-Z,0-9\s]+
string {name}({name}|{digit}|{other}|{esc})+
text ({name}|{digit}|{other}|{esc}){1,140}
day_num [01-31]
boolean "true"|"false"
timestamp ([2][0-3]|[0-1][0-9]|[1-9]):[0-5][0-9]:([0-5][0-9]|[6][0])
descriptiontype [^\r\n]+((\r|\n|\r\n)[^\r\n]+)*
digit1to9 [1-9]
integer {digit1to9}{digit}*{digit}+
singlequote ["]
nline [\n]
plus ([+]|"%2B")
mark ([!]|"%21")
comma ([,]|"%2C")
amp ([&]|"%26")
ws ([ \s\t]|"%20")
%%
{timestamp}    	{printf("%s", yytext); return (TIMESTAMP);}
"created_at"   	{printf("%s", yytext); return (CREATEDAT);}
"truncated"   	{printf("%s", yytext); return (TRUNC);}
"retweeted_status" {printf("%s", yytext); return (RETWEETED);}
"tweet"   		{printf("%s", yytext); return (TWEET);}
"text"   		{printf("%s", yytext); return (TEXT);}
"extended_tweet" {printf("%s", yytext); return (EXTENDED);}
"full_text"   	{printf("%s", yytext); return (FULLTEXT);}
"name"   		{printf("%s", yytext); return (USERNAME);}
"screen_name"   {printf("%s", yytext); return (SCREENNAME);}
"location"    	{printf("%s", yytext); return (LOCATION);}
"url"		 	{printf("%s", yytext); return (URL);}
"description" 	{printf("%s", yytext); return (DESC);}
{day_num}    	{printf("%s", yytext); return (DAYNUM);}
"id"    		{printf("%s", yytext); return (USERID);}
"true" 			{printf("%s", yytext); return (TRUE);}
"false" 		{printf("%s", yytext); return (FALSE);}
"user"    	 	{printf("%s", yytext); return (USEROBJECT);}
"id_str" 		{printf("%s", yytext); return (ID_STR);}
{month_name}    {printf("%s", yytext); return (MONTHNAME);}
{digit}{4}  	{printf("%s", yytext); return (YEAR);}
{day_name}    	{printf("%s", yytext); return (DAYNAME);}
{integer}    	{printf("%s", yytext); return (INTEGER);}
{singlequote} 	{printf("%s", yytext); return (SINGLEQUOTE);}
"+"0000 		{printf("%s", yytext); return (ZONE);}
{comma} 			{printf("%s", yytext); return (COMMA);}
":" 			{printf("%s", yytext); return (EQ);}
"RT@"{text}		{printf("%s", yytext); return (RTTWEET);}
{nline}	        {printf("\n"); lineNumber++;}
"{"		        {printf("%s", yytext); return (OP_OBJECT);}
"}"		        {printf("%s", yytext); return (CL_OBJECT);}
"["		        {printf("%s", yytext); return (OPARRAY);}
"]"		        {printf("%s", yytext); return (CLARRAY);}
