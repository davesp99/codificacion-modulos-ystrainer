package medAntropometricas;

import conexion.conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class medidasConsultar {
    public static void main(String[] args) {
        conexion con = new conexion();
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            // Cargar el driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establecer conexión
            cn = con.getConnection(); // Asegúrate de que este método existe en la clase 'conexion'
            st = cn.createStatement();
            rs = st.executeQuery("SELECT * FROM MedAntropometricas"); // Nombre correcto de la tabla

            // Verificar si hay resultados antes de iterar
            if (!rs.isBeforeFirst()) {
                System.out.println("No hay registros de medidas antropométricas.");
            } else {
                while (rs.next()) {
                    // Extraer datos de la consulta
                    int id = rs.getInt("idMedAntropometricas");
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

                    // Imprimir los datos
                    System.out.println("ID: " + id + " | Cliente: " + idCliente + " | Empleado: " + idEmpleado +
                            " | IMC: " + imc + " | Perímetro Cintura: " + periCintura + " | Perímetro Cadera: " + periCadera +
                            " | Circunferencia Brazo: " + circuBrazo + " | Circunferencia Muslo: " + circuMuslo +
                            " | Altura: " + altura + " | Peso Corporal: " + pesoCorporal +
                            " | Pliegues Cutáneos: " + plieguesCutaneos + " | Masa Grasa: " + masaGrasa +
                            " | Masa Muscular: " + masaMuscular);
                }
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(medidasConsultar.class.getName()).log(Level.SEVERE, "Error cargando el driver", ex);
        } catch (SQLException ex) {
            Logger.getLogger(medidasConsultar.class.getName()).log(Level.SEVERE, "Error en la consulta SQL", ex);
        } finally {
            // Cerrar recursos para evitar fugas de memoria
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (cn != null) cn.close();
            } catch (SQLException ex) {
                Logger.getLogger(medidasConsultar.class.getName()).log(Level.SEVERE, "Error cerrando recursos", ex);
            }
        }
    }
}
