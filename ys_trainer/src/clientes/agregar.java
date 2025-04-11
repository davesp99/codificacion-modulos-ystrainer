/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package clientes;

import conexion.conexion;
import java.sql.Connection; 
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class agregar {
    public static void main(String[] args) {
        conexion con = new conexion();
        Connection cn;
        Statement st;
        ResultSet rs;

        // Datos del cliente
        String noDocuCliente = "1104141414";
        String tipoDocuCliente = "Cédula";
        String nombres = "David Andres";
        String apellidos = "Morales Beltran";
        String emailCliente = "David@gmail.com";
        String userCliente = "david";
        String celCliente = "3022998220";
        String direcCliente = "Calle 10 #33 - 40";
        String ciudadCliente = "Bogotá";
        String paisCliente = "Colombia";
        String planCliente = "Basic";
        int edadCliente = 25; // Campo de tipo INT

        // Consulta SQL para insertar los datos
        String sql = "INSERT INTO clientes (noDocuCliente, tipoDocuCliente, nombres, apellidos, emailCliente, userCliente, celCliente, direcCliente, ciudadCliente, paisCliente, planCliente, edadCliente) " +
                     "VALUES ('" + noDocuCliente + "', '" + tipoDocuCliente + "', '" + nombres + "', '" + apellidos + "', '" + emailCliente + "', '" + userCliente + "', '" + celCliente + "', '" + direcCliente + "', '" + ciudadCliente + "', '" + paisCliente + "', '" + planCliente + "', " + edadCliente + ")";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(agregar.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            cn = con.getConnection();
            st = cn.createStatement();
            st.executeUpdate(sql); // Ejecuta la consulta INSERT

            // Consultar y mostrar los datos insertados
            rs = st.executeQuery("SELECT * FROM clientes");

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

        } catch (SQLException ex) {
            Logger.getLogger(agregar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

System.out.println("SQL a ejecutar: " + sql);

/**
 *
 * @author dmora
 */
