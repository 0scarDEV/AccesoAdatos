package E33_P3;

import java.io.Serial;
import java.io.Serializable;

public class Participante implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String nombre;
    private int dorsal;
    private double tiempo;

    public Participante(String nombre, int dorsal, double tiempo) {
        this.nombre = nombre;
        this.dorsal = dorsal;
        this.tiempo = tiempo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    public double getTiempo() {
        return tiempo;
    }

    public void setTiempo(double tiempo) {
        this.tiempo = tiempo;
    }

    @Override
    public String toString() {
        return nombre + '(' + dorsal + ") tiempo=" + tiempo;
    }
}
