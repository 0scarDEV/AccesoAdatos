package EJ1_A3UD2;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author ofernpast
 */
public class Ejercicio3_1 {
    public static void main(String[] args) {
        //fichero que contiene las hojas de estilos
        String hojaEstilos = "src/EJ1_A3UD2/Actores.xsl";
        //fichero que contiene el documento XML
        String datosActores = "src/EJ1_A3UD2/Actores.xml";
        //fichero HTML
        String paginaHTML = "src/EJ1_A3UD2/Actores.html";

        TransformerFactory transFact;
        try {
            //Se instancia el
            transFact = TransformerFactory.newInstance();
            Transformer trans;
            try {
                //La interfaz Source se utiliza tanto para leer el docuemnto XML como el fichero que contiene las hojas de estilo. Se utiliza un StreamSource , que tiene la capacidad de leer de un Objeto File, un InputStream, un reader o un ID de sistema.
                StreamSource estilos = new StreamSource(hojaEstilos);
                StreamSource datos = new StreamSource(datosActores);
                // El TransformerFactory es responsable de crear el transformador aplicado la plantilla objetos.
                trans = transFact.newTransformer(estilos);
                // Creamos fichero para escribir en modo texto
                FileWriter sw = new FileWriter(paginaHTML);
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