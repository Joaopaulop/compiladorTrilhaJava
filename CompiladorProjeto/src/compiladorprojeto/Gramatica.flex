package compiladorprojeto;
import static compiladorprojeto.Tokens.*;
%%
%class Gramatica
%type Tokens

Letra=[a-zA-Z_]+
Digito=[0-9]+
espaco=[ ,\t,\r]+

%{
    public String lexema;
%}
%%
( public ) {lexema=yytext(); return PUBLICO;}
( static ) {lexema=yytext(); return ESTATICO;}
( void ) {lexema=yytext(); return VAZIO;}
( main ) {lexema=yytext(); return PRINCIPAL;}
( String ) {lexema=yytext(); return STRING;}
( int ) {lexema=yytext(); return INT;}
( if ) {lexema=yytext(); return SE;}
( else ) {lexema=yytext(); return SENAO;}
( for ) {lexema=yytext(); return PARA;}
( do ) {lexema=yytext(); return FACA;}
( while ) {lexema=yytext(); return ENQUANTO;}

( "=" ) {lexema=yytext(); return IGUAL;}
( "\"" ) {lexema=yytext(); return ASPAS;}
( "\n" ) {lexema=yytext(); return LINHA;}
( "(" ) {lexema=yytext(); return ABREPAR;}
( ")" ) {lexema=yytext(); return FECHAPAR;}
( "{" ) {lexema=yytext(); return ABRECHA;}
( "}" ) {lexema=yytext(); return FECHACHA;}
( ";" ) {lexema=yytext(); return PONTOV;}

{espaco} {/*Ignore*/}
"//".* {/*Ignore*/}


( "+" ) {lexema=yytext(); return SOMA;}
( "-" ) {lexema=yytext(); return SUB;}
( "*" ) {lexema=yytext(); return MULT;}
( "/" ) {lexema=yytext(); return DIV;}


( byte | char | long | float | double ) {lexema=yytext(); return DADO;}
( "&&" | "||" | "!" | "&" | "|" ) {lexema=yytext(); return OPLOG;}
( ">" | "<" | "==" | "!=" | ">=" | "<=" | "<<" | ">>" ) {lexema=yytext(); return OPREL;}
( "+=" | "-="  | "*=" | "/=" | "%=" ) {lexema=yytext(); return OPATRI;}
( "++" | "--" ) {lexema=yytext(); return OPINCR;}
( true | false ) {lexema=yytext(); return OPBOOL;}

{Letra}({Letra}|{Digito})* {lexema=yytext(); return ID;}
("(-"{Digito}+")")|{Digito}+ {lexema=yytext(); return NUM;}
 . {lexema=yytext(); return ERROR;}

