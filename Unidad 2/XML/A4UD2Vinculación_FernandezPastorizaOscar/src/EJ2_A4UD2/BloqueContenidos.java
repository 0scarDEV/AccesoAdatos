package EJ2_A4UD2;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"numSesions", "num", "titulo", "descripcion"})
public class BloqueContenidos {
    @XmlAttribute(name = "num")
    private int num;
    @XmlElement(name = "Titulo")
    private String titulo;
    @XmlElement(name = "Descripcion")
    private String descripcion;
    @XmlAttribute(name = "sesions")
    private int numSesions;

    public BloqueContenidos() {

    }

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

    @Override public String toString() {
        return "Bloque num=" + num +
                "\n, titulo='" + titulo +
                "\n, descripcion='" + descripcion +
                "\n, numSesions=" + numSesions;
    }
}
