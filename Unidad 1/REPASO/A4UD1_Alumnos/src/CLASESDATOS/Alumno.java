/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CLASESDATOS;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Alumno implements Serializable {
    static SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

    private int numero;
    private Nombre nombre;
    Date fechaNac;
    ArrayList<String> telefono;
    boolean borrado;

    public Alumno(Nombre nombre, Date fechaNac, ArrayList<String> telefono, boolean borrado) {
        this.nombre = nombre;
        this.fechaNac = fechaNac;
        this.telefono = telefono;
        this.borrado = borrado;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Nombre getNombre() {
        return nombre;
    }

    public void setNombre(Nombre nombre) {
        this.nombre = nombre;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public ArrayList<String> getTelefono() {
        return telefono;
    }

    public void setTelefono(ArrayList<String> telefono) {
        this.telefono = telefono;
    }

    public boolean isBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }

    @Override public String toString() {
        return "Alumno número " + numero +
                "\nNombre: " + nombre +
                "\nFecha nacimiento: " + formato.format(fechaNac) +
                "\nTelefono:" + obtenerTelefonos();
    }

    private String obtenerTelefonos() {
        StringBuilder str = new StringBuilder();
        for (String s : telefono) {
            str.append(" ").append(s);
        }
        return str.toString();
    }
}
