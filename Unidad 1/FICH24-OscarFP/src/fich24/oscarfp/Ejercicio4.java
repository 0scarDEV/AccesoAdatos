package fich24.oscarfp;

import java.util.Scanner;

public class Ejercicio4 {
    public static void main(String[] args) {
        getOperacionBorrado(3);
    }

    private static void getOperacionBorrado(int codRestaurante) {
        Scanner sc = new Scanner(System.in);
        Restaurante restaurante = RestauranteBinarioAleatorio.get(Ejercicio2.RUTA_FICHERO_BIN, codRestaurante, Ejercicio2.LONGITUD_REGISTRO);
        System.out.println("Restaurante seleccionado:");
        System.out.println(restaurante);

        // Pedimos el cocinero
        System.out.print("Por favor, introduzca el identificador de uno de los cocineros: ");
        int codCocinero = sc.nextInt();
        sc.nextLine(); // Limpiar buffer

        // Preguntamos si desea borrarlo
        System.out.println(CocineroBinarioSecuencial.getCocinero(codCocinero));
        System.out.println("B: Borrar el cocinero de la lista. (Cualquier otra letra para anular la operación)");

        // Borramos de listaInfoCocineros
        char opcion = sc.nextLine().charAt(0);
        if (String.valueOf(opcion).equalsIgnoreCase("B")) {
            restaurante.borrarCocinero(codCocinero);
            RestauranteBinarioAleatorio.put(restaurante, Ejercicio2.RUTA_FICHERO_BIN, Ejercicio2.LONGITUD_REGISTRO, Ejercicio2.RUTA_FICHERO_COCINEROS);
        }
        System.out.println(restaurante);
    }
}
