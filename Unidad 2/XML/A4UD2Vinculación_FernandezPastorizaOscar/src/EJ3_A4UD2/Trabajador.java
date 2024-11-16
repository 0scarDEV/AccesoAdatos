package EJ3_A4UD2;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;

@XmlType(propOrder = {"compania", "sueldo", "cargo"})
public class Trabajador extends Persona {
    private String compania;
    private double sueldo;
    private String cargo;

    public Trabajador() {
        super();
    }

    public Trabajador(String nombre, int edad, Direccion direccion, ArrayList<String> telefonos, String compania, double sueldo, String cargo) {
        super(nombre, edad, direccion, telefonos);
        this.compania = compania;
        this.sueldo = sueldo;
        this.cargo = cargo;
    }

    @XmlElement(name = "Empresa")
    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    @XmlElement(name = "Salario")
    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    @XmlElement(name = "Puesto")
    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}
