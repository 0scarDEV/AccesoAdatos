package ejercicios;

import clasesdatos.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/* Orientación:
    Mira en los anexos los diferentes interfaces de Map (SortedMap y TreeMap) y como mantener la ordenación de un arrayList cuando se añade elementos (ordenación binaria)
*/
public class Ejercicio1 {
    public static void main(String[] args) {
        Document document;

        // a) A partir del anterior documento, crea un árbol DOM con validación por esquema. El ID del libro es un atributo xs:ID , el nombre de la sección y el ISBN son únicos.
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setValidating(true);
            dbf.setNamespaceAware(true);
            dbf.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaLanguage", "http://www.w3.org/2001/XMLSchema");
            dbf.setIgnoringElementContentWhitespace(true);

            document = dbf.newDocumentBuilder().parse("BibliotecaInformatica.xml");
        } catch (SAXException | ParserConfigurationException | IOException e) {
            throw new RuntimeException(e);
        }

        // b) Crea un metodo que se le pase una sección y devuelva una lista de los libros que están en dicha         sección. Si no existe dar un mensaje al respecto. Crea un metodo para visualizar los libros.
        ArrayList<Libro> listaLibros = getLibrosPorSeccion(document, "Programación");
        visualizarLibros(listaLibros);

        // c) Crea un metodo que visualice de forma ordenada de mayor a menor, el número de libros que hay por cada sección. Para los que coincidan en el número de libros, de menor a mayor por el nombre sección, En este metodo habrá una llamada a un metodo que se le pase el nombre de la sección y devuelva el número de libros de dicha sección.
        visualizacionPersonalizada(document);

        // d) Hacer un metodo que reciba un ISBN de un libro, un numero de copia y un estado y permita modificar el estado de la copia.
        modifyCopy(document, "666-1234567890", 2, "Disponible");

        // e) Haz un metodo que reciba la información completa de un libro y una sección y añada este libro a la sección. Si la sección no existe se crea y se añade el libro.
        Libro libro = new Libro("A100", "666-1234567890", "Libro de prueba", 100, new ArrayList<>(), LocalDate.now(), "Editorial de prueba", 10.0, new ArrayList<>());

