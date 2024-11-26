package Comunes;

import org.w3c.dom.*;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/* Óscar Fernández Pastoriza - 53862191D */

public class OperacionesDOM {
    private Document doc;

    public OperacionesDOM(File ficheroXML) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setIgnoringElementContentWhitespace(true);
        doc = generarDoc(dbf, ficheroXML);
    }

    public boolean hayMedicionEnRioFecha(String codRio, String fecha) {
        Node nodoQueCoinciden = getNodosPorXPATH("/Programa/Rios/Rio[@codigo='" + codRio + "']/Medicion[@fecha = '" + fecha + "']"
        ).item(0);

        return nodoQueCoinciden != null;
    }

    public Element getRioPorCodigo(String codRio) {
        return (Element) getNodosPorXPATH("/Programa/Rios/Rio[@codigo='" + codRio + "']").item(0);
    }

    public NodeList getNodosPorXPATH(String xpression) {
        XPath xpath = XPathFactory.newInstance().newXPath();
        try {
            XPathExpression expresion = xpath.compile(xpression);
            return (NodeList) expresion.evaluate(doc, XPathConstants.NODESET);
        } catch (XPathExpressionException e) {
            throw new RuntimeException(e);
        }
    }

    /** Devuelve un documento DOM a partir de un fichero .xml */
    public Document generarDoc(DocumentBuilderFactory dbf, File fichXML) {
        // Hacemos que el documento que se genere se valide con DTD
        dbf.setValidating(true);

        // Creamos el documento y lo devolvemos
        Document documento;
        try {
            DocumentBuilder constructor = dbf.newDocumentBuilder();
            constructor.setErrorHandler(new ErrorHandler() {
                @Override
                public void warning(SAXParseException exception) {}

                @Override
                public void error(SAXParseException exception) {
                    System.err.println("Ha ocurrido un error, verifica el problema en el siguiente texto de error:");
                    exception.printStackTrace();
                }

                @Override
                public void fatalError(SAXParseException exception) {
                    System.err.println("Ha ocurrido un error, verifica el problema en el siguiente texto de error:");
                    exception.printStackTrace();
                    System.exit(1);
                }
            });
            documento = constructor.parse(fichXML);
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException(e);
        }
        return documento;
    }

    public Element getNewRio(String codigo, String nombreRio) {
        Element nuevoRio = getNewElemento("Rio");
        addAtributoToElemento(nuevoRio, "codigo", codigo);
        addAtributoToElemento(nuevoRio, "nombre", nombreRio);
        return nuevoRio;
    }

    /** Crea un nuevo elemento con un nodeValue de texto y lo devuelve */
    public Element getNewElemento(String nodeName, String nodeValue) {
        Element elemento = getNewElemento(nodeName);
        elemento.appendChild(doc.createTextNode(nodeValue));
        return elemento;
    }

    /** Crea un nuevo elemento y lo devuelve */
    public Element getNewElemento(String nodeName) {
        return doc.createElement(nodeName);
    }

    /** Añade un atributo al elemento existente */
    public boolean addAtributoToElemento(Element element, String attributeName, String attributeValue) {
        element.setAttribute(attributeName, attributeValue);
        return true;
    }

    public Element addElementoToElemento(Element elementoPadre, String elementName) {
        Element element = getNewElemento(elementName);
        elementoPadre.appendChild(element);
        return element;
    }

    public Element addElementoToElemento(Element elementoPadre, String elementName, String elementText) {
        Element element = getNewElemento(elementName, elementText);
        elementoPadre.appendChild(element);
        return element;
    }

    /** Guarda el Document en un nuevo fichero .xml creado  */
    public void grabarNuevoArbol(String rutaNewXML) {
        grabarNuevoArbol(doc.getDocumentElement(), rutaNewXML);
    }

    /** Guarda los elementos que cuelgan del raiz en un nuevo fichero .xml creado  */
    public static void grabarNuevoArbol(Node raiz, String rutaNewXML) {
        TransformerFactory transFact;
        try {
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

    public Element getMedicion(String codRio, String fechaFormateada) {
        return (Element) getNodosPorXPATH("/Programa/Rios/Rio[@codigo='" + codRio + "']/Medicion[@fecha = '" + fechaFormateada + "']").item(0);
    }

    public void eliminarDatosMedicion(Element medicionActual) {
        // Eliminar nodos hijos
        NodeList listaNodos = medicionActual.getChildNodes();
        for (int i = 0; i <= listaNodos.getLength(); i++) {
            medicionActual.removeChild(listaNodos.item(i));
        }
    }
}