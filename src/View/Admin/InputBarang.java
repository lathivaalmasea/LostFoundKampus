/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.Admin;

import Controller.ControllerBarang;
import Model.Barang.ModelBarang;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author Ivaa
 */
public class InputBarang {
    JTextField txtNamaBarang;
    JTextField txtKategori;
    JTextArea txtDeskripsi;
    JTextField txtLokasi;
    JComboBox cbStatus;
    JTextField txtTanggal;

    JButton btnSimpan;
    JButton btnReset;

    public InputBarang() {
        setTitle("Input Barang");
        setSize(500,500);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel title = new JLabel("INPUT BARANG");
        title.setFont(new Font("Segoe UI",Font.BOLD,20));
        title.setBounds(150,20,250,30);

        JLabel lblNama = new JLabel("Nama Barang");
        lblNama.setBounds(50,80,120,25);

        txtNamaBarang = new JTextField();
        txtNamaBarang.setBounds(180,80,220,25);

        JLabel lblKategori = new JLabel("Kategori");
        lblKategori.setBounds(50,120,120,25);

        txtKategori = new JTextField();
        txtKategori.setBounds(180,120,220,25);

        JLabel lblDeskripsi = new JLabel("Deskripsi");
        lblDeskripsi.setBounds(50,160,120,25);

        txtDeskripsi = new JTextArea();
        JScrollPane sp = new JScrollPane(txtDeskripsi);
        sp.setBounds(180,160,220,80);

        JLabel lblLokasi = new JLabel("Lokasi");
        lblLokasi.setBounds(50,260,120,25);

        txtLokasi = new JTextField();
        txtLokasi.setBounds(180,260,220,25);

        JLabel lblStatus = new JLabel("Status");
        lblStatus.setBounds(50,300,120,25);
        
        cbStatus = new JComboBox(new String[]{
            "Hilang", "Ditemukan"
        });
        
        cbStatus.setBounds(180,300,220,25);

        JLabel lblTanggal = new JLabel("Tanggal");
        lblTanggal.setBounds(50,340,120,25);

        txtTanggal = new JTextField();
        txtTanggal.setBounds(180,340,220,25);

        btnSimpan = new JButton("SIMPAN");
        btnSimpan.setBounds(100,400,120,35);

        btnReset = new JButton("RESET");
        btnReset.setBounds(250,400,120,35);
        
        panel.add(title);
        panel.add(lblNama);
        panel.add(txtNamaBarang);
        panel.add(lblKategori);
        panel.add(txtKategori);
        panel.add(lblDeskripsi);
        panel.add(sp);
        panel.add(lblLokasi);
        panel.add(txtLokasi);
        panel.add(lblStatus);
        panel.add(cbStatus);
        panel.add(lblTanggal);
        panel.add(txtTanggal);
        panel.add(btnSimpan);
        panel.add(btnReset);

        add(panel);

        btnSimpan.addActionListener(e -> simpanData());
        
        btnReset.addActionListener(e -> resetForm());
    }
    
    private void SimpanData() {
        ModelBarang barang = new ModelBarang();

        barang.setNamaBarang(
                txtNamaBarang.getText()
        );

        barang.setKategori(
                txtKategori.getText()
        );

        barang.setDeskripsi(
                txtDeskripsi.getText()
        );

        barang.setLokasi(
                txtLokasi.getText()
        );
        
        barang.setStatus(
                cbStatus.getSelectedItem().toString()
        );

        barang.setTanggal(
                txtTanggal.getText()
        );

        ControllerBarang controller = new ControllerBarang();

        controller.insert(barang);

        JOptionPane.showMessageDialog(
                this, "Data berhasil disimpan"
        );
        
        resetForm();
    }
    
    private void resetForm() {
        txtNamaBarang.setText("");
        txtKategori.setText("");
        txtDeskripsi.setText("");
        txtLokasi.setText("");
        txtTanggal.setText("");
    }
}
