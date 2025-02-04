/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejerciciosBasicos;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import pojos.Familiar;
import utiles.OperacionesHB;
import pojos.Departamento;
import pojos.Empregado;

import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author ofernpast
 */
public class Inserciones {
    public static void main(String[] args) {
        OperacionesHB opHB = new OperacionesHB();
        Session s = opHB.getSession();
        Transaction transaction = s.beginTransaction();

        /*
        try {
            Empregado e = new Empregado("87654321A", "Vipo", "Rua");
        if (opHB.insertarEmpregado(opHB.getSession(), e)) {
            System.out.println("Empregado " + e + " insertado automáticamente.");
        } else {
            System.out.println("Error al insertar");
        }

        Departamento d = new Departamento("PRUEBA3");
        if (opHB.insertarDepartamento(opHB.getSession(), d)) {
            System.out.println("Departamento " + d + " insertado correctamente.");
        } else {
            System.out.println("Error al insertar");
        }

        d = new Departamento("PRUEBA4");
        if (opHB.insertarDepartamento(opHB.getSession(), d)) {
            System.out.println("Departamento " + d + " insertado correctamente.");
        } else {
            System.out.println("Error al insertar");
        }
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            System.out.println("Error al insertar empleado o departamento");
        }*/

        Familiar f = new Familiar("12345678V", "Cristina", "Pastoriza", "Otero", new Date(1967, 12, 27), "Tia", 'M');
        try {
            opHB.addFamiliar(s, "12345678A", f);
            transaction.commit();
            System.out.println("Familiar insertado correctamente");
        } catch (HibernateException he) {
            transaction.rollback();
            System.err.println("Error al insertar el familiar.");
        }
        
        opHB.liberarRecursos();
        System.exit(0);
    }
}
