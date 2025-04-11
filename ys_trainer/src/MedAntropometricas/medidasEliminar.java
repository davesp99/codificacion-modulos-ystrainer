package medAntropometricas;

import conexion.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class medidasEliminar {

    public static void main(String[] args) {
        conexion con = new conexion();
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        int id_eliminar = 3; // ID del registro a eliminar

        // Instrucción SQL para eliminar un registro con PreparedStatement
        String sql = "DELETE FROM medAntropometricas WHERE idMedAntropometricas = ?";  // Usando el nombre correcto de la columna

        try {
            // Conectar a la base de datos
            cn = con.getConnection();
            pst = cn.prepareStatement(sql);
            pst.setInt(1, id_eliminar);

            // Ejecutar la eliminación
            int filasAfectadas = pst.executeUpdate();
            System.out.println("Registros eliminados: " + filasAfectadas);

            // Consulta para mostrar los registros restantes en la tabla "clientes"
            String consulta = "SELECT * FROM medAntropometricas";
            rs = pst.executeQuery(consulta);

            // Mostrar los datos
            while (rs.next()) {
            int idMedAntropometricas = rs.getInt("idMedAntropometricas");
            int idCliente = rs.getInt("idCliente");
            int idEmpleado = rs.getInt("idEmpleado");
            double imc = rs.getDouble("imc");
            double periCintura = rs.getDouble("periCintura");
            double periCadera = rs.getDouble("periCadera");
            double circuBrazo = rs.getDouble("circuBrazo");
            double circuMuslo = rs.getDouble("circuMuslo");
            double altura = rs.getDouble("altura");
            double pesoCorporal = rs.getDouble("pesoCorporal");
            double plieguesCutaneos = rs.getDouble("plieguesCutaneos");
            double masaGrasa = rs.getDouble("masaGrasa");
            double masaMuscular = rs.getDouble("masaMuscular");

            System.out.println("ID: " + idMedAntropometricas + " | Cliente: " + idCliente + " | Empleado: " + idEmpleado +
                    " | IMC: " + imc + " | Perímetro Cintura: " + periCintura + " | Perímetro Cadera: " + periCadera +
                    " | Circunferencia Brazo: " + circuBrazo + " | Circunferencia Muslo: " + circuMuslo +
                    " | Altura: " + altura + " | Peso Corporal: " + pesoCorporal +
                    " | Pliegues Cutáneos: " + plieguesCutaneos + " | Masa Grasa: " + masaGrasa +
                    " | Masa Muscular: " + masaMuscular);
}

        } catch (SQLException ex) {
            Logger.getLogger(medidasEliminar.class.getName()).log(Level.SEVERE, "Error SQL", ex);
        } finally {
            // Cerrar recursos
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (cn != null) cn.close();
            } catch (SQLException ex) {
                Logger.getLogger(medidasEliminar.class.getName()).log(Level.SEVERE, "Error cerrando conexión", ex);
            }
        }
    }
}
