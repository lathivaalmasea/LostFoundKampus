/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Ivaa
 */
public class ModelKlaim {
    private int id;
    private int barangId;
    private int pemohonId;
    private String alasan;
    private String kontak;
    private String buktiPath;
    private String status;
    private String createdAt;
    private String judulBarang;
    private String namaPemohon;
    
    public ModelKlaim() {}

    public ModelKlaim(int barangId, int pemohonId, String alasan, String kontak) {
        this.barangId = barangId;
        this.pemohonId = pemohonId;
        this.alasan = alasan;
        this.kontak = kontak;
        this.status = "menunggu";
    }
    
    public int getId(){ 
        return id; 
    }
    
    public void setId(int id){ 
        this.id = id; 
    }
    
    public int getBarangId(){ 
        return barangId; 
    }
    
    public void setBarangId(int barangId){ 
        this.barangId = barangId;
    }
    
    public int getPemohonId(){ 
        return pemohonId; 
    }
    
    public void setPemohonId(int pemohonId){ 
        this.pemohonId = pemohonId; 
    }
    
    public String getAlasan(){ 
        return alasan; 
    }
    
    public void setAlasan(String alasan){ 
        this.alasan = alasan; 
    }
    
    public String getKontak(){ 
        return kontak; 
    }
    
    public void setKontak(String kontak){ 
        this.kontak = kontak;
    }
    
    public String getBuktiPath(){ 
        return buktiPath; 
    }
    
    public void setBuktiPath(String buktiPath){ 
        this.buktiPath = buktiPath;
    }
    
    public String getStatus(){ 
        return status; 
    }
    
    public void setStatus(String status){ 
        this.status = status; 
    }
    
    public String getCreatedAt(){ 
        return createdAt; 
    }
    
    public void setCreatedAt(String createdAt){ 
        this.createdAt = createdAt; 
    }
    
    public String getJudulBarang(){ 
        return judulBarang; 
    }
    
    public void setJudulBarang(String judulBarang){ 
        this.judulBarang = judulBarang; 
    }
    
    public String getNamaPemohon(){ 
        return namaPemohon; 
    }
    
    public void setNamaPemohon(String namaPemohon){ 
        this.namaPemohon = namaPemohon; 
    }
}
