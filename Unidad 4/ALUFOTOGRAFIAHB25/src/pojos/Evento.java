package pojos;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/* Óscar Fernández Pastoriza */

public class Evento implements java.io.Serializable {
    private int idEvento;
    private String nombreEvento;
    private String ciudad;
    private Date fecha;
    private String tipoEvento;
    private Set<Fotografo> fotografosAsistentes = new HashSet<>();

    public Set<Fotografo> getFotografosAsistentes() {
        return fotografosAsistentes;
    }

    public void setFotografosAsistentes(Set<Fotografo> fotografosAsistentes) {
        this.fotografosAsistentes = fotografosAsistentes;
    }

    public Evento() {
    }

    public Evento(String nombreEvento, String ciudad, Date fecha, String tipoEvento, Set<Fotografo> fotografosAsistentes) {
        this.nombreEvento = nombreEvento;
        this.ciudad = ciudad;
        this.fecha = fecha;
        this.tipoEvento = tipoEvento;
        this.fotografosAsistentes = fotografosAsistentes;
    }

    public Evento(int idEvento, String nombreEvento, String ciudad, Date fecha, String tipoEvento) {
        this.idEvento = idEvento;
        this.nombreEvento = nombreEvento;
        this.ciudad = ciudad;
        this.fecha = fecha;
        this.tipoEvento = tipoEvento;
    }


    public int getIdEvento() {
        return this.idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public String getNombreEvento() {
        return this.nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public String getCiudad() {
        return this.ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Date getFecha() {
        return this.fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTipoEvento() {
        return this.tipoEvento;
    }

    public void setTipoEvento(String tipoEvento) {
        this.tipoEvento = tipoEvento;
    }


}
