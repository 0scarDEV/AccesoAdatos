package EJ4_A4UD2;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlType (propOrder = {"calle", "numero", "codPostal"})
public class Direccion implements Serializable {
    private String calle;
    private int numero;
    private String codPostal;

    public Direccion() {
    }

    public Direccion(String calle, int numero, String codPostal) {
        this.calle = calle;
        this.numero = numero;
        this.codPostal = codPostal;
    }

    @XmlElement (name = "Calle")
    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    @XmlElement (name = "Numero")
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    @XmlElement (name = "CodigoPostal")
    public String getCodPostal() {
        return codPostal;
    }

    public void setCodPostal(String codPostal) {
        this.codPostal = codPostal;
    }
}
