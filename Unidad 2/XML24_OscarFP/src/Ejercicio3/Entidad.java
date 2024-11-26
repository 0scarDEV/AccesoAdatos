package Ejercicio3;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

/* Óscar Fernández Pastoriza - 53862191D */

@XmlType(propOrder = {"nombre", "siglas", "anho", "sede"})
public class Entidad {
    private String nombre;
    private String siglas;
    private String anho;
    private String sede;

    public Entidad() {}

    @XmlValue
    public String getNombre() {
        return nombre;
    }

    @XmlAttribute(name = "siglas")
    public String getSiglas() {
        return siglas;
    }

    @XmlAttribute(name = "año")
    public String getAnho() {
        return anho;
    }

    @XmlAttribute(name = "sede")
    public String getSede() {
        return sede;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setSiglas(String siglas) {
        this.siglas = siglas;
    }

    public void setAnho(String anho) {
        this.anho = anho;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    @Override public String toString() {
        return getNombre() + " (" + getSiglas() + ")\tsede: " + getSede() +
                "\t\tAño de Creacion: " + getAnho();
    }
}


