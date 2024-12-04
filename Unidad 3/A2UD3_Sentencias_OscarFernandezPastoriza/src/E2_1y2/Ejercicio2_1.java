/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package E2_1y2;

import Comunes.Conexion;
import Comunes.OperacionesBBDD;

/** @author ofernpast */
public class Ejercicio2_1 {
    public static void main(String[] args) {
        Conexion conexion = new Conexion(Conexion.SGDB.SQLServer);
        OperacionesBBDD bd = new OperacionesBBDD(conexion);

        if (bd.aumentarSalario("PERSOAL", 700)) {
            System.out.println("Salarios aumentados correctamente.");
        } else {
            System.out.println("Error al aumentar los salarios.");
        }

        if (bd.insertarDepartamento(7, "VENTASofp", "1100222")) {
            System.out.println("Departamento insertado correctamente.");
        } else {
            System.out.println("Error al insertar el departamento.");
        }

        if (bd.deleteEmpregadoProxecto("1100222", 5)) {
            System.out.println("Empregado eliminado correctamente del proxecto.");
        } else {
            System.out.println("Error al eliminar el empregado");
        }
    }
}
