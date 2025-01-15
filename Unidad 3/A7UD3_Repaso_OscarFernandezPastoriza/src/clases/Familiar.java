package clases;

import java.sql.Date;

public class Familiar {
    public enum Sexo {Hombre, Mujer}

    private String nss;
    private String nome;
    private String apelido1;
    private String apelido2;
    private Date dataNacemento;
    private String parentesco;
    private char sexo;

    public Familiar(String nss, String nome, String apelido1, String parentesco, Sexo sexo) {
        this.nss = nss;
        this.nome = nome;
        this.apelido1 = apelido1;
        this.parentesco = parentesco;
        setSexo(sexo);
    }

    public Familiar(String nss, String nome, String apelido1, String apelido2, Date dataNacemento, String parentesco, Sexo sexo) {
        this.nss = nss;
        this.nome = nome;
        this.apelido1 = apelido1;
        this.apelido2 = apelido2;
        this.dataNacemento = dataNacemento;
        this.parentesco = parentesco;
        setSexo(sexo);
    }

    public String getNss() {
        return nss;
    }

    public void setNss(String nss) {
        this.nss = nss;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelido1() {
        return apelido1;
    }

    public void setApelido1(String apelido1) {
        this.apelido1 = apelido1;
    }

    public String getApelido2() {
        return apelido2;
    }

    public void setApelido2(String apelido2) {
        this.apelido2 = apelido2;
    }

    public Date getDataNacemento() {
        return dataNacemento;
    }

    public void setDataNacemento(Date dataNacemento) {
        this.dataNacemento = dataNacemento;
    }

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        if (sexo == Sexo.Hombre) {
            this.sexo = 'H';
        } else {
            this.sexo = 'M';
        }
    }
}
