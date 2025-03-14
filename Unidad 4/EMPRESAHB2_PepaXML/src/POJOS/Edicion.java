package POJOS;
// Generated 09-feb-2023 8:57:23 by Hibernate Tools 3.6.0

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Edicion generated by hbm2java
 */
public class Edicion implements java.io.Serializable {

    private EdicionId id;

    private Date data;
    private String lugar;
    //los alumnos que cursan una edicion
    private Collection <Empregado> alumnos = new ArrayList();
    //el curso al que pertenece la edicion
    private Curso curso;
    // el profeso que da el curso  
    private Empregadofixo empregadofixo;

    public Edicion() {
    }

    public Edicion(Date data, String lugar, Empregadofixo empregadofixo) {
        this.data = data;
        this.lugar = lugar;
        this.empregadofixo = empregadofixo;
    }

    public Edicion(EdicionId id, Curso curso, Empregadofixo empregadofixo) {
        this.id = id;
        this.curso = curso;
        this.empregadofixo = empregadofixo;
    }


    public EdicionId getId() {
        return this.id;
    }

    public void setId(EdicionId id) {
        this.id = id;
    }

    public Curso getCurso() {
        return this.curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Empregadofixo getEmpregadofixo() {
        return this.empregadofixo;
    }

    public void setEmpregadofixo(Empregadofixo empregadofixo) {
        this.empregadofixo = empregadofixo;
    }

    public Date getData() {
        return this.data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getLugar() {
        return this.lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public Collection<Empregado> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(Collection<Empregado> alumnos) {
        this.alumnos = alumnos;
    }

   

}
