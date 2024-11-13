package fich24.oscarfp;

import java.util.Comparator;

public class EdadComparator implements Comparator<Cocinero> {
    @Override public int compare(Cocinero c1, Cocinero c2) {
        return c1.calcularEdad() - c2.calcularEdad();
    }
}
