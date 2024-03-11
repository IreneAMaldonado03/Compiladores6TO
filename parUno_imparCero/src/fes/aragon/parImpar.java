package fes.aragon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class parImpar {
    public static void main(String[] args) {
    String archivo = "fuente.txt";
    List<String> lineas = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
        String linea;
        while ((linea = br.readLine()) != null) {
            lineas.add(linea);
        }
    } catch (IOException e) {
        System.out.println("Error al leer el archivo: " + e.getMessage());
        System.exit(1);
    }

    for (int i = 0; i < lineas.size(); i++) {
        String linea = lineas.get(i);
        boolean paresValidos = true;
        boolean imparesValidos = true;

        for (int j = 0; j < linea.length(); j++) {
            char caracter = linea.charAt(j);

            if (caracter != '0' && caracter != '1') {
                System.out.println(linea + " invalida: Caracter invalido detectado, el programa solo acepta 0 y 1");
                paresValidos = false;
                imparesValidos = false;
                break;
            }

            if (caracter == '1') {
                int contadorPares = 0;
                int contadorImpares = 0;

                for (int k = j; k < linea.length(); k++) {
                    if (linea.charAt(k) == '1') {
                        contadorPares++;
                    } else {
                        contadorImpares++;
                    }
                }

                if (contadorPares % 2 != 0) {
                    paresValidos = false;
                }

                if (contadorImpares % 2 == 0) {
                    imparesValidos = false;
                }
            }
        }

        if (paresValidos && imparesValidos) {
            System.out.println(linea + " valida para pares de uno, valida para impares de cero");
        } else if (!paresValidos && imparesValidos) {
            System.out.println(linea + " invalida para pares de uno, valida para impares de cero");
        } else if (paresValidos && !imparesValidos) {
            System.out.println(linea + " valida para pares de uno, invalida para impares de cero");
        } else {
            System.out.println(linea + " invalida para pares de uno, invalida para impares de cero");
        }
    }
}
}
