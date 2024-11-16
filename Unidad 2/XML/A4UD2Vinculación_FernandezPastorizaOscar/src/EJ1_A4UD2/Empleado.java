package EJ1_A4UD2;

import javax.xml.bind.annotation.*;

@XmlType(propOrder = {"dni", "nombre", "edad"})
public class Empleado {
    private String dni;
    private String nombre;
    private int edad;

    public Empleado() {
    }

    @XmlElement
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    @XmlElement
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlElement
    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}
