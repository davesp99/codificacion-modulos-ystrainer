package acPsicologico;

import conexion.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class acAgregar {
    public static void main(String[] args) {
        conexion con = new conexion();
        Connection cn;
        PreparedStatement pst;
        ResultSet rs;

        // Datos del cliente
        int idCliente = 1;  // ID de cliente de ejemplo
        int idEmpleado = 2; // ID de empleado de ejemplo
        String antecedentes = "Antecedente 1, antecedente 2";
        String recomendaciones = "Recomendaciones 1, recomendaciones 2";
        String notas = "Notas 1, notas 2, notas 3";
        
        // Consulta SQL para insertar los datos
        String sqlInsert = "INSERT INTO PlanAcPsicologico (idCliente, idEmpleado, antecedentes, recomendaciones, notas) " +
                           "VALUES (?, ?, ?, ?, ?)";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = con.getConnection();
            
            // Usar PreparedStatement para evitar inyección SQL
            pst = cn.prepareStatement(sqlInsert);
            pst.setInt(1, idCliente);
            pst.setInt(2, idEmpleado);
            pst.setString(3, antecedentes);
            pst.setString(4, recomendaciones);
            pst.setString(5, notas);
            pst.executeUpdate();

            System.out.println("✅ Datos insertados correctamente.");

            // Consultar y mostrar los datos insertados
            String sqlSelect = "SELECT * FROM PlanAcPsicologico";
            pst = cn.prepareStatement(sqlSelect);
            rs = pst.executeQuery();

            while (rs.next()) {
                System.out.println(
                    "ID: " + rs.getInt("idPlanAcPsicologico") + " | " +
                    "Cliente ID: " + rs.getInt("idCliente") + " | " +
                    "Empleado ID: " + rs.getInt("idEmpleado") + " | " +
                    "Antecedentes: " + rs.getString("antecedentes") + " | " +
                    "Recomendaciones: " + rs.getString("recomendaciones") + " | " +
                    "Notas: " + rs.getString("notas")
                );
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(acAgregar.class.getName()).log(Level.SEVERE, "Error cargando el driver", ex);
        } catch (SQLException ex) {
            Logger.getLogger(acAgregar.class.getName()).log(Level.SEVERE, "Error en la consulta SQL", ex);
        }
    }
}
