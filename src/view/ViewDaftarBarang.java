/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;
import controller.ControllerBarang;
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
public class ViewDaftarBarang extends JFrame implements ActionListener{
    private ModelUser userAktif;
    private ControllerBarang controllerBarang;
    private JTable tabel;
    private DefaultTableModel tableModel;
    private JTextField tfCari;
    private JButton btnCari, btnFilter, btnLapor, btnKlaim;
    private JComboBox<String> cbFilter;
    
    public ViewDaftarBarang(ModelUser user) {
        this.userAktif = user;
        controllerBarang = new ControllerBarang();
        initComponents();
        loadSemuaBarang();
    }
    
    private void initComponents() {
        setTitle("Lost & Found Kampus - Daftar Barang");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Header
        JPanel header = new JPanel(null);
        header.setBackground(new Color(30, 58, 138));
        header.setPreferredSize(new Dimension(900, 60));
        add(header, BorderLayout.NORTH);
        
        JButton btnBack = new JButton("← Dashboard");
        btnBack.setBounds(10, 15, 130, 30);
        btnBack.setBackground(new Color(59, 91, 180));
        btnBack.setForeground(Color.WHITE);
        btnBack.setBorderPainted(false);
        btnBack.setFocusPainted(false);
        btnBack.addActionListener(e -> { new ViewDashboard(userAktif).setVisible(true); dispose(); });
        header.add(btnBack);
        
        JLabel lblJudul = new JLabel("Daftar Barang", SwingConstants.CENTER);
        lblJudul.setFont(new Font("Arial", Font.BOLD, 16));
        lblJudul.setForeground(Color.WHITE);
        lblJudul.setBounds(300, 15, 300, 30);
        header.add(lblJudul);

        // Panel kontrol (cari, filter, tombol)
        JPanel kontrol = new JPanel(null);
        kontrol.setBackground(Color.WHITE);
        kontrol.setPreferredSize(new Dimension(900, 60));
        add(kontrol, BorderLayout.BEFORE_FIRST_LINE);
        
        // Search bar
        tfCari = new JTextField("Cari barang...");
        tfCari.setBounds(10, 15, 250, 30);
        tfCari.setForeground(Color.GRAY);
        tfCari.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (tfCari.getText().equals("Cari barang...")) { tfCari.setText(""); tfCari.setForeground(Color.BLACK); }
            }
        });
        kontrol.add(tfCari);
        
        btnCari = new JButton("🔍 Cari");
        btnCari.setBounds(265, 15, 80, 30);
        btnCari.setBackground(new Color(30, 58, 138));
        btnCari.setForeground(Color.WHITE);
        btnCari.setBorderPainted(false);
        btnCari.setFocusPainted(false);
        btnCari.addActionListener(this);
        kontrol.add(btnCari);
        
        String[] filterOpsi = {"Semua", "Hilang", "Ditemukan", "Selesai"};
        cbFilter = new JComboBox<>(filterOpsi);
        cbFilter.setBounds(355, 15, 120, 30);
        cbFilter.addActionListener(this);
        kontrol.add(cbFilter);
        
        btnLapor = new JButton("+ Lapor Barang");
        btnLapor.setBounds(600, 15, 150, 30);
        btnLapor.setBackground(new Color(22, 163, 74));
        btnLapor.setForeground(Color.WHITE);
        btnLapor.setFont(new Font("Arial", Font.BOLD, 12));
        btnLapor.setBorderPainted(false);
        btnLapor.setFocusPainted(false);
        btnLapor.addActionListener(this);
        kontrol.add(btnLapor);
        
        // Tabel
        String[] kolom = {"ID", "Nama Barang", "Kategori", "Lokasi", "Tanggal", "Jenis", "Status"};
        tableModel = new DefaultTableModel(kolom, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        tabel = new JTable(tableModel);
        tabel.setRowHeight(30);
        tabel.setFont(new Font("Arial", Font.PLAIN, 12));
        tabel.getTableHeader().setBackground(new Color(30, 58, 138));
        tabel.getTableHeader().setForeground(Color.WHITE);
        tabel.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        tabel.getColumnModel().getColumn(0).setMaxWidth(40);
        tabel.setSelectionBackground(new Color(210, 220, 255));
        
        JScrollPane scroll = new JScrollPane(tabel);
        add(scroll, BorderLayout.CENTER);

        // Panel bawah - tombol klaim
        JPanel bawah = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bawah.setBackground(Color.WHITE);
        bawah.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(220, 220, 220)));
        
        btnKlaim = new JButton("Saya Pemiliknya (Klaim)");
        btnKlaim.setBackground(new Color(22, 163, 74));
        btnKlaim.setForeground(Color.WHITE);
        btnKlaim.setFont(new Font("Arial", Font.BOLD, 12));
        btnKlaim.setBorderPainted(false);
        btnKlaim.setFocusPainted(false);
        btnKlaim.addActionListener(this);
        bawah.add(btnKlaim);
        
        JButton btnDetail = new JButton("Lihat Detail");
        btnDetail.setBackground(new Color(30, 58, 138));
        btnDetail.setForeground(Color.WHITE);
        btnDetail.setBorderPainted(false);
        btnDetail.setFocusPainted(false);
        btnDetail.addActionListener(e -> lihatDetail());
        bawah.add(btnDetail);

        add(bawah, BorderLayout.SOUTH);
        
        // Set layout yang benar - header di atas, kontrol di bawah header
        setLayout(new BorderLayout());
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(header, BorderLayout.NORTH);
        topPanel.add(kontrol, BorderLayout.SOUTH);
        add(topPanel, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(bawah, BorderLayout.SOUTH);
    }
    
    private void loadSemuaBarang() {
        tableModel.setRowCount(0);
        List<ModelBarang> list = controllerBarang.getAllBarang();
        for (ModelBarang b : list) {
            tableModel.addRow(new Object[]{
                b.getId(), b.getJudul(), b.getKategori(),
                b.getLokasi(), b.getTanggal(),
                b.getJenis().toUpperCase(), b.getStatus().toUpperCase()
            });
        }
    }
    
    private void lihatDetail() {
        int baris = tabel.getSelectedRow();
        if (baris < 0) {
            JOptionPane.showMessageDialog(this, "Pilih barang dulu!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int id = (int) tableModel.getValueAt(baris, 0);
        ModelBarang barang = controllerBarang.getBarangById(id);
        if (barang != null) {
            new ViewDetailBarang(userAktif, barang).setVisible(true);
            dispose();
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCari) {
            String keyword = tfCari.getText().trim();
            if (!keyword.isEmpty() && !keyword.equals("Cari barang...")) {
                tableModel.setRowCount(0);
                List<ModelBarang> list = controllerBarang.cariBarang(keyword);
                for (ModelBarang b : list) {
                    tableModel.addRow(new Object[]{
                        b.getId(), b.getJudul(), b.getKategori(),
                        b.getLokasi(), b.getTanggal(),
                        b.getJenis().toUpperCase(), b.getStatus().toUpperCase()
                    });
                }
            } else {
                loadSemuaBarang();
            }
        } else if (e.getSource() == cbFilter) {
            String pilihan = (String) cbFilter.getSelectedItem();
            tableModel.setRowCount(0);
            List<ModelBarang> list;
            if (pilihan.equals("Hilang")) list = controllerBarang.getBarangByJenis("hilang");
            else if (pilihan.equals("Ditemukan")) list = controllerBarang.getBarangByJenis("ditemukan");
            else list = controllerBarang.getAllBarang();
            for (ModelBarang b : list) {
                tableModel.addRow(new Object[]{
                    b.getId(), b.getJudul(), b.getKategori(),
                    b.getLokasi(), b.getTanggal(),
                    b.getJenis().toUpperCase(), b.getStatus().toUpperCase()
                });
            }
        } else if (e.getSource() == btnLapor) {
            new ViewFormLapor(userAktif).setVisible(true);
            dispose();
        } else if (e.getSource() == btnKlaim) {
            lihatDetail();
        }
    }
}
