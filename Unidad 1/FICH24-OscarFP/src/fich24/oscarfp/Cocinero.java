package fich24.oscarfp;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

/**
 * @author ofernpast
 * Óscar Fernández Pastoriza - 53862191D
 */

public class Cocinero implements Serializable {
    static final long serialVersionUID = 100L;
    private int codigo; // Clave principal, empieza en uno
    private String apodo; // Debe ser único
    private String nombreCompleto;
    private LocalDate fechaNacimiento;
    private ArrayList<String> recetas;

    public Cocinero(String apodo, String nombreCompleto, LocalDate fechaNacimiento, ArrayList<String> recetas) {
        this.apodo = apodo;
        this.nombreCompleto = nombreCompleto;
        this.fechaNacimiento = fechaNacimiento;
        this.recetas = recetas;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getApodo() {
        return apodo;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public ArrayList<String> getRecetas() {
        return recetas;
    }

    @Override public String toString() {
        String string = "------------------------------";
        string += "\nCocinero código " + codigo;
        string += "\n------------------------------";
        string += "\nNombre: " + nombreCompleto + "\tApodo: " + apodo;
        string += "\nFecha nacimiento: " + fechaNacimiento.getDayOfMonth() + "/" + fechaNacimiento.getMonthValue() + "/" + fechaNacimiento.getYear();
        string += "\nRecetas: " + recetas.toString();
        return string;
    }

    public int calcularEdad() {
        LocalDate fechaActual = LocalDate.now();
        return Period.between(fechaNacimiento, fechaActual).getYears();
    }
}
