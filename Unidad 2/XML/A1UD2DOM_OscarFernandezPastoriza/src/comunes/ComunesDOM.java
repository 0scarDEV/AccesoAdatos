package comunes;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ComunesDOM {
    public static Document generarDoc(DocumentBuilderFactory dbf, String xmlDocument) {
        dbf.setIgnoringElementContentWhitespace(true);
        Document documento;
        try {
            DocumentBuilder constructor = dbf.newDocumentBuilder();
            documento = constructor.parse(new File(xmlDocument));
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException(e);
        }
        return documento;
    }

    public static void grabarNuevoArbol(Node raiz, String rutaNewXML) {
        TransformerFactory transFact;
        try{
            transFact = TransformerFactory.newInstance();
            Transformer trans = transFact.newTransformer();
            trans.setOutputProperty(OutputKeys.INDENT, "yes");
            trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            trans.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

            try {
                Source src = new DOMSource(raiz);
                Result rst = new StreamResult(new FileOutputStream(rutaNewXML));
                trans.transform(src, rst);
            } catch (TransformerException | FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        } catch (TransformerFactoryConfigurationError | TransformerConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    public static Document getNewDocument(String nameForRoot) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db;
        try {
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }

        DOMImplementation implementacion=db.getDOMImplementation();

        return implementacion.createDocument(null, nameForRoot, null);
    }
}