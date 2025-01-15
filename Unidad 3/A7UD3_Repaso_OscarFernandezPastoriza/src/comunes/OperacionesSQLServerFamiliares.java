package comunes;

import clases.Familiar;

import java.sql.*;

public class OperacionesSQLServerFamiliares extends Operaciones {
    public OperacionesSQLServerFamiliares(Connection con) {
        super(con);
    }

    // region E7_1
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
            "ALTER TABLE FAMILIAR ADD CONSTRAINT FK_FAMILIAR_EMPLEADOS FOREIGN KEY (NSS_empleado) REFERENCES EMPREGADO(NSS)"
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
    // endregion

    // region E7_2
    public void insertarFamiliar(String nssEmpregado, Familiar familiar) {
        crearProcedimientoExisteFamiliar();
        try {
            CallableStatement cs = con.prepareCall("{call prExisteFamiliar(?, ?, ?)}");
            cs.setString(1, nssEmpregado);
            cs.setString(2, familiar.getNss());
            cs.registerOutParameter(3, Types.BIT);
            cs.execute();

            if (cs.getBoolean(3)) {
                System.err.println("Ya existe un familiar con el NSS " + familiar.getNss() + " para el empleado " + nssEmpregado);
                return;
            }

            String sql =
                "INSERT INTO FAMILIAR (" +
                    "NSS_empleado, Numero, NSS, Nombre, Apellido1, Apellido2, FechaNacimiento, Parentesco, Sexo " +
                ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"
            ;

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nssEmpregado);
            ps.setInt(2, numeroFamiliar(nssEmpregado));
            ps.setString(3, familiar.getNss());
            ps.setString(4, familiar.getNome());
            ps.setString(5, familiar.getApelido1());
            ps.setString(6, familiar.getApelido2());
            ps.setDate(7, familiar.getDataNacemento());
            ps.setString(8, familiar.getParentesco());
            ps.setString(9, String.valueOf(familiar.getSexo()));
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Conseguir el número de familiar para un empleado
    private int numeroFamiliar(String nssEmpregado) {
        try {
            PreparedStatement ps = con.prepareStatement("SELECT MAX(Numero) FROM FAMILIAR WHERE NSS_empleado = ?");
            ps.setString(1, nssEmpregado);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) + 1;
            }
            return 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Crear un procedimiento que se le pase el empleado y el nss del familiar y devuelva en un parámetro de tipo bit si existe o no ( 0 no existe, 1 existe).
    private void crearProcedimientoExisteFamiliar() {
        try {
            Statement stmt = con.createStatement();
            stmt.execute("DROP PROCEDURE IF EXISTS prExisteFamiliar");

            stmt.execute(
            "CREATE PROCEDURE prExisteFamiliar (" +
                    "@NSS_empleado VARCHAR(15), " +
                    "@NSS VARCHAR(15), " +
                    "@existe BIT OUTPUT" +
                ") AS BEGIN " +
                    "IF EXISTS (SELECT * FROM FAMILIAR WHERE NSS_empleado = @NSS_empleado AND NSS = @NSS) " +
                        "SET @existe = 1 " +
                    "ELSE " +
                        "SET @existe = 0 " +
                "END"
            );
            System.out.println("Procedimiento prExisteFamiliar creado correctamente");
        } catch (Exception e) {
            System.out.println("Error al crear el procedimiento prExisteFamiliar");
            e.printStackTrace();
        }
    }
    // endregion
}
