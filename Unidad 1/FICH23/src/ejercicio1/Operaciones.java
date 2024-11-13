// Xabier Pastoriza Rodriguesz 53860349f
package ejercicio1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Operaciones {
    static EscribirFichero ef = new EscribirFichero("Pacientes.dat");
    static LeerFichero lf = new LeerFichero("Pacientes.dat");
    static LeerTxt ltxt = new LeerTxt("DatosPacientes.txt");
    static EscribirTxt etxtLog = new EscribirTxt("DatosRegistros.txt");

    public static void addPaciente(Paciente p) {
        lf.abrirArchivo();

        // Comprobar si el dni del paciente es existente.
        if (lf.dniExistente(p.getDatosPaciente().getDni())) {
            System.out.println("El dni del paciente ya existe.");
            lf.cerrarArchivo();
            return;
        }

        lf.abrirArchivo();
        p.setNumPaciente(lf.ultimoDorsal() + 1);
        p.getDatosPaciente().setNumHistoria(p.getNumPaciente() + p.getDatosPaciente().getNombre());
        lf.cerrarArchivo();
        ef.abrirArchivo();
        ef.add(p);
        ef.cerrarArchivo();

        System.out.println("Se ha creado el paciente:");
        addLog(p);
        System.out.println(p.toString());
    }

    public static void addTxt() throws ParseException {

        ltxt.abrirArchivo();
        int numPacientes = ltxt.contarLineas();
        ltxt.cerrarArchivo();

        ltxt.abrirArchivo();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        for (int i = 0; i < numPacientes; i++) {
            String lineaPaciente = ltxt.leerLinea();
            String[] datosPaciente = lineaPaciente.split(";");

            String dni = datosPaciente[0];
            String nombre = datosPaciente[1];
            String apellido1 = datosPaciente[2];
            String apellido2 = datosPaciente[3];
            Date fnac = sdf.parse(datosPaciente[4]);

            ArrayList<String> telefonos = new ArrayList<>();
            if (datosPaciente.length > 5) {
                for (int j = 5; j < datosPaciente.length; j++) {
                    telefonos.add(datosPaciente[i]);
                }
            }

            Datos datos = new Datos(dni, nombre, apellido1, apellido2, fnac);
            Paciente p = new Paciente(datos, telefonos);

            ef.abrirArchivo();
            ef.add(p);
            ef.cerrarArchivo();

        }
        ltxt.cerrarArchivo();

    }

    private static void addLog(Paciente p) {
        String paciente = p.getDatosPaciente().getDni() + "           " + p.getNumPaciente() + "           "
                + p.getDatosPaciente().getNombre() + "           " + p.getDatosPaciente().getApellido1() + "           "
                + p.getDatosPaciente().getApellido2() + "           " + p.getDatosPaciente().getNumHistoria()
                + "           " + p.getDatosPaciente().getFechaAlta();

        etxtLog.abrirArchivo();
        etxtLog.escribir(paciente);
        etxtLog.cerrarArchivo();
    }

}
