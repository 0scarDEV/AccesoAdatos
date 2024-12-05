package E2_3y4;

public class Proxecto {
    private int numProxecto;
    private String nomeProxecto;
    private String lugar;
    private int numDepartamentoPertenece;

    public Proxecto(int numProxecto, String nomeProxecto, String lugar, int numDepartamentoPertenece) {
        this.numProxecto = numProxecto;
        this.nomeProxecto = nomeProxecto;
        this.lugar = lugar;
        this.numDepartamentoPertenece = numDepartamentoPertenece;
    }

    public int getNumProxecto() {
        return numProxecto;
    }

    public void setNumProxecto(int numProxecto) {
        this.numProxecto = numProxecto;
    }

    public String getNomeProxecto() {
        return nomeProxecto;
    }

    public void setNomeProxecto(String nomeProxecto) {
        this.nomeProxecto = nomeProxecto;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public int getNumDepartamentoPertenece() {
        return numDepartamentoPertenece;
    }

    public void setNumDepartamentoPertenece(int numDepartamentoPertenece) {
        this.numDepartamentoPertenece = numDepartamentoPertenece;
    }
}
