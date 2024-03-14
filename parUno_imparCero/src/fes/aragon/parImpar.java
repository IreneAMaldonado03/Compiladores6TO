package fes.aragon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class parImpar {
    public static void main(String[] args) {
    String archivo = "fuente.txt";
    try {
        FileReader fileReader = new FileReader(archivo);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String linea;
        int lineaActual = 0;
        while ((linea = bufferedReader.readLine()) != null) {
            lineaActual++;
            System.out.println("Linea " + lineaActual + ": " + linea);
            boolean paresDeUnoValido = true;
            boolean imparesDeCeroValido = true;
            for (char c : linea.toCharArray()) {
                if (c != '0' && c != '1') {
                    System.out.println("Caracter o caracteres invalidos detectados en la linea " + lineaActual + ". Solo se aceptan 0 y 1");
                    paresDeUnoValido = false;
                    imparesDeCeroValido = false;
                    break;
                }
                if (c == '1') {
                    paresDeUnoValido = false;
                }
                if (c == '0') {
                    imparesDeCeroValido = false;
                }
            }
            if (paresDeUnoValido) {
                System.out.println("Valida para pares de uno");
            } else {
                System.out.println("Invalida para pares de uno");
            }
            if (imparesDeCeroValido) {
                System.out.println("Valida para impares de cero");
            } else {
                System.out.println("Invalida para impares de cero");
            }
            System.out.println();
        }
        bufferedReader.close();
    } catch (IOException e) {
        System.out.println("Error al leer el archivo: " + e.getMessage());
    }
}
}