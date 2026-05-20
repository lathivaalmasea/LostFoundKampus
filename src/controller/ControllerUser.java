/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import database.DatabaseConnection;
import model.ModelUser;
import java.sql.*;
/**
 *
 * @author Ivaa
 */
public class ControllerUser {
   private Connection koneksi;
    private static ModelUser userAktif = null;

    public ControllerUser() {
        try {
            koneksi = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            System.err.println("Gagal koneksi: " + e.getMessage());
        }
    }
    
    public ModelUser login(String username, String password) {
        if (username == null || username.trim().isEmpty())
            throw new IllegalArgumentException("Username tidak boleh kosong!");
        if (password == null || password.trim().isEmpty())
            throw new IllegalArgumentException("Password tidak boleh kosong!");

        try {
            String sql = "SELECT * FROM users WHERE username = ? "
                    + "AND password = ?";
            PreparedStatement ps = koneksi.prepareStatement(sql);
            ps.setString(1, username.trim());
            ps.setString(2, password.trim());
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                ModelUser user = new ModelUser();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setNamaLengkap(rs.getString("nama_lengkap"));
                user.setEmail(rs.getString("email"));
                user.setNoHp(rs.getString("no_hp"));
                userAktif = user;
                ps.close();
                return user;
            }
            ps.close();
            return null;
            
        } catch (SQLException e) {
            throw new RuntimeException("Error login: " + e.getMessage());
        }
    }
    
    public boolean register(ModelUser user) {
        if (user.getUsername() == null || user.getUsername().trim().isEmpty())
            throw new IllegalArgumentException("Username tidak boleh kosong!");
        if (user.getPassword() == null || user.getPassword().length() < 4)
            throw new IllegalArgumentException("Password minimal 4 karakter!");
        if (user.getNamaLengkap() == null || user.getNamaLengkap().trim().isEmpty())
            throw new IllegalArgumentException("Nama lengkap tidak boleh kosong!");
        
        try {
            String cekSql = "SELECT id FROM users WHERE username = ?";
            PreparedStatement cekPs = koneksi.prepareStatement(cekSql);
            cekPs.setString(1, user.getUsername().trim());
            if (cekPs.executeQuery().next()) {
                cekPs.close();
                throw new IllegalArgumentException("Username sudah digunakan!");
            }
            cekPs.close();
            
            String sql = "INSERT INTO users (username, password, nama_lengkap, "
                    + "email, no_hp) VALUES (?,?,?,?,?)";
            PreparedStatement ps = koneksi.prepareStatement(sql);
            ps.setString(1, user.getUsername().trim());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getNamaLengkap().trim());
            ps.setString(4, user.getEmail() != null ? user.getEmail() : "");
            ps.setString(5, user.getNoHp() != null ? user.getNoHp() : "");
            int result = ps.executeUpdate();
            ps.close();
            return result > 0;
            
        } catch (SQLException e) {
            throw new RuntimeException("Error register: " + e.getMessage());
        }
    }
    
    public void logout() { userAktif = null; }
    public static ModelUser getUserAktif() { return userAktif; }
    public static void setUserAktif(ModelUser user) { userAktif = user; }
}