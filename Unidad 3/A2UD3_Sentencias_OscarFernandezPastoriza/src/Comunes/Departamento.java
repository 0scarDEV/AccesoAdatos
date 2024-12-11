package Comunes;

import java.sql.Date;

public class Departamento {
    private int numDepartamento;
    private String nomeDepartamento;
    private String nssDirige;
    private Date dataDireccion;

    public Departamento(int numDepartamento, String nomeDepartamento, String nssDirige, Date dataDireccion) {
        this.numDepartamento = numDepartamento;
        this.nomeDepartamento = nomeDepartamento;
        this.nssDirige = nssDirige;
        this.dataDireccion = dataDireccion;
    }

    public int getNumDepartamento() {
        return numDepartamento;
    }

    public void setNumDepartamento(int numDepartamento) {
        this.numDepartamento = numDepartamento;
    }

    public String getNomeDepartamento() {
        return nomeDepartamento;
    }

    public void setNomeDepartamento(String nomeDepartamento) {
        this.nomeDepartamento = nomeDepartamento;
    }

    public String getNssDirige() {
        return nssDirige;
    }

    public void setNssDirige(String nssDirige) {
        this.nssDirige = nssDirige;
    }

    public Date getDataDireccion() {
        return dataDireccion;
    }

    public void setDataDireccion(Date dataDireccion) {
        this.dataDireccion = dataDireccion;
    }

    @Override public String toString() {
        return "Departamento{" +
                "numDepartamento=" + numDepartamento +
                ", nomeDepartamento='" + nomeDepartamento + '\'' +
                ", nssDirige='" + nssDirige + '\'' +
                ", dataDireccion='" + dataDireccion + '\'' +
                '}';
    }
}
