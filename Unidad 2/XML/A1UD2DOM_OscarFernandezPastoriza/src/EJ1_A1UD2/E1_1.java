package EJ1_A1UD2;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilderFactory;
import comunes.ComunesActor;
import static comunes.ComunesDOM.generarDoc;

public class E1_1 {
    private static final String DOCUMENTO_ACTORES = "src/comunes/Actores.xml";

    public static void main(String[] args) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        // 1) Visualiza si el analizador puede o no validar documentos XML. Si no valida, haz que pueda validar documentos.
        System.out.println("¿El analizador puede validar documentos XML? " + dbf.isValidating());
        dbf.setValidating(true);
        System.out.println("Ahora el analizador puede validar documentos: " + dbf.isValidating());

        // 2) Visualiza si el analizador tiene establecido soporte para espacios de nombres.  En caso negativo, establece soporte para espacios de nombres.
        System.out.println("¿El analizador tiene establecido soporte para espacios de nombres?" + dbf.isNamespaceAware());
        dbf.setNamespaceAware(true);
        System.out.println("Ahora el analizador puede validar documentos: " + dbf.isNamespaceAware());

        //3) Visualiza si el analizador ignorara los comentarios.- En caso positivo, establece que se ignoren los comentarios.
        System.out.println("¿El analizador ignorará los comentarios?" + dbf.isIgnoringComments());
        dbf.setIgnoringComments(true);
        System.out.println("Ahora el analizador ignorará los comentarios: " + dbf.isIgnoringComments());

        // Cargamos el documento
        Document documento = generarDoc(dbf, DOCUMENTO_ACTORES);

        // 4) Visualiza la codificación utilizada del documento XML
        System.out.println("La codificación utilizada en el documento XML es: " + documento.getXmlEncoding());

        // 5) Visualiza si tiene o no un DTD asociado. En el caso de que si tenga un DTD asociado, visualiza el nombre del DTD.
        DocumentType documentType = documento.getDoctype();
        if (documentType != null) {
            System.out.println("El nombre del DTD asociado es: " + documentType.getName());
            System.out.println("El nombre físico del DTD es: " + documentType.getSystemId());
        } else {
            System.out.println("No hay DTD asociado");
        }


        // 6) Visualiza el nombre del elemento raíz, el valor de este nodo y el número de hijos.
        Node raiz = documento.getDocumentElement();
        System.out.println("Del elemento raíz:" +
                "\nNombre: " + raiz.getNodeName() +
                "\nValor: " + raiz.getNodeValue() +
                "\nNúmero de hijos: " + raiz.getChildNodes().getLength());

        // 7) Visualiza el texto contenido en el elemento raíz y de todos sus descendientes:
        System.out.println(raiz.getTextContent());

        // 8) Ejecuta las siguientes instrucciones:
        Node nodo=raiz.getFirstChild().getNextSibling();
        System.out.println("Nobre nodo:"+nodo.getNodeName()+ " Valor del nodo:"+nodo.getNodeValue());

        // 9) A partir del nodo situado en el ejercicio anterior, visualiza la siguiente información:
        Node primerActor = raiz.getFirstChild();
        visualizarInfoActor(primerActor);

        // 10) A partir del nodo anterior, obtén la misma información del nodo hermano de Actor que está situado dos posiciones hacia la derecha (nodo con id=”100”)
        Node tercerActor = primerActor.getNextSibling().getNextSibling();
        visualizarInfoActor(tercerActor);

        // 11) A partir del nodo anterior, obtén información del nodo hermano izquierdo que está contiguo.
        Node segundoActor = tercerActor.getPreviousSibling();
        visualizarInfoActor(segundoActor);

        // 12) Desde el objeto Document, visualiza los nombres de los nodos y sus valores de toda la estructura de
        //árbol DOM creada. Para ello crea un méthod recursivo
        System.out.println(ComunesActor.obtenerArbolDOM(raiz));
    }

    private static void visualizarInfoActor(Node actor) {
        Node atributo = actor.getAttributes().getNamedItem("id");
        System.out.println("<" + atributo.getNodeName() + ">:" + atributo.getNodeValue());
        NodeList actorHijos = actor.getChildNodes();
        for (int j = 0; j < actorHijos.getLength(); j++) {
            Node hijoActor = actorHijos.item(j);
            System.out.println("<" + hijoActor.getNodeName() + ">: " + hijoActor.getTextContent());
        }
    }
}