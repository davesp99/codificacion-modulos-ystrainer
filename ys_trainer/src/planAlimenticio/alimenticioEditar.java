package planAlimenticio;

import conexion.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class alimenticioEditar {
    public static void main(String[] args) {
        conexion con = new conexion();
        Connection cn = null;
        PreparedStatement pst = null;

        // ID del plan a actualizar
        int idPlanAlimenticio = 1; // Asegúrate de que este ID exista en la tabla

        // Nuevos datos a actualizar
        int idCliente = 4;
        int idEmpleado = 2;
        String nombreComida = "Ensalada de quinoa";
        String ingredientes = "Quinoa, tomate, pepino, cebolla morada, limón, sal, aceite de oliva";
        String preparacion = "Mezclar todos los ingredientes en un bowl y servir frío.";
        String preferenciaVeg = "Sí";
        String restriccionDiet = "Bajo en sodio";
        String alergia = "Ninguna";
        String intolerancia = "Lactosa";
        String cantidades = "1 taza de quinoa, 1 tomate, 1/2 pepino, etc.";

        // Consulta SQL para actualizar los datos
        String sql = "UPDATE PlanAlimenticio SET idCliente=?, idEmpleado=?, nombreComida=?, ingredientes=?, " +
                     "preparacion=?, preferenciaVeg=?, restriccionDiet=?, alergia=?, intolerancia=?, cantidades=? " +
                     "WHERE idPlanAlimenticio=?";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = con.getConnection();
            pst = cn.prepareStatement(sql);

            // Establecer parámetros
            pst.setInt(1, idCliente);
            pst.setInt(2, idEmpleado);
            pst.setString(3, nombreComida);
            pst.setString(4, ingredientes);
            pst.setString(5, preparacion);
            pst.setString(6, preferenciaVeg);
            pst.setString(7, restriccionDiet);
            pst.setString(8, alergia);
            pst.setString(9, intolerancia);
            pst.setString(10, cantidades);
            pst.setInt(11, idPlanAlimenticio);

            int filasAfectadas = pst.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("✅ Plan alimenticio actualizado correctamente.");
            } else {
                System.out.println("⚠️ No se encontró el plan alimenticio con ID: " + idPlanAlimenticio);
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(alimenticioEditar.class.getName()).log(Level.SEVERE, "Error actualizando Plan alimenticio", ex);
        } finally {
            try {
                if (pst != null) pst.close();
                if (cn != null) cn.close();
            } catch (SQLException ex) {
                Logger.getLogger(alimenticioEditar.class.getName()).log(Level.SEVERE, "Error cerrando recursos", ex);
            }
        }
    }
}
