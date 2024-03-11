package fes.aragon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class parImpar {
    public static void main(String[] args) {
        String archivo = "fuente.txt";
        validarCadenas(archivo);
    }

    public static void validarCadenas(String archivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                validarCadena(linea);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    public static void validarCadena(String cadena) {
        int indice = 0;
        int estadoActual = 0;
        int contadorUnos = 0;
        int contadorCeros = 0;

        while (indice <= cadena.length() - 1) {
            char c = cadena.charAt(indice);

            if (c != '0' && c != '1') {
                System.out.println("Caracter o caracteres invalidos detectados, el programa solo acepta 0 y 1");
                return;
            }

            switch (estadoActual) {
                case 0:
                    if (c == '0') {
                        estadoActual = 1;
                        contadorCeros++;
                    } else if (c == '1') {
                        estadoActual = 2;
                        contadorUnos++;
                    }
                    break;
                case 1:
                    if (c == '0') {
                        estadoActual = 1;
                        contadorCeros++;
                    } else if (c == '1') {
                        estadoActual = 2;
                        contadorUnos++;
                    }
                    break;
                case 2:
                    if (c == '0') {
                        estadoActual = 1;
                        contadorCeros++;
                    } else if (c == '1') {
                        estadoActual = 3;
                        contadorUnos++;
                    }
                    break;
                case 3:
                    if (c == '0') {
                        estadoActual = 1;
                        contadorCeros++;
                    } else if (c == '1') {
                        estadoActual = 3;
                        contadorUnos++;
                    }
                    break;
            }
            indice++;
        }

        if (contadorUnos % 2 == 0) {
            System.out.println(cadena + " valida para pares de uno");
        } else {
            System.out.println(cadena + " invalida para pares de uno");
        }

        if (contadorCeros % 2 != 0) {
            System.out.println(cadena + " valida para impares de cero");
        } else {
            System.out.println(cadena + " invalida para impares de cero");
        }
    }
}

