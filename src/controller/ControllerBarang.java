/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import database.DatabaseConnection;
import model.ModelBarang;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Ivaa
 */
public class ControllerBarang {
    private Connection koneksi;

    public ControllerBarang() {
        try {
            koneksi = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            System.err.println("Gagal koneksi: " + e.getMessage());
        }
    }
    
    public boolean simpanBarang(ModelBarang barang) {
        if (barang.getJudul() == null || barang.getJudul().trim().isEmpty())
            throw new IllegalArgumentException("Nama barang tidak boleh kosong!");
        if (barang.getLokasi() == null || barang.getLokasi().trim().isEmpty())
            throw new IllegalArgumentException("Lokasi tidak boleh kosong!");
        
        try {
            String sql = "INSERT INTO barang (user_id, judul, kategori, deskripsi, lokasi, tanggal, jenis, status, pelapor_kontak) VALUES (?,?,?,?,?,?,?,'aktif',?)";
            PreparedStatement ps = koneksi.prepareStatement(sql);
            ps.setInt(1, barang.getUserId());
            ps.setString(2, barang.getJudul().trim());
            ps.setString(3, barang.getKategori());
            ps.setString(4, barang.getDeskripsi() != null ? barang.getDeskripsi() : "");
            ps.setString(5, barang.getLokasi().trim());
            ps.setString(6, barang.getTanggal());
            ps.setString(7, barang.getJenis());
            ps.setString(8, barang.getPelaporKontak() != null ? barang.getPelaporKontak() : "");
            int result = ps.executeUpdate();
            ps.close();
            return result > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error simpan barang: " + e.getMessage());
        }
    }
    
    public List<ModelBarang> getAllBarang() {
        List<ModelBarang> list = new ArrayList<>();
        try {
            Statement stmt = koneksi.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM barang ORDER BY created_at DESC");
            while (rs.next()) list.add(mapToBarang(rs));
            stmt.close();
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return list;
    }
    
    public List<ModelBarang> getBarangByJenis(String jenis) {
        List<ModelBarang> list = new ArrayList<>();
        try {
            PreparedStatement ps = koneksi.prepareStatement("SELECT * FROM barang WHERE jenis = ? ORDER BY created_at DESC");
            ps.setString(1, jenis);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(mapToBarang(rs));
            ps.close();
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return list;
    }
    
    public List<ModelBarang> cariBarang(String keyword) {
        List<ModelBarang> list = new ArrayList<>();
        try {
            PreparedStatement ps = koneksi.prepareStatement(
                "SELECT * FROM barang WHERE judul LIKE ? OR lokasi LIKE ? OR kategori LIKE ? ORDER BY created_at DESC");
            String kw = "%" + keyword + "%";
            ps.setString(1, kw); ps.setString(2, kw); ps.setString(3, kw);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(mapToBarang(rs));
            ps.close();
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return list;
    }
    
    public List<ModelBarang> getBarangByUser(int userId) {
        List<ModelBarang> list = new ArrayList<>();
        try {
            PreparedStatement ps = koneksi.prepareStatement(
                "SELECT * FROM barang WHERE user_id = ? ORDER BY created_at DESC");
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(mapToBarang(rs));
            ps.close();
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return list;
    }
    
    public ModelBarang getBarangById(int id) {
        try {
            PreparedStatement ps = koneksi.prepareStatement("SELECT * FROM barang WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) { ModelBarang b = mapToBarang(rs); ps.close(); return b; }
            ps.close();
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return null;
    }
    
    public boolean updateStatus(int id, String status) {
        try {
            PreparedStatement ps = koneksi.prepareStatement("UPDATE barang SET status = ? WHERE id = ?");
            ps.setString(1, status); ps.setInt(2, id);
            int r = ps.executeUpdate(); ps.close();
            return r > 0;
        } catch (SQLException e) { return false; }
    }
    
    public int countBarang(String jenis, String status) {
        try {
            PreparedStatement ps;
            if (status != null) {
                ps = koneksi.prepareStatement("SELECT COUNT(*) FROM barang WHERE jenis = ? AND status = ?");
                ps.setString(1, jenis); ps.setString(2, status);
            } else {
                ps = koneksi.prepareStatement("SELECT COUNT(*) FROM barang WHERE jenis = ?");
                ps.setString(1, jenis);
            }
            ResultSet rs = ps.executeQuery();
            if (rs.next()) { int c = rs.getInt(1); ps.close(); return c; }
            ps.close();
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return 0;
    }
    
    private ModelBarang mapToBarang(ResultSet rs) throws SQLException {
        ModelBarang b = new ModelBarang();
        b.setId(rs.getInt("id"));
        b.setUserId(rs.getInt("user_id"));
        b.setJudul(rs.getString("judul"));
        b.setKategori(rs.getString("kategori"));
        b.setDeskripsi(rs.getString("deskripsi"));
        b.setLokasi(rs.getString("lokasi"));
        b.setTanggal(rs.getString("tanggal"));
        b.setJenis(rs.getString("jenis"));
        b.setStatus(rs.getString("status"));
        b.setFotoPath(rs.getString("foto_path"));
        b.setPelaporKontak(rs.getString("pelapor_kontak"));
        b.setCreatedAt(rs.getString("created_at"));
        return b;
    }
}
