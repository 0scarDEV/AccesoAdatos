package a4ud1_alumno;

import CLASESDATOS.Alumno;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.Date;

public class AnhosComparator implements Comparator<Alumno> {
    @Override public int compare(Alumno a1, Alumno a2) {
        Date o1 = a1.getFechaNac();
        Date o2 = a2.getFechaNac();

        LocalDate d1 = o1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate d2 = o2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        return d1.getYear() - d2.getYear();
    }
}
