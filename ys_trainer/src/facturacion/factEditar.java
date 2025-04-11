package facturacion;

import conexion.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

public class factEditar {
    public static void main(String[] args) {
        conexion con = new conexion();
        Connection cn = null;
        PreparedStatement pst = null;

        // ID de la factura a actualizar
        int idFactura = 1; // Asegúrate de que exista en la base de datos

        // Nuevos datos
        int idCliente = 4;
        String planCliente = "Premium";
        double monto = 129.99;
        Date cicloInicio = Date.valueOf("2025-04-01");
        Date cicloFin = Date.valueOf("2025-04-30");
        Timestamp fechaHoraTransaccion = Timestamp.valueOf(LocalDateTime.now());
        String medioPago = "Transferencia";
        Date planInicio = Date.valueOf("2025-04-01");
        Date planFin = Date.valueOf("2025-07-01");

        // Consulta SQL para actualizar los datos
        String sql = "UPDATE Facturacion SET idCliente=?, planCliente=?, monto=?, cicloInicio=?, cicloFin=?, " +
                     "fechaHoraTransaccion=?, medioPago=?, planInicio=?, planFin=? WHERE idFactura=?";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = con.getConnection();
            pst = cn.prepareStatement(sql);

            // Establecer parámetros
            pst.setInt(1, idCliente);
            pst.setString(2, planCliente);
            pst.setDouble(3, monto);
            pst.setDate(4, cicloInicio);
            pst.setDate(5, cicloFin);
            pst.setTimestamp(6, fechaHoraTransaccion);
            pst.setString(7, medioPago);
            pst.setDate(8, planInicio);
            pst.setDate(9, planFin);
            pst.setInt(10, idFactura);

            int filasAfectadas = pst.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("✅ Factura actualizada correctamente.");
            } else {
                System.out.println("⚠️ No se encontró la factura con ID: " + idFactura);
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(factEditar.class.getName()).log(Level.SEVERE, "Error actualizando la factura", ex);
        } finally {
            try {
                if (pst != null) pst.close();
                if (cn != null) cn.close();
            } catch (SQLException ex) {
                Logger.getLogger(factEditar.class.getName()).log(Level.SEVERE, "Error cerrando recursos", ex);
            }
        }
    }
}
