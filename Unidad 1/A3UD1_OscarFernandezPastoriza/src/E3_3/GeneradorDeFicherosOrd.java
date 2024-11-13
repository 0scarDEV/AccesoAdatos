package E3_3;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class GeneradorDeFicherosOrd {
    public static void main(String[] args) {
        String ruta = "src/E3_3/";
        Random random = new Random();
        ArrayList<Integer> listaNumFile1 = new ArrayList<>();
        ArrayList<Integer> listaNumFile2 = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            listaNumFile1.add(random.nextInt(0, 10));
            listaNumFile2.add(random.nextInt(0, 10));
        }

        listaNumFile1.sort(Comparator.naturalOrder());
        listaNumFile2.sort(Comparator.naturalOrder());

        escribir(ruta+"Num1.dat", listaNumFile1);
        escribir(ruta+"Num2.dat", listaNumFile2);
    }

    private static void escribir(String ruta, ArrayList<Integer> listaEnteros) {
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream(ruta))) {
            for (Integer entero : listaEnteros) {
                out.writeInt(entero);
            }
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
