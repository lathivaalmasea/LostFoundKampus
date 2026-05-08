/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import database.DatabaseConnection;
import model.ModelKlaim;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Ivaa
 */
public class ControllerKlaim {
    private Connection koneksi;

    public ControllerKlaim() {
        try {
            koneksi = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            System.err.println("Gagal koneksi: " + e.getMessage());
        }
    }
    
    public boolean kirimKlaim(ModelKlaim klaim) {
        if (klaim.getAlasan() == null || klaim.getAlasan().trim().isEmpty())
            throw new IllegalArgumentException("Alasan klaim tidak boleh kosong!");
        if (klaim.getKontak() == null || klaim.getKontak().trim().isEmpty())
            throw new IllegalArgumentException("Kontak tidak boleh kosong!");

        try {
            String sql = "INSERT INTO klaim (barang_id, pemohon_id, alasan, kontak, status) VALUES (?,?,?,?,'menunggu')";
            PreparedStatement ps = koneksi.prepareStatement(sql);
            ps.setInt(1, klaim.getBarangId());
            ps.setInt(2, klaim.getPemohonId());
            ps.setString(3, klaim.getAlasan().trim());
            ps.setString(4, klaim.getKontak().trim());
            int result = ps.executeUpdate();
            ps.close();
            
            if (result > 0) {
                new ControllerBarang().updateStatus(klaim.getBarangId(), "proses");
            }
            return result > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error klaim: " + e.getMessage());
        }
    }
    
    public List<ModelKlaim> getKlaimByUser(int userId) {
        List<ModelKlaim> list = new ArrayList<>();
        try {
            String sql = "SELECT k.*, b.judul AS judul_barang, u.nama_lengkap AS nama_pemohon " +
                         "FROM klaim k JOIN barang b ON k.barang_id = b.id " +
                         "JOIN users u ON k.pemohon_id = u.id " +
                         "WHERE k.pemohon_id = ? ORDER BY k.created_at DESC";
            PreparedStatement ps = koneksi.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ModelKlaim k = new ModelKlaim();
                k.setId(rs.getInt("id"));
                k.setBarangId(rs.getInt("barang_id"));
                k.setPemohonId(rs.getInt("pemohon_id"));
                k.setAlasan(rs.getString("alasan"));
                k.setKontak(rs.getString("kontak"));
                k.setStatus(rs.getString("status"));
                k.setCreatedAt(rs.getString("created_at"));
                k.setJudulBarang(rs.getString("judul_barang"));
                k.setNamaPemohon(rs.getString("nama_pemohon"));
                list.add(k);
            }
            ps.close();
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return list;
    }
    
    public boolean updateStatusKlaim(int klaimId, String status) {
        try {
            PreparedStatement ps = koneksi.prepareStatement("UPDATE klaim SET status = ? WHERE id = ?");
            ps.setString(1, status); ps.setInt(2, klaimId);
            int r = ps.executeUpdate(); ps.close();
            return r > 0;
        } catch (SQLException e) { return false; }
    }
}
