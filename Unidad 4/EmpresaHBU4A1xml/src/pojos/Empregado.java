package pojos;
// Generated 23 ene. 2025 9:18:23 by Hibernate Tools 4.3.1

import java.util.*;

/**
 * Empregado generated by hbm2java
 */
public class Empregado implements java.io.Serializable {

    private String nss;
    private String nome;
    private String apelido1;
    private String apelido2;
    private Double salario;
    private Date dataNacemento;
    private Character sexo;

    public Empregado() {
    }

    public Empregado(String nss, String nome, String apelido1) {
        this(nss, nome, apelido1, null, null, null, null);
    }

    public Empregado(String nss, String nome, String apelido1, String apelido2, Double salario, Date dataNacemento, Character sexo) {
        this.nss = nss;
        this.nome = nome;
        this.apelido1 = apelido1;
        this.apelido2 = apelido2;
        this.salario = salario;
        this.dataNacemento = dataNacemento;
        this.sexo = sexo;
        this.telefonos = new HashSet<>();
    }

    public String getNss() {
        return this.nss;
    }

    public void setNss(String nss) {
        this.nss = nss;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelido1() {
        return this.apelido1;
    }

    public void setApelido1(String apelido1) {
        this.apelido1 = apelido1;
    }

    public String getApelido2() {
        return this.apelido2;
    }

    public void setApelido2(String apelido2) {
        this.apelido2 = apelido2;
    }

    public Double getSalario() {
        return this.salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public Date getDataNacemento() {
        return this.dataNacemento;
    }

    public void setDataNacemento(Date dataNacemento) {
        this.dataNacemento = dataNacemento;
    }

    public Character getSexo() {
        return this.sexo;
    }

    public void setSexo(Character sexo) {
        this.sexo = sexo;
    }

    
    /*
        Mapeo cuando en la base de datos únicamente teníamos el número de teléfono.
    * private Set<String> telefonos;
    * public void setTelefonos(Set<String> telefonos) {
    *     this.telefonos = telefonos;
    * }
    * public Set<String> getTelefonos() {
    *     return telefonos;
    * }
    */
    
    
    /* 
        Mapeo cuando en la base de datos 

    public void setTelefonos(HashSet<String> telefonos) {
        this.telefonos = telefonos;
    }
    public HashSet<String> getTelefonos() {
        return telefonos;
    }
    */
    private Set<Telefono> telefonos;

    public Set<Telefono> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(Set<Telefono> telefonos) {
        this.telefonos = telefonos;
    }

    private List<Familiar> familiares = new ArrayList<>();

    public List<Familiar> getFamiliares() {
        return familiares;
    }

    public void setFamiliares(List<Familiar> familiares) {
        this.familiares = familiares;
    }

    @Override public String toString() {
        return "Empregado{" +
                "nss='" + nss + '\'' +
                ", nome='" + nome + '\'' +
                ", apelido1='" + apelido1 + '\'' +
                ", apelido2='" + apelido2 + '\'' +
                ", salario=" + salario +
                ", dataNacemento=" + dataNacemento +
                ", sexo=" + sexo +
                ", telefonos=" + telefonos +
                '}';
    }
}