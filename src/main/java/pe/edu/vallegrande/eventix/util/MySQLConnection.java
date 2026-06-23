package pe.edu.vallegrande.eventix.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {
    
    private static final String URL = "jdbc:mysql://localhost:3306/eventix_db";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver MySQL no encontrado", e);
        }
    }
    
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
