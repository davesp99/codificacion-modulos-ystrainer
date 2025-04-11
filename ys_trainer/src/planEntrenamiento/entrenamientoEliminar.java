package planEntrenamiento;

import java.sql.*;

public class entrenamientoEliminar {
    public static void main(String[] args) {
        int idAEliminar = 1; // Cambia este valor por el ID que desees eliminar

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/YsTrainer", "root", "Damasco47*");
            System.out.println("Conexión exitosa a la base de datos!");

            // Eliminar registro
            PreparedStatement eliminar = conn.prepareStatement("DELETE FROM planEntrenamiento WHERE idPlanEntrenamiento = ?");
            eliminar.setInt(1, idAEliminar);
            int registrosEliminados = eliminar.executeUpdate();
            System.out.println("Registros eliminados: " + registrosEliminados);

            // Mostrar registros restantes
            PreparedStatement mostrar = conn.prepareStatement("SELECT * FROM planEntrenamiento");
            ResultSet rs = mostrar.executeQuery();

            while (rs.next()) {
                int idPlanEntrenamiento = rs.getInt("idPlanEntrenamiento");
                int idCliente = rs.getInt("idCliente");
                int idEmpleado = rs.getInt("idEmpleado");
                String nombreEjercicios = rs.getString("nombreEjercicios");
                int numSeries = rs.getInt("numSeries");
                int numRepeticiones = rs.getInt("numRepeticiones");
                int tiempoDescanso = rs.getInt("tiempoDescanso");

                System.out.println("ID: " + idPlanEntrenamiento + " | Cliente: " + idCliente +
                        " | Empleado: " + idEmpleado + " | Ejercicio: " + nombreEjercicios +
                        " | Series: " + numSeries + " | Repeticiones: " + numRepeticiones +
                        " | Descanso: " + tiempoDescanso + " seg");
            }

            conn.close();

        } catch (SQLException e) {
            System.out.println("Error al conectar o eliminar datos: " + e.getMessage());
        }
    }
}
