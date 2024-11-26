package Ejercicio3;

import javax.xml.bind.annotation.XmlAttribute;

/* Óscar Fernández Pastoriza - 53862191D */

public class Instituto extends Entidad {
    private String numProyectos;

    public Instituto() {
    }

    @XmlAttribute(name = "numProyectos")
    public String getNumProyectos() {
        return numProyectos;
    }

    public void setNumProyectos(String numProyectos) {
        this.numProyectos = numProyectos;
    }

    @Override public String toString() {
        return "Instituto " + super.toString() +
                "\n\tNumero Proyectos: " + numProyectos;
    }
}
