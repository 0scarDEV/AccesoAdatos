/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejerciciosBasicos;

import utiles.OperacionesHB;

/**
 *
 * @author ofernpast
 */
public class Lecturas {
    public static void main(String[] args) {
        OperacionesHB opHb = new OperacionesHB();
        //System.out.println(opHb.getEmpregado("87654321A"));
        //opHb.loadDepartamento(5);
        opHb.showEmpregado(opHb.getSession(), "87654321B");
        //opHb.loadDepartamento(8);
        opHb.liberarRecursos();
        System.exit(0);
    }
}
