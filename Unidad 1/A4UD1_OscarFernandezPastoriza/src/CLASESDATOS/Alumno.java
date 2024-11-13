package CLASESDATOS;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class Alumno implements Serializable {
    private int numero;
    private Nombre nombre;
    Date fechaNac;
    private ArrayList <String> telefono;
    boolean borrado;

    public Alumno( Nombre nombre, Date fechaNac, ArrayList<String> telefono, boolean borrado) {
        
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

    public String getFechaNacimiento() {
        Date date = getFechaNac();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return localDate.getDayOfMonth() + "/" + localDate.getMonthValue() + "/" + localDate.getYear();
    }

    public LocalDate getFechaNacimientoLocalDate() {
        return getFechaNac().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public int getEdad() {
        return Period.between(getFechaNacimientoLocalDate(), LocalDate.now()).getYears();
    }

    @Override public String toString() {

        StringBuilder cadena = new StringBuilder("Alumno numero: " + numero +
                "\nNombre: " + nombre.toString() +
                "\nFecha de Nacimiento: " + getFechaNacimiento());

        if (telefono.size() == 1) {
            cadena.append("\nTeléfono: ");
        } else {
            cadena.append("\nTeléfonos: ");
        }

        for (String t : telefono) {
            cadena.append(t).append(" ");
        }

        return cadena.toString();
    }

    public long LongitudRegistro() {
        //Date en Internet dice que ocupa 12 bytes? . Vamos a suponer esto
        
        return (4+nombre.nombre.length() +nombre.apellido1.length()+nombre.apellido2.length()+12 + (telefono.toString()).length()+ 1);

    }
}
