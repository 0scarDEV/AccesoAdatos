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
    private static Session s;

    public OperacionesHB() {
        sf = HibernateUtil.getSessionFactory();
    }

    public void liberarRecursos() {
        if (s != null) s.close();
        sf.close();
    }

    // region INSERCIONES
    public boolean insertarEmpregado(Empregado e) {
        if (s == null) {
            s = sf.openSession();
        }
        Transaction t = s.beginTransaction();
        boolean flagInsercion = false;

        try {
            s.save(e);
            t.commit();
            flagInsercion = true;
        } catch (HibernateException he) {
            t.rollback();
            he.printStackTrace();
        }

        return flagInsercion;
    }

    public boolean insertarDepartamento(Departamento d) {
        if (s == null) {
            s = sf.openSession();
        }
        Transaction t = s.beginTransaction();
        boolean flagInsercion = false;

        try {
            s.save(d);
            t.commit();
            flagInsercion = true;
        } catch (HibernateException he) {
            t.rollback();
            he.printStackTrace();
        }

        return flagInsercion;
    }
    // endregion

    // region LECTURA
    public Departamento loadDepartamento(int numDepartamento) {
        if (s == null) {
            s = sf.openSession();
        }
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

    public Empregado getEmpregado(String nss) {
        if (s == null) {
            s = sf.openSession();
        }
        Empregado e = (Empregado) s.get(Empregado.class, nss);
        return e;
    }

    public void showEmpregado(String nss) {
        if (s == null) {
            s = sf.openSession();
        }
        Empregado e = (Empregado) s.get(Empregado.class, nss);
        System.out.println(e);
    }
    // endregion

    // region ELIMINAR
    public boolean removeDepartamento(int numDepartamento) {
        if (s == null) {
            s = sf.openSession();
        }
        Transaction t = s.beginTransaction();
        boolean flagBorrado = false;

        try {
            Departamento d = (Departamento) s.get(Departamento.class, numDepartamento);
            System.out.println(d);
            s.delete("Eliminando " + d);
            t.commit();
            flagBorrado = true;
        } catch (HibernateException he) {
            t.rollback();
            he.printStackTrace();
        }

        return flagBorrado;
    }

    public boolean removeEmpregado(String nss) {
        if (s == null) {
            s = sf.openSession();
        }
        Transaction t = s.beginTransaction();
        boolean flagBorrado = false;

        try {
            Empregado e = (Empregado) s.get(Empregado.class, nss);
            System.out.println("Eliminando " + e);
            s.delete(e);
            t.commit();
            flagBorrado = true;
        } catch (HibernateException he) {
            t.rollback();
            he.printStackTrace();
        }

        return flagBorrado;
    }
    // endregion

    public boolean modificarSalarioEmpleado(String nss, double nuevoSalario) {
        if (s == null) {
            s = sf.openSession();
        }
        boolean flagModificacion = false;

        Transaction t = s.beginTransaction();
        try {
            Empregado e = getEmpregado(nss);
            e.setSalario(nuevoSalario);
            System.out.println(e);
            t.commit();
            flagModificacion = true;
        } catch (HibernateException he) {
            t.rollback();
            he.printStackTrace();
        }

        return flagModificacion;
    }

    public boolean addTelefonoEmpleado(String nss, HashSet<String> telefonos) {
        if (s == null) {
            s = sf.openSession();
        }
        boolean flagModificacion = false;

        Transaction t = s.beginTransaction();
        try {
            Empregado e = (Empregado) s.get(Empregado.class, nss);

            if (e != null) {
                e.setTelefonos(telefonos);
                t.commit();
                System.out.println(e);
                flagModificacion = true;
            }
        } catch (HibernateException he) {
            t.rollback();
            he.printStackTrace();
        }

        return flagModificacion;
    }

    public boolean removeTelefonoEmpleado(String nss, String telefono) {
        if (s == null) {
            s = sf.openSession();
        }
        Transaction t = s.beginTransaction();
        boolean flagModificacion = false;

        try {
            Empregado e = (Empregado) s.get(Empregado.class, nss);
            e.getTelefonos().remove(telefono);
            System.out.println(e);
            t.commit();
            flagModificacion = true;
        } catch (HibernateException he) {
            t.rollback();
        }

        return flagModificacion;
    }
}