/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.User;

/**
 *
 * @author Ivaa
 */
import Controller.ControllerLogin;
import Model.User.ModelUser;
import View.Admin.DashboardAdmin;

import javax.swing.*;
import java.awt.*;

public class Login extends JFrame {

    JTextField txtUsername;
    JPasswordField txtPassword;

    JButton btnLogin;
    JButton btnRegister;

    public Login(){

        setTitle("Login");
        setSize(400,300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel title = new JLabel("LOGIN");
        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        22
                )
        );

        title.setBounds(150,20,200,30);

        JLabel lblUsername =
                new JLabel("Username");

        lblUsername.setBounds(50,80,100,25);

        txtUsername = new JTextField();

        txtUsername.setBounds(150,80,180,25);

        JLabel lblPassword =
                new JLabel("Password");

        lblPassword.setBounds(50,120,100,25);

        txtPassword =
                new JPasswordField();

        txtPassword.setBounds(150,120,180,25);

        btnLogin = new JButton("LOGIN");

        btnLogin.setBounds(70,180,100,35);

        btnRegister =
                new JButton("REGISTER");

        btnRegister.setBounds(200,180,120,35);

        panel.add(title);
        panel.add(lblUsername);
        panel.add(txtUsername);
        panel.add(lblPassword);
        panel.add(txtPassword);
        panel.add(btnLogin);
        panel.add(btnRegister);

        add(panel);

        btnLogin.addActionListener(
                e -> login()
        );

        btnRegister.addActionListener(
                e -> {

                    dispose();

                    new Register()
                            .setVisible(true);
                }
        );
    }

    private void login(){

        String username =
                txtUsername.getText();

        String password =
                txtPassword.getText();

        ControllerLogin controller =
                new ControllerLogin();

        ModelUser user =
                controller.login(
                        username,
                        password
                );

        if(user != null){

            JOptionPane.showMessageDialog(
                    this,
                    "Login berhasil"
            );

            dispose();

            if(user.getRole()
                    .equals("admin")){

                new DashboardAdmin()
                        .setVisible(true);

            }else{

                new DashboardUser()
                        .setVisible(true);
            }

        }else{

            JOptionPane.showMessageDialog(
                    this,
                    "Username / Password salah"
            );
        }
    }
}