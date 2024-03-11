package fes.aragon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class parImpar {
    private static final int ESTADO_INICIAL = 0;
    private static final int ESTADO_PAR = 1;
    private static final int ESTADO_IMPAR = 2;
    private static final int ESTADO_ERROR = 3;

    public static void main(String[] args) {
        String archivo = "fuente.txt";
        try {
            FileReader fileReader = new FileReader(archivo);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String linea;
            while ((linea = bufferedReader.readLine()) != null) {
                int estado = ESTADO_INICIAL;
                int contadorPares = 0;
                int contadorImpares = 0;
                for (char caracter : linea.toCharArray()) {
                    switch (estado) {
                        case ESTADO_INICIAL:
                            if (caracter == '0') {
                                estado = ESTADO_IMPAR;
                                contadorImpares++;
                            } else if (caracter == '1') {
                                estado = ESTADO_PAR;
                                contadorPares++;
                            } else {
                                estado = ESTADO_ERROR;
                            }
                            break;
                        case ESTADO_PAR:
                            if (caracter == '0') {
                                estado = ESTADO_IMPAR;
                                contadorImpares++;
                            } else if (caracter == '1') {
                                estado = ESTADO_PAR;
                                contadorPares++;
                            } else {
                                estado = ESTADO_ERROR;
                            }
                            break;
                        case ESTADO_IMPAR:
                            if (caracter == '0') {
                                estado = ESTADO_IMPAR;
                                contadorImpares++;
                            } else if (caracter == '1') {
                                estado = ESTADO_PAR;
                                contadorPares++;
                            } else {
                                estado = ESTADO_ERROR;
                            }
                            break;
                        case ESTADO_ERROR:
                            System.out.println("Caracter o caracteres invalidos detectados en la linea " + linea);
                            break;
                    }
                }
                if (estado == ESTADO_INICIAL || estado == ESTADO_PAR) {
                    System.out.println(linea + " valida para pares de uno, valida para impares de cero");
                } else if (estado == ESTADO_IMPAR) {
                    System.out.println(linea + " invalida para pares de uno, invalida para impares de cero");
                } else if (estado == ESTADO_ERROR) {
                    System.out.println("Caracter o caracteres invalidos detectados en la linea " + linea);
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("Error al leer el archivo " + archivo);
        }
    }
}
