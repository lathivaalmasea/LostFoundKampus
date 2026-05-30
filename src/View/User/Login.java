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
import Controller.LoginViewContract;
import View.Admin.DashboardAdmin;
import View.Component.AppButtonFactory;
import View.Component.AppTheme;
import View.Component.LabeledInput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Login extends JFrame implements LoginViewContract {

    private final LabeledInput usernameInput;
    private final LabeledInput passwordInput;
    private final transient ControllerLogin controller;

    private final JButton btnLogin;
    private final JButton btnRegister;

    public Login() {

        this.controller = new ControllerLogin(this);

        setTitle("Login");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(AppTheme.BACKGROUND);

        JPanel form = new JPanel(new GridBagLayout());
        form.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 14, 8, 14);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.weightx = 1.0;

        JLabel title = new JLabel("LOGIN");
        title.setFont(AppTheme.TITLE_FONT);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setForeground(new Color(41, 128, 185));

        usernameInput = LabeledInput.text("Username", 16);
        passwordInput = LabeledInput.password("Password", 16);

        btnLogin = AppButtonFactory.primary("LOGIN");
        btnRegister = AppButtonFactory.success("REGISTER");

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 12, 0));
        buttonPanel.setOpaque(false);
        buttonPanel.add(btnLogin);
        buttonPanel.add(btnRegister);

        gbc.gridy = 0;
        form.add(title, gbc);

        gbc.gridy = 1;
        form.add(usernameInput, gbc);

        gbc.gridy = 2;
        form.add(passwordInput, gbc);

        gbc.gridy = 3;
        gbc.insets = new Insets(14, 14, 8, 14);
        form.add(buttonPanel, gbc);

        panel.add(form, BorderLayout.CENTER);
        add(panel);

        btnLogin.addActionListener(loginEventHandler());
        btnRegister.addActionListener(registerEventHandler());
    }

    private ActionListener loginEventHandler() {
        return e -> {
            this.controller.handleLogin(
                usernameInput.getText(),
                passwordInput.getPassword()
            );
        };
    }

    private static ActionListener registerEventHandler() {
        return e -> {
            new Register().setVisible(true);
        };
    }

    @Override
    public void showInfoMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    @Override
    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    @Override
    public void openAdminDashboard() {
        dispose();
        new DashboardAdmin().setVisible(true);
    }

    @Override
    public void openUserDashboard() {
        dispose();
        new DashboardUser().setVisible(true);
    }
}