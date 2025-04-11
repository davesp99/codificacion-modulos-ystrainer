package empleados;

import conexion.conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class empleConsultar {
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
            rs = st.executeQuery("SELECT * FROM empleados");

            // Verificar si hay resultados antes de iterar
            if (!rs.isBeforeFirst()) {
                System.out.println("No hay empleados registrados.");
            } else {
                while (rs.next()) {
                    // Extraer datos de la consulta
                    int id = rs.getInt("idEmpleado");
                    String noDocuEmpleado = rs.getString("noDocuEmpleado");
                    String tipoDocuEmpleado = rs.getString("tipoDocuEmpleado");
                    String nombresEmpleado = rs.getString("nombresEmpleado");
                    String apellidosEmpleado = rs.getString("apellidosEmpleado");
                    String emailEmpleado = rs.getString("emailEmpleado");
                    String userEmpleado = rs.getString("userEmpleado");
                    String celEmpleado = rs.getString("celEmpleado");
                    String direcEmpleado = rs.getString("direcEmpleado");
                    String ciudadEmpleado = rs.getString("ciudadEmpleado");
                    String paisEmpleado = rs.getString("paisEmpleado");
                    String rolEmpleado = rs.getString("rolEmpleado");

                    // Imprimir los datos
               System.out.println(id + " : " + noDocuEmpleado + " : " + nombresEmpleado + " " + apellidosEmpleado + " | " +
                       noDocuEmpleado + " - " + tipoDocuEmpleado + " | " +
                       emailEmpleado + " | " + userEmpleado + " | " + celEmpleado + " | " +
                       direcEmpleado + " | " + ciudadEmpleado + ", " + paisEmpleado + " | " + rolEmpleado);


                }
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
