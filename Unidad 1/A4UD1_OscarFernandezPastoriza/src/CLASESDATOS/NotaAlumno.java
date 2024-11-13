package CLASESDATOS;

/**
 *
 * @author usuario
 */
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class NotaAlumno implements Serializable{
    @Serial private static final long serialVersionUID = 42L;
    private int numero;
     private ArrayList <NotaModulo> notas;
    

    public NotaAlumno() {
    }

    public NotaAlumno(int numero, ArrayList<NotaModulo> notas) {
        this.numero = numero;
        this.notas = notas;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public ArrayList<NotaModulo> getNotas() {
        return notas;
    }

    public void setNotas(ArrayList<NotaModulo> notas) {
        this.notas = notas;
    }
}

