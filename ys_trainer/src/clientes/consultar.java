package clientes;

import conexion.conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Consultar {
    public static void main(String[] args) {
        conexion con = new conexion();
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            // Cargar el driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establecer conexión
            cn = con.getConnection(); // Asegúrate de que este método existe en la clase 'conexion'
            st = cn.createStatement();
            rs = st.executeQuery("SELECT * FROM clientes");

            // Verificar si hay resultados
            if (!rs.next()) { 
                System.out.println("No hay clientes registrados.");
            } else {
                do {
                    // Extraer datos de la consulta
                    int id = rs.getInt("idCliente");
                    String nombres = rs.getString("nombres");
                    String apellidos = rs.getString("apellidos");
                    String noDocuCliente = rs.getString("noDocuCliente");
                    String tipoDocuCliente = rs.getString("tipoDocuCliente");
                    String emailCliente = rs.getString("emailCliente");
                    String userCliente = rs.getString("userCliente");
                    String celCliente = rs.getString("celCliente");
                    String direcCliente = rs.getString("direcCliente");
                    String ciudadCliente = rs.getString("ciudadCliente");
                    String paisCliente = rs.getString("paisCliente");
                    String planCliente = rs.getString("planCliente");

                    // Imprimir los datos
                    System.out.println(id + " : " + nombres + " " + apellidos + " | " +
                            noDocuCliente + " - " + tipoDocuCliente + " | " +
                            emailCliente + " | " + userCliente + " | " + celCliente + " | " +
                            direcCliente + " | " + ciudadCliente + ", " + paisCliente + " | " + planCliente);
                } while (rs.next());
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Consultar.class.getName()).log(Level.SEVERE, "Error cargando el driver", ex);
        } catch (SQLException ex) {
            Logger.getLogger(Consultar.class.getName()).log(Level.SEVERE, "Error en la consulta SQL", ex);
        } finally {
            // Cerrar recursos para evitar fugas de memoria
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (cn != null) cn.close();
            } catch (SQLException ex) {
                Logger.getLogger(Consultar.class.getName()).log(Level.SEVERE, "Error cerrando recursos", ex);
            }
        }
    }
}
