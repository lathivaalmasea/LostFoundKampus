/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.User;

/**
 *
 * @author Ivaa
 */

import Controller.ControllerBarang;
import Model.Barang.ModelBarang;
import Model.Barang.ModelTableBarang;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class LihatBarang extends JFrame {

    JTable tableBarang;

    JTextField txtSearch;

    JButton btnSearch;
    JButton btnRefresh;

    public LihatBarang(){

        setTitle("Lihat Barang");
        setSize(800,500);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel title =
                new JLabel("DAFTAR BARANG");

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        20
                )
        );

        title.setBounds(280,20,250,30);

        txtSearch = new JTextField();

        txtSearch.setBounds(
                50,
                70,
                250,
                30
        );

        btnSearch =
                new JButton("SEARCH");

        btnSearch.setBounds(
                320,
                70,
                100,
                30
        );

        btnRefresh =
                new JButton("REFRESH");

        btnRefresh.setBounds(
                440,
                70,
                100,
                30
        );

        tableBarang = new JTable();

        JScrollPane scroll =
                new JScrollPane(
                        tableBarang
                );

        scroll.setBounds(
                50,
                130,
                680,
                280
        );

        panel.add(title);
        panel.add(txtSearch);
        panel.add(btnSearch);
        panel.add(btnRefresh);
        panel.add(scroll);

        add(panel);

        loadTable();

        btnSearch.addActionListener(
                e -> searchData()
        );

        btnRefresh.addActionListener(
                e -> {

                    txtSearch.setText("");

                    loadTable();
                }
        );

        txtSearch.addKeyListener(
                new java.awt.event.KeyAdapter() {

                    public void keyReleased(
                            java.awt.event.KeyEvent evt
                    ){

                        searchData();
                    }
                }
        );
    }

    private void loadTable(){

        ControllerBarang controller =
                new ControllerBarang();

        List<ModelBarang> list =
                controller.getAll();

        ModelTableBarang model =
                new ModelTableBarang(list);

        tableBarang.setModel(model);
    }

    private void searchData(){

        ControllerBarang controller =
                new ControllerBarang();

        List<ModelBarang> list =
                controller.search(
                        txtSearch.getText()
                );

        ModelTableBarang model =
                new ModelTableBarang(list);

        tableBarang.setModel(model);
    }
}