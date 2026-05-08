/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Ivaa
 */
public class ModelBarang {
    private int id;
    private int userId;
    private String judul;
    private String kategori;
    private String deskripsi;
    private String lokasi;
    private String tanggal;
    private String jenis;
    private String status;
    private String fotoPath;
    private String pelaporKontak;
    private String createdAt;
    
    public ModelBarang(){}

    public ModelBarang(int userId, String judul, String kategori, 
            String deskripsi, String lokasi, String tanggal, String jenis){
        this.userId = userId;
        this.judul = judul;
        this.kategori = kategori;
        this.deskripsi = deskripsi;
        this.lokasi = lokasi;
        this.tanggal = tanggal;
        this.jenis = jenis;
        this.status = "aktif";
    }
    
    public int getId(){ 
        return id; 
    }
    
    public void setId(int id){ 
        this.id = id; 
    }
    
    public int getUserId(){ 
        return userId;
    }
    public void setUserId(int userId){ 
        this.userId = userId;
    }
    
    public String getJudul(){ 
        return judul; 
    }
    
    public void setJudul(String judul){ 
        this.judul = judul; 
    }
    
    public String getKategori(){ 
        return kategori; 
    }
    
    public void setKategori(String kategori){ 
        this.kategori = kategori; 
    }
    
    public String getDeskripsi(){ 
        return deskripsi; 
    }
    
    public void setDeskripsi(String deskripsi){ 
        this.deskripsi = deskripsi; 
    }
    
    public String getLokasi(){ 
        return lokasi; 
    }
    
    public void setLokasi(String lokasi){ 
        this.lokasi = lokasi; 
    }
    
    public String getTanggal(){ 
        return tanggal; 
    }
    
    public void setTanggal(String tanggal){ 
        this.tanggal = tanggal; 
    }
    
    public String getJenis(){ 
        return jenis; 
    }
    
    public void setJenis(String jenis){ 
        this.jenis = jenis; 
    }
    
    public String getStatus(){ 
        return status; 
    }
    
    public void setStatus(String status){ 
        this.status = status; 
    }
    
    public String getFotoPath(){ 
        return fotoPath; 
    }
    
    public void setFotoPath(String fotoPath){ 
        this.fotoPath = fotoPath; 
    }
    
    public String getPelaporKontak(){ 
        return pelaporKontak; 
    }
    
    public void setPelaporKontak(String pelaporKontak){ 
        this.pelaporKontak = pelaporKontak; 
    }
    
    public String getCreatedAt(){ 
        return createdAt; 
    }
    
    public void setCreatedAt(String createdAt){ 
        this.createdAt = createdAt; 
    }
    
    @Override
    public String toString(){
        return judul + " (" + jenis + ") - " + lokasi;
    }
}
