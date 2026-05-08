/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Ivaa
 */
public class DatabaseConnection {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/lostfound_kampus";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "";
    
    private static Connection connection = null;

    private DatabaseConnection() {}
    
    public static Connection getConnection() throws SQLException {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            }
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver MySQL tidak ditemukan!");
        }
        return connection;
    }
    
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                connection = null;
            }
        } catch (SQLException e) {
            System.err.println("Error tutup koneksi: " + e.getMessage());
        }
    }
}