        addLibroEnSeccion(document, libro, "Programación");
    }

    private static void addLibroEnSeccion(Document document, Libro libro, String section) {
        Element seccion = (Element) document.getElementsByTagName(section).item(0);
        if (seccion == null) {
            Element nuevaSeccion = document.createElement("seccion");
            nuevaSeccion.setAttribute("nombre", section);

            nuevaSeccion.appendChild(document.createElement("libros"));
            document.getDocumentElement().appendChild(nuevaSeccion);
            seccion = nuevaSeccion;
        }

        seccion.getElementsByTagName("libros").item(0).appendChild(libro.toElement(document));
    }

    private static void modifyCopy(Document document, String isbn, int numCopia, String estado) {
        Scanner scanner = new Scanner(System.in);
        XPath xpath = XPathFactory.newInstance().newXPath();
        try {
            XPathExpression expresion = xpath.compile("//libro[@isbn=\"" + isbn + "\"]/copias/copia[@numero=\""+ numCopia +"\"]");
            Element elemenCopia = (Element) ((NodeList) expresion.evaluate(document, javax.xml.xpath.XPathConstants.NODESET)).item(0);

            String estadoActual = String.valueOf(elemenCopia.getAttributeNode("estado"));

            System.out.println("La copia " + numCopia + " del libro con ISBN " + isbn + " se encuentra " + estadoActual + ". ¿Desea cambiarlo a " + estado + "? Escriba 'S' para confirmar.");
            if (scanner.next().equals("S")) {
                System.out.println("Cambiando estado de la copia...");
                elemenCopia.setAttribute("estado", estado);
            }

            elemenCopia.setAttribute("estado", estado);
        } catch (XPathExpressionException e) {
            throw new RuntimeException(e);
        }
    }

    private static void visualizacionPersonalizada(Document document) {
        ArrayList<MiniSeccion> seccionesLibros = new ArrayList<>();
        ArrayList<String> secciones = getAllSectionsName(document);
        for (String nombreSeccion : secciones) {
            seccionesLibros.add(new MiniSeccion(nombreSeccion, getNumLibros(document, nombreSeccion)));
        }
        seccionesLibros.sort((o1, o2) -> {
            if (o1.getNumLibros() == o2.getNumLibros()) {
                return o1.getNombre().compareTo(o2.getNombre());
            } else {
                return o2.getNumLibros() - o1.getNumLibros();
            }
        });

        System.out.println(seccionesLibros);
    }

    private static ArrayList<String> getAllSectionsName(Document document) {
        ArrayList<String> nombres = new ArrayList<>();

        NodeList listaSecciones = document.getElementsByTagName("seccion");
        for (int i = 0; i < listaSecciones.getLength(); i++) {
            Element element = (Element) listaSecciones.item(i);
            nombres.add(element.getAttribute("nombre"));
        }

        return nombres;
    }

    private static int getNumLibros(Document document, String nombreSeccion) {
        Element elementRaiz = getSectionElement(document, nombreSeccion);
        NodeList nodosLibros = elementRaiz.getElementsByTagName("libro");

        return nodosLibros.getLength();
    }

    private static void visualizarLibros(ArrayList<Libro> listaLibros) {
        for (Libro libro : listaLibros) {
            System.out.println(libro.toString());
        }
    }

    private static ArrayList<Libro> getLibrosPorSeccion(Document document,  String nombreSeccion) {
        return getLibrosPorSeccion(getSeccion(document, nombreSeccion));
    }

    private static ArrayList<Libro> getLibrosPorSeccion(Seccion seccion) {
        return (ArrayList<Libro>) seccion.getLibros();
    }

    private static Seccion getSeccion(Document document, String nombre) {
        Element elementRaiz = getSectionElement(document, nombre);

        NodeList nodosLibros = elementRaiz.getElementsByTagName("libro");
        ArrayList<Libro> listaLibros = new ArrayList<>();
        for (int i = 0; i < nodosLibros.getLength(); i++) {
            Element elementLibro = (Element) nodosLibros.item(i);

            // Atributos generales
            String ID = elementLibro.getAttributeNode("ID").getTextContent();
            String isbn = elementLibro.getAttributeNode("isbn").getTextContent();
            String titulo = elementLibro.getAttributeNode("titulo").getTextContent();
            int numero_de_paginas = Integer.parseInt(elementLibro.getAttributeNode("numero_de_paginas").getTextContent());
            String editorial = elementLibro.getElementsByTagName("editorial").item(0).getTextContent();
            double precio = Double.parseDouble(elementLibro.getElementsByTagName("precio").item(0).getTextContent());

            // Autores
            ArrayList<String> autores = new ArrayList<>();
            NodeList nodosAutores = elementLibro.getElementsByTagName("autor");
            for (int j = 0; j < nodosAutores.getLength(); j++) {
                autores.add(nodosAutores.item(j).getTextContent());
            }

            // Fecha de edición
            String fechaEdicion = elementLibro.getElementsByTagName("fechaEdicion").item(0).getTextContent();
            LocalDate fechaEdicionLocalDate = LocalDate.parse(fechaEdicion, DateTimeFormatter.ofPattern("dd-MM-yyyy"));

            // Copias
            ArrayList<Copia> copias = new ArrayList<>();
            NodeList nodosCopias = elementLibro.getElementsByTagName("copia");
            for (int j = 0; j < nodosCopias.getLength(); j++) {
                Element elementCopia = (Element) nodosCopias.item(j);
                int numero = Integer.parseInt(elementCopia.getAttributeNode("numero").getTextContent());
                String estado = elementCopia.getAttributeNode("estado").getTextContent();
                copias.add(new Copia(numero, estado));
            }

            // Nuevo libro
            listaLibros.add(new Libro(ID, isbn, titulo, numero_de_paginas, autores, fechaEdicionLocalDate, editorial, precio, copias));
        }

        return new Seccion(nombre, listaLibros);
    }

    private static Element getSectionElement(Document document, String nombre) {
        Element elementRaiz = null;
        NodeList listaSecciones = document.getElementsByTagName("seccion");
        for (int i = 0; i < listaSecciones.getLength(); i++) {
            Element element = (Element) listaSecciones.item(i);
            if (element.getAttribute("nombre").equals(nombre)) {
                elementRaiz = element;
            }
        }
        if (elementRaiz == null) {
            throw new RuntimeException("Sección no encontrada");
        }
        return elementRaiz;
    }
}