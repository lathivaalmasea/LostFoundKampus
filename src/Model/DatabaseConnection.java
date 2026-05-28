/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Ivaa
 */


import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    private static Connection connection;

    public static Connection getConnection(){

        try {
            String jdbc_driver = "com.mysql.cj.jdbc.Driver";
            String nama_db = "lostfoundkampus";
            String url = "jdbc:mysql://localhost:3306/" + nama_db;
            String user = "root";
            String pass = "";

            DriverManager.registerDriver(
                    new com.mysql.cj.jdbc.Driver()
            );

            connection =
                    DriverManager.getConnection(
                            url,
                            user,
                            pass
                    );

        } catch (Exception e) {

            System.out.println(
                    "Koneksi gagal : "
                    + e.getMessage()
            );
        }

        return connection;
    }
}