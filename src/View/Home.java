/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

/**
 *
 * @author Ivaa
 */

import View.User.Login;
import View.User.Register;

import javax.swing.*;
import java.awt.*;

public class Home extends JFrame {

    JButton btnLogin;
    JButton btnRegister;
    JButton btnExit;

    public Home(){

        setTitle("Lost & Found Kampus");
        setSize(500,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        panel.setBackground(Color.WHITE);

        JLabel title =
                new JLabel("LOST & FOUND KAMPUS");

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        24
                )
        );

        title.setBounds(80,40,350,40);

        JLabel subtitle =
                new JLabel(
                        "Aplikasi Barang Hilang & Ditemukan"
                );

        subtitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        subtitle.setBounds(
                110,
                90,
                300,
                30
        );

        btnLogin =
                new JButton("LOGIN");

        btnLogin.setBounds(
                150,
                160,
                180,
                40
        );

        btnRegister =
                new JButton("REGISTER");

        btnRegister.setBounds(
                150,
                220,
                180,
                40
        );

        btnExit =
                new JButton("EXIT");

        btnExit.setBounds(
                150,
                280,
                180,
                40
        );

        panel.add(title);
        panel.add(subtitle);
        panel.add(btnLogin);
        panel.add(btnRegister);
        panel.add(btnExit);

        add(panel);

        btnLogin.addActionListener(
                e -> {

                    dispose();

                    new Login().setVisible(true);
                }
        );

        btnRegister.addActionListener(
                e -> {

                    dispose();

                    new Register().setVisible(true);
                }
        );

        btnExit.addActionListener(
                e -> {

                    int confirm =
                            JOptionPane.showConfirmDialog(
                                    this,
                                    "Keluar aplikasi?",
                                    "Konfirmasi",
                                    JOptionPane.YES_NO_OPTION
                            );

                    if(confirm ==
                            JOptionPane.YES_OPTION){

                        System.exit(0);
                    }
                }
        );
    }
}