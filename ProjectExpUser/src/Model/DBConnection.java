package Model;

import java.sql.*;

public class DBConnection {

    // Database connection parameters
    static String bd = "deprojectfinal";
    static String port = "3306";
    static String login = "root";
    static String password = "Choco2004";
    static String url = "jdbc:mariadb://localhost:" + port + "/" + bd;

    Connection connection = null;

    public DBConnection() {
        try {// Load the MariaDB JDBC driver
            Class.forName("org.mariadb.jdbc.Driver");
            // Establish a database connection
            connection = DriverManager.getConnection(url, login, password);
            System.out.println("Conexion exitosa a la base de datos");
        } catch (ClassNotFoundException e) {
            System.err.println("Error al cargar el controlador JDBC: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Error al establecer la conexion: " + e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    // Close the database connection
    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Conexion cerrada");
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexion: " + e.getMessage());
            }
        }
    }
}
