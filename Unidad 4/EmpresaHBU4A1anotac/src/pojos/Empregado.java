package pojos;
// Generated 23 ene. 2025 9:06:45 by Hibernate Tools 4.3.1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Empregado generated by hbm2java
 */
@Entity
@Table(name = "EMPREGADO",
        schema = "dbo",
        catalog = "EmpresaHB"
)
public class Empregado implements java.io.Serializable {
    @Id
    @Column(name = "NSS", unique = true, nullable = false, length = 15)
    private String nss;
    @Column(name = "Nome", nullable = false, length = 25)
    private String nome;
    @Column(name = "Apelido_1", nullable = false, length = 25)
    private String apelido1;
    @Column(name = "Apelido_2", length = 25)
    private String apelido2;
    @Column(name = "Salario", precision = 53, scale = 0)
    private Double salario;
    @Temporal(TemporalType.DATE)
    @Column(name = "Data_nacemento", length = 10)
    private Date dataNacemento;
    @Column(name = "Sexo", length = 1)
    private Character sexo;

    // Mapeo de la colección de teléfonos como un componente
    @ElementCollection
    @CollectionTable(name="TELEFONO", joinColumns=@JoinColumn(name = "NSS"))
    @Column(name="Numero")
    private Set<String> telefonos;
    
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

    public Set<String> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(Set<String> telefonos) {
        this.telefonos = telefonos;
    }

    @Override
    public String toString() {
        return "Empregado{" + "nss=" + nss + ", nome=" + nome + ", apelido1=" + apelido1 + ", apelido2=" + apelido2 + ", salario=" + salario + ", dataNacemento=" + dataNacemento + ", sexo=" + sexo + '}';
    }
}
