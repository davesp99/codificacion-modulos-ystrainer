/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package empleados;

import conexion.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class empleEditar {
    public static void main(String[] args) {
        conexion con = new conexion();
        
        // ID del cliente a actualizar
        int idEmpleado = 1; // Asegúrate de que este ID exista en la base de datos
        
        // Nuevos datos del cliente
        String noDocuEmpleado = "1104141414";
        String tipoDocuEmpleado = "Cédula";
        String nombresEmpleado = "David Andres";
        String apellidosEmpleado = "Morales Beltran";
        String emailEmpleado = "David@gmail.com";
        String userEmpleado = "david";
        String celEmpleado = "3022998220";
        String direcEmpleado = "Calle 10 #33 - 40";
        String ciudadEmpleado = "Bogotá";
        String paisEmpleado = "Colombia";
        String rolEmpleado = "Trainer";

        // Consulta SQL parametrizada para actualizar los datos
      String sql = "UPDATE empleados SET noDocuEmpleado=?, tipoDocuEmpleado=?, nombresEmpleado=?, " +
             "apellidosEmpleado=?, emailEmpleado=?, userEmpleado=?, celEmpleado=?, " +
             "direcEmpleado=?, ciudadEmpleado=?, paisEmpleado=?, rolEmpleado=? " +
             "WHERE idEmpleado=?";

        
        try (Connection cn = con.getConnection();
             PreparedStatement pst = cn.prepareStatement(sql)) {

            // Asignar valores a la consulta
            pst.setString(1, noDocuEmpleado);
            pst.setString(2, tipoDocuEmpleado);
            pst.setString(3, nombresEmpleado);
            pst.setString(4, apellidosEmpleado);
            pst.setString(5, emailEmpleado);
            pst.setString(6, userEmpleado);
            pst.setString(7, celEmpleado);
            pst.setString(8, direcEmpleado);
            pst.setString(9, ciudadEmpleado);
            pst.setString(10, paisEmpleado);
            pst.setString(11, rolEmpleado);
            pst.setInt(12, idEmpleado);

            int rowsUpdated = pst.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("✅ Empleado actualizado correctamente.");
            } else {
                System.out.println("⚠ No se encontró el empleado con idEmpleado=" + idEmpleado);
            }

            // Consultar y mostrar los datos actualizados
            String consulta = "SELECT * FROM empleados WHERE idEmpleado=?";
            try (PreparedStatement pst2 = cn.prepareStatement(consulta)) {
                pst2.setInt(1, idEmpleado);
                try (ResultSet rs = pst2.executeQuery()) {
                    while (rs.next()) {
                        System.out.println(
                            rs.getString("noDocuEmpleado") + ": " +
                            rs.getString("tipoDocuEmpleado") + " - " +
                            rs.getString("nombresEmpleado") + " " +
                            rs.getString("apellidosEmpleado") + " | " +
                            rs.getString("emailEmpleado") + " | " +
                            rs.getString("userEmpleado") + " | " +
                            rs.getString("celEmpleado") + " | " +
                            rs.getString("direcEmpleado") + " | " +
                            rs.getString("ciudadEmpleado") + " | " +
                            rs.getString("paisEmpleado") + " | " +
                            rs.getString("rolEmpleado")
                        );
                    }
                }
            }


        } catch (SQLException ex) {
            Logger.getLogger(empleEditar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
