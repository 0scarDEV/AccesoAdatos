/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utiles;

import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pojos.Departamento;
import pojos.Empregado;
import pojos.Familiar;

import java.util.HashSet;

/**
 * @author ofernpast
 */
public class OperacionesHB {
    protected static SessionFactory sf;

    public OperacionesHB() {
        sf = HibernateUtil.getSessionFactory();
    }

    public void liberarRecursos() {
        sf.close();
    }

    public Session getSession() {
        return sf.openSession();
    }

    // region INSERCIONES
    public boolean insertarEmpregado(Session s, Empregado e) {
        boolean flagInsercion = false;

        try {
            s.save(e);
            flagInsercion = true;
        } catch (HibernateException he) {
            System.out.println("Error al insertar Empregado");
        }

        return flagInsercion;
    }

    public boolean insertarDepartamento(Session s, Departamento d) {
        boolean flagInsercion = false;

        try {
            s.save(d);
            flagInsercion = true;
        } catch (HibernateException he) {
            System.out.println("Error al insertar Departamento");
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
        boolean flagBorrado = false;

        try {
            Departamento d = (Departamento) s.get(Departamento.class, numDepartamento);
            System.out.println(d);
            s.delete("Eliminando " + d);
            flagBorrado = true;
        } catch (HibernateException he) {
            System.out.println("Error al eliminar Departamento");
        }

        return flagBorrado;
    }

    public boolean removeEmpregado(Session s, String nss) {
        boolean flagBorrado = false;

        try {
            Empregado e = (Empregado) s.get(Empregado.class, nss);
            System.out.println("Eliminando " + e);
            s.delete(e);
            flagBorrado = true;
        } catch (HibernateException he) {
            System.out.println("Error al eliminar Empregado");
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
            System.out.println("Error al modificar salario");
        }

        return flagModificacion;
    }

    public boolean setNumsTelefonosEmpleado(Session s, String nss, HashSet<String> telefonos) {
        boolean flagModificacion = false;
        try {
            Empregado e = (Empregado) s.get(Empregado.class, nss);

            if (e != null) {
                //e.setTelefonos(telefonos);
                System.out.println(e);
                flagModificacion = true;
            }
        } catch (HibernateException he) {
            System.out.println("Error al establecer los teléfonos de los empleados");
        }

        return flagModificacion;
    }

    public boolean removeNumTelefonoEmpleado(Session s, String nss, String telefono) {
        boolean flagModificacion = false;

        try {
            Empregado e = (Empregado) s.get(Empregado.class, nss);
            e.getTelefonos().remove(telefono);
            System.out.println(e);
            flagModificacion = true;
        } catch (HibernateException he) {
            System.out.println("Error al eliminar un teléfono de un empleado");
        }

        return flagModificacion;
    }

    public boolean addFamiliar(Session s, String nss, Familiar familiar) {
        Empregado empregado = (Empregado) s.get(Empregado.class, nss);
        if (empregado != null) {
            empregado.getFamiliares().add(familiar);
            return true;
        }
        return false;
    }

    public boolean addAficion(Session s, String nss, String aficion) {
        Empregado empregado = (Empregado) s.get(Empregado.class, nss);
        if (empregado != null) {
            empregado.getAficiones().add(aficion);
            return true;
        }
        return false;
    }
}