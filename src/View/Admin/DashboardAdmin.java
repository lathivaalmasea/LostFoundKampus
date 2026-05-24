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
        setSize(400,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel title = new JLabel("DASHBOARD ADMIN");
        title.setFont(new Font("Segoe UI",Font.BOLD,20));
        title.setBounds(80,20,250,30);

        btnViewBarang = new JButton("VIEW BARANG");
        btnViewBarang.setBounds(100,80,180,40);

        btnInputBarang = new JButton("INPUT BARANG");
        btnInputBarang.setBounds(100,130,180,40);

        btnLogout = new JButton("LOGOUT");
        btnLogout.setBounds(100,180,180,40);
        
        panel.add(title);
        panel.add(btnViewBarang);
        panel.add(btnInputBarang);
        panel.add(btnLogout);

        add(panel);

        btnViewBarang.addActionListener(e -> {
            new ViewBarang().setVisible(true);
        });

        btnInputBarang.addActionListener(e -> {
            new InputBarang().setVisible(true);
        });

        btnLogout.addActionListener(e -> {
            dispose();
            new View.User.Login().setVisible(true);
        });
    }
}
