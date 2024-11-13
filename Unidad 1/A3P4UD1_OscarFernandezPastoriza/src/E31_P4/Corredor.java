package E31_P4;

import java.io.Serializable;

public class Corredor implements Serializable {
    String nombre;
    int dorsal;
    double tiempo;
    boolean borrado;

    public Corredor(String nombre, int dorsal, double tiempo, boolean borrado) {
        this.nombre = nombre;
        this.dorsal = dorsal;
        this.tiempo = tiempo;
        this.borrado = borrado;
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

    public boolean isBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }

    @Override public String toString() {
        return nombre + " (#" + dorsal + ") tardó " + tiempo + " segundos.";
    }
}
