package EJ4_A4UD2;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Ejercicio4_4 {
    public static void main(String[] args) {
        Hoteles hoteles = new Hoteles();
        hoteles.setNombre("SOL CONFORT");
        hoteles.setCif("AB237237287");
        ArrayList<Hotel> hotelesList = new ArrayList<>();

        Hotel hotel1 = new Hotel(1, "Sancho", "34567654 45345322", new Direccion("Sol", 3, "98888"));
        hotelesList.add(hotel1);

        Hotel hotel2 = new Hotel(2, "Quijote", "68768668 67487335 24324242", new Direccion("Luna", 5, "55555"));
        hotelesList.add(hotel2);

        Hotel hotel3 = new Hotel(3, "Dulcinaea", null, new Direccion("Estrella", 6, "66666"));
        hotelesList.add(hotel3);

        hoteles.setHoteles(hotelesList);

        File file = new File("src/EJ4_A4UD2/hoteles.dat");
        escribirArchivoBIN(file, hoteles.getHoteles());

        try {
            Marshaller m = JAXBContext.newInstance(Hoteles.class).createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            m.marshal(hoteles, new File("src/EJ4_A4UD2/hoteles.xml"));
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    private static void escribirArchivoBIN(File file, ArrayList<Hotel> hoteles) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            for (Hotel o : hoteles) {
                objectOutputStream.writeObject(o);
            }
        } catch (Exception e) {
            System.out.println("Error al escribir el objeto.");
        }
    }
}
