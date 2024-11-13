package EJ2_A1UD2;

import comunes.ComunesActor;
import comunes.ComunesDOM;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;

public class E1_2 {
    final static String NOMBRE_ARCHIVO = "src/EJ2_A1UD2/actores.txt";
    private static final String DOCUMENTO_ACTORES = "src/comunes/Actores.xml";

    public static void main(String[] arg) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        // Cargamos el documento
        Document documento = ComunesDOM.generarDoc(dbf, DOCUMENTO_ACTORES);
        Node raiz = documento.getDocumentElement();

        // Escribimos el .txt
        try (OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(NOMBRE_ARCHIVO))) {
            out.write(String.valueOf(ComunesActor.obtenerArbolDOM(raiz)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}