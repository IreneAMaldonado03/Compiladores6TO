import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ParUno {

    public static void main(String[] args) {
        String archivo = "fuente.txt";
        try {
            FileReader fileReader = new FileReader(archivo);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String linea;
            int lineaActual = 0;
            while ((linea = bufferedReader.readLine()) != null) {
                lineaActual++;
                System.out.println("Linea " + lineaActual + ": " + evaluarLinea(linea));
            }
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    private static String evaluarLinea(String linea) {
        int estado = 0;
        StringBuilder resultado = new StringBuilder();
        for (char caracter : linea.toCharArray()) {
            switch (estado) {
                case 0:
                    if (caracter == '0') {
                        estado = 0;
                    } else if (caracter == '1') {
                        estado = 1;
                    } else {
                        return "Caracter o caracteres invalidos detectados en la linea " + linea;
                    }
                    break;
                case 1:
                    if (caracter == '0') {
                        estado = 1;
                    } else if (caracter == '1') {
                        estado = 0;
                    } else {
                        return "Caracter o caracteres invalidos detectados en la linea " + linea;
                    }break;
            }
        }
        if (estado == 0) {
            resultado.append("valida para pares de uno");
        } else {
            resultado.append("invalida para pares de uno");
        }
        if (contarUnos(linea) % 2 == 1) {
            resultado.append(", valida para impares de cero");
        } else {
            resultado.append(", invalida para impares de cero");
        }
        return resultado.toString();
    }

    private static int contarUnos(String linea) {
        int contador = 0;
        for (char caracter : linea.toCharArray()) {
            if (caracter == '1') {
                contador++;
            }
        }
        return contador;
    }
}