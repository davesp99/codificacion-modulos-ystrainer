package planAlimenticio;

import conexion.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class alimenticioEliminar {

    public static void main(String[] args) {
        conexion con = new conexion();
        Connection cn = null;
        PreparedStatement pst = null;
        PreparedStatement pstConsulta = null;
        ResultSet rs = null;

        int idPlanEliminar = 1; // Cambia este valor según el ID que desees eliminar

        String sql = "DELETE FROM PlanAlimenticio WHERE idPlanAlimenticio = ?";

        try {
            cn = con.getConnection();

            pst = cn.prepareStatement(sql);
            pst.setInt(1, idPlanEliminar);
            int filasAfectadas = pst.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("✅ Plan alimenticio eliminado correctamente.");
            } else {
                System.out.println("⚠️ No se encontró el plan con ID: " + idPlanEliminar);
            }

            String consulta = "SELECT * FROM PlanAlimenticio";
            pstConsulta = cn.prepareStatement(consulta);
            rs = pstConsulta.executeQuery();

            System.out.println("\n📋 Planes alimenticios restantes:");
            while (rs.next()) {
                int idPlan = rs.getInt("idPlanAlimenticio");
                int idCliente = rs.getInt("idCliente");
                int idEmpleado = rs.getInt("idEmpleado");
                String nombreComida = rs.getString("nombreComida");
                String ingredientes = rs.getString("ingredientes");
                String preparacion = rs.getString("preparacion");
                String preferenciaVeg = rs.getString("preferenciaVeg");
                String restriccionDiet = rs.getString("restriccionDiet");
                String alergia = rs.getString("alergia");
                String intolerancia = rs.getString("intolerancia");
                String cantidades = rs.getString("cantidades");

                System.out.println(idPlan + " | Cliente: " + idCliente + " | Empleado: " + idEmpleado +
                        " | Comida: " + nombreComida + " | Ingredientes: " + ingredientes +
                        " | Preparación: " + preparacion + " | Preferencia Veg: " + preferenciaVeg +
                        " | Restricción: " + restriccionDiet + " | Alergia: " + alergia +
                        " | Intolerancia: " + intolerancia + " | Cantidades: " + cantidades);
            }

        } catch (SQLException ex) {
            Logger.getLogger(alimenticioEliminar.class.getName()).log(Level.SEVERE, "❌ Error al eliminar el plan", ex);
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (pstConsulta != null) pstConsulta.close();
                if (cn != null) cn.close();
            } catch (SQLException ex) {
                Logger.getLogger(alimenticioEliminar.class.getName()).log(Level.SEVERE, "❌ Error al cerrar recursos", ex);
            }
        }
    }
}
