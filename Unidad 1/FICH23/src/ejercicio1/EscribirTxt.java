package ejercicio1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class EscribirTxt extends Archivo {
    private BufferedWriter fichero;

    public EscribirTxt(String entrada) {
        super(entrada);
    }

    public void abrirArchivo() {
        boolean ficheroExiste = true;

        try {
            fichero = new BufferedWriter(new FileWriter(f, true));

        } catch (Exception e) {
            System.out.println("Error al importar el archivo");
        }

        if (!f.exists()) {
            try {
                fichero.write(
                        "DNI           NUMPACIENTE           NOMBRE           APELLIDO1           APELLIDO2           NUMHISTORIA           FECHA ALTA");
                fichero.newLine();
            } catch (Exception e) {
                System.out.println("Error al escribir en el log");
            }
        }
    }

    public void escribir(String text) {
        try {
            fichero.write(text);
            fichero.newLine();
        } catch (Exception e) {
            System.out.println("Error al escribir en el archivo");
        }
    }

    public void cerrarArchivo() {
        try {
            fichero.close();
        } catch (Exception e) {
            System.out.println("Error al cerrar el archivo");
        }

    }

}
