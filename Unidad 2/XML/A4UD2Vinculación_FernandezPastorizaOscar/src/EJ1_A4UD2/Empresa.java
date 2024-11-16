package EJ1_A4UD2;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;

@XmlRootElement(name = "empresa")
@XmlType(propOrder = {"cif", "nombre", "empleados"})
public class Empresa {
    private String cif;
    private String nombre;
    private ArrayList<Empleado> empleados;

    public Empresa() {
        empleados = new ArrayList<>();
    }

    @XmlAttribute()
    public String getCif() {return cif;}

    @XmlElement(name = "nombre")
    public String getNombre() {
        return nombre;
    }

    @XmlElementWrapper(name = "empleados")
    @XmlElement(name = "empleado")
    public ArrayList<Empleado> getEmpleados() {
        return empleados;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEmpleados(ArrayList<Empleado> empleados) {
        this.empleados = empleados;
    }
}
