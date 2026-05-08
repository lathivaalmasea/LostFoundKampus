/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;
import controller.ControllerBarang;
import model.ModelBarang;
import model.ModelUser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
/**
 *
 * @author Ivaa
 */
public class ViewFormLapor extends JFrame implements ActionListener{
    private ModelUser userAktif;
    private ControllerBarang controllerBarang;
    private JTextField tfNama, tfLokasi, tfKontak;
    private JTextArea taDesc;
    private JComboBox<String> cbKategori, cbJenis;
    private JButton btnSimpan, btnBatal;
    private JLabel lblTanggal;

    public ViewFormLapor(ModelUser user) {
        this.userAktif = user;
        controllerBarang = new ControllerBarang();
        initComponents();
    }
    
    private void initComponents() {
        setTitle("Lapor Barang");
        setSize(500, 550);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(new Color(245, 247, 250));
        setLayout(null);

        // Header
        JPanel header = new JPanel(null);
        header.setBackground(new Color(30, 58, 138));
        header.setBounds(0, 0, 500, 50);
        add(header);
        
        JLabel lblJudul = new JLabel("Laporkan Barang", SwingConstants.CENTER);
        lblJudul.setFont(new Font("Arial", Font.BOLD, 14));
        lblJudul.setForeground(Color.WHITE);
        lblJudul.setBounds(0, 12, 500, 25);
        header.add(lblJudul);

        // Form fields
        int y = 65;
        String[] labels = {"Nama Barang *", "Jenis Laporan *", "Kategori *", 
            "Lokasi *", "Deskripsi", "Kontak Anda"
        };
        
        for (String label : labels) {
            JLabel lbl = new JLabel(label);
            lbl.setFont(new Font("Arial", Font.PLAIN, 12));
            lbl.setBounds(30, y, 200, 20);
            add(lbl);
            y += 22;
            
            if (label.equals("Jenis Laporan *")) {
                cbJenis = new JComboBox<>(new String[]{"hilang", "ditemukan"});
                cbJenis.setBounds(30, y, 440, 30);
                cbJenis.setFont(new Font("Arial", Font.PLAIN, 12));
                add(cbJenis);
            } else if (label.equals("Kategori *")) {
                cbKategori = new JComboBox<>(new String[]{"Elektronik", "Dompet", "Kunci", "Tas", "Buku", "Pakaian", "Perlengkapan", "Lainnya"});
                cbKategori.setBounds(30, y, 440, 30);
                cbKategori.setFont(new Font("Arial", Font.PLAIN, 12));
                add(cbKategori);
            } else if (label.equals("Deskripsi")) {
                taDesc = new JTextArea();
                taDesc.setFont(new Font("Arial", Font.PLAIN, 12));
                taDesc.setLineWrap(true);
                JScrollPane sp = new JScrollPane(taDesc);
                sp.setBounds(30, y, 440, 60);
                add(sp);
                y += 32;
            } else {
                JTextField tf = new JTextField();
                tf.setBounds(30, y, 440, 30);
                tf.setFont(new Font("Arial", Font.PLAIN, 12));
                tf.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(200, 200, 200)),
                    BorderFactory.createEmptyBorder(4, 8, 4, 8)
                ));
                if (label.equals("Nama Barang *")) tfNama = tf;
                else if (label.equals("Lokasi *")) tfLokasi = tf;
                else if (label.equals("Kontak Anda")) tfKontak = tf;
                add(tf);
            }
            y += 38;
        }
        
        // Tanggal otomatis
        JLabel lblTgl = new JLabel("Tanggal: " + LocalDate.now().toString());
        lblTgl.setFont(new Font("Arial", Font.ITALIC, 11));
        lblTgl.setForeground(Color.GRAY);
        lblTgl.setBounds(30, y, 300, 20);
        add(lblTgl);
        lblTanggal = lblTgl;
        y += 30;
        
        // Tombol
        btnSimpan = new JButton("Simpan Laporan");
        btnSimpan.setBounds(30, y, 200, 38);
        btnSimpan.setBackground(new Color(30, 58, 138));
        btnSimpan.setForeground(Color.WHITE);
        btnSimpan.setFont(new Font("Arial", Font.BOLD, 13));
        btnSimpan.setBorderPainted(false);
        btnSimpan.setFocusPainted(false);
        btnSimpan.addActionListener(this);
        add(btnSimpan);
        
        btnBatal = new JButton("Batal");
        btnBatal.setBounds(245, y, 100, 38);
        btnBatal.setBackground(new Color(200, 200, 200));
        btnBatal.setFont(new Font("Arial", Font.BOLD, 13));
        btnBatal.setBorderPainted(false);
        btnBatal.setFocusPainted(false);
        btnBatal.addActionListener(this);
        add(btnBatal);

        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSimpan) {
            try {
                ModelBarang barang = new ModelBarang();
                barang.setUserId(userAktif.getId());
                barang.setJudul(tfNama.getText());
                barang.setJenis((String) cbJenis.getSelectedItem());
                barang.setKategori((String) cbKategori.getSelectedItem());
                barang.setLokasi(tfLokasi.getText());
                barang.setDeskripsi(taDesc.getText());
                barang.setTanggal(LocalDate.now().toString());
                barang.setPelaporKontak(tfKontak.getText());
                
                boolean berhasil = controllerBarang.simpanBarang(barang);
                if (berhasil) {
                    JOptionPane.showMessageDialog(this,
                        "Laporan berhasil disimpan!", "Berhasil", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    new ViewDaftarBarang(userAktif).setVisible(true);
                }
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Peringatan", JOptionPane.WARNING_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == btnBatal) {
            dispose();
        }
    }
}
