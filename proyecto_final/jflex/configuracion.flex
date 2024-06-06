package init;
import utilidades.Tokens;
%%
%class Lexico
%type Tokens
WHITE=[ \t\r\n\f]+
D = [0-9]+
FINCADENA=";"
%{
    public String lexema;
%}
%%
{WHITE} {/* NO HACER NADA */}

"coloca"                { System.out.println("Reconocio coloca"); return Tokens.COLOCA; }
"derecha"               { System.out.println("Reconocio derecha"); return Tokens.DERECHA; }
{D}+ 			{ lexema=yytext(); return Tokens.NUMERO;}
"izquierda"             { System.out.println("Reconocio izquierda"); return Tokens.IZQUIERDA; }
"arriba"                { System.out.println("Reconocio arriba"); return Tokens.ARRIBA; }
"abajo"                 { System.out.println("Reconocio abajo"); return Tokens.ABAJO; }
"mover"                 { System.out.println("Reconocio mover"); return Tokens.MOVER; }
";"                     { System.out.println("Reconocio ;"); return Tokens.PUNTOYCOMA; }
.                       { System.out.println("Error Caracter Invalido"); }