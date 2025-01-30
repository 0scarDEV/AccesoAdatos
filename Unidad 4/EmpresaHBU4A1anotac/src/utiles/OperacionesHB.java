/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utiles;

import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import pojos.Departamento;
import pojos.Empregado;

import java.util.HashSet;

/**
 * @author ofernpast
 */
public class OperacionesHB {
    private static SessionFactory sf;

    public OperacionesHB() {
        sf = HibernateUtil.getSessionFactory();
    }

    public Session openSession() {
        return sf.openSession();
    }

    public void liberarRecursos() {
        sf.close();
    }

    // region INSERCIONES
    public boolean insertarEmpregado(Session s, Empregado e) {
        boolean flagInsercion;

        try {
            s.save(e);
            flagInsercion = true;
        } catch (HibernateException he) {
            flagInsercion = false;
        }

        return flagInsercion;
    }

    public boolean insertarDepartamento(Session s, Departamento d) {
        boolean flagInsercion;

        try {
            s.save(d);
            flagInsercion = true;
        } catch (HibernateException he) {
            flagInsercion = false;
            he.printStackTrace();
        }

        return flagInsercion;
    }
    // endregion

    // region LECTURA
    public Departamento loadDepartamento(Session s, int numDepartamento) {
        Departamento d;
        try {
            d = (Departamento) s.load(Departamento.class, numDepartamento);
            System.out.println(d);
        } catch (ObjectNotFoundException e) {
            d = null;
            System.out.println("No se ha encontrado un departamento con el número " + numDepartamento);
        }

        return d;
    }

    public Empregado getEmpregado(Session s, String nss) {
        return (Empregado) s.get(Empregado.class, nss);
    }

    public void showEmpregado(Session s, String nss) {
        Empregado e = (Empregado) s.get(Empregado.class, nss);
        System.out.println(e);
    }
    // endregion

    // region ELIMINAR
    public boolean removeDepartamento(Session s, int numDepartamento) {
        boolean flagBorrado;

        try {
            Departamento d = (Departamento) s.get(Departamento.class, numDepartamento);
            System.out.println(d);
            s.delete("Eliminando " + d);
            flagBorrado = true;
        } catch (HibernateException he) {
            flagBorrado = false;
            he.printStackTrace();
        }

        return flagBorrado;
    }

    public boolean removeEmpregado(Session s, String nss) {
        boolean flagBorrado;

        try {
            Empregado e = (Empregado) s.get(Empregado.class, nss);
            System.out.println("Eliminando " + e);
            s.delete(e);
            flagBorrado = true;
        } catch (HibernateException he) {
            flagBorrado = false;
            he.printStackTrace();
        }

        return flagBorrado;
    }
    // endregion

    public boolean modificarSalarioEmpleado(Session s, String nss, double nuevoSalario) {
        boolean flagModificacion = false;
        try {
            Empregado e = getEmpregado(s, nss);
            e.setSalario(nuevoSalario);
            System.out.println(e);
            flagModificacion = true;
        } catch (HibernateException he) {
            he.printStackTrace();
        }

        return flagModificacion;
    }

    public boolean addTelefonoEmpleado(Session s, String nss, HashSet<String> telefonos) {
        boolean flagModificacion = false;

        try {
            Empregado e = (Empregado) s.get(Empregado.class, nss);

            if (e != null) {
                e.setTelefonos(telefonos);
                System.out.println(e);
                flagModificacion = true;
            }
        } catch (HibernateException he) {
            he.printStackTrace();
        }

        return flagModificacion;
    }

    public boolean removeTelefonoEmpleado(Session s, String nss, String telefono) {
        boolean flagModificacion;

        try {
            Empregado e = (Empregado) s.get(Empregado.class, nss);
            e.getTelefonos().remove(telefono);
            System.out.println(e);
            flagModificacion = true;
        } catch (HibernateException he) {
            flagModificacion = false;
        }

        return flagModificacion;
    }
}