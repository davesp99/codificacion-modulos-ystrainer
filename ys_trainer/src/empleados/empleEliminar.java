package empleados;

import conexion.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class empleEliminar {

    public static void main(String[] args) {
        conexion con = new conexion();
        Connection cn = null;
        PreparedStatement pst = null;
        PreparedStatement pstConsulta = null;
        ResultSet rs = null;

        int id_eliminar = 3; // ID del registro a eliminar

        // Instrucción SQL para eliminar un registro
        String sql = "DELETE FROM empleados WHERE idEmpleado = ?";

        try {
            // Conectar a la base de datos
            cn = con.getConnection();
            
            // Preparar y ejecutar la eliminación
            pst = cn.prepareStatement(sql);
            pst.setInt(1, id_eliminar);
            int filasAfectadas = pst.executeUpdate();
            System.out.println("Registros eliminados: " + filasAfectadas);

            // Preparar y ejecutar la consulta para ver los registros restantes
            String consulta = "SELECT * FROM empleados";
            pstConsulta = cn.prepareStatement(consulta);
            rs = pstConsulta.executeQuery();

            // Mostrar los datos
            while (rs.next()) {
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

                System.out.println(id + " : " + noDocuEmpleado + " " + tipoDocuEmpleado + " | " +
                        nombresEmpleado + " | " + apellidosEmpleado + " - " + emailEmpleado + " | " +
                        userEmpleado + " | " + celEmpleado + " | " + direcEmpleado + " | " +
                        ciudadEmpleado + " | " + paisEmpleado + " | " + rolEmpleado);
            }

        } catch (SQLException ex) {
            Logger.getLogger(empleEliminar.class.getName()).log(Level.SEVERE, "Error SQL", ex);
        } finally {
            // Cerrar recursos
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (pstConsulta != null) pstConsulta.close();
                if (cn != null) cn.close();
            } catch (SQLException ex) {
                Logger.getLogger(empleEliminar.class.getName()).log(Level.SEVERE, "Error cerrando conexión", ex);
            }
        }
    }
}
