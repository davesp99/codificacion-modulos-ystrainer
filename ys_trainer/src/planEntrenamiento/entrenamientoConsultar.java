package entrenamientoConsultar;

import conexion.conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class entrenamientoConsultar {
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
            rs = st.executeQuery("SELECT * FROM planEntrenamiento");

            // Verificar si hay resultados
            if (!rs.next()) { 
                System.out.println("No hay plan de entrenamiento registrado.");
            } else {
                do {
                    // Extraer datos de la consulta
                    int idPlanEntrenamiento = rs.getInt("idPlanEntrenamiento");
                    int idCliente = rs.getInt("idCliente");
                    int idEmpleado = rs.getInt("idEmpleado");
                    String nombreEjercicios = rs.getString("nombreEjercicios");
                    int numSeries = rs.getInt("numSeries");
                    int numRepeticiones = rs.getInt("numRepeticiones");
                    int tiempoDescanso = rs.getInt("tiempoDescanso");

                    // Imprimir los datos
                    System.out.println(idPlanEntrenamiento + " | " + idCliente + " | " + idEmpleado + " | " + 
                                       nombreEjercicios + " | " + numSeries + " | " + numRepeticiones + " | " + 
                                       tiempoDescanso);

                } while (rs.next());
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(entrenamientoConsultar.class.getName()).log(Level.SEVERE, "Error cargando el driver", ex);
        } catch (SQLException ex) {
            Logger.getLogger(entrenamientoConsultar.class.getName()).log(Level.SEVERE, "Error en la consulta SQL", ex);
        } finally {
            // Cerrar recursos para evitar fugas de memoria
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (cn != null) cn.close();
            } catch (SQLException ex) {
                Logger.getLogger(entrenamientoConsultar.class.getName()).log(Level.SEVERE, "Error cerrando recursos", ex);
            }
        }
    }
}
