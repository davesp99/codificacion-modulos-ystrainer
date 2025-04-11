package facturacion;

import conexion.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class factAgregar {
    public static void main(String[] args) {
        conexion con = new conexion();
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        // Datos del cliente para insertar en la tabla Facturacion
        int idCliente = 1;
        String planCliente = "Mensual";
        double monto = 59.99;
        Date cicloInicio = Date.valueOf("2025-04-01");
        Date cicloFin = Date.valueOf("2025-04-30");
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        String medioPago = "Tarjeta";
        Date planInicio = Date.valueOf("2025-04-01");
        Date planFin = Date.valueOf("2025-07-01");

        // Consulta SQL para insertar los datos
        String sqlInsert = "INSERT INTO Facturacion (idCliente, planCliente, monto, cicloInicio, cicloFin, " +
                           "fechaHoraTransaccion, medioPago, planInicio, planFin) " +
                           "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            cn = con.getConnection();
            pst = cn.prepareStatement(sqlInsert);

            // Asignar valores al PreparedStatement
            pst.setInt(1, idCliente);
            pst.setString(2, planCliente);
            pst.setDouble(3, monto);
            pst.setDate(4, cicloInicio);
            pst.setDate(5, cicloFin);
            pst.setString(6, fechaHoraActual.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            pst.setString(7, medioPago);
            pst.setDate(8, planInicio);
            pst.setDate(9, planFin);

            int resultado = pst.executeUpdate();

            if (resultado > 0) {
                System.out.println("✅ Registro insertado correctamente en Facturacion.");
            }

            // Consulta para mostrar todos los registros
            String sqlSelect = "SELECT * FROM Facturacion";
            pst = cn.prepareStatement(sqlSelect);
            rs = pst.executeQuery();

            System.out.println("\n📄 Registros en la tabla Facturacion:");
            while (rs.next()) {
                System.out.println(
                    "ID Factura: " + rs.getInt("idFactura") +
                    " | Cliente ID: " + rs.getInt("idCliente") +
                    " | Plan: " + rs.getString("planCliente") +
                    " | Monto: $" + rs.getDouble("monto") +
                    " | Ciclo: " + rs.getDate("cicloInicio") + " a " + rs.getDate("cicloFin") +
                    " | Fecha Transacción: " + rs.getString("fechaHoraTransaccion") +
                    " | Medio de pago: " + rs.getString("medioPago") +
                    " | Plan Vigencia: " + rs.getDate("planInicio") + " a " + rs.getDate("planFin")
                );
            }

        } catch (SQLException ex) {
            Logger.getLogger(factAgregar.class.getName()).log(Level.SEVERE, "❌ Error al insertar o consultar en Facturacion", ex);
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (cn != null) cn.close();
            } catch (SQLException ex) {
                Logger.getLogger(factAgregar.class.getName()).log(Level.SEVERE, "❌ Error cerrando recursos", ex);
            }
        }
    }
}
