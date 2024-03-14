package fes.aragon.compilador;
import javax.management.loading.PrivateClassLoader;
import java.io.*;
public class Principal {
    private Lexico lexico;
    private Tokens token;
    public static void main(String[] args) {
        Principal ap= new Principal();
        try{
            Reader rd=new BufferedReader(new FileReader("fuente.txt"));
            ap.lexico= new Lexico(rd);
            ap.token=ap.lexico.yylex();
            ap.S();
        }
        catch (FileNotFoundException e ){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    private void S() throws IOException{
        System.out.println("S");
        E();
        token=lexico.yylex();
        if (token != Tokens.PUNTOYCOMA){
            System.out.println("Error, se esperaba un ;");
        }
        return;
    }

    private void E() throws IOException{
        System.out.println("E");
        T();
        Ep();
        return;
    }

    private void Ep() throws IOException{
        System.out.println("Ep");
        if (token == Tokens.OR){
            T();
            Ep();
        } else {
            System.out.println("Error, se esperaba un or");
        }
        return;
    }

    private void T() throws IOException {
        System.out.println("T");
        F();
        Tp();
        return;
    }

    private void Tp() throws IOException{
        System.out.println("Ep");
        if (token == Tokens.AND){
            F();
            Tp();
        } else {
            System.out.println("Error, se esperaba un and");
        }
        return;
    }

    private void F() throws IOException{
        System.out.println("F");

        if (token == Tokens.NOT){
            token = lexico.yylex();
            E();
        } else if (token == Tokens.TRUE || token == Tokens.FALSE){
            token = lexico.yylex();
            return;
        } else if (token == Tokens.ABREPARENTESIS) {
            token = lexico.yylex();
            E();
            if (token == Tokens.CIERREPARENTESIS){
                token = lexico.yylex();
                return;
            }
        } else {
            System.out.println("Error, caracter no esperado");
        }
    }
}
