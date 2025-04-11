package medAntropometricas;

import conexion.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class medidasEditar {
    public static void main(String[] args) {
        conexion con = new conexion();
        Connection cn = null;
        PreparedStatement pst = null;

        // ID de la medida antropométrica a actualizar (por defecto = 1)
        int idMedAntropometricas = 1;

        // Valores predefinidos
        double imc = 22.5;
        double periCintura = 85.0;
        double periCadera = 95.0;
        double circuBrazo = 30.0;
        double circuMuslo = 55.0;
        double altura = 1.75;
        double pesoCorporal = 70.0;
        double plieguesCutaneos = 12.5;
        double masaGrasa = 15.0;
        double masaMuscular = 40.0;

        try {
            // Establecer conexión
            cn = con.getConnection();

            // Consulta SQL parametrizada para actualizar los datos en MedAntropometricas
            String sql = "UPDATE MedAntropometricas SET imc=?, periCintura=?, periCadera=?, circuBrazo=?, circuMuslo=?, " +
                         "altura=?, pesoCorporal=?, plieguesCutaneos=?, masaGrasa=?, masaMuscular=? WHERE idMedAntropometricas=?";

            pst = cn.prepareStatement(sql);

            // Asignar valores a los parámetros
            pst.setDouble(1, imc);
            pst.setDouble(2, periCintura);
            pst.setDouble(3, periCadera);
            pst.setDouble(4, circuBrazo);
            pst.setDouble(5, circuMuslo);
            pst.setDouble(6, altura);
            pst.setDouble(7, pesoCorporal);
            pst.setDouble(8, plieguesCutaneos);
            pst.setDouble(9, masaGrasa);
            pst.setDouble(10, masaMuscular);
            pst.setInt(11, idMedAntropometricas);

            // Ejecutar la actualización
            int filasActualizadas = pst.executeUpdate();

            if (filasActualizadas > 0) {
                System.out.println("Registro actualizado correctamente.");
            } else {
                System.out.println("No se encontró el registro con ID: " + idMedAntropometricas);
            }

        } catch (SQLException ex) {
            Logger.getLogger(medidasEditar.class.getName()).log(Level.SEVERE, "Error en la actualización SQL", ex);
        } finally {
            // Cerrar recursos
            try {
                if (pst != null) pst.close();
                if (cn != null) cn.close();
            } catch (SQLException ex) {
                Logger.getLogger(medidasEditar.class.getName()).log(Level.SEVERE, "Error cerrando recursos", ex);
            }
        }
    }
}
