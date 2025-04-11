package clientes;

import conexion.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class eliminar {

    public static void main(String[] args) {
        conexion con = new conexion();
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        int id_eliminar = 3; // ID del registro a eliminar

        // Instrucción SQL para eliminar un registro con PreparedStatement
        String sql = "DELETE FROM clientes WHERE idCliente = ?";  // Usando el nombre correcto de la columna

        try {
            // Conectar a la base de datos
            cn = con.getConnection();
            pst = cn.prepareStatement(sql);
            pst.setInt(1, id_eliminar);

            // Ejecutar la eliminación
            int filasAfectadas = pst.executeUpdate();
            System.out.println("Registros eliminados: " + filasAfectadas);

            // Consulta para mostrar los registros restantes en la tabla "clientes"
            String consulta = "SELECT * FROM clientes";
            rs = pst.executeQuery(consulta);

            // Mostrar los datos
            while (rs.next()) {
                int id = rs.getInt("idCliente");  // Usando el nombre correcto de la columna
                String nombres = rs.getString("nombres");
                String apellidos = rs.getString("apellidos");
                String edadCliente = rs.getString("edadCliente");
                String noDocuCliente = rs.getString("noDocuCliente");
                String tipoDocuCliente = rs.getString("tipoDocuCliente");
                String emailCliente = rs.getString("emailCliente");
                String userCliente = rs.getString("userCliente");
                String celCliente = rs.getString("celCliente");
                String direcCliente = rs.getString("direcCliente");
                String ciudadCliente = rs.getString("ciudadCliente");
                String paisCliente = rs.getString("paisCliente");
                String planCliente = rs.getString("planCliente");

                System.out.println(id + " : " + nombres + " " + apellidos + " | " +
                        edadCliente + " años | " + noDocuCliente + " - " + tipoDocuCliente + " | " +
                        emailCliente + " | " + userCliente + " | " + celCliente + " | " +
                        direcCliente + " | " + ciudadCliente + ", " + paisCliente + " | " + planCliente);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Eliminar.class.getName()).log(Level.SEVERE, "Error SQL", ex);
        } finally {
            // Cerrar recursos
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (cn != null) cn.close();
            } catch (SQLException ex) {
                Logger.getLogger(Eliminar.class.getName()).log(Level.SEVERE, "Error cerrando conexión", ex);
            }
        }
    }
}
