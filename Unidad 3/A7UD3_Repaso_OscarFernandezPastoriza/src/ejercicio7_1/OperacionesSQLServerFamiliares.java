package ejercicio7_1;

import comunes.Operaciones;

import java.sql.Connection;
import java.sql.Statement;

public class OperacionesSQLServerFamiliares extends Operaciones {
    public OperacionesSQLServerFamiliares(Connection con) {
        super(con);
    }

    public void crearTablas() {
        try {
            // Crear tabla Familiares
            Statement stmt = con.createStatement();
            stmt.addBatch(
            "CREATE TABLE FAMILIAR ("
                + "NSS_empleado VARCHAR(15) NOT NULL,"
                + "Numero SMALLINT NOT NULL,"
                + "NSS VARCHAR(15) NOT NULL,"
                + "Nombre VARCHAR(25) NOT NULL,"
                + "Apellido1 VARCHAR(25) NOT NULL,"
                + "Apellido2 VARCHAR(25),"
                + "FechaNacimiento DATE,"
                + "Parentesco VARCHAR(20) NOT NULL,"
                + "Sexo CHAR(1) DEFAULT 'M'" +
                ")"
            );

            stmt.addBatch(
            "ALTER TABLE FAMILIAR ADD CONSTRAINT PK_FAMILIAR PRIMARY KEY (NSS_empleado, Numero)"
            );

            stmt.addBatch(
            "ALTER TABLE FAMILIAR ADD CONSTRAINT FK_FAMILIAR_EMPLEADOS FOREIGN KEY (NSS_empleado) REFERENCES EMPLEADO(NSS)"
            );

            stmt.addBatch(
            "ALTER TABLE FAMILIAR ADD CONSTRAINT CK_SEXO CHECK (Sexo IN ('H', 'M'))"
            );

            stmt.executeBatch();
            System.out.println("Tabla FAMILIAR creada correctamente");
        } catch (Exception e) {
            System.out.println("Error al crear la tabla FAMILIAR");
            e.printStackTrace();
        }
    }
}
