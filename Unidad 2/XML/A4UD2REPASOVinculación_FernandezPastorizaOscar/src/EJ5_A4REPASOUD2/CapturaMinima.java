package EJ5_A4REPASOUD2;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

@XmlRootElement (name = "CapturaMinima")
public class CapturaMinima {
    private String unidade;
    private double valor;

    public CapturaMinima() {
    }

    public CapturaMinima(String unidade, double valor) {
        this.unidade = unidade;
        this.valor = valor;
    }

    @XmlAttribute (name = "unidade")
    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    @XmlValue
    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override public String toString() {
        return valor + unidade;
    }
}
