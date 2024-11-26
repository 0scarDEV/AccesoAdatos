package Ejercicio3;

import javax.xml.bind.annotation.XmlAttribute;

/* Óscar Fernández Pastoriza - 53862191D */
public class Laboratorio extends Entidad {
    private int numEmpleados;
    private String especialidad;

    public Laboratorio() {
    }

    @XmlAttribute(name = "numEmpleados")
    public int getNumEmpleados() {
        return numEmpleados;
    }

    public void setNumEmpleados(int numEmpleados) {
        this.numEmpleados = numEmpleados;
    }

    @XmlAttribute(name = "especialidad")
    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    @Override public String toString() {
        return "Laboratorio: " + super.toString() +
                "\n\tEmpleados: " + numEmpleados + " Especialidad: " + especialidad;
    }
}
