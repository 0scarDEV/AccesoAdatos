package fich24.oscarfp;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class RestauranteBinarioAleatorio {
    public static void add(File ficheroRestaurantes, File ficheroCocineros, double longitudMaxRegistro, Restaurante restaurante) {
        // Comprobamos opciones de salida.

        // El registro es mayor de lo permitido
        int longitudTotalRegistro = getLongitudTotalRegistro(restaurante);
        if (longitudTotalRegistro > longitudMaxRegistro) {
            throw new IllegalArgumentException("Longitud del registro no valido");
        }

        // El restaurante ya existe.
        if (nombreRestauranteYaRegistrado(ficheroRestaurantes, longitudMaxRegistro, restaurante)) {
            throw new IllegalArgumentException("El nombre del restaurante ya existe");
        }

        // Le asignamos como número el número consecutivo al anterior.
        int numRegistros = (int) Math.ceil(ficheroRestaurantes.length() / longitudMaxRegistro);
        restaurante.setNumero(numRegistros + 1);

        // Empezamos a escribir
        escribirEnPosicion(restaurante, ficheroRestaurantes, longitudMaxRegistro, ficheroCocineros);
    }

    public static void put(Restaurante restaurante, String rutaRestaurantes, double longitudMaxRegistro, String rutaCocineros) {
        File ficheroRestaurantes = new File(rutaRestaurantes);
        File ficheroCocineros = new File(rutaCocineros);
        // El registro es mayor de lo permitido
        int longitudTotalRegistro = getLongitudTotalRegistro(restaurante);
        if (longitudTotalRegistro > longitudMaxRegistro) {
            throw new IllegalArgumentException("Longitud del registro no valido");
        }

        // Empezamos a escribir
        escribirEnPosicion(restaurante, ficheroRestaurantes, longitudMaxRegistro, ficheroCocineros);
    }

    private static void escribirEnPosicion(Restaurante restaurante, File ficheroRestaurantes, double longitudMaxRegistro, File ficheroCocineros) {
        try (RandomAccessFile raf = new RandomAccessFile(ficheroRestaurantes, "rw")) {
            raf.setLength((long) (restaurante.getNumero() * longitudMaxRegistro));           // Aumentas la longitud del archivo para que quepa el registro
            raf.seek((long) Math.ceil((restaurante.getNumero() - 1) * longitudMaxRegistro)); // Calculas la posición en la que empezarás a escribir tu archivo

            raf.writeInt(restaurante.getNumero());
            raf.writeUTF(restaurante.getNombre());
            raf.writeUTF(restaurante.getLocalidad());
            raf.writeInt(restaurante.getNumCocineros());
            raf.writeBoolean(restaurante.isBaja());

            for (InfoCocinero infoCocinero : restaurante.getInfoCocineros()) {
                // Comprobamos que el cocinero esté dado de alta en Cocineros.dat. Si está se añade, si no, no.
                if (CocineroBinarioSecuencial.comprobarSiExisteCocinero(ficheroCocineros, infoCocinero.getNumero())) {
                    raf.writeInt(infoCocinero.getNumero());
                    raf.writeUTF(infoCocinero.getTipo());
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean nombreRestauranteYaRegistrado(File fichero, double longitudMaxRegistro, Restaurante restaurante) {
        if (!fichero.exists()) {
            return false; // El fichero no existe, por lo tanto no está registrado el nombre del restaurante
        }

        try (RandomAccessFile raf = new RandomAccessFile(fichero, "r")) {
            // Nos colocamos al principio del registro
            raf.seek((long) Math.ceil((restaurante.getNumero()) * longitudMaxRegistro));

            int numRegistro = raf.readInt();
            String nombreRestaurante = raf.readUTF();
            if (nombreRestaurante.equals(restaurante.getNombre())) {
                return true;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    private static int getLongitudTotalRegistro(Restaurante restaurante) {
        final int longitudCodigo = 4;
        final int longitudNombre = restaurante.getNombre().length();
        final int longitudLocalidad = restaurante.getLocalidad().length();
        final int longitudNumCocineros = 4;
        final int longitudBaja = 1;

        int longitudCocineros = 0;
        ArrayList<InfoCocinero> infoCocineros = restaurante.getInfoCocineros();

        for (InfoCocinero infoCocinero : infoCocineros) {
            longitudCocineros++; // Le sumamos el código del cocinero
            longitudCocineros += infoCocinero.getTipo().length(); // Le sumamos el tamaño del tipo del cocinero
        }

        return longitudCodigo + longitudNombre + longitudLocalidad + longitudNumCocineros + longitudCocineros + longitudBaja;
    }

    public static void mostrarTodos(File fichero, double longitud_registro) {
        try (RandomAccessFile raf = new RandomAccessFile(fichero, "r")) {
            long file_size = fichero.length();
            int numero_registros = (int) Math.ceil(file_size / longitud_registro);

            for (int i = 0; i < numero_registros; i++) {
                // Te pones al principio de la posición de memoria.
                raf.seek((long) Math.ceil(i * longitud_registro));

                int codigo = raf.readInt();
                String nombre = raf.readUTF();
                String localidad = raf.readUTF();
                int numCocineros = raf.readInt();
                boolean baja = raf.readBoolean();

                // Si el restaurante está dado de baja, no se mostrará.
                if (baja) {
                    continue;
                }

                ArrayList<InfoCocinero> infoCocineros = new ArrayList<>();
                for (int j = 0; j < numCocineros; j++) {
                    infoCocineros.add(new InfoCocinero(raf.readInt(), raf.readUTF()));
                }

                Restaurante res = new Restaurante(nombre, localidad, infoCocineros, baja);
                res.setNumero(codigo);
                System.out.println(res);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Restaurante get(String rutaFichero, int codRestaurante, double longitudMaxRegistro) {
        File fichero = new File(rutaFichero);
        if (!fichero.exists()) {
            return null; // El fichero no existe, por lo tanto no está registrado el el restaurante
        }

        try (RandomAccessFile raf = new RandomAccessFile(fichero, "r")) {
            // Nos colocamos al principio del registro que queremos consultar
            raf.seek((long) Math.ceil((codRestaurante - 1) * longitudMaxRegistro));

            int numRegistro = raf.readInt();
            String nombreRestaurante = raf.readUTF();
            String localidad = raf.readUTF();
            int numCocineros = raf.readInt();
            boolean baja = raf.readBoolean();

            if (baja) {
                System.err.println("El restaurante que buscas está dado de baja.");
                return null;
            }

            ArrayList<InfoCocinero> cocineros = new ArrayList<>();
            for (int i = 0; i < numCocineros; i++) {
                cocineros.add(new InfoCocinero(raf.readInt(), raf.readUTF()));
            }

            Restaurante restaurante = new Restaurante(nombreRestaurante, localidad, cocineros, false);
            restaurante.setNumero(numRegistro);
            return restaurante;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
