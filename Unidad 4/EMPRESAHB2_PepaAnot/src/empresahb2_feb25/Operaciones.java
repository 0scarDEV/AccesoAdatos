/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresahb2_feb25;

import POJOS.*;
import org.hibernate.Query;
import org.hibernate.Session;

import java.sql.Date;
import java.util.List;

/**
 * @author usuario
 */
public class Operaciones {
    public static void conectarHibernate() {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        if (sesion != null) {
            System.out.println("Conexión realizada con éxito");
            sesion.close();
        } else {
            System.out.println("ERROR");
        }
    }

    public static Session crearSesion() {
        return HibernateUtil.getSessionFactory().openSession();
    }

    public static void listarEmpregados(Session s) {
        String tipo;
        Query consulta = s.createQuery("from Empregado");
        List<Empregado> empregados = consulta.list();
        for (Empregado e : empregados) {
            if (e instanceof Empregadofixo) {
                tipo = "Fixo";
            } else if (e instanceof Empregadotemporal) {
                tipo = "Temporal";
            } else {
                tipo = "Descoñecido";
            }

            String formato = "%-5s %-30s %-20s";
            System.out.printf((formato) + "%n", e.getNss(),
                    e.getApelido1() + " " + e.getApelido2() + ", " + e.getNome(), tipo);
        }
    }

    public static boolean registrarEdicion(Session s, int codCurso, Date fecha, String lugar, String nssProfesor) {
        Curso curso;
        Empregadofixo profesor;
        List<Edicion> ediciones;
        Edicion ed;

        curso = (Curso) s.get(Curso.class, codCurso);
        if (curso == null) {
            System.err.println("No existe el curso al que se intenta añadir una edición");
            return false;
        }

        profesor = (Empregadofixo) s.get(Empregadofixo.class, nssProfesor);
        if (profesor == null) {
            System.err.println("No existe el profesor al que se intenta añadir una edición");
            return false;
        }

        ediciones = curso.getEdicions();
        ed = new Edicion(fecha, lugar, profesor);
        ediciones.add(ediciones.size(), ed);
        ed.setCurso(curso);
        ed.setId(new EdicionId(curso.getCodigo()));
        curso.setEdicions(ediciones);

        s.save(ed);
        return true;
    }
}