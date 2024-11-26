package javalib;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

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

public class OperacionesDOM {
    private Document doc;

    public OperacionesDOM() {}

    public Document getDocument() {
        return doc;
    }

    public OperacionesDOM(File ficheroXML) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        this.doc = generarDoc(dbf, ficheroXML);
    }

    public OperacionesDOM(String nombreRaiz) {
        this.doc = getNuevoDocumento(nombreRaiz);
    }

    /** Devuelve un documento DOM a partir de una ruta .xml */
    public Document generarDoc(DocumentBuilderFactory dbf, String xmlDocument) {
        return generarDoc(dbf, new File(xmlDocument));
    }

    /** Devuelve un documento DOM a partir de un fichero .xml */
    public Document generarDoc(DocumentBuilderFactory dbf, File fichXML) {
        dbf.setIgnoringElementContentWhitespace(true);
        Document documento;
        try {
            DocumentBuilder constructor = dbf.newDocumentBuilder();
            documento = constructor.parse(fichXML);
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException(e);
        }
        return documento;
    }

    /** Devuelve un nuevo documento DOM vacío */
    public Document getNuevoDocumento(String nombreRaiz) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db;
        try {
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }

        DOMImplementation implementacion=db.getDOMImplementation();

        return implementacion.createDocument(null, nombreRaiz, null);
    }

    /** Guarda el Document en un nuevo fichero .xml creado  */
    public static void grabarNuevoArbol(Document documento, String rutaNewXML) {
        grabarNuevoArbol(documento.getDocumentElement(), rutaNewXML);
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

    public void addAtributoToRoot(String atributeName, String atributeValue) {
        addAtributoToElemento(doc.getDocumentElement(), atributeName, atributeValue);
    }

    /** Crea un nuevo elemento y añade al final de la estructura DOM */
    public Element appendNewElemento(String nodeName) {
        Element nuevoElemento;
        if (doc.getDocumentElement() == null) {
            nuevoElemento = (Element) doc.appendChild(getNewElemento(nodeName));
        } else {
            nuevoElemento = (Element) doc.getDocumentElement().appendChild(getNewElemento(nodeName));
        }

        return nuevoElemento;
    }

    /** Crea un nuevo elemento y añade al final de la estructura DOM */
    public Element appendNewElemento(String nodeName, String nodeValue) {
        Element nuevoElemento;
        if (doc.getDocumentElement() == null) {
            nuevoElemento = (Element) doc.appendChild(getNewElemento(nodeName, nodeValue));
        } else {
            nuevoElemento = (Element) doc.getDocumentElement().appendChild(getNewElemento(nodeName));
        }

        return nuevoElemento;
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

    /** Devuelve un atributo de un elemento, buscado por nombre */
    public Attr getAtributoPorNombre(Element element, String attributeName) {
        return element.getAttributeNode(attributeName);
    }

    /** Crear un ID a todos los nodos hijos de un raiz */
    public void crearID(Node raiz) {
        NodeList nodos = raiz.getChildNodes();
        for (int i = 0; i < nodos.getLength(); i++) {
            Node nodo = nodos.item(i);
            int ultID;

            if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) nodo;
                // Creamos el atributo
                Attr atributoID = raiz.getOwnerDocument().createAttribute("ID");

                // Obtenemos el valor anterior si lo hay, e incrementamos.
                Node hermanoAnterior = nodo.getPreviousSibling();
                if (hermanoAnterior != null && hermanoAnterior.hasAttributes()) {
                    ultID = Integer.parseInt(hermanoAnterior.getAttributes().getNamedItem("ID").getNodeValue());
                    int idIncrementado = ultID + 1;
                    atributoID.setNodeValue(Integer.toString(idIncrementado));
                } else {
                    atributoID.setNodeValue(Integer.toString(1));
                }

                // Le asignamos al nodo ese atributo
                element.setAttributeNode(atributoID);
            }
        }
    }

    public void appendAtributos(Element element, StringBuilder output) {
        NamedNodeMap atributos = element.getAttributes();
        for (int i = 0, len = atributos.getLength(); i < len; i++) {
            Node atributo = atributos.item(i);
            output.append(" ").append(atributo.getNodeName()).append(" ").append(atributo.getNodeValue());
        }
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
}
