/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;
import controller.ControllerBarang;
import controller.ControllerUser;
import model.ModelBarang;
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
public class ViewDashboard extends JFrame implements ActionListener{
    private ModelUser userAktif;
    private ControllerBarang controllerBarang;

    private JButton btnDashboard, btnBarangHilang, btnBarangDitemukan,
                    btnDaftarBarang, btnRiwayat, btnNotifikasi, btnProfil, btnLogout;
    private JLabel lblJmlHilang, lblJmlDitemukan, lblJmlSelesai;
    private JTable tabelTerbaru;
    private DefaultTableModel tableModel;
    
    public ViewDashboard(ModelUser user) {
        this.userAktif = user;
        ControllerUser.setUserAktif(user);
        controllerBarang = new ControllerBarang();
        initComponents();
        loadData();
    }
    
    private void initComponents() {
        setTitle("Lost & Found Kampus - Dashboard");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // === SIDEBAR ===
        JPanel sidebar = new JPanel(null);
        sidebar.setBackground(new Color(30, 58, 138));
        sidebar.setPreferredSize(new Dimension(200, 600));
        add(sidebar, BorderLayout.WEST);
        
        JLabel lblBrand = new JLabel("<html><center>Lost & Found<br>Kampus</center></html>", SwingConstants.CENTER);
        lblBrand.setFont(new Font("Arial", Font.BOLD, 14));
        lblBrand.setForeground(Color.WHITE);
        lblBrand.setBounds(10, 20, 180, 50);
        sidebar.add(lblBrand);

        JSeparator sep = new JSeparator();
        sep.setForeground(new Color(80, 108, 178));
        sep.setBounds(10, 80, 180, 2);
        sidebar.add(sep);
        
        // Menu sidebar
        String[] menuLabels = {"Dashboard", "Barang Hilang", "Barang Ditemukan",
                               "Daftar Barang", "Riwayat Saya", "Notifikasi", "Profil", "Logout"};
        JButton[] menuBtns = new JButton[menuLabels.length];
        int yMenu = 95;
        for (int i = 0; i < menuLabels.length; i++) {
            menuBtns[i] = buatMenuButton(menuLabels[i]);
            menuBtns[i].setBounds(5, yMenu, 190, 38);
            sidebar.add(menuBtns[i]);
            menuBtns[i].addActionListener(this);
            yMenu += 42;
        }
        btnDashboard = menuBtns[0]; btnBarangHilang = menuBtns[1];
        btnBarangDitemukan = menuBtns[2]; btnDaftarBarang = menuBtns[3];
        btnRiwayat = menuBtns[4]; btnNotifikasi = menuBtns[5];
        btnProfil = menuBtns[6]; btnLogout = menuBtns[7];
        btnDashboard.setBackground(new Color(59, 91, 180));

        // === MAIN CONTENT ===
        JPanel main = new JPanel(null);
        main.setBackground(new Color(245, 247, 250));
        add(main, BorderLayout.CENTER);
        
        JLabel lblHeader = new JLabel("Dashboard");
        lblHeader.setFont(new Font("Arial", Font.BOLD, 20));
        lblHeader.setBounds(20, 20, 300, 30);
        main.add(lblHeader);

        JLabel lblSambut = new JLabel("Selamat datang, " + userAktif.getNamaLengkap());
        lblSambut.setFont(new Font("Arial", Font.PLAIN, 12));
        lblSambut.setForeground(new Color(100, 100, 100));
        lblSambut.setBounds(20, 50, 400, 20);
        main.add(lblSambut);
        
        // Kartu statistik
        buatKartuStat(main, "🔴", "Barang Hilang", 20, 85, new Color(255, 235, 235), new Color(200, 50, 50));
        buatKartuStat(main, "🔵", "Barang Ditemukan", 200, 85, new Color(235, 245, 255), new Color(30, 80, 200));
        buatKartuStat(main, "🟢", "Selesai / Diklaim", 380, 85, new Color(235, 255, 235), new Color(30, 150, 50));

        lblJmlHilang = new JLabel("0", SwingConstants.CENTER);
        lblJmlHilang.setFont(new Font("Arial", Font.BOLD, 28));
        lblJmlHilang.setForeground(new Color(200, 50, 50));
        lblJmlHilang.setBounds(20, 100, 160, 40);
        main.add(lblJmlHilang);
        
        lblJmlDitemukan = new JLabel("0", SwingConstants.CENTER);
        lblJmlDitemukan.setFont(new Font("Arial", Font.BOLD, 28));
        lblJmlDitemukan.setForeground(new Color(30, 80, 200));
        lblJmlDitemukan.setBounds(200, 100, 160, 40);
        main.add(lblJmlDitemukan);

        lblJmlSelesai = new JLabel("0", SwingConstants.CENTER);
        lblJmlSelesai.setFont(new Font("Arial", Font.BOLD, 28));
        lblJmlSelesai.setForeground(new Color(30, 150, 50));
        lblJmlSelesai.setBounds(380, 100, 160, 40);
        main.add(lblJmlSelesai);
        
        // Tabel barang terbaru
        JLabel lblTerbaru = new JLabel("Barang Terbaru");
        lblTerbaru.setFont(new Font("Arial", Font.BOLD, 14));
        lblTerbaru.setBounds(20, 200, 200, 25);
        main.add(lblTerbaru);

        String[] kolom = {"Nama Barang", "Keterangan", "Tanggal", "Status"};
        tableModel = new DefaultTableModel(kolom, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        tabelTerbaru = new JTable(tableModel);
        tabelTerbaru.setRowHeight(28);
        tabelTerbaru.setFont(new Font("Arial", Font.PLAIN, 12));
        tabelTerbaru.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        tabelTerbaru.getTableHeader().setBackground(new Color(30, 58, 138));
        tabelTerbaru.getTableHeader().setForeground(Color.WHITE);
        
        JScrollPane scroll = new JScrollPane(tabelTerbaru);
        scroll.setBounds(20, 230, 640, 250);
        main.add(scroll);

        // Tombol lihat semua
        JButton btnLihatSemua = new JButton("Lihat Semua →");
        btnLihatSemua.setBounds(510, 200, 150, 25);
        btnLihatSemua.setBackground(new Color(30, 58, 138));
        btnLihatSemua.setForeground(Color.WHITE);
        btnLihatSemua.setFont(new Font("Arial", Font.PLAIN, 11));
        btnLihatSemua.setFocusPainted(false);
        btnLihatSemua.setBorderPainted(false);
        btnLihatSemua.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLihatSemua.addActionListener(e -> {
            new ViewDaftarBarang(userAktif).setVisible(true);
            dispose();
        });
        main.add(btnLihatSemua);
    }
    
    private JButton buatMenuButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.PLAIN, 13));
        btn.setForeground(new Color(180, 200, 255));
        btn.setBackground(new Color(30, 58, 138));
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }
    
    private void buatKartuStat(JPanel parent, String icon, String label, int x, int y, Color bgColor, Color txtColor) {
        JPanel kartu = new JPanel(null);
        kartu.setBounds(x, y, 160, 90);
        kartu.setBackground(bgColor);
        kartu.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));
        parent.add(kartu);

        JLabel lblIcon = new JLabel(icon + " " + label, SwingConstants.CENTER);
        lblIcon.setFont(new Font("Arial", Font.BOLD, 11));
        lblIcon.setForeground(txtColor);
        lblIcon.setBounds(5, 55, 150, 20);
        kartu.add(lblIcon);
    }
    
    private void loadData() {
        // Hitung statistik
        int jmlHilang = controllerBarang.countBarang("hilang", null);
        int jmlDitemukan = controllerBarang.countBarang("ditemukan", null);
        int jmlSelesai = controllerBarang.countBarang("hilang", "selesai") +
                         controllerBarang.countBarang("ditemukan", "selesai");

        lblJmlHilang.setText(String.valueOf(jmlHilang));
        lblJmlDitemukan.setText(String.valueOf(jmlDitemukan));
        lblJmlSelesai.setText(String.valueOf(jmlSelesai));
        
        // Isi tabel terbaru (max 5 data)
        tableModel.setRowCount(0);
        List<ModelBarang> list = controllerBarang.getAllBarang();
        int max = Math.min(list.size(), 5);
        for (int i = 0; i < max; i++) {
            ModelBarang b = list.get(i);
            String ket = b.getJenis().equals("hilang") ? "Hilang • " + b.getLokasi() : "Ditemukan • " + b.getLokasi();
            tableModel.addRow(new Object[]{b.getJudul(), ket, b.getTanggal(), b.getStatus().toUpperCase()});
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnLogout) {
            int konfirm = JOptionPane.showConfirmDialog(this, "Yakin ingin logout?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (konfirm == JOptionPane.YES_OPTION) {
                new ControllerUser().logout();
                new ViewLogin();
                dispose();
            }
        } else if (e.getSource() == btnDaftarBarang || e.getSource() == btnBarangHilang || e.getSource() == btnBarangDitemukan) {
            new ViewDaftarBarang(userAktif).setVisible(true);
            dispose();
        } else if (e.getSource() == btnRiwayat) {
            new ViewRiwayat(userAktif).setVisible(true);
            dispose();
        } else if (e.getSource() == btnDashboard) {
            loadData();
        }
    }
}
