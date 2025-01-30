/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejerciciosBasicos;

import utiles.OperacionesHB;
import pojos.Departamento;
import pojos.Empregado;

/**
 *
 * @author ofernpast
 */
public class Inserciones {
    public static void main(String[] args) {
        OperacionesHB opHB = new OperacionesHB();
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
        
        opHB.liberarRecursos();
        System.exit(0);
    }
}
