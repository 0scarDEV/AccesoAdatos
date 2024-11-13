// Xabier Pastoriza Rodriguesz 53860349f
package ejercicio1;

import java.io.File;

public abstract class Archivo {
    File f;

    public Archivo(String f) {
        this.f = new File(f);
    }

    abstract void abrirArchivo();

    abstract void cerrarArchivo();

    public int longitudArchivo() {
        return (int) f.length();
    }

}
