package entrenamientoAgregar;

import conexion.conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class entrenamientoAgregar {
    public static void main(String[] args) {
        conexion con = new conexion();
        Connection cn;
        Statement st;
        ResultSet rs;

        // Datos del cliente y empleado (asigna valores según tu lógica)
        int idCliente = 1;  // Cambia este valor según corresponda
        int idEmpleado = 2; // Cambia este valor según corresponda

        // Datos del ejercicio
        String nombreEjercicios = "Sentadillas";
        int numSeries = 3;
        int numRepeticiones = 10;
        int tiempoDescanso = 50;

        // Consulta corregida (agregando idCliente e idEmpleado)
        String sql = "INSERT INTO PlanEntrenamiento (idCliente, idEmpleado, nombreEjercicios, numSeries, numRepeticiones, tiempoDescanso) " + 
                     "VALUES (" + idCliente + ", " + idEmpleado + ", '" + nombreEjercicios + "', " + numSeries + ", " + numRepeticiones + ", " + tiempoDescanso + ")";

        System.out.println("SQL a ejecutar: " + sql); 
        
        try {
            cn = con.getConnection(); // Obtener conexión
            if (cn != null) {
                st = cn.createStatement();
                st.executeUpdate(sql); // Ejecuta la consulta INSERT
                System.out.println("✅ Inserción exitosa.");

                // Consultar y mostrar los datos insertados
                rs = st.executeQuery("SELECT * FROM PlanEntrenamiento");

                while (rs.next()) {
                    System.out.println(
                        "ID Cliente: " + rs.getInt("idCliente") + " | " +
                        "ID Empleado: " + rs.getInt("idEmpleado") + " | " +
                        rs.getString("nombreEjercicios") + ": " +
                        rs.getInt("numSeries") + " series - " +
                        rs.getInt("numRepeticiones") + " reps - " +
                        rs.getInt("tiempoDescanso") + " seg"
                    );
                }
            } else {
                System.out.println("❌ Error: No se pudo establecer la conexión.");
            }
        } catch (SQLException ex) {
            System.err.println("❌ Error SQL: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
