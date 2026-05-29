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


public class InputBarang extends JFrame {

    JTextField txtNamaBarang;

    JComboBox cbKategori;

    JTextArea txtDeskripsi;

    JTextField txtLokasi;

    JComboBox cbStatus;

    JComboBox cbStatusClaim;

    JButton btnSimpan;

    JButton btnReset;

    JButton btnBack;
    
    

    public InputBarang() {

        setTitle("Input Barang");

        setSize(500,550);

        setLocationRelativeTo(null);

        JPanel panel = new JPanel();

        panel.setLayout(null);
        panel.setBackground(new Color(245,245,245));
        

        JLabel title =
                new JLabel("INPUT BARANG");

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        20
                )
        );

        title.setBounds(150,20,250,30);
        title.setForeground(new Color(41,128,185));

        JLabel lblNama =
                new JLabel("Nama Barang");

        lblNama.setBounds(50,80,120,25);

        txtNamaBarang =
                new JTextField();

        txtNamaBarang.setBounds(
                180,
                80,
                220,
                25
        );

        JLabel lblKategori =
                new JLabel("Kategori");

        lblKategori.setBounds(
                50,
                120,
                120,
                25
        );

        cbKategori =
                new JComboBox(
                        new String[]{
                                "Elektronik",
                                "Dokumen",
                                "Aksesoris",
                                "Pakaian",
                                "Kendaraan",
                                "Peralatan Kuliah"
                        }
                );

        cbKategori.setBounds(
                180,
                120,
                220,
                25
        );

        JLabel lblDeskripsi =
                new JLabel("Deskripsi");

        lblDeskripsi.setBounds(
                50,
                160,
                120,
                25
        );

        txtDeskripsi =
                new JTextArea();

        JScrollPane sp =
                new JScrollPane(
                        txtDeskripsi
                );

        sp.setBounds(
                180,
                160,
                220,
                80
        );

        JLabel lblLokasi =
                new JLabel("Lokasi");

        lblLokasi.setBounds(
                50,
                260,
                120,
                25
        );

        txtLokasi =
                new JTextField();

        txtLokasi.setBounds(
                180,
                260,
                220,
                25
        );

        JLabel lblStatus =
                new JLabel("Status");

        lblStatus.setBounds(
                50,
                300,
                120,
                25
        );

        cbStatus =
                new JComboBox(
                        new String[]{
                                "Hilang",
                                "Ditemukan"
                        }
                );

        cbStatus.setBounds(
                180,
                300,
                220,
                25
        );

        JLabel lblClaim =
                new JLabel("Status Claim");

        lblClaim.setBounds(
                50,
                340,
                120,
                25
        );

        cbStatusClaim =
                new JComboBox(
                        new String[]{
                                "Belum Diklaim",
                                "Sudah Diklaim",
                                "Sudah Ditemukan"
                        }
                );

        cbStatusClaim.setBounds(
                180,
                340,
                220,
                25
        );

        btnSimpan =
                new JButton("SIMPAN");

        btnSimpan.setBounds(
                50,
                430,
                120,
                35
        );
        btnSimpan.setBackground(new Color(46,204,113));
        btnSimpan.setForeground(Color.WHITE);
        btnSimpan.setFocusPainted(false);

        btnReset =
                new JButton("RESET");

        btnReset.setBounds(
                190,
                430,
                120,
                35
        );
        btnReset.setBackground(new Color(241,196,15));
        btnReset.setForeground(Color.WHITE);
        btnReset.setFocusPainted(false);

        btnBack =
                new JButton("BACK");

        btnBack.setBounds(
                330,
                430,
                120,
                35
        );
        btnBack.setBackground(new Color(231,76,60));
        btnBack.setForeground(Color.WHITE);
        btnBack.setFocusPainted(false);

        panel.add(title);

        panel.add(lblNama);
        panel.add(txtNamaBarang);

        panel.add(lblKategori);
        panel.add(cbKategori);

        panel.add(lblDeskripsi);
        panel.add(sp);

        panel.add(lblLokasi);
        panel.add(txtLokasi);

        panel.add(lblStatus);
        panel.add(cbStatus);

        panel.add(lblClaim);
        panel.add(cbStatusClaim);

        panel.add(btnSimpan);
        panel.add(btnReset);
        panel.add(btnBack);

        add(panel);

        btnSimpan.addActionListener(
                e -> simpanData()
        );

        btnReset.addActionListener(
                e -> resetForm()
        );

        btnBack.addActionListener(
                e -> {

                    dispose();

                    new DashboardAdmin()
                            .setVisible(true);
                }
        );
    }

    private void simpanData() {

        ModelBarang barang =
                new ModelBarang();

        barang.setNamaBarang(
                txtNamaBarang.getText()
        );

        barang.setKategori(
                cbKategori
                        .getSelectedItem()
                        .toString()
        );

        barang.setDeskripsi(
                txtDeskripsi.getText()
        );

        barang.setLokasi(
                txtLokasi.getText()
        );

        barang.setStatus(
                cbStatus
                        .getSelectedItem()
                        .toString()
        );

        barang.setStatusClaim(
                cbStatusClaim
                        .getSelectedItem()
                        .toString()
        );

        // sementara hardcode user
        barang.setUserId(1);

        ControllerBarang controller =
                new ControllerBarang();

        controller.insert(barang);

        JOptionPane.showMessageDialog(
                this,
                "Data berhasil disimpan"
        );

        resetForm();
    }

    private void resetForm() {

        txtNamaBarang.setText("");

        txtDeskripsi.setText("");

        txtLokasi.setText("");

        cbKategori.setSelectedIndex(0);

        cbStatus.setSelectedIndex(0);

        cbStatusClaim.setSelectedIndex(0);
    }
}

