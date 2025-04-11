package medAntropometricas;

import conexion.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class medidasAgregar {
    public static void main(String[] args) {
        conexion con = new conexion();
        Connection cn;
        PreparedStatement ps;
        ResultSet rs;

        // Datos del cliente y empleado
        int idCliente = 1;  
        int idEmpleado = 2; 

        // Datos antropométricos
        double imc = 24.5;
        double periCintura = 85.2;
        double periCadera = 95.3;
        double circuBrazo = 32.1;
        double circuMuslo = 57.4;
        double altura = 1.75;
        double pesoCorporal = 72.5;
        double plieguesCutaneos = 12.4;
        double masaGrasa = 18.2;
        double masaMuscular = 34.7;

        String sql = "INSERT INTO MedAntropometricas (idCliente, idEmpleado, imc, periCintura, periCadera, circuBrazo, circuMuslo, altura, pesoCorporal, plieguesCutaneos, masaGrasa, masaMuscular) " + 
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            cn = con.getConnection(); // Obtener conexión
            if (cn != null) {
                ps = cn.prepareStatement(sql);
                ps.setInt(1, idCliente);
                ps.setInt(2, idEmpleado);
                ps.setDouble(3, imc);
                ps.setDouble(4, periCintura);
                ps.setDouble(5, periCadera);
                ps.setDouble(6, circuBrazo);
                ps.setDouble(7, circuMuslo);
                ps.setDouble(8, altura);
                ps.setDouble(9, pesoCorporal);
                ps.setDouble(10, plieguesCutaneos);
                ps.setDouble(11, masaGrasa);
                ps.setDouble(12, masaMuscular);

                int filasInsertadas = ps.executeUpdate();
                
                if (filasInsertadas > 0) {
                    System.out.println("✅ Inserción exitosa.");
                }

                // Consultar y mostrar los datos insertados
                String consulta = "SELECT * FROM MedAntropometricas";
                ps = cn.prepareStatement(consulta);
                rs = ps.executeQuery();

                while (rs.next()) {
                    System.out.println(
                        "ID Cliente: " + rs.getInt("idCliente") + " | " +
                        "ID Empleado: " + rs.getInt("idEmpleado") + " | IMC: " + rs.getDouble("imc") +
                        " | Perímetro Cintura: " + rs.getDouble("periCintura") +
                        " | Perímetro Cadera: " + rs.getDouble("periCadera") +
                        " | Circunferencia Brazo: " + rs.getDouble("circuBrazo") +
                        " | Circunferencia Muslo: " + rs.getDouble("circuMuslo") +
                        " | Altura: " + rs.getDouble("altura") +
                        " | Peso Corporal: " + rs.getDouble("pesoCorporal") +
                        " | Pliegues Cutáneos: " + rs.getDouble("plieguesCutaneos") +
                        " | Masa Grasa: " + rs.getDouble("masaGrasa") +
                        " | Masa Muscular: " + rs.getDouble("masaMuscular")
                    );
                }
            } else {
                System.out.println("❌ Error: No se pudo establecer la conexión.");
            }
        } catch (SQLException ex) {
            System.err.println("❌ Error SQL: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
