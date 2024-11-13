package E32_P2;

import java.io.*;

public class E32_P2 {
    public static void main(String[] args) {
        String rutaLogs = "src/E32_P2/logs.txt";
        String ficheroLeer = "src/E32_P2/ficheroEntrada.txt";
        try (BufferedWriter salida = new BufferedWriter(new FileWriter(rutaLogs));
             BufferedReader lector = new BufferedReader(new FileReader(ficheroLeer))) {
            while(lector.ready()) {
                String linea = lector.readLine();
                String[] contenido = linea.split("/");
                if (contenido.length != 3) {
                    salida.write(linea + " -----> no tiene el formato CURSO/NUMERO/ALUMNO\n");
                } else {
                    String curso = contenido[0];
                    String num = contenido[1];
                    String nombre = contenido[2];
                    File directorio = new File("src/E32_P2/ALUMNOS/" + curso + "/" + num + "-" + nombre);

                    directorio.mkdirs();
                    salida.write(num + "-" + nombre + " -----> se creo correctamente el directorio\n");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
