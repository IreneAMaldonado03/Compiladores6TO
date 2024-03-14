package fes.aragon.compilador;
import static fes.aragon.compilador.Tokens.*;
%%
%class Lexico
%type Tokens
WHITE=[\t\r\n]
%{
    public String lexema;
%}
%%
{WHITE} {/* NO HACER NADA */}
" " { }
"or" {return OR;}
"and" {return AND;}
"not" {return NOT;}
")" {return CIERREPARENTESIS;}
"(" {return ABREPARENTESIS;}
"true" {return TRUE;}
"false" {return FALSE;}
";" {return PUNTOYCOMA;}
. {return ERROR;}