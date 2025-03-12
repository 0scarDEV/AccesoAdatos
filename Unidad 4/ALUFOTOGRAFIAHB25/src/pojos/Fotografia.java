package pojos;

import java.util.Date;

/* Óscar Fernández Pastoriza */

public class Fotografia implements java.io.Serializable {
    private int idFotografia;
    private String titulo;
    private Date fechaCaptura;
    private Character color;
    private Fotografo fotografo;

    public Fotografia() {
    }

    public Fotografia(int idFotografia, String titulo, Date fechaCaptura) {
        this.idFotografia = idFotografia;

        this.titulo = titulo;
        this.fechaCaptura = fechaCaptura;
    }

    public int getIdFotografia() {
        return this.idFotografia;
    }

    public void setIdFotografia(int idFotografia) {
        this.idFotografia = idFotografia;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getFechaCaptura() {
        return this.fechaCaptura;
    }

    public void setFechaCaptura(Date fechaCaptura) {
        this.fechaCaptura = fechaCaptura;
    }

    public Character getColor() {
        return this.color;
    }

    public void setColor(Character color) {
        this.color = color;
    }

    public Fotografo getFotografo() {
        return fotografo;
    }

    public void setFotografo(Fotografo fotografo) {
        this.fotografo = fotografo;
    }

}
