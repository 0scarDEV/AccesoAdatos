package EJ3_A4UD2;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;

@XmlRootElement(name = "Trabajador")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"compania", "cargo", "sueldo"})
public class Trabajador extends Persona {
    @XmlElement(name = "Empresa")
    private String compania;
    @XmlElement(name = "Salario")
    private double sueldo;
    @XmlElement(name = "Puesto")
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

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}
