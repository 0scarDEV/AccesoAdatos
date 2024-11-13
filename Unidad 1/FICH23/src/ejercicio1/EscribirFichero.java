// Xabier Pastoriza Rodriguesz 53860349f
package ejercicio1;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class EscribirFichero extends Archivo {
    private ObjectOutputStream ois;

    public EscribirFichero(String f) {
        super(f);
    }

    @Override
    void abrirArchivo() {
        if (f.exists()) {
            try {
                ois = new MiObjectOutputStream(new FileOutputStream(f, true));
            } catch (IOException e) {
                System.out.println("Error al inicializar el archivo.");
            }
        } else {
            try {
                System.out.println("Se ha creado el fichero " + f.getName());
                ois = new ObjectOutputStream(new FileOutputStream(f));
            } catch (IOException e) {
                System.out.println("Error al inicializar el archivo.");
            }
        }
    }

    @Override
    void cerrarArchivo() {
        try {
            ois.close();
        } catch (IOException e) {
            System.out.println("Error al cerrar el archivo.");
        }
    }

    public void add(Paciente p) {
        try {
            ois.writeObject(p);
        } catch (IOException e) {
            System.out.println("Error al insertar el paciente.");
        }

    }

}
