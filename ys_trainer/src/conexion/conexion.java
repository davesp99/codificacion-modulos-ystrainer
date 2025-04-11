package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexion {
    private Connection con;

    public conexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Cargar el driver
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ysTrainer", "root", "Damasco47*");
            System.out.println("✅ Conexión exitosa a la base de datos!");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("❌ No Conectado: " + e.getMessage());
        }
    }

    // CORREGIDO: Nombre del método a "getConnection()"
    public Connection getConnection() {
        return con;
    }
    

    public static void main(String[] args) {
        new conexion(); // Prueba la conexión
    }
}
