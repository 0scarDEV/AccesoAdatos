package EJ3_A4UD2;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;

@XmlRootElement(name = "Estudiante")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Estudiante")
public class Estudiante extends Persona {
    @XmlElement(name = "Universidad")
    private String universidad;
    @XmlElement(name = "Carrera")
    private String carrera;

    public Estudiante() {
        super();
    }

    public Estudiante(String nombre, int edad, Direccion direccion, ArrayList<String> telefonos, String universidad, String carrera) {
        super(nombre, edad, direccion, telefonos);
        this.universidad = universidad;
        this.carrera = carrera;
    }

    public String getUniversidad() {
        return universidad;
    }

    public void setUniversidad(String universidad) {
        this.universidad = universidad;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }
}
