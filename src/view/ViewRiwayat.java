/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;
import controller.ControllerBarang;
import controller.ControllerKlaim;
import model.ModelBarang;
import model.ModelKlaim;
import model.ModelUser;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
/**
 *
 * @author Ivaa
 */
public class ViewRiwayat extends JFrame implements ActionListener{
    private ModelUser userAktif;
    private ControllerBarang controllerBarang;
    private ControllerKlaim controllerKlaim;
    private JTable tabelLaporan, tabelKlaim;
    private DefaultTableModel modelLaporan, modelKlaim;
    private JButton btnKembali;
    
    public ViewRiwayat(ModelUser user) {
        this.userAktif = user;
        controllerBarang = new ControllerBarang();
        controllerKlaim = new ControllerKlaim();
        initComponents();
        loadData();
    }
    
    private void initComponents() {
        setTitle("Riwayat Saya");
        setSize(900, 580);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        // Header
        JPanel header = new JPanel(null);
        header.setBackground(new Color(30, 58, 138));
        header.setPreferredSize(new Dimension(900, 55));
        add(header, BorderLayout.NORTH);

        btnKembali = new JButton("← Kembali");
        btnKembali.setBounds(10, 12, 110, 30);
        btnKembali.setBackground(new Color(59, 91, 180));
        btnKembali.setForeground(Color.WHITE);
        btnKembali.setBorderPainted(false);
        btnKembali.setFocusPainted(false);
        btnKembali.addActionListener(this);
        header.add(btnKembali);
        
        JLabel lblJudul = new JLabel("Riwayat Saya", SwingConstants.CENTER);
        lblJudul.setFont(new Font("Arial", Font.BOLD, 16));
        lblJudul.setForeground(Color.WHITE);
        lblJudul.setBounds(300, 12, 300, 30);
        header.add(lblJudul);

        // Tabs
        JTabbedPane tabs = new JTabbedPane();
        tabs.setFont(new Font("Arial", Font.BOLD, 12));
        
        // Tab 1: Laporan Saya
        String[] kolomLaporan = {"ID", "Nama Barang", "Jenis", "Lokasi", "Tanggal", "Status"};
        modelLaporan = new DefaultTableModel(kolomLaporan, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        tabelLaporan = new JTable(modelLaporan);
        tabelLaporan.setRowHeight(28);
        tabelLaporan.setFont(new Font("Arial", Font.PLAIN, 12));
        tabelLaporan.getTableHeader().setBackground(new Color(30, 58, 138));
        tabelLaporan.getTableHeader().setForeground(Color.WHITE);
        tabelLaporan.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        tabelLaporan.getColumnModel().getColumn(0).setMaxWidth(40);
        tabs.addTab("Laporan Saya", new JScrollPane(tabelLaporan));
        
        // Tab 2: Klaim Saya
        String[] kolomKlaim = {"ID", "Barang", "Alasan", "Kontak", "Status", "Tanggal"};
        modelKlaim = new DefaultTableModel(kolomKlaim, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        tabelKlaim = new JTable(modelKlaim);
        tabelKlaim.setRowHeight(28);
        tabelKlaim.setFont(new Font("Arial", Font.PLAIN, 12));
        tabelKlaim.getTableHeader().setBackground(new Color(30, 58, 138));
        tabelKlaim.getTableHeader().setForeground(Color.WHITE);
        tabelKlaim.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        tabelKlaim.getColumnModel().getColumn(0).setMaxWidth(40);
        tabs.addTab("Klaim Saya", new JScrollPane(tabelKlaim));
        
        add(tabs, BorderLayout.CENTER);
    }
    
    private void loadData() {
        // Load laporan
        modelLaporan.setRowCount(0);
        List<ModelBarang> laporan = controllerBarang.getBarangByUser(userAktif.getId());
        for (ModelBarang b : laporan) {
            modelLaporan.addRow(new Object[]{
                b.getId(), b.getJudul(), b.getJenis().toUpperCase(),
                b.getLokasi(), b.getTanggal(), b.getStatus().toUpperCase()
            });
        }
        
        // Load klaim
        modelKlaim.setRowCount(0);
        List<ModelKlaim> klaims = controllerKlaim.getKlaimByUser(userAktif.getId());
        for (ModelKlaim k : klaims) {
            modelKlaim.addRow(new Object[]{
                k.getId(), k.getJudulBarang(), k.getAlasan(),
                k.getKontak(), k.getStatus().toUpperCase(), k.getCreatedAt()
            });
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnKembali) {
            new ViewDashboard(userAktif).setVisible(true);
            dispose();
        }
    }
}
