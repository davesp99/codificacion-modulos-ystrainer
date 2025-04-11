package empleados;

import conexion.conexion;
import java.sql.Connection; 
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class empleAgregar {
    public static void main(String[] args) {
        conexion con = new conexion();
        Connection cn;
        Statement st;
        ResultSet rs;

        // Datos del empleado
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

        // Consulta SQL para insertar los datos
        String sql = "INSERT INTO empleados (noDocuEmpleado, tipoDocuEmpleado, nombresEmpleado, " +
                     "apellidosEmpleado, emailEmpleado, userEmpleado, celEmpleado, direcEmpleado, " +
                     "ciudadEmpleado, paisEmpleado, rolEmpleado) VALUES ('" + 
                     noDocuEmpleado + "', '" + tipoDocuEmpleado + "', '" + nombresEmpleado + "', '" + 
                     apellidosEmpleado + "', '" + emailEmpleado + "', '" + userEmpleado + "', '" + 
                     celEmpleado + "', '" + direcEmpleado + "', '" + ciudadEmpleado + "', '" + 
                     paisEmpleado + "', '" + rolEmpleado + "')";

        System.out.println("SQL a ejecutar: " + sql); // Ahora está dentro de la clase

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = con.getConnection();
            st = cn.createStatement();
            st.executeUpdate(sql); // Ejecuta la consulta INSERT

            // Consultar y mostrar los datos insertados
            rs = st.executeQuery("SELECT * FROM empleados");

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
        } catch (ClassNotFoundException ex) {
            System.err.println("Error: No se encontró el driver de MySQL.");
            ex.printStackTrace();
        } catch (SQLException ex) {
            System.err.println("Error SQL: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
