package ejercicios;

import utiles.OperacionesHB;

public class Modificaciones {
    public static void main(String[] args) {
        OperacionesHB opHb = new OperacionesHB();
        System.out.println(opHb.modificarSalarioEmpleado("12345678B", 2000));
        opHb.liberarRecursos();
        System.exit(0);
    }
}
