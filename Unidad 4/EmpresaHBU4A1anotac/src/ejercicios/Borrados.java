/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicios;

import utiles.OperacionesHB;

/**
 *
 * @author ofernpast
 */
public class Borrados {
    public static void main(String[] args) {
        OperacionesHB opHb = new OperacionesHB();
        opHb.removeEmpregado("12345678Z");
        opHb.removeDepartamento(2);
        opHb.liberarRecursos();
        System.exit(0);
    }
}
