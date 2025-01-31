package pojos;
// Generated 23 ene. 2025 9:06:45 by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.GenericGenerator;

/**
 * Departamento generated by hbm2java
 */
@Entity
@Table(name = "DEPARTAMENTO",
         schema = "dbo",
         catalog = "EmpresaHB",
         uniqueConstraints = @UniqueConstraint(columnNames = "Nome_departamento")
)
public class Departamento implements java.io.Serializable {
    @Id @GeneratedValue(generator = "increment")
    @GenericGenerator(name="increment", strategy="increment")
    @Column(name = "Num_departamento", unique = true, nullable = false)
    private int numDepartamento;
    
    @Column(name = "Nome_departamento", unique = true, nullable = false, length = 25)
    private String nomeDepartamento;

    public Departamento() {
    }

    public Departamento(String nomeDepartamento) {
        this.nomeDepartamento = nomeDepartamento;
    }
    
    public Departamento(int numDepartamento, String nomeDepartamento) {
        this.numDepartamento = numDepartamento;
        this.nomeDepartamento = nomeDepartamento;
    }

    public int getNumDepartamento() {
        return this.numDepartamento;
    }

    public void setNumDepartamento(int numDepartamento) {
        this.numDepartamento = numDepartamento;
    }

    public String getNomeDepartamento() {
        return this.nomeDepartamento;
    }

    public void setNomeDepartamento(String nomeDepartamento) {
        this.nomeDepartamento = nomeDepartamento;
    }

    @Override
    public String toString() {
        return "Departamento{" + "numDepartamento=" + numDepartamento + ", nomeDepartamento=" + nomeDepartamento + '}';
    }
}
