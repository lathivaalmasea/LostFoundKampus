/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.User;

/**
 *
 * @author Ivaa
 */
import View.Admin.InputBarang;

import javax.swing.*;
import java.awt.*;

public class DashboardUser extends JFrame {

    JButton btnLihatBarang;
    JButton btnTambahBarang;
    JButton btnLogout;

    public DashboardUser(){

        setTitle("Dashboard User");
        setSize(400,300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel title = new JLabel("DASHBOARD USER");

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        20
                )
        );

        title.setBounds(90,30,250,30);

        btnLihatBarang =
                new JButton("LIHAT BARANG");

        btnLihatBarang.setBounds(
                100,
                90,
                180,
                40
        );

        btnTambahBarang =
                new JButton("TAMBAH BARANG");

        btnTambahBarang.setBounds(
                100,
                140,
                180,
                40
        );

        btnLogout =
                new JButton("LOGOUT");

        btnLogout.setBounds(
                100,
                190,
                180,
                40
        );

        panel.add(title);
        panel.add(btnLihatBarang);
        panel.add(btnTambahBarang);
        panel.add(btnLogout);

        add(panel);

        btnLihatBarang.addActionListener(
                e -> {

                    new LihatBarang()
                            .setVisible(true);
                }
        );

        btnTambahBarang.addActionListener(
                e -> {

                    new InputBarang().setVisible(true);
                }
        );

        btnLogout.addActionListener(
                e -> {

                    dispose();

                    new Login()
                            .setVisible(true);
                }
        );
    }
}