package EJ3_A4UD2;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Direccion {
    @XmlElement(name = "Calle")
    private String calle;
    @XmlElement(name = "Numero")
    private int numero;
    @XmlElement(name = "Localidad")
    private String localidad;
    @XmlElement(name = "Provincia")
    private String provincia;

    public Direccion() { }

    public Direccion(String calle, int numero, String localidad, String provincia) {
        this.calle = calle;
        this.numero = numero;
        this.localidad = localidad;
        this.provincia = provincia;
    }
}
