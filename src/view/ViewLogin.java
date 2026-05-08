/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;
import controller.ControllerUser;
import model.ModelUser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 *
 * @author Ivaa
 */
public class ViewLogin extends JFrame implements ActionListener{
    private JLabel lblJudul, lblSub, lblUsername, lblPassword, lblDaftar;
    private JTextField tfUsername;
    private JPasswordField pfPassword;
    private JButton btnLogin;
    private ControllerUser controllerUser;

    public ViewLogin() {
        controllerUser = new ControllerUser();
        initComponents();
    }
    
    private void initComponents() {
        setTitle("Lost & Found Kampus - Login");
        setSize(420, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(new Color(245, 247, 250));
        setLayout(null);
        
        // Panel kartu putih
        JPanel card = new JPanel(null);
        card.setBounds(40, 60, 340, 380);
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1));
        add(card);
        
        // Icon dan Judul
        lblJudul = new JLabel("Lost & Found Kampus", SwingConstants.CENTER);
        lblJudul.setFont(new Font("Arial", Font.BOLD, 18));
        lblJudul.setForeground(new Color(30, 58, 138));
        lblJudul.setBounds(20, 30, 300, 30);
        card.add(lblJudul);

        lblSub = new JLabel("Masuk ke akun Anda", SwingConstants.CENTER);
        lblSub.setFont(new Font("Arial", Font.PLAIN, 12));
        lblSub.setForeground(new Color(100, 100, 100));
        lblSub.setBounds(20, 65, 300, 20);
        card.add(lblSub);
        
        // Separator
        JSeparator sep = new JSeparator();
        sep.setBounds(20, 95, 300, 2);
        card.add(sep);

        // Username
        lblUsername = new JLabel("Username atau Email");
        lblUsername.setFont(new Font("Arial", Font.PLAIN, 12));
        lblUsername.setBounds(20, 110, 200, 20);
        card.add(lblUsername);
        
        tfUsername = new JTextField();
        tfUsername.setBounds(20, 135, 300, 35);
        tfUsername.setFont(new Font("Arial", Font.PLAIN, 13));
        tfUsername.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        card.add(tfUsername);
        
        // Password
        lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("Arial", Font.PLAIN, 12));
        lblPassword.setBounds(20, 185, 200, 20);
        card.add(lblPassword);

        pfPassword = new JPasswordField();
        pfPassword.setBounds(20, 210, 300, 35);
        pfPassword.setFont(new Font("Arial", Font.PLAIN, 13));
        pfPassword.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        card.add(pfPassword);
        
        // Tombol Login
        btnLogin = new JButton("Login");
        btnLogin.setBounds(20, 270, 300, 40);
        btnLogin.setBackground(new Color(30, 58, 138));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFont(new Font("Arial", Font.BOLD, 14));
        btnLogin.setFocusPainted(false);
        btnLogin.setBorderPainted(false);
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLogin.addActionListener(this);
        card.add(btnLogin);
        
        // Link daftar
        lblDaftar = new JLabel("<html>Belum punya akun? <font color='#1e3a8a'><u>Daftar di sini</u></font></html>",
                               SwingConstants.CENTER);
        lblDaftar.setFont(new Font("Arial", Font.PLAIN, 12));
        lblDaftar.setBounds(20, 325, 300, 20);
        lblDaftar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblDaftar.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                new ViewRegister().setVisible(true);
                dispose();
            }
        });
        card.add(lblDaftar);
        
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnLogin) {
            prosesLogin();
        }
    }
    
    private void prosesLogin() {
        String username = tfUsername.getText().trim();
        String password = new String(pfPassword.getPassword()).trim();

        try {
            ModelUser user = controllerUser.login(username, password);
            if (user != null) {
                JOptionPane.showMessageDialog(this,
                    "Selamat datang, " + user.getNamaLengkap() + "!",
                    "Login Berhasil", JOptionPane.INFORMATION_MESSAGE);
                new ViewDashboard(user).setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this,
                    "Username atau password salah!",
                    "Login Gagal", JOptionPane.ERROR_MESSAGE);
                pfPassword.setText("");
            }
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Peringatan", JOptionPane.WARNING_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                "Tidak dapat terhubung ke database.\nPastikan XAMPP sudah berjalan!",
                "Error Koneksi", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ViewLogin());
    }
}
