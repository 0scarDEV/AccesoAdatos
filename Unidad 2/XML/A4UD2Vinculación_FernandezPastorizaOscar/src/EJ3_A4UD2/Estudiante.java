package EJ3_A4UD2;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;

@XmlType(name = "Estudiante", propOrder = {"universidad", "carrera"})
public class Estudiante extends Persona {
    private String universidad;
    private String carrera;

    public Estudiante() {
        super();
    }

    public Estudiante(String nombre, int edad, Direccion direccion, ArrayList<String> telefonos, String universidad, String carrera) {
        super(nombre, edad, direccion, telefonos);
        this.universidad = universidad;
        this.carrera = carrera;
    }

    @XmlElement(name = "Universidad")
    public String getUniversidad() {
        return universidad;
    }

    public void setUniversidad(String universidad) {
        this.universidad = universidad;
    }

    @XmlElement(name = "Carrera")
    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }
}
