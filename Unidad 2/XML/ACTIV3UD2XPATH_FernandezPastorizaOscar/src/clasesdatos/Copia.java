package clasesdatos;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 *
 * @author usuario
 */
public class Copia {
     private int numero;
    private String estado;

    public Copia() {
    }

    public Copia(int numero, String estado) {
        this.numero = numero;
        this.estado = estado;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    
     @Override
    public String toString() {
        return "{Número de copia:" + numero + ", Estado: " + estado+"}";
    }

    public Element toElement(Document document) {
        Element copia = document.createElement("copia");
        copia.setAttribute("numero", String.valueOf(numero));
        copia.setAttribute("estado", estado);
        return copia;
    }
}
