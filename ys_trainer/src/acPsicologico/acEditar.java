package acPsicologico;

import conexion.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class acEditar {
    public static void main(String[] args) {
        conexion con = new conexion();
        Connection cn = null;
        PreparedStatement pst = null;

        // ID del plan de acción psicológica a actualizar
        int idPlanAcPsicologico = 1;

        // Valores predefinidos
        int idCliente = 2;
        int idEmpleado = 3;
        String antecedentes = "Antecedentes actualizados";
        String recomendaciones = "Recomendaciones mejoradas";
        String notas = "Notas adicionales";

        try {
            // Establecer conexión
            cn = con.getConnection();

            // Consulta SQL parametrizada para actualizar los datos en PlanAcPsicologico
            String sql = "UPDATE PlanAcPsicologico SET idCliente = ?, idEmpleado = ?, antecedentes = ?, recomendaciones = ?, notas = ? WHERE idPlanAcPsicologico = ?";

            pst = cn.prepareStatement(sql);

            // Asignar valores a los parámetros (ahora en el orden correcto)
            pst.setInt(1, idCliente);
            pst.setInt(2, idEmpleado);
            pst.setString(3, antecedentes);
            pst.setString(4, recomendaciones);
            pst.setString(5, notas);
            pst.setInt(6, idPlanAcPsicologico); // ID del plan debe ir en la última posición

            // Ejecutar la actualización
            int filasActualizadas = pst.executeUpdate();

            if (filasActualizadas > 0) {
                System.out.println("Registro actualizado correctamente.");
            } else {
                System.out.println("No se encontró el registro con ID: " + idPlanAcPsicologico);
            }

        } catch (SQLException ex) {
            Logger.getLogger(acEditar.class.getName()).log(Level.SEVERE, "Error en la actualización SQL", ex);
        } finally {
            // Cerrar recursos
            try {
                if (pst != null) pst.close();
                if (cn != null) cn.close();
            } catch (SQLException ex) {
                Logger.getLogger(acEditar.class.getName()).log(Level.SEVERE, "Error cerrando recursos", ex);
            }
        }
    }
}
