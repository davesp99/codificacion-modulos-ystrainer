package reserva;

import conexion.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

public class reservaEditar {
    public static void main(String[] args) {
        conexion con = new conexion();
        Connection cn = null;
        PreparedStatement pst = null;

        // ID de la reserva a actualizar
        int idReserva = 1; // Asegúrate de que este ID exista en la base de datos

        // Nuevos datos
        int idCliente = 2;
        int idEmpleado = 5;
        int contadorReserva = 2;
        String nombreEspecialista = "Dr. Roberto Martínez";
        Timestamp fechaHoraReserva = Timestamp.valueOf(LocalDateTime.of(2025, 4, 10, 10, 30));
        String emailEnviado = "Email de recordatorio reenviado.";
        String tipoEspecialista = "Psicólogo";

        // Consulta SQL para actualizar la reserva
        String sql = "UPDATE Reserva SET idCliente=?, idEmpleado=?, contadorReserva=?, nombreEspecialista=?, " +
                     "fechaHoraReserva=?, emailEnviado=?, tipoEspecialista=? WHERE idReserva=?";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = con.getConnection();
            pst = cn.prepareStatement(sql);

            // Establecer parámetros
            pst.setInt(1, idCliente);
            pst.setInt(2, idEmpleado);
            pst.setInt(3, contadorReserva);
            pst.setString(4, nombreEspecialista);
            pst.setTimestamp(5, fechaHoraReserva);
            pst.setString(6, emailEnviado);
            pst.setString(7, tipoEspecialista);
            pst.setInt(8, idReserva);

            int filasAfectadas = pst.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("✅ Reserva actualizada correctamente.");
            } else {
                System.out.println("⚠️ No se encontró la reserva con ID: " + idReserva);
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(reservaEditar.class.getName()).log(Level.SEVERE, "❌ Error actualizando la reserva", ex);
        } finally {
            try {
                if (pst != null) pst.close();
                if (cn != null) cn.close();
            } catch (SQLException ex) {
                Logger.getLogger(reservaEditar.class.getName()).log(Level.SEVERE, "❌ Error cerrando recursos", ex);
            }
        }
    }
}
