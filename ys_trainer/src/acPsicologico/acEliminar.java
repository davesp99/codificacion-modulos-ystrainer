package acPsicologico;

import conexion.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class acEliminar {

    public static void main(String[] args) {
        conexion con = new conexion();
        Connection cn = null;
        PreparedStatement pst = null;
        Statement st = null;
        ResultSet rs = null;

        int id_eliminar = 3; // ID del registro a eliminar

        // Instrucción SQL para eliminar un registro
        String sql = "DELETE FROM PlanAcPsicologico WHERE idPlanAcPsicologico = ?";  

        try {
            // Conectar a la base de datos
            cn = con.getConnection();
            pst = cn.prepareStatement(sql);
            pst.setInt(1, id_eliminar);

            // Ejecutar la eliminación
            int filasAfectadas = pst.executeUpdate();
            System.out.println("Registros eliminados: " + filasAfectadas);

            // Crear una nueva consulta para obtener los datos restantes
            String consulta = "SELECT * FROM PlanAcPsicologico";
            st = cn.createStatement();
            rs = st.executeQuery(consulta);

            // Mostrar los datos restantes
            while (rs.next()) {
                int idPlanAcPsicologico = rs.getInt("idPlanAcPsicologico");  
                int idCliente = rs.getInt("idCliente");
                int idEmpleado = rs.getInt("idEmpleado");
                String antecedentes = rs.getString("antecedentes");
                String recomendaciones = rs.getString("recomendaciones");
                String notas = rs.getString("notas");

                System.out.println("ID Plan: " + idPlanAcPsicologico + " | Cliente ID: " + idCliente +
                        " | Empleado ID: " + idEmpleado + " | Antecedentes: " + antecedentes +
                        " | Recomendaciones: " + recomendaciones + " | Notas: " + notas);
            }

        } catch (SQLException ex) {
            Logger.getLogger(acEliminar.class.getName()).log(Level.SEVERE, "Error SQL", ex);
        } finally {
            // Cerrar recursos
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (pst != null) pst.close();
                if (cn != null) cn.close();
            } catch (SQLException ex) {
                Logger.getLogger(acEliminar.class.getName()).log(Level.SEVERE, "Error cerrando conexión", ex);
            }
        }
    }
}
