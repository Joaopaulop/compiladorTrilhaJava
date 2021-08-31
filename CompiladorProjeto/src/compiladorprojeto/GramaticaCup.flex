package compiladorprojeto;
import java_cup.runtime.Symbol;
%%
%class GramaticaCup
%type java_cup.runtime.Symbol
%cup
%full
%line
%char

Letra=[a-zA-Z_]+
Digito=[0-9]+
espaco=[ ,\t,\r, \n]+

%{
    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline, yycolumn, value);
    }
    private Symbol symbol(int type) {
        return new Symbol(type, yyline, yycolumn, value);
    }
%}
%%

( public ) {return new Symbol(sym.PUBLICO, yychar, yyline, yytext());}
( static ) {return new Symbol(sym.ESTATICO, yychar, yyline, yytext());}
( void ) {return new Symbol(sym.VAZIO, yychar, yyline, yytext());}
( main ) {return new Symbol(sym.PRINCIPAL, yychar, yyline, yytext());}
( String ) {return new Symbol(sym.STRING, yychar, yyline, yytext());}
( int ) {return new Symbol(sym.INT, yychar, yyline, yytext());}
( if ) {return new Symbol(sym.SE, yychar, yyline, yytext());}
( else ) {return new Symbol(sym.SENAO, yychar, yyline, yytext());}
( for ) {return new Symbol(sym.PARA, yychar, yyline, yytext());}
( do ) {return new Symbol(sym.FACA, yychar, yyline, yytext());}
( while ) {return new Symbol(sym.ENQUANTO, yychar, yyline, yytext());}


( "=" ) {return new Symbol(sym.IGUAL, yychar, yyline, yytext());}
( "\"" ) {return new Symbol(sym.ASPAS, yychar, yyline, yytext());}
( "\n" ) {return new Symbol(sym.LINHA, yychar, yyline, yytext());}
( "(" ) {return new Symbol(sym.ABREPAR, yychar, yyline, yytext());}
( ")" ) {return new Symbol(sym.FECHAPAR, yychar, yyline, yytext());}
( "{" ) {return new Symbol(sym.ABRECHA, yychar, yyline, yytext());}
( "}" ) {return new Symbol(sym.FECHACHA, yychar, yyline, yytext());}
( ";" ) {return new Symbol(sym.PONTOV, yychar, yyline, yytext());}

{espaco} {/*Ignore*/}
"//".* {/*Ignore*/}

( "+" ) {return new Symbol(sym.SOMA, yychar, yyline, yytext());}
( "-" ) {return new Symbol(sym.SUB, yychar, yyline, yytext());}
( "*" ) {return new Symbol(sym.MULT, yychar, yyline, yytext());}
( "/" ) {return new Symbol(sym.DIV, yychar, yyline, yytext());}

( byte | char | long | float | double ) {return new Symbol(sym.DADO, yychar, yyline, yytext());}
( "&&" | "||" | "!" | "&" | "|" ) {return new Symbol(sym.OPLOG, yychar, yyline, yytext());}
( ">" | "<" | "==" | "!=" | ">=" | "<=" | "<<" | ">>" ) {return new Symbol(sym.OPREL, yychar, yyline, yytext());}
( "+=" | "-="  | "*=" | "/=" | "%=" ) {return new Symbol(sym.OPATRI, yychar, yyline, yytext());}
( "++" | "--" ) {return new Symbol(sym.OPINCR, yychar, yyline, yytext());}
( true | false ) {return new Symbol(sym.OPBOOL, yychar, yyline, yytext());}


{Letra}({Letra}|{Digito})* {return new Symbol(sym.ID, yychar, yyline, yytext());}
("(-"{Digito}+")")|{Digito}+ {return new Symbol(sym.NUM, yychar, yyline, yytext());}
 . {return new Symbol(sym.ERROR, yychar, yyline, yytext());}


