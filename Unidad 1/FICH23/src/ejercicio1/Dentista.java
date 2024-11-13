package ejercicio1;

import java.util.ArrayList;

public class Dentista {
    private int numero;
    private String nombre;
    private String numColegiado;
    private ArrayList<Integer> pacientes;
    private boolean baja;

    public Dentista() {

    }

    public Dentista(int numero, String nombre, String numColegiado, ArrayList<Integer> pacientes, boolean baja) {
        this.numero = numero;
        this.nombre = nombre;
        this.numColegiado = numColegiado;
        this.pacientes = pacientes;
        this.baja = baja;
    }

    public int getNumero() {
        return numero;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNumColegiado() {
        return numColegiado;
    }

    public ArrayList<Integer> getPacientes() {
        return pacientes;
    }

    public boolean isBaja() {
        return baja;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNumColegiado(String numColegiado) {
        this.numColegiado = numColegiado;
    }

    public void setPacientes(ArrayList<Integer> pacientes) {
        this.pacientes = pacientes;
    }

    public void setBaja(boolean baja) {
        this.baja = baja;
    }

    @Override
    public String toString() {

        return "Dentista numero: " + numero + "\n---------------------------\nNombre: " + nombre
                + " Numero de Colegiado:" + numColegiado;
    }

}
