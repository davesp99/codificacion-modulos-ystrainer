package reserva;

import conexion.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class reservaEliminar {  // ← Asegúrate de que el nombre del archivo sea reservaEliminar.java

    public static void main(String[] args) {
        conexion con = new conexion();
        Connection cn = null;
        PreparedStatement pst = null;
        PreparedStatement pstConsulta = null;
        ResultSet rs = null;

        int idReservaEliminar = 2; // Cambia este valor según la reserva que desees eliminar

        String sql = "DELETE FROM Reserva WHERE idReserva = ?";

        try {
            cn = con.getConnection();

            pst = cn.prepareStatement(sql);
            pst.setInt(1, idReservaEliminar);
            int filasAfectadas = pst.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("✅ Reserva eliminada correctamente.");
            } else {
                System.out.println("⚠️ No se encontró la reserva con ID: " + idReservaEliminar);
            }

            // Consulta y muestra todas las reservas restantes
            String consulta = "SELECT * FROM Reserva";
            pstConsulta = cn.prepareStatement(consulta);
            rs = pstConsulta.executeQuery();

            System.out.println("\n📋 Reservas restantes:");
            while (rs.next()) {
                int idReserva = rs.getInt("idReserva");
                int idCliente = rs.getInt("idCliente");
                int idEmpleado = rs.getInt("idEmpleado");
                int contadorReserva = rs.getInt("contadorReserva");
                String nombreEspecialista = rs.getString("nombreEspecialista");
                String fechaHoraReserva = rs.getString("fechaHoraReserva");
                String emailEnviado = rs.getString("emailEnviado");
                String tipoEspecialista = rs.getString("tipoEspecialista");

                System.out.println("ID Reserva: " + idReserva +
                        " | Cliente ID: " + idCliente +
                        " | Empleado ID: " + idEmpleado +
                        " | Contador: " + contadorReserva +
                        " | Especialista: " + nombreEspecialista +
                        " | Fecha/Hora: " + fechaHoraReserva +
                        " | Email: " + emailEnviado +
                        " | Tipo: " + tipoEspecialista);
            }

        } catch (SQLException ex) {
            Logger.getLogger(reservaEliminar.class.getName()).log(Level.SEVERE, "❌ Error al eliminar la reserva", ex);
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (pstConsulta != null) pstConsulta.close();
                if (cn != null) cn.close();
            } catch (SQLException ex) {
                Logger.getLogger(reservaEliminar.class.getName()).log(Level.SEVERE, "❌ Error al cerrar recursos", ex);
            }
        }
    }
}
