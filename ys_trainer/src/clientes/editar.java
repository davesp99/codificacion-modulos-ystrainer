/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clientes;

import conexion.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class editar {
    public static void main(String[] args) {
        conexion con = new conexion();
        
        // ID del cliente a actualizar
        int idCliente = 4; // Asegúrate de que este ID exista en la base de datos
        
        // Nuevos datos del cliente
        String noDocuCliente = "168686868";
        String tipoDocuCliente = "Cédula";
        String nombres = "Juan Camilo";
        String apellidos = "Fernandez";
        String emailCliente = "juan@gmail.com";
        String userCliente = "juan";
        String celCliente = "3014515156";
        String direcCliente = "Calle 40 #22 - 19";
        String ciudadCliente = "Bogotá";
        String paisCliente = "Colombia";
        String planCliente = "Premium";
        int edadCliente = 25;

        // Consulta SQL parametrizada para actualizar los datos
        String sql = "UPDATE clientes SET noDocuCliente=?, tipoDocuCliente=?, nombres=?, apellidos=?, emailCliente=?, " +
                     "userCliente=?, celCliente=?, direcCliente=?, ciudadCliente=?, paisCliente=?, planCliente=?, edadCliente=? " +
                     "WHERE idCliente=?";
        
        try (Connection cn = con.getConnection();
             PreparedStatement pst = cn.prepareStatement(sql)) {

            // Asignar valores a la consulta
            pst.setString(1, noDocuCliente);
            pst.setString(2, tipoDocuCliente);
            pst.setString(3, nombres);
            pst.setString(4, apellidos);
            pst.setString(5, emailCliente);
            pst.setString(6, userCliente);
            pst.setString(7, celCliente);
            pst.setString(8, direcCliente);
            pst.setString(9, ciudadCliente);
            pst.setString(10, paisCliente);
            pst.setString(11, planCliente);
            pst.setInt(12, edadCliente);
            pst.setInt(13, idCliente);

            int rowsUpdated = pst.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("✅ Cliente actualizado correctamente.");
            } else {
                System.out.println("⚠ No se encontró el cliente con idCliente=" + idCliente);
            }

            // Consultar y mostrar los datos actualizados
            String consulta = "SELECT * FROM clientes WHERE idCliente=?";
            try (PreparedStatement pst2 = cn.prepareStatement(consulta)) {
                pst2.setInt(1, idCliente);
                try (ResultSet rs = pst2.executeQuery()) {
                    while (rs.next()) {
                        System.out.println(
                            rs.getString("noDocuCliente") + ": " +
                            rs.getString("tipoDocuCliente") + " - " +
                            rs.getString("nombres") + " " +
                            rs.getString("apellidos") + " | " +
                            rs.getString("emailCliente") + " | " +
                            rs.getString("userCliente") + " | " +
                            rs.getString("celCliente") + " | " +
                            rs.getString("direcCliente") + " | " +
                            rs.getString("ciudadCliente") + " | " +
                            rs.getString("paisCliente") + " | " +
                            rs.getString("planCliente") + " | " +
                            rs.getInt("edadCliente")
                        );
                    }
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(editar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
