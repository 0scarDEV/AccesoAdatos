package POJOS;
// Generated 09-feb-2023 8:57:23 by Hibernate Tools 3.6.0
/**
 * EmpregadoProxecto generated by hbm2java
 */
public class EmpregadoProxecto implements java.io.Serializable {
    //definicion de la clave compuesta
    private EmpregadoProxectoId id;
    //empreado del proyecto
    private Empregado empregado;
    //proxecto que tiene el empleado
    private Proxecto proxecto;
    private Integer horas;

    public EmpregadoProxecto() {
    }

    public EmpregadoProxecto(EmpregadoProxectoId id, Empregado empregado, Proxecto proxecto) {
        this.id = id;
        this.empregado = empregado;
        this.proxecto = proxecto;
    }

    public EmpregadoProxecto(EmpregadoProxectoId id, Empregado empregado, Proxecto proxecto, Integer horas) {
        this.id = id;
        this.empregado = empregado;
        this.proxecto = proxecto;
        this.horas = horas;
    }

    public EmpregadoProxectoId getId() {
        return this.id;
    }

    public void setId(EmpregadoProxectoId id) {
        this.id = id;
    }

    public Empregado getEmpregado() {
        return this.empregado;
    }

    public void setEmpregado(Empregado empregado) {
        this.empregado = empregado;
    }

    public Proxecto getProxecto() {
        return this.proxecto;
    }

    public void setProxecto(Proxecto proxecto) {
        this.proxecto = proxecto;
    }

    public Integer getHoras() {
        return this.horas;
    }

    public void setHoras(Integer horas) {
        this.horas = horas;
    }
}
