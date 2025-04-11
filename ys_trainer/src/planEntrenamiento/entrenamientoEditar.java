package entrenamientoEditar;

import conexion.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class entrenamientoEditar {
    public static void main(String[] args) {
        conexion con = new conexion();
        
        // ID del plan de entrenamiento a actualizar (por defecto = 1)
        int idPlanEntrenamiento = 1; 

        // Valores predefinidos
        String nombreEjercicios = "Abdominales";
        int numSeries = 4;
        int numRepeticiones = 10;
        int tiempoDescanso = 30; // en segundos

        try {
            // Consulta SQL parametrizada para actualizar los datos
            String sql = "UPDATE PlanEntrenamiento SET nombreEjercicios=?, numSeries=?, numRepeticiones=?, tiempoDescanso=? " +
                         "WHERE idPlanEntrenamiento=?";

            // Establecer conexión y ejecutar actualización
            try (Connection cn = con.getConnection();
                 PreparedStatement pst = cn.prepareStatement(sql)) {

                // Asignar valores a la consulta
                pst.setString(1, nombreEjercicios);
                pst.setInt(2, numSeries);
                pst.setInt(3, numRepeticiones);
                pst.setInt(4, tiempoDescanso);
                pst.setInt(5, idPlanEntrenamiento);

                int rowsUpdated = pst.executeUpdate();

                if (rowsUpdated > 0) {
                    System.out.println("✅ Plan de entrenamiento actualizado correctamente.");
                } else {
                    System.out.println("⚠ No se encontró el plan de entrenamiento con idPlanEntrenamiento=" + idPlanEntrenamiento);
                }

                // Consultar y mostrar los datos actualizados
                String consulta = "SELECT * FROM PlanEntrenamiento WHERE idPlanEntrenamiento=?";
                try (PreparedStatement pst2 = cn.prepareStatement(consulta)) {
                    pst2.setInt(1, idPlanEntrenamiento);
                    try (ResultSet rs = pst2.executeQuery()) {
                        while (rs.next()) {
                            System.out.println(
                                "📌 " + rs.getString("nombreEjercicios") + " | " +
                                rs.getInt("numSeries") + " series | " +
                                rs.getInt("numRepeticiones") + " repeticiones | " +
                                rs.getInt("tiempoDescanso") + " seg descanso"
                            );
                        }
                    }
                }

            } catch (SQLException ex) {
                Logger.getLogger(entrenamientoEditar.class.getName()).log(Level.SEVERE, "Error al ejecutar la actualización", ex);
            }

        } catch (Exception e) {
            Logger.getLogger(entrenamientoEditar.class.getName()).log(Level.SEVERE, "Error general en la ejecución", e);
        }
    }
}
