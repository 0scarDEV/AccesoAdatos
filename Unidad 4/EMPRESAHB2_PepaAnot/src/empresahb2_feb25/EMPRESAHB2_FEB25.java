/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresahb2_feb25;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

/**
 * @author usuario
 */
public class EMPRESAHB2_FEB25 {
    /**
     * @param args the command line arguments
     */
    static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public static void main(String[] args) {
        Operaciones.conectarHibernate();

        Session s = Operaciones.crearSesion();

        // Listar los empregados
        Operaciones.listarEmpregados(s);

        Transaction t = s.beginTransaction();
        try {
            Operaciones.registrarEdicion(s, 1, Date.valueOf(LocalDate.of(2020, 7, 17)), "A Coruña", "0110010");
        } catch (HibernateException e) {
            t.rollback();
        }

        s.close();

        System.exit(0);
    }
}
