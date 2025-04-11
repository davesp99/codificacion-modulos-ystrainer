package facturacion;

import conexion.conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

public class factConsultar {
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
            rs = st.executeQuery("SELECT * FROM Facturacion");

            // Verificar si hay resultados antes de iterar
            if (!rs.isBeforeFirst()) {
                System.out.println("No hay facturación registrada.");
            } else {
                while (rs.next()) {
                    int idFactura = rs.getInt("idFactura");
                    int idCliente = rs.getInt("idCliente");
                    String planCliente = rs.getString("planCliente");
                    double monto = rs.getDouble("monto");
                    Date cicloInicio = rs.getDate("cicloInicio");
                    Date cicloFin = rs.getDate("cicloFin");
                    Timestamp fechaHoraTransaccion = rs.getTimestamp("fechaHoraTransaccion");
                    String medioPago = rs.getString("medioPago");
                    Date planInicio = rs.getDate("planInicio");
                    Date planFin = rs.getDate("planFin");

                    System.out.println(
                        "Factura ID: " + idFactura +
                        " | Cliente ID: " + idCliente +
                        " | Plan: " + planCliente +
                        " | Monto: $" + monto +
                        " | Ciclo: " + cicloInicio + " a " + cicloFin +
                        " | Fecha/Hora Transacción: " + fechaHoraTransaccion +
                        " | Medio de Pago: " + medioPago +
                        " | Plan Inicio: " + planInicio +
                        " | Plan Fin: " + planFin
                    );
                }
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(factConsultar.class.getName()).log(Level.SEVERE, "Error cargando el driver", ex);
        } catch (SQLException ex) {
            Logger.getLogger(factConsultar.class.getName()).log(Level.SEVERE, "Error en la consulta SQL", ex);
        } finally {
            // Cerrar recursos
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (cn != null) cn.close();
            } catch (SQLException ex) {
                Logger.getLogger(factConsultar.class.getName()).log(Level.SEVERE, "Error cerrando recursos", ex);
            }
        }
    }
}
