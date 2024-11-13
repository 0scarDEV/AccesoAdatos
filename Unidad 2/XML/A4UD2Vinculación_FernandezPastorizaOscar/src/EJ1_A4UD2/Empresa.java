package EJ1_A4UD2;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "empresa")
@XmlType(propOrder = {"cif", "nombre", "empleados"})
public class Empresa {
    @XmlAttribute()
    private String cif;
    @XmlElement(name = "nombre")
    private String nombre;
    @XmlElementWrapper(name = "empleados")
    @XmlElement(name = "empleado")
    private ArrayList<Empleado> empleados;

    public Empresa() {
        empleados = new ArrayList<>();
    }

    public String getCif() {return cif;}

    public String getNombre() {
        return nombre;
    }

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
