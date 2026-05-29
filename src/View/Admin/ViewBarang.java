/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.Admin;

import Controller.ControllerBarang;
import Model.Barang.ModelBarang;
import Model.Barang.ModelTableBarang;
import javax.swing.*;
import java.awt.*;
import java.util.List;
/**
 *
 * @author Ivaa
 */
public class ViewBarang extends JFrame {
    JTable tableBarang;

    JTextField txtSearch;

    JButton btnSearch;
    JButton btnRefresh;
    JButton btnDelete;
    
    public ViewBarang() {
        setTitle("View Barang");
        setSize(800,500);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel title = new JLabel("DATA BARANG");
        title.setFont(new Font("Segoe UI",Font.BOLD,20));
        title.setBounds(300,20,250,30);

        txtSearch = new JTextField();
        txtSearch.setBounds(50,70,250,30);

        btnSearch = new JButton("SEARCH");
        btnSearch.setBounds(320,70,100,30);

        btnRefresh = new JButton("REFRESH");
        btnRefresh.setBounds(440,70,100,30);

        btnDelete = new JButton("DELETE");
        btnDelete.setBounds(560,70,100,30);

        tableBarang = new JTable();
        tableBarang.getTableHeader().setBackground(new Color(52,152,219));
        tableBarang.setRowHeight(25);
        tableBarang.getTableHeader().setForeground(Color.WHITE);

        JScrollPane scroll = new JScrollPane(tableBarang);

        scroll.setBounds(50,120,680,280);

        panel.add(title);
        panel.add(txtSearch);
        panel.add(btnSearch);
        panel.add(btnRefresh);
        panel.add(btnDelete);
        panel.add(scroll);
        
        add(panel);

        loadTable();

        btnSearch.addActionListener(e -> searchData());

        btnRefresh.addActionListener(e -> {
            txtSearch.setText("");
            loadTable();
        });
        
        btnDelete.addActionListener(e -> deleteData());

        txtSearch.addKeyListener(
                new java.awt.event.KeyAdapter() {

                    public void keyReleased(java.awt.event.KeyEvent evt){
                        searchData();
                    }
                }
        );
        txtSearch.setBorder(
        BorderFactory.createLineBorder(
        new Color(52,152,219),
        2
            )
        );
    }
    
    private void loadTable(){
        ControllerBarang controller = new ControllerBarang();
        List<ModelBarang> list = controller.getAll();
        ModelTableBarang model = new ModelTableBarang(list);
        tableBarang.setModel(model);
    }
    
    private void searchData() {
        ControllerBarang controller = new ControllerBarang();
        List<ModelBarang> list = controller.search(txtSearch.getText());
        ModelTableBarang model = new ModelTableBarang(list);
        tableBarang.setModel(model);
    }
    
    private void deleteData(){
        int row = tableBarang.getSelectedRow();
        
        if(row == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data dulu!");
            return;
        }
        
        int id = Integer.parseInt(tableBarang.getValueAt(row, 0).toString());
        
        ControllerBarang controller = new ControllerBarang();
        controller.delete(id);
        JOptionPane.showMessageDialog(this, "Data berhasil dihapus");
        
        loadTable();
    }
}
