package EJ1_A4UD2;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

public class Ejercicio4_1 {
    public static void main(String[] args) {
        Empresa empresa = new Empresa();
        empresa.setCif("A58818501");
        empresa.setNombre("TECNOMUR S.L.");

        Empleado empleado1 = new Empleado();
        empleado1.setDni("12345678C");
        empleado1.setNombre("Carlos Pérez Ruiz");
        empleado1.setEdad(29);

        Empleado empleado2 = new Empleado();
        empleado2.setDni("87654321C");
        empleado2.setNombre("Claudia Ortiz Zaldo");
        empleado2.setEdad(31);

        Empleado empleado3 = new Empleado();
        empleado3.setDni("876543225T");
        empleado3.setNombre("Carlos Pérez Pérez");
        empleado3.setEdad(30);

        empresa.getEmpleados().add(empleado1);
        empresa.getEmpleados().add(empleado2);
        empresa.getEmpleados().add(empleado3);

        try {
            Marshaller m = JAXBContext.newInstance(Empresa.class).createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            m.marshal(empresa, new File("src/EJ1_A4UD2/empresa.xml"));
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
}