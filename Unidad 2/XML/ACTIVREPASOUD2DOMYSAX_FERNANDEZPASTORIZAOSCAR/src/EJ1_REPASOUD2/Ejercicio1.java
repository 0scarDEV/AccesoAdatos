package EJ1_REPASOUD2;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;

import static Comunes.ComunesDOM.generarDoc;
import static Comunes.ComunesDOM.grabarNuevoArbol;

public class Ejercicio1 {
    public static void main(String[] args) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setValidating(true);
        dbf.setNamespaceAware(true);
        Document documento = generarDoc(dbf, "Actores.xml");

        // a) Actualice el campo fecha de nacimiento. Los actores con las fechas a modificar provienen de un fichero de texto con el siguiente formato:
        modificarFechaNacimiento(documento);

        // b) Añade al documento el actor id 32 Michael Caine nacido el 14 de Marzo de 1933
        Actor actor = new Actor(32, "Michael Caine", "home", "14/03/1933");
        actor.appendToXML(documento);
        grabarNuevoArbol(documento, "Actores.xml");

        // c) Dado un id de actor nos visualice su información.
        visualizarActor(documento, 32);
    }

    private static void visualizarActor(Document documento, int id) {
        Element actor = documento.getElementById("A" + id);
        if (actor != null) {
            String nome = actor.getElementsByTagName("Nome").item(0).getTextContent();
            String sexo = actor.getElementsByTagName("Sexo").item(0).getTextContent();
            String dataNacemento = actor.getElementsByTagName("DataNacemento").item(0).getTextContent();
            System.out.println("Actor " + id + ": " + nome + " (" + sexo + ") nacido el " + dataNacemento);
        } else {
            System.err.println("No existe el actor con código A" + id);
        }
    }

    private static void modificarFechaNacimiento(Document documento) {
        File file = new File("ModificarFecha.txt");
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            while (in.ready()) {
                // Leer cod y fecha de nacimiento
                String[] linea = in.readLine().split(",");
                String cod = linea[0];
                String fecha = linea[1];

                // Modificar fecha de nacimiento
                Element actor = documento.getElementById(cod);
                if (actor != null) {
                    Node dataNacemento = actor.getElementsByTagName("DataNacemento").item(0);
                    dataNacemento.setNodeValue(fecha);
                } else {
                    // Se deberá visualizar un mensaje de error para un actor que no existe en el archivo xml. Se validará con DTD.
                    System.err.println("No existe el actor con código " + cod);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}