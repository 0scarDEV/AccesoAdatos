package ejercicio1;

import java.io.BufferedReader;
import java.io.FileReader;

public class LeerTxt extends Archivo {
    private BufferedReader fichero;

    public LeerTxt(String entrada) {
        super(entrada);
    }

    public void abrirArchivo() {
        try {
            fichero = new BufferedReader(new FileReader(f));
        } catch (Exception e) {
            System.out.println("Error al importar el archivo");
        }

    }

    public int contarLineas() {
        return (int) fichero.lines().count();
    }

    public String leerLinea() {
        try {
            return fichero.readLine();
        } catch (Exception e) {
            return null;
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
