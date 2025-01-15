package clases;

import java.util.List;

public class Empleado {
    String nss;
    List<Familiar> familiares;

    public Empleado(String nss, List<Familiar> familiares) {
        this.nss = nss;
        this.familiares = familiares;
    }

    public String getNss() {
        return nss;
    }

    public void setNss(String nss) {
        this.nss = nss;
    }

    public List<Familiar> getFamiliares() {
        return familiares;
    }

    public void setFamiliares(List<Familiar> familiares) {
        this.familiares = familiares;
    }
}