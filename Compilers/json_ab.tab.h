/* A Bison parser, made by GNU Bison 3.0.2.  */

/* Bison interface for Yacc-like parsers in C

   Copyright (C) 1984, 1989-1990, 2000-2013 Free Software Foundation, Inc.

   This program is free software: you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation, either version 3 of the License, or
   (at your option) any later version.

   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.

   You should have received a copy of the GNU General Public License
   along with this program.  If not, see <http://www.gnu.org/licenses/>.  */

/* As a special exception, you may create a larger work that contains
   part or all of the Bison parser skeleton and distribute that work
   under terms of your choice, so long as that work isn't itself a
   parser generator using the skeleton or a modified version thereof
   as a parser skeleton.  Alternatively, if you modify or redistribute
   the parser skeleton itself, you may (at your option) remove this
   special exception, which will cause the skeleton and the resulting
   Bison output files to be licensed under the GNU General Public
   License without this special exception.

   This special exception was added by the Free Software Foundation in
   version 2.2 of Bison.  */

#ifndef YY_YY_JSON_AB_TAB_H_INCLUDED
# define YY_YY_JSON_AB_TAB_H_INCLUDED
/* Debug traces.  */
#ifndef YYDEBUG
# define YYDEBUG 0
#endif
#if YYDEBUG
extern int yydebug;
#endif

/* Token type.  */
#ifndef YYTOKENTYPE
# define YYTOKENTYPE
  enum yytokentype
  {
    COMMA = 258,
    EQ = 259,
    CREATEDAT = 260,
    DAYNAME = 261,
    MONTHNAME = 262,
    DAYNUM = 263,
    TIMESTAMP = 264,
    ZONE = 265,
    YEAR = 266,
    USERID = 267,
    INTEGER = 268,
    SINGLEQUOTE = 269,
    USEROBJECT = 270,
    ID_STR = 271,
    USERNAME = 272,
    UNIQUENAME = 273,
    TEXT = 274,
    RETWEETED = 275,
    NAME = 276,
    BOOL = 277,
    SCREENNAME = 278,
    LOCATION = 279,
    URL = 280,
    DESC = 281,
    DESCRIPTIONTYPE = 282,
    USERNAMETYPE = 283,
    NUMBER = 284,
    TWEET = 285,
    RTTWEET = 286,
    TRUNC = 287,
    TRUE = 288,
    FALSE = 289,
    EXTENDED = 290,
    FULLTEXT = 291,
    CL_OBJECT = 292,
    CLARRAY = 293,
    OP_OBJECT = 294,
    OPARRAY = 295
  };
#endif

/* Value type.  */
#if ! defined YYSTYPE && ! defined YYSTYPE_IS_DECLARED
typedef int YYSTYPE;
# define YYSTYPE_IS_TRIVIAL 1
# define YYSTYPE_IS_DECLARED 1
#endif


extern YYSTYPE yylval;

int yyparse (void);

#endif /* !YY_YY_JSON_AB_TAB_H_INCLUDED  */
