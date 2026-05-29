/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.Admin;

import javax.swing.*;
import java.awt.*;
/**
 *
 * @author Ivaa
 */

public class DashboardAdmin extends JFrame {

    JButton btnViewBarang;
    JButton btnInputBarang;
    JButton btnLogout;

    public DashboardAdmin() {
        setTitle("Dashboard Admin");

        setSize(900,600);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLocationRelativeTo(null);

        setLayout(null);

        // =========================
        // SIDEBAR
        // =========================

        JPanel sidebar = new JPanel();

        sidebar.setLayout(null);

        sidebar.setBounds(0,0,220,600);

        sidebar.setBackground(
                new Color(44,62,80)
        );

        JLabel lblMenu =
                new JLabel("ADMIN MENU");

        lblMenu.setForeground(Color.WHITE);

        lblMenu.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        20
                )
        );

        lblMenu.setBounds(35,30,200,30);

        btnViewBarang =
                new JButton("VIEW BARANG");

        btnViewBarang.setBounds(
                20,
                100,
                180,
                40
        );

        styleButton(btnViewBarang);

        btnInputBarang =
                new JButton("INPUT BARANG");

        btnInputBarang.setBounds(
                20,
                160,
                180,
                40
        );

        styleButton(btnInputBarang);

        btnLogout =
                new JButton("LOGOUT");

        btnLogout.setBounds(
                20,
                220,
                180,
                40
        );

        btnLogout.setBackground(
                new Color(231,76,60)
        );

        btnLogout.setForeground(Color.WHITE);

        btnLogout.setFocusPainted(false);

        sidebar.add(lblMenu);

        sidebar.add(btnViewBarang);

        sidebar.add(btnInputBarang);

        sidebar.add(btnLogout);

        add(sidebar);

        JPanel content = new JPanel();

        content.setLayout(null);

        content.setBounds(220,0,680,600);

        content.setBackground(
                new Color(245,245,245)
        );

        // =========================
        // TITLE DASHBOARD
        // =========================

        JLabel title =
                new JLabel(
                        "DASHBOARD ADMIN"
                );

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        28
                )
        );

        title.setForeground(
                new Color(41,128,185)
        );

        title.setBounds(
                180,
                30,
                400,
                40
        );

        // =========================
        // CARD 1
        // =========================

        JPanel card1 = new JPanel();

        card1.setBounds(
                60,
                120,
                180,
                120
        );

        card1.setBackground(
                new Color(52,152,219)
        );

        JLabel lbl1 =
                new JLabel("Barang Hilang");

        lbl1.setForeground(Color.WHITE);

        lbl1.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        18
                )
        );

        card1.add(lbl1);

        // =========================
        // CARD 2
        // =========================

        JPanel card2 = new JPanel();

        card2.setBounds(
                260,
                120,
                180,
                120
        );

        card2.setBackground(
                new Color(46,204,113)
        );

        JLabel lbl2 =
                new JLabel("Barang Ditemukan");

        lbl2.setForeground(Color.WHITE);

        lbl2.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        16
                )
        );

        card2.add(lbl2);

        // =========================
        // CARD 3
        // =========================

        JPanel card3 = new JPanel();

        card3.setBounds(
                460,
                120,
                180,
                120
        );

        card3.setBackground(
                new Color(241,196,15)
        );

        JLabel lbl3 =
                new JLabel("Sudah Diklaim");

        lbl3.setForeground(Color.WHITE);

        lbl3.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        16
                )
        );

        card3.add(lbl3);

        // =========================
        // INFO TEXT
        // =========================

        JLabel info =
                new JLabel(
                        "Selamat datang di sistem Lost & Found Kampus"
                );

        info.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        16
                )
        );

        info.setBounds(
                120,
                320,
                500,
                30
        );

        // =========================
        // ADD CONTENT
        // =========================

        content.add(title);

        content.add(card1);

        content.add(card2);

        content.add(card3);

        content.add(info);

        add(content);

        // =========================
        // ACTION BUTTON
        // =========================

        btnViewBarang.addActionListener(
                e -> {

                    new ViewBarang()
                            .setVisible(true);
                }
        );

        btnInputBarang.addActionListener(
                e -> {

                    new InputBarang()
                            .setVisible(true);
                }
        );

        btnLogout.addActionListener(
                e -> {

                    dispose();

                    new View.User.Login()
                            .setVisible(true);
                }
        );
    }

    // =========================
    // STYLE BUTTON
    // =========================

    private void styleButton(JButton button){

        button.setBackground(
                new Color(52,152,219)
        );

        button.setForeground(Color.WHITE);

        button.setFocusPainted(false);

        button.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );
    }
}
