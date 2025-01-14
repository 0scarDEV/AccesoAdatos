package ejercicio5_2;

import comunes.Conexion;

import java.sql.Date;
import java.time.LocalDate;

public class E5_2 {
    /*
        Se quiere guardar información de los vehículos de los que disponen la empresa. Estos vehículos pueden ser propios, es decir, comprados por la empresa o de renting. De todos los vehículos se guarda un código que se genera automáticamente por el SGBD empezando en 1 e incrementándose de 1 en 1, también se guarda la matrícula (char (8) y con el formato (DDDD XXX, siendo D cualquier dígito número y X letras en Mayúsculas), Marca, Modelo y el tipo de combustible (char(1) pudiendo solo introducir G ( Gasolina) o D ( Diesel). De los de Renting se guarda la fecha de inicio, el precio mensual del renting y los meses que se han alquilado. De los propios la fecha de compra y el precio pagado.
     */

    public static void main(String[] args) {
        OperacionesSQLServerVehiculos opVehiculos = new OperacionesSQLServerVehiculos(new Conexion(Conexion.SGDB.SQLServer));
        // 1) Crear las tablas en SQL Server para almacenar la información anterior, teniendo en cuenta que para la especialización se crearán tablas aparte.
        opVehiculos.createTables();
        // 2) Realiza metodo que permita insertar un nuevo vehículo, guardando la información en las tablas correspondientes.
        opVehiculos.insertarVehiculo(new VehiculoPropio("1234ABC", "Volkswagen", "Polo", 'D', Date.valueOf(LocalDate.of(2023, 5, 15)), 20000));
        opVehiculos.insertarVehiculo(new VehiculoRenting("5678DEF", "Volkswagen", "Golf", 'G', Date.valueOf(LocalDate.of(2023, 7, 1)), 500, 24));
        opVehiculos.insertarVehiculosJSON("src/ejercicio5_2/InsertarVehiculos.json");
    }
}
