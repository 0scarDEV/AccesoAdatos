package EJ1_A2UD2;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;

public class EJ1_A2UD2 {
    final static String RUTA_ARCHIVO_ACTOR = "Actores.xml";
    final static String RUTA_ARCHIVO_TXT = "Actores.txt";

    public static void main(String[] args) {
        String val="http://xml.org/sax/features/validation";
        String namespaces="http://xml.org/sax/features/namespaces";
        String esquemas="http://apache.org/xml/features/validation/schema";
        // A.- Realizar un programa Java que construya un analizador SAX para ir recorriendo el anterior documento y comprueba lo siguiente:
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setNamespaceAware(true);
        SAXParser saxParser;
        XMLReader procesador;

        try {
            saxParser = factory.newSAXParser();
            procesador = saxParser.getXMLReader();
            procesador.setFeature(esquemas, true);

            // 1) Visualiza si el analizador puede o no validar documentos XML. Si no valida, haz que pueda validar documentos mediante un DTD.
            if (procesador.getFeature(val))
                System.out.println("Esta activada la validación");
            else{
                System.out.println("NO Esta activada la validación. Se va a establecer");
                procesador.setFeature(val,true);
            }
            // 2) Visualiza si el analizador tiene establecido soporte para espacios de nombres. En caso negativo, establece soporte para espacios de nombres.
            if (procesador.getFeature(namespaces))
                System.out.println("Soporta espacios de nombres");
            else{
                System.out.println("NO soporta espacios de nombres. Se van a establecer");
                procesador.setFeature(namespaces,true);
            }
        } catch (ParserConfigurationException | SAXException e) {
            throw new RuntimeException(e);
        }

        // B.-Crea un DTD llamado actores.dtd asociado al documento XML actores.xml para su validación. El atributo id es obligatorio y en cada etiqueta actor debe aparecer sus etiquetas contenidas en este orden: Nome, Sexo, DataNacemento.

        // C.-Visualiza la información del documento XML por pantalla y también guárdala en un fichero de texto, con el siguiente formato: Al final se debe visualizar el número total de actores.
        /* run:
            NO Esta activada la validación. Se va a establecer
            Soporta espacios de nombres
            Información de actores
            ==========================
            Actor 1
            **********
            id:51
            Nome:Eliabeth Shue
            Sexo:muller
            DataNacemento: 06/10/1963
            --------------------------------
            Actor 2
            **********
            id:139
            Nome:Jhonny Depp
            Sexo:home
            DataNacemento:09/06/1963
            --------------------------------
            Actor 3
            **********
            id:100
            Nome:Harrison Ford
            Sexo:home
            DataNacemento:13/07/1943
            --------------------------------
            Total de actores:3
         */


        Manejador manejador = new Manejador();
        procesador.setContentHandler(manejador);
        try {
            procesador.parse(new InputSource(new FileInputStream(RUTA_ARCHIVO_ACTOR)));
        } catch (IOException | SAXException e) {
            throw new RuntimeException(e);
        }

        StringBuilder descDoc = manejador.getStringBuilder();
        System.out.println(descDoc);
        if (escribirArchivoTXT(RUTA_ARCHIVO_TXT, descDoc)) {
            System.out.println("Archivo txt generado correctamente.");
        }

        // D.- En el fichero Actores.xml introduce 2 nuevos actores y vuelve a ejecutar tu programa para comprobar que se ejecuta correctamente.
        /*
            <Actor id="101">
                <Nome>Jhonny Depp</Nome>
                <Sexo>home</Sexo>
                <DataNacemento formato="dd/mm/aaaa">11/11/1111</DataNacemento>
            </Actor>
         */

        // E.- Comprueba si valida correctamente el documento xml a través del DTD, por ejemplo, prueba a eliminar el atributo id de un actor, o poner las etiquetas en diferente orden.
            // Al cambiar un atributo de orden, salta una excepción.

        // F.-Modifica el anterior programa para que la validación se realice mediante un esquema. Comprueba que valida correctamente.
            // Se valida correctamente
    }

    private static boolean escribirArchivoTXT(String ruta, StringBuilder contenido) {
            File file = new File(ruta);
            try ( BufferedWriter out = new BufferedWriter(new FileWriter(file, true))) {
                out.write(String.valueOf(contenido));
                return true;
            } catch (IOException e) {
                return false;
            }
        }
}