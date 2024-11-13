// Xabier Pastoriza Rodriguesz 53860349f
package ejercicio1;

import java.io.Serializable;
import java.util.Date;
import java.time.LocalDate;

public class Datos implements Serializable {
    static final long serialVersionUID = 120L;
    private String dni;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private Date fechanacimiento;
    private String numHistoria;
    private Date fechaAlta;

    public Datos(String dni, String nombre, String apellido1, String apellido2, Date fechanacimiento) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.fechanacimiento = fechanacimiento;
        this.fechaAlta = new Date();
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getDni() {
        return dni;
    }

    public void setNumHistoria(String numHistoria) {
        this.numHistoria = numHistoria;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public Date getFechanacimiento() {
        return fechanacimiento;
    }

    public String getNumHistoria() {
        return numHistoria;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

}
