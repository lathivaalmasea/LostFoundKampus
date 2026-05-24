/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.User;

/**
 *
 * @author Ivaa
 */

import Controller.ControllerUser;
import Model.User.ModelUser;

import javax.swing.*;
import java.awt.*;

public class Register extends JFrame {

    JTextField txtNama;
    JTextField txtUsername;
    JPasswordField txtPassword;

    JButton btnRegister;
    JButton btnLogin;

    public Register(){

        setTitle("Register");
        setSize(400,350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel title =
                new JLabel("REGISTER");

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        22
                )
        );

        title.setBounds(120,20,200,30);

        JLabel lblNama =
                new JLabel("Nama");

        lblNama.setBounds(50,80,100,25);

        txtNama = new JTextField();

        txtNama.setBounds(150,80,180,25);

        JLabel lblUsername =
                new JLabel("Username");

        lblUsername.setBounds(50,120,100,25);

        txtUsername =
                new JTextField();

        txtUsername.setBounds(150,120,180,25);

        JLabel lblPassword =
                new JLabel("Password");

        lblPassword.setBounds(50,160,100,25);

        txtPassword =
                new JPasswordField();

        txtPassword.setBounds(150,160,180,25);

        btnRegister =
                new JButton("REGISTER");

        btnRegister.setBounds(60,230,120,35);

        btnLogin =
                new JButton("LOGIN");

        btnLogin.setBounds(200,230,120,35);

        panel.add(title);
        panel.add(lblNama);
        panel.add(txtNama);
        panel.add(lblUsername);
        panel.add(txtUsername);
        panel.add(lblPassword);
        panel.add(txtPassword);
        panel.add(btnRegister);
        panel.add(btnLogin);

        add(panel);

        btnRegister.addActionListener(
                e -> register()
        );

        btnLogin.addActionListener(
                e -> {

                    dispose();

                    new Login().setVisible(true);
                }
        );
    }

    private void register(){

        if(txtNama.getText().isEmpty()
                || txtUsername.getText().isEmpty()
                || txtPassword.getText().isEmpty()){

            JOptionPane.showMessageDialog(
                    this,
                    "Data tidak boleh kosong"
            );

            return;
        }

        ModelUser user =
                new ModelUser();

        user.setNama(
                txtNama.getText()
        );

        user.setUsername(
                txtUsername.getText()
        );

        user.setPassword(
                txtPassword.getText()
        );

        ControllerUser controller =
                new ControllerUser();

        controller.insert(user);

        JOptionPane.showMessageDialog(
                this,
                "Register berhasil"
        );

        dispose();

        new Login().setVisible(true);
    }
}