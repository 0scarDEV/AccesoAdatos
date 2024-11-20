package javalib;

import org.xml.sax.InputSource;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.transform.*;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.FileWriter;
import java.io.IOException;

public class OperacionesTransformaciones {
    public static void transformXML(String rutaXML, String rutaXSL, String rutaNewFile) {
        TransformerFactory transFact;
        try {
            //Se instancia el
            transFact = TransformerFactory.newInstance();
            Transformer trans;
            try {
                //La interfaz Source se utiliza tanto para leer el docuemnto XML como el fichero que contiene las hojas de estilo. Se utiliza un StreamSource , que tiene la capacidad de leer de un Objeto File, un InputStream, un reader o un ID de sistema.
                StreamSource estilos = new StreamSource(rutaXSL);
                StreamSource datos = new StreamSource(rutaXML);
                // El TransformerFactory es responsable de crear el transformador aplicado la plantilla objetos.
                trans = transFact.newTransformer(estilos);
                // Creamos fichero para escribir en modo texto
                FileWriter sw = new FileWriter(rutaNewFile);
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

    /** Escribe un nuevo fichero (rutaNewFile) a partir de un XML (rutaXML) siguiendo las indicaciones del XSL (rutaXSL) */
    public static void transformBySAX(String rutaXML, String rutaXSL, String rutaNewFile) {
        TransformerFactory transFact;
        transFact = TransformerFactory.newInstance();

        try {
            Transformer trans;
            SAXSource sourceXsl = new SAXSource(new InputSource(rutaXSL));
            SAXSource sourceAlumnos = new SAXSource(new InputSource(rutaXML));
            trans = transFact.newTransformer(sourceXsl);

            StreamResult sr = new StreamResult();
            sr.setWriter(new FileWriter(rutaNewFile));
            trans.transform(sourceAlumnos, sr);
        } catch (TransformerFactoryConfigurationError e) {
            System.out.println("ERROR a la hora de implementar la transformación");
        } catch (TransformerException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    /** Parsea un XML con un manejador, que debería mostrar por pantalla */
    public static void transformByHandler(String rutaXML, DefaultHandler manejador) {
        TransformerFactory transFact;
        transFact = TransformerFactory.newInstance();

        try {
            Transformer transformer = transFact.newTransformer();
            SAXSource sourceNotas = new SAXSource(new InputSource(rutaXML));
            SAXResult result = new SAXResult(manejador);
            transformer.transform(sourceNotas, result);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }
}
