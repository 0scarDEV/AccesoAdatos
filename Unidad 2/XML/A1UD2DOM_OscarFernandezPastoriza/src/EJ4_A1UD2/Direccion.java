package EJ4_A1UD2;

import java.io.Serializable;

public class Direccion implements Serializable {
    String calle;
    int numero;
    int codPostal;

    public Direccion(String calle, int numero, int codPostal) {
        this.calle = calle;
        this.numero = numero;
        this.codPostal = codPostal;
    }

    public String getCalle() {
        return calle;
    }

    public int getNumero() {
        return numero;
    }

    public int getCodPostal() {
        return codPostal;
    }
}
