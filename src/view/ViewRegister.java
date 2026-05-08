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
public class ViewRegister extends JFrame implements ActionListener{
    private JTextField tfUsername, tfNama, tfEmail, tfHp;
    private JPasswordField pfPassword;
    private JButton btnDaftar, btnBatal;
    private ControllerUser controllerUser;

    public ViewRegister() {
        controllerUser = new ControllerUser();
        initComponents();
    }
    
    private void initComponents() {
        setTitle("Daftar Akun Baru");
        setSize(420, 480);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(new Color(245, 247, 250));
        setLayout(null);
        
        JPanel card = new JPanel(null);
        card.setBounds(30, 30, 360, 420);
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));
        add(card);

        JLabel lblJudul = new JLabel("Buat Akun Baru", SwingConstants.CENTER);
        lblJudul.setFont(new Font("Arial", Font.BOLD, 16));
        lblJudul.setForeground(new Color(30, 58, 138));
        lblJudul.setBounds(10, 15, 340, 25);
        card.add(lblJudul);
        
        String[] labels = {"Nama Lengkap", "Username", "Password", "Email", "No. HP"};
        int y = 55;
        for (String label : labels) {
            JLabel lbl = new JLabel(label);
            lbl.setFont(new Font("Arial", Font.PLAIN, 11));
            lbl.setBounds(20, y, 150, 18);
            card.add(lbl);
            y += 18;
            
            JTextField tf;
            if (label.equals("Password")) {
                pfPassword = new JPasswordField();
                tf = pfPassword;
            } else {
                tf = new JTextField();
                switch (label) {
                    case "Nama Lengkap": tfNama = tf; break;
                    case "Username": tfUsername = tf; break;
                    case "Email": tfEmail = tf; break;
                    case "No. HP": tfHp = tf; break;
                }
            }
            tf.setBounds(20, y, 320, 30);
            tf.setFont(new Font("Arial", Font.PLAIN, 12));
            tf.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(4, 8, 4, 8)
            ));
            card.add(tf);
            y += 38;
        }
        
        btnDaftar = new JButton("Daftar");
        btnDaftar.setBounds(20, 345, 150, 35);
        btnDaftar.setBackground(new Color(30, 58, 138));
        btnDaftar.setForeground(Color.WHITE);
        btnDaftar.setFont(new Font("Arial", Font.BOLD, 13));
        btnDaftar.setFocusPainted(false);
        btnDaftar.setBorderPainted(false);
        btnDaftar.addActionListener(this);
        card.add(btnDaftar);
        
        btnBatal = new JButton("Batal");
        btnBatal.setBounds(190, 345, 150, 35);
        btnBatal.setBackground(new Color(200, 200, 200));
        btnBatal.setFont(new Font("Arial", Font.BOLD, 13));
        btnBatal.setFocusPainted(false);
        btnBatal.setBorderPainted(false);
        btnBatal.addActionListener(this);
        card.add(btnBatal);

        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnDaftar) {
            try {
                ModelUser user = new ModelUser();
                user.setNamaLengkap(tfNama.getText());
                user.setUsername(tfUsername.getText());
                user.setPassword(new String(pfPassword.getPassword()));
                user.setEmail(tfEmail.getText());
                user.setNoHp(tfHp.getText());
                
                boolean berhasil = controllerUser.register(user);
                if (berhasil) {
                    JOptionPane.showMessageDialog(this, "Akun berhasil dibuat! Silakan login.");
                    dispose();
                    new ViewLogin();
                }
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Peringatan", JOptionPane.WARNING_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == btnBatal) {
            dispose();
            new ViewLogin();
        }
    }
}
