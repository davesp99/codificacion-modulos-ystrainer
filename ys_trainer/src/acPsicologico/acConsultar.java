package acPsicologico;

import conexion.conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class acConsultar {
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
            rs = st.executeQuery("SELECT * FROM PlanAcPsicologico");

            if (!rs.next()) {
                System.out.println("No hay plan de acción psicológica registrado.");
            } else {
                do {
                    int idPlan = rs.getInt("idPlanAcPsicologico");
                    int idCliente = rs.getInt("idCliente");
                    int idEmpleado = rs.getInt("idEmpleado");
                    String antecedentes = rs.getString("antecedentes");
                    String recomendaciones = rs.getString("recomendaciones");
                    String notas = rs.getString("notas");

                    // Imprimir los datos
                    System.out.println(idPlan + " | " + idCliente + " | " + idEmpleado + " | ");
                    System.out.println("Antecedentes: " + antecedentes);
                    System.out.println("Recomendaciones: " + recomendaciones);
                    System.out.println("Notas: " + notas);
                    System.out.println("------------------------------------------------------");

                } while (rs.next());
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(acConsultar.class.getName()).log(Level.SEVERE, "Error cargando el driver", ex);
        } catch (SQLException ex) {
            Logger.getLogger(acConsultar.class.getName()).log(Level.SEVERE, "Error en la consulta SQL", ex);
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (cn != null) cn.close();
            } catch (SQLException ex) {
                Logger.getLogger(acConsultar.class.getName()).log(Level.SEVERE, "Error cerrando recursos", ex);
            }
        }
    }
}
