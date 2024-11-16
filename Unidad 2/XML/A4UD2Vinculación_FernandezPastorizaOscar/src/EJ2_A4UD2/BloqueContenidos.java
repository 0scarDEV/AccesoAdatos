package EJ2_A4UD2;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"numSesions", "num", "titulo", "descripcion"})
public class BloqueContenidos {
    private int num;
    private String titulo;
    private String descripcion;
    private int numSesions;

    public BloqueContenidos() {}

    public void setNum(int num) {
        this.num = num;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setNumSesions(int numSesions) {
        this.numSesions = numSesions;
    }

    @XmlAttribute(name = "num")
    public int getNum() {
        return num;
    }

    @XmlElement(name = "Titulo")
    public String getTitulo() {
        return titulo;
    }

    @XmlElement(name = "Descripcion")
    public String getDescripcion() {
        return descripcion;
    }

    @XmlAttribute(name = "sesions")
    public int getNumSesions() {
        return numSesions;
    }

    @Override public String toString() {
        return "Bloque num=" + num +
                "\n, titulo='" + titulo +
                "\n, descripcion='" + descripcion +
                "\n, numSesions=" + numSesions;
    }
}
