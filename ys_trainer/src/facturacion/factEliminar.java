package facturacion;

import conexion.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class factEliminar {  // ← Este nombre debe coincidir con el del archivo

    public static void main(String[] args) {
        conexion con = new conexion();
        Connection cn = null;
        PreparedStatement pst = null;
        PreparedStatement pstConsulta = null;
        ResultSet rs = null;

        int idFacturaEliminar = 2; // Cambia este valor según el ID que desees eliminar

        String sql = "DELETE FROM Facturacion WHERE idFactura = ?";

        try {
            cn = con.getConnection();

            pst = cn.prepareStatement(sql);
            pst.setInt(1, idFacturaEliminar);
            int filasAfectadas = pst.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("✅ Factura eliminada correctamente.");
            } else {
                System.out.println("⚠️ No se encontró la factura con ID: " + idFacturaEliminar);
            }

            String consulta = "SELECT * FROM Facturacion";
            pstConsulta = cn.prepareStatement(consulta);
            rs = pstConsulta.executeQuery();

            System.out.println("\n📋 Facturas restantes:");
            while (rs.next()) {
                int idFactura = rs.getInt("idFactura");
                int idCliente = rs.getInt("idCliente");
                String planCliente = rs.getString("planCliente");
                double monto = rs.getDouble("monto");
                String cicloInicio = rs.getString("cicloInicio");
                String cicloFin = rs.getString("cicloFin");
                String fechaHoraTransaccion = rs.getString("fechaHoraTransaccion");
                String medioPago = rs.getString("medioPago");
                String planInicio = rs.getString("planInicio");
                String planFin = rs.getString("planFin");

                System.out.println(idFactura + " | Cliente ID: " + idCliente + " | Plan: " + planCliente +
                        " | Monto: $" + monto + " | Ciclo: " + cicloInicio + " - " + cicloFin +
                        " | Fecha Transacción: " + fechaHoraTransaccion + " | Medio de pago: " + medioPago +
                        " | Vigencia: " + planInicio + " - " + planFin);
            }

        } catch (SQLException ex) {
            Logger.getLogger(factEliminar.class.getName()).log(Level.SEVERE, "❌ Error al eliminar la factura", ex);
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (pstConsulta != null) pstConsulta.close();
                if (cn != null) cn.close();
            } catch (SQLException ex) {
                Logger.getLogger(factEliminar.class.getName()).log(Level.SEVERE, "❌ Error al cerrar recursos", ex);
            }
        }
    }
}
