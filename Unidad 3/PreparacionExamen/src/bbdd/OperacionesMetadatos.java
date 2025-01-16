package bbdd;

import java.sql.*;
public class OperacionesMetadatos extends Operaciones {
    public OperacionesMetadatos(Conexion con) {
        super(con.getConnection());
    }

    public void mostrarInformacion() {
        DatabaseMetaData metaData;
        String nombreSGDB, nombreJDBC, url, user, versionjdbc;
        int versionPjdbc, versionSjdbc;
        boolean soloLectura;

        try {
            metaData = con.getMetaData();

            nombreSGDB = metaData.getDatabaseProductName();
            nombreJDBC = metaData.getDriverName();
            url = metaData.getURL();
            user = metaData.getUserName();
            versionjdbc = metaData.getDriverVersion();
            soloLectura = metaData.isReadOnly();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        versionPjdbc = metaData.getDriverMajorVersion();
        versionSjdbc = metaData.getDriverMinorVersion();

        System.out.println("Información de la base de datos:");
        System.out.println("Nombre del SGDB: " + nombreSGDB);
        System.out.println("Nombre del conector JDBC utilizado: " + nombreJDBC);
        System.out.println("Número de versión principal del conector JDBC: " + versionPjdbc);
        System.out.println("Número de versión secundario del conector JDBC: " + versionSjdbc);
        System.out.println("Número de versión del conector JDBC utilizado: " + versionjdbc);
        System.out.println("URL de la base de datos: " + url);
        System.out.println("Nombre del usuario actual conectado a la base de datos: " + user);
        System.out.println("La base de datos es de solo lectura: " + soloLectura);
    }

    public void mostrarInfoTablasUsuario() {
        DatabaseMetaData metaData;
        try {
            metaData = con.getMetaData();
            ResultSet tablas = metaData.getTables(null, null, null, new String[]{"TABLE"});
            System.out.println("-- TABLAS DE USUARIO --");
            while (tablas.next()) {
                System.out.println(tablas.getString("TABLE_NAME"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void mostrarColumnasTabla(String schema, String nombreTabla) {
        DatabaseMetaData metaData;
        try {
            metaData = con.getMetaData();
            ResultSet columnas = metaData.getColumns(null, schema, nombreTabla, null);
            System.out.println("-- TABLA " + schema + "." + nombreTabla + " --");
            while (columnas.next()) {
                System.out.println("Nombre: " + columnas.getString("COLUMN_NAME"));
                System.out.println("Tipo de datos: " + columnas.getString("TYPE_NAME"));
                System.out.println("Tamaño: " + columnas.getString("COLUMN_SIZE"));
                System.out.println("Permite nulos: " + columnas.getString("IS_NULLABLE"));
                System.out.println("------------------------------");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void mostrarProcedimientos() {
        DatabaseMetaData metaData;
        try {
            metaData = con.getMetaData();
            ResultSet procedimientos = metaData.getProcedures(null, null, null);
            System.out.println("-- PROCEDIMIENTOS --");
            while (procedimientos.next()) {
                System.out.println(procedimientos.getString("PROCEDURE_NAME"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void mostrarColumnasClavePrimaria(String schema, String table) {
        DatabaseMetaData metaData;
        try {
            metaData = con.getMetaData();
            ResultSet columnasClavePrimaria = metaData.getPrimaryKeys(null, schema, table);
            System.out.println("-- COLUMNAS CLAVE PRIMARIA --");
            while (columnasClavePrimaria.next()) {
                System.out.println("Nombre: " + columnasClavePrimaria.getString("COLUMN_NAME"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void mostrarColumnasClaveForanea(String schema, String tabla) {
        DatabaseMetaData metaData;
        try {
            metaData = con.getMetaData();
            ResultSet columnasClaveForanea = metaData.getImportedKeys(null, schema, tabla);
            System.out.println("-- COLUMNAS CLAVE FORANEA --");
            while (columnasClaveForanea.next()) {
                System.out.println("Columna: " + columnasClaveForanea.getString("FKCOLUMN_NAME"));
                System.out.println("Tabla: " + columnasClaveForanea.getString("PKTABLE_NAME"));
                System.out.println("Columna: " + columnasClaveForanea.getString("PKCOLUMN_NAME"));
                System.out.println("------------------------------");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void getQueryMetadata(String query) {
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            ResultSetMetaData metaData = rs.getMetaData();
            int numColumnas = metaData.getColumnCount();
            System.out.println("Número de columnas recuperadas: " + numColumnas);
            System.out.println("--------------------------------");
            for (int i = 1; i <= numColumnas; i++) {
                System.out.println("Nome da columna: " + metaData.getColumnName(i));
                System.out.println("Tipo da columna: " + metaData.getColumnTypeName(i));
                System.out.println("Tamaño da columna: " + metaData.getColumnDisplaySize(i));
                System.out.println("¿A columna admite nulos? " + (metaData.isNullable(i) == ResultSetMetaData.columnNullable));
                System.out.println("--------------------------------");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
