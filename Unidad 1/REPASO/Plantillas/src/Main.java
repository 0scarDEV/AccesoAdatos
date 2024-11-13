import java.io.*;
import java.util.ArrayList;

public class Main {
    private StringBuilder leerArchivoTXT(String ruta) {
        File file = new File(ruta);
        StringBuilder str = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            while (in.ready()) {
                str.append(in.readLine()).append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return str;
    }

    private boolean escribirArchivoTXT(String ruta, String contenido) {
        File file = new File(ruta);
        try (BufferedWriter out = new BufferedWriter(new FileWriter(file, true))) {
            out.write(contenido);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private ArrayList<Object> leerArchivoBIN(String ruta) {
        File file = new File(ruta);
        ArrayList<Object> objetos = new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            while (true) {
                objetos.add(in.readObject());
            }
        } catch (EOFException e) {
            System.out.println("Fin del archivo.");
            return objetos;
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static void escribirArchivoBIN(File file, ArrayList<Object> objects) {
        if (file.exists()) {
            try (ObjectOutputStream appendableObjectOutputStream = new AppendableObjectOutputStream(new FileOutputStream(file, true))) {
                for (Object o : objects) {
                    appendableObjectOutputStream.writeObject(o);
                }
            } catch (Exception e) {
                System.out.println("Error al escribir el objeto.");
            }
        } else {
            try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file))) {
                for (Object o : objects) {
                    objectOutputStream.writeObject(o);
                }
            } catch (Exception e) {
                System.out.println("Error al escribir el objeto.");
            }
        }
    }

    private static void leerRAF(File fichero, double longitud_registro) {
        try (RandomAccessFile raf = new RandomAccessFile(fichero, "r")) {
            long file_size = fichero.length();
            int numero_registros = (int) Math.ceil(file_size / longitud_registro);

            for (int i = 0; i < numero_registros; i++) {
                // Te pones al principio de la posición de memoria.
                raf.seek((long) Math.ceil(i * longitud_registro));

                String campo1 = raf.readUTF();
                // TODO: Seguir leyendo todos los datos (el mismo orden)

                // TODO: Guardar de alguna manera los datos obtenidos en memoria, o devolverlos.
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void escribirRAF(Object object, int idObject) {
        try (RandomAccessFile raf = new RandomAccessFile(FICHERO_DATOS, "rw")) {
            raf.setLength((long) (idObject * LONGITUD_REGISTRO_ALUMNO));           // Aumentas la longitud del archivo para que quepa el registro
            raf.seek((long) Math.ceil((idObject - 1) * LONGITUD_REGISTRO_ALUMNO)); // Calculas la posición en la que empezarás a escribir tu archivo

            raf.writeUTF(object.getDato1());
            // TODO: Seguir escribiendo todos los datos (el mismo orden)
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
}