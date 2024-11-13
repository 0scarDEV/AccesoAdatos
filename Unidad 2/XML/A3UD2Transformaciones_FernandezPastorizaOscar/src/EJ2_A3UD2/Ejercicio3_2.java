package EJ2_A3UD2;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.FileWriter;
import java.io.IOException;

public class Ejercicio3_2 {
    public static void main(String[] args) {
        //fichero que contiene las hojas de estilos
        String xsl = "src/EJ2_A3UD2/Hardware.xsl";
        //fichero que contiene el documento XML
        String hardware = "src/EJ2_A3UD2/Hardware.xml";
        //fichero nuevo
        String nuevoHardware = "src/EJ2_A3UD2/NuevoHardware.xml";

        TransformerFactory transFact;
        try {
            //Se instancia el
            transFact = TransformerFactory.newInstance();
            Transformer trans;
            try {
                //La interfaz Source se utiliza tanto para leer el docuemnto XML como el fichero que contiene las hojas de estilo. Se utiliza un StreamSource , que tiene la capacidad de leer de un Objeto File, un InputStream, un reader o un ID de sistema.
                StreamSource estilos = new StreamSource(xsl);
                StreamSource datos = new StreamSource(hardware);
                // El TransformerFactory es responsable de crear el transformador aplicado la plantilla objetos.
                trans = transFact.newTransformer(estilos);
                // Creamos fichero para escribir en modo texto
                FileWriter sw = new FileWriter(nuevoHardware);
                // escribimos el arbol en el fichero
                StreamResult sr = new StreamResult(sw);

                trans.transform(datos, sr);
            } catch (TransformerConfigurationException ex) {
                System.out.println("ERROR al construir el motor de transformación ");
            } catch (IOException | TransformerException e) {
                throw new RuntimeException(e);
            }
        } catch (TransformerFactoryConfigurationError e) {
            System.out.println("ERROR a la hora de implementar la transformación");
        }
    }
}
