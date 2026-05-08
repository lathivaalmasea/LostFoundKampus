/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;
import controller.ControllerKlaim;
import model.ModelBarang;
import model.ModelKlaim;
import model.ModelUser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 *
 * @author Ivaa
 */
public class ViewDetailBarang extends JFrame implements ActionListener{
    private ModelUser userAktif;
    private ModelBarang barang;
    private ControllerKlaim controllerKlaim;
    private JButton btnKlaim, btnHubungi, btnKembali;

    public ViewDetailBarang(ModelUser user, ModelBarang barang) {
        this.userAktif = user;
        this.barang = barang;
        this.controllerKlaim = new ControllerKlaim();
        initComponents();
    }
    
    private void initComponents() {
        setTitle("Detail Barang - " + barang.getJudul());
        setSize(550, 520);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(245, 247, 250));
        
        btnKembali = new JButton("← Kembali");
        btnKembali.setBounds(10, 10, 100, 28);
        btnKembali.setBackground(new Color(30, 58, 138));
        btnKembali.setForeground(Color.WHITE);
        btnKembali.setBorderPainted(false);
        btnKembali.setFocusPainted(false);
        btnKembali.addActionListener(this);
        add(btnKembali);
        
        // Judul
        JLabel lblJudul = new JLabel(barang.getJudul());
        lblJudul.setFont(new Font("Arial", Font.BOLD, 20));
        lblJudul.setBounds(20, 55, 500, 30);
        add(lblJudul);
        
        // Badge status jenis
        JLabel lblBadge = new JLabel(barang.getJenis().toUpperCase());
        lblBadge.setFont(new Font("Arial", Font.BOLD, 11));
        lblBadge.setForeground(Color.WHITE);
        lblBadge.setBackground(barang.getJenis().equals("ditemukan") ? new Color(22, 163, 74) : new Color(220, 50, 50));
        lblBadge.setOpaque(true);
        lblBadge.setBounds(20, 90, 90, 22);
        lblBadge.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblBadge);
        
        // Detail info
        String[][] info = {
            {"Kategori", barang.getKategori()},
            {"Lokasi " + (barang.getJenis().equals("ditemukan") ? "Ditemukan" : "Terakhir"), barang.getLokasi()},
            {"Tanggal", barang.getTanggal()},
            {"Status", barang.getStatus().toUpperCase()},
            {"Kontak Pelapor", barang.getPelaporKontak() != null ? barang.getPelaporKontak() : "-"}
        };
        
        int y = 130;
        for (String[] baris : info) {
            JLabel lblKey = new JLabel(baris[0] + ":");
            lblKey.setFont(new Font("Arial", Font.BOLD, 12));
            lblKey.setForeground(new Color(100, 100, 100));
            lblKey.setBounds(20, y, 160, 22);
            add(lblKey);

            JLabel lblVal = new JLabel(baris[1]);
            lblVal.setFont(new Font("Arial", Font.PLAIN, 12));
            lblVal.setBounds(185, y, 340, 22);
            add(lblVal);
            y += 30;
        }
        
        // Deskripsi
        JLabel lblDescLabel = new JLabel("Deskripsi:");
        lblDescLabel.setFont(new Font("Arial", Font.BOLD, 12));
        lblDescLabel.setForeground(new Color(100, 100, 100));
        lblDescLabel.setBounds(20, y, 160, 22);
        add(lblDescLabel);
        y += 25;
        
        JTextArea taDesc = new JTextArea(barang.getDeskripsi() != null ? barang.getDeskripsi() : "Tidak ada deskripsi.");
        taDesc.setFont(new Font("Arial", Font.PLAIN, 12));
        taDesc.setLineWrap(true);
        taDesc.setEditable(false);
        taDesc.setBackground(new Color(245, 247, 250));
        taDesc.setBounds(20, y, 505, 60);
        add(taDesc);
        y += 75;
        
        // Tombol aksi
        if (!barang.getStatus().equals("selesai")) {
            if (barang.getJenis().equals("ditemukan")) {
                btnKlaim = new JButton("Saya Pemiliknya (Klaim)");
                btnKlaim.setBounds(20, y, 220, 38);
                btnKlaim.setBackground(new Color(22, 163, 74));
                btnKlaim.setForeground(Color.WHITE);
                btnKlaim.setFont(new Font("Arial", Font.BOLD, 12));
                btnKlaim.setBorderPainted(false);
                btnKlaim.setFocusPainted(false);
                btnKlaim.addActionListener(this);
                add(btnKlaim);
            }
            
            btnHubungi = new JButton("Hubungi Pelapor");
            btnHubungi.setBounds(255, y, 160, 38);
            btnHubungi.setBackground(new Color(30, 58, 138));
            btnHubungi.setForeground(Color.WHITE);
            btnHubungi.setFont(new Font("Arial", Font.BOLD, 12));
            btnHubungi.setBorderPainted(false);
            btnHubungi.setFocusPainted(false);
            btnHubungi.addActionListener(this);
            add(btnHubungi);
        } else {
            JLabel lblSelesai = new JLabel("✅ Barang ini sudah selesai/diklaim");
            lblSelesai.setFont(new Font("Arial", Font.BOLD, 13));
            lblSelesai.setForeground(new Color(22, 163, 74));
            lblSelesai.setBounds(20, y, 400, 38);
            add(lblSelesai);
        }

        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnKembali) {
            dispose();
        } else if (e.getSource() == btnKlaim) {
            prosesKlaim();
        } else if (btnHubungi != null && e.getSource() == btnHubungi) {
            String kontak = barang.getPelaporKontak() != null ? barang.getPelaporKontak() : "Tidak tersedia";
            JOptionPane.showMessageDialog(this,
                "Hubungi pelapor di:\n" + kontak, "Kontak Pelapor", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void prosesKlaim() {
        JPanel panelKlaim = new JPanel(new GridLayout(4, 2, 5, 8));
        JTextField tfKontak = new JTextField();
        JTextArea taAlasan = new JTextArea(3, 20);
        taAlasan.setLineWrap(true);

        panelKlaim.add(new JLabel("Kontak Anda:")); panelKlaim.add(tfKontak);
        panelKlaim.add(new JLabel("Alasan Klaim:")); panelKlaim.add(new JScrollPane(taAlasan));

        int result = JOptionPane.showConfirmDialog(this, panelKlaim,
            "Form Klaim Barang", JOptionPane.OK_CANCEL_OPTION);
        
        if (result == JOptionPane.OK_OPTION) {
            try {
                ModelKlaim klaim = new ModelKlaim();
                klaim.setBarangId(barang.getId());
                klaim.setPemohonId(userAktif.getId());
                klaim.setAlasan(taAlasan.getText());
                klaim.setKontak(tfKontak.getText());
                
                boolean berhasil = controllerKlaim.kirimKlaim(klaim);
                if (berhasil) {
                    JOptionPane.showMessageDialog(this,
                        "Klaim berhasil dikirim!\nSilakan tunggu konfirmasi dari pelapor.",
                        "Klaim Terkirim", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                }
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Peringatan", JOptionPane.WARNING_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }   
}
