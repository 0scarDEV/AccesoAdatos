package pojos;

import java.util.Date;

/* Óscar Fernández Pastoriza */

public class Licencia implements java.io.Serializable {
    private int idFotografo;
    private String numeroLicencia;
    private Date fechaExpedicion;
    private Date fechaVencimiento;
    private Fotografo fotografo;

    public Licencia() {
    }

    public int getIdFotografo() {
        return this.idFotografo;
    }

    public void setIdFotografo(int idFotografo) {
        this.idFotografo = idFotografo;
    }

    public String getNumeroLicencia() {
        return this.numeroLicencia;
    }

    public void setNumeroLicencia(String numeroLicencia) {
        this.numeroLicencia = numeroLicencia;
    }

    public Date getFechaExpedicion() {
        return this.fechaExpedicion;
    }

    public void setFechaExpedicion(Date fechaExpedicion) {
        this.fechaExpedicion = fechaExpedicion;
    }

    public Date getFechaVencimiento() {
        return this.fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public Fotografo getFotografo() {
        return fotografo;
    }

    public void setFotografo(Fotografo fotografo) {
        this.fotografo = fotografo;
    }
}
