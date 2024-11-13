package fich24.oscarfp;

public class InfoCocinero {
    private int codigo;
    private String tipo;

    public InfoCocinero(int codigo, String tipo) {
        this.codigo = codigo;
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public int getNumero() {
        return codigo;
    }

    @Override public String toString() {
        Cocinero cocinero = CocineroBinarioSecuencial.getCocinero(codigo);
        if (cocinero != null) {
            return tipo + ": " + codigo + "-" + cocinero.getApodo() + " (" + cocinero.getNombreCompleto() + ")";
        }
        return tipo + ": " + codigo;
    }
}
