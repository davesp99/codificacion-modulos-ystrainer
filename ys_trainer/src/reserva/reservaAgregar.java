package reserva;

import conexion.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

public class reservaAgregar {
    public static void main(String[] args) {
        conexion con = new conexion();
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        // Datos para insertar en la tabla Reserva
        int idCliente = 1;
        int idEmpleado = 3;
        int contadorReserva = 1; // Puedes incrementar este valor lógicamente si se repiten reservas
        String nombreEspecialista = "Dra. Patricia Gómez";
        LocalDateTime fechaHoraReserva = LocalDateTime.now();
        String emailEnviado = "Confirmación enviada al correo del cliente.";
        String tipoEspecialista = "Nutricionista";

        // Consulta SQL para insertar los datos
        String sqlInsert = "INSERT INTO Reserva (idCliente, idEmpleado, contadorReserva, nombreEspecialista, " +
                           "fechaHoraReserva, emailEnviado, tipoEspecialista) " +
                           "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            cn = con.getConnection();
            pst = cn.prepareStatement(sqlInsert);

            // Asignar valores
            pst.setInt(1, idCliente);
            pst.setInt(2, idEmpleado);
            pst.setInt(3, contadorReserva);
            pst.setString(4, nombreEspecialista);
            pst.setTimestamp(5, Timestamp.valueOf(fechaHoraReserva));
            pst.setString(6, emailEnviado);
            pst.setString(7, tipoEspecialista);

            int resultado = pst.executeUpdate();

            if (resultado > 0) {
                System.out.println("✅ Reserva insertada correctamente.");
            }

            // Consulta para mostrar todos los registros
            String sqlSelect = "SELECT * FROM Reserva";
            pst = cn.prepareStatement(sqlSelect);
            rs = pst.executeQuery();

            System.out.println("\n📄 Registros en la tabla Reserva:");
            while (rs.next()) {
                System.out.println(
                    "ID Reserva: " + rs.getInt("idReserva") +
                    " | Cliente ID: " + rs.getInt("idCliente") +
                    " | Empleado ID: " + rs.getInt("idEmpleado") +
                    " | Contador: " + rs.getInt("contadorReserva") +
                    " | Especialista: " + rs.getString("nombreEspecialista") +
                    " | Fecha y Hora: " + rs.getTimestamp("fechaHoraReserva") +
                    " | Email: " + rs.getString("emailEnviado") +
                    " | Tipo: " + rs.getString("tipoEspecialista")
                );
            }

        } catch (SQLException ex) {
            Logger.getLogger(reservaAgregar.class.getName()).log(Level.SEVERE, "❌ Error al insertar o consultar en Reserva", ex);
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (cn != null) cn.close();
            } catch (SQLException ex) {
                Logger.getLogger(reservaAgregar.class.getName()).log(Level.SEVERE, "❌ Error cerrando recursos", ex);
            }
        }
    }
}
