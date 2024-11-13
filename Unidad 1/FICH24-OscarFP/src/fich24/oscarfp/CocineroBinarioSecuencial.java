package fich24.oscarfp;

import java.io.*;
import java.util.ArrayList;

/**
 * @author ofernpast
 * Óscar Fernández Pastoriza - 53862191D
 */

public class CocineroBinarioSecuencial {
    final static String RUTA_FICHERO = "Cocineros.dat";

    public static boolean esApodoRepetido(File file, String apodo) {
        // Si el fichero no existe, entonces es que el apodo no está repetido, se sale.
        if (!file.exists()) {
            return false;
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            while (true) {
                Cocinero c = (Cocinero) in.readObject();
                if (c.getApodo().equals(apodo)) {
                    System.err.println("-------------------------------------\n" +
                            "ERROR. El apodo del cocinero, \"" + apodo + "\", al que intentas añadir ya se encuentra en el listado." +
                            "\n-------------------------------------");
                    return true;
                }
            }
        } catch (EOFException e) {
            return false;
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static int obtenerUltCodigo(File file) {
        // Si el fichero no existe, entonces se devuelve el número de cocineros.
        if (!file.exists()) {
            return 0;
        }

        // Por si no existieran datos, creamos un cocinero con nulos y le asignamos el código 0.
        Cocinero c = new Cocinero(null, null, null, null);
        c.setCodigo(0);

        // Si el fichero si existe, se recorre todos los cocineros y en el último se obtiene su código. Si no existiera ningún cocinero se devuelve cero.
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            while (true) {
                c = (Cocinero) in.readObject();
            }
        } catch (EOFException e) {
            return c.getCodigo();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void add(File file, Cocinero cocinero) {
        if (file.exists()) {
            // Si el fichero existe, añado el cocinero al final sin cabecera previa.
            try (ObjectOutputStream aout = new AppendableObjectOutputStream(new FileOutputStream(file, true))) {
                aout.writeObject(cocinero);
            } catch (Exception e) {
                System.out.println("Error al escribir el objeto.");
                throw new RuntimeException(e);
            }
        } else {
            // Si el fichero no existe, lo creo, le añado la cabecera y escribo el cocinero.
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
                out.writeObject(cocinero);
            } catch (Exception e) {
                System.out.println("Error al escribir el primer objeto.");
                throw new RuntimeException(e);
            }
        }
    }

    public static boolean comprobarSiExisteCocinero(File file, int numero) {
        // Si el fichero no existe, entonces es que el cocinero no existe.
        if (!file.exists()) {
            throw new IllegalArgumentException("El archivo no existe");
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            while (true) {
                Cocinero c = (Cocinero) in.readObject();
                if (c.getCodigo() == numero) {
                    return true;
                }
            }
        } catch (EOFException e) {
            System.err.println("-------------------------------------\n" +
                    "ERROR. El cocinero \"" + numero + "\" no está en el archivo, no se añadirá." +
                    "\n-------------------------------------");
            return false;
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<Cocinero> getCocineros(File ficheroCocineros) {
        ArrayList<Cocinero> lista = new ArrayList<>();
        for (int i = 1; i <= obtenerUltCodigo(ficheroCocineros); i++) {
            lista.add(getCocinero(i));
        }
        return lista;
    }

    public static Cocinero getCocinero(int codigo) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(RUTA_FICHERO))) {
            while (true) {
                Cocinero c = (Cocinero) in.readObject();
                if (c.getCodigo() == codigo) {
                    return c;
                }
            }
        } catch (EOFException e) {
            // Fin de fichero
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
