package a4ud1_alumno;

import CLASESDATOS.Alumno;

import java.util.Comparator;
import java.util.Date;

public class FechasComparator implements Comparator<Alumno> {
    @Override public int compare(Alumno a1, Alumno a2) {
        Date o1 = a1.getFechaNac();
        Date o2 = a2.getFechaNac();
        return o1.compareTo(o2);
    }
}
