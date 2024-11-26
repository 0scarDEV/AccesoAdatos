package Ejercicio3;

import javax.xml.bind.annotation.XmlAttribute;

/* Óscar Fernández Pastoriza - 53862191D */
public class Universidad extends Entidad {
    private int numEstudiantes;
    private int numFacultades;

    public Universidad() {
    }

    @XmlAttribute(name = "numEstudiantes")
    public int getNumEstudiantes() {
        return numEstudiantes;
    }

    public void setNumEstudiantes(int numEstudiantes) {
        this.numEstudiantes = numEstudiantes;
    }

    @XmlAttribute(name = "facultades")
    public int getNumFacultades() {
        return numFacultades;
    }

    public void setNumFacultades(int numFacultades) {
        this.numFacultades = numFacultades;
    }

    @Override public String toString() {
        return "Universidad: " + super.toString() +
                "\n\tNumero Estudiantes: " + numEstudiantes + " Facultades: " + numFacultades;
    }
}
