package planAlimenticio;

import conexion.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class alimenticioConsultar {
    public static void main(String[] args) {
        conexion con = new conexion();
        Connection cn = null;
        PreparedStatement pst = null;

        try {
            cn = con.getConnection();

            // Consulta SQL para insertar directamente en la tabla PlanAlimenticio
            String sqlInsert = "INSERT INTO PlanAlimenticio (" +
                    "idCliente, idEmpleado, nombreComida, ingredientes, preparacion, " +
                    "preferenciaVeg, restriccionDiet, alergia, intolerancia, cantidades) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            pst = cn.prepareStatement(sqlInsert);

            // 🔽 Aquí defines los datos manualmente o con Scanner si prefieres
            pst.setInt(1, 1); // idCliente
            pst.setInt(2, 101); // idEmpleado
            pst.setString(3, "Ensalada César");
            pst.setString(4, "Lechuga, pollo, croutons");
            pst.setString(5, "Mezclar todos los ingredientes");
            pst.setString(6, "No");
            pst.setString(7, "Baja en carbohidratos");
            pst.setString(8, "Ninguna");
            pst.setString(9, "Lactosa");
            pst.setString(10, "1 porción");

            int resultado = pst.executeUpdate();

            if (resultado > 0) {
                System.out.println("✅ Registro insertado correctamente en PlanAlimenticio.");
            }

        } catch (SQLException ex) {
            Logger.getLogger(alimenticioConsultar.class.getName()).log(Level.SEVERE, "❌ Error al insertar en PlanAlimenticio", ex);
        } finally {
            try {
                if (pst != null) pst.close();
                if (cn != null) cn.close();
            } catch (SQLException ex) {
                Logger.getLogger(alimenticioConsultar.class.getName()).log(Level.SEVERE, "❌ Error cerrando recursos", ex);
            }
        }
    }
}
