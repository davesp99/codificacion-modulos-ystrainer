package reserva;

import conexion.conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

public class reservaConsultar {
    public static void main(String[] args) {
        conexion con = new conexion();
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            // Cargar el driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establecer conexión
            cn = con.getConnection();
            st = cn.createStatement();
            rs = st.executeQuery("SELECT * FROM Reserva");

            // Verificar si hay resultados
            if (!rs.isBeforeFirst()) {
                System.out.println("No hay reservas registradas.");
            } else {
                while (rs.next()) {
                    int idReserva = rs.getInt("idReserva");
                    int idCliente = rs.getInt("idCliente");
                    int idEmpleado = rs.getInt("idEmpleado");
                    int contadorReserva = rs.getInt("contadorReserva");
                    String nombreEspecialista = rs.getString("nombreEspecialista");
                    Timestamp fechaHoraReserva = rs.getTimestamp("fechaHoraReserva");
                    String emailEnviado = rs.getString("emailEnviado");
                    String tipoEspecialista = rs.getString("tipoEspecialista");

                    System.out.println(
                        "Reserva ID: " + idReserva +
                        " | Cliente ID: " + idCliente +
                        " | Empleado ID: " + idEmpleado +
                        " | Contador: " + contadorReserva +
                        " | Especialista: " + nombreEspecialista +
                        " | Fecha y Hora: " + fechaHoraReserva +
                        " | Email: " + emailEnviado +
                        " | Tipo Especialista: " + tipoEspecialista
                    );
                }
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(reservaConsultar.class.getName()).log(Level.SEVERE, "❌ Error cargando el driver", ex);
        } catch (SQLException ex) {
            Logger.getLogger(reservaConsultar.class.getName()).log(Level.SEVERE, "❌ Error en la consulta SQL", ex);
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (cn != null) cn.close();
            } catch (SQLException ex) {
                Logger.getLogger(reservaConsultar.class.getName()).log(Level.SEVERE, "❌ Error cerrando recursos", ex);
            }
        }
    }
}
