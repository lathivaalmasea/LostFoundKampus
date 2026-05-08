/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Ivaa
 */
public class ModelUser {
    private int id;
    private String username;
    private String password;
    private String namaLengkap;
    private String email;
    private String noHp;

    public ModelUser() {}
    
    public ModelUser(String username, String password, String namaLengkap) {
        this.username = username;
        this.password = password;
        this.namaLengkap = namaLengkap;
    }
    
    public int getId(){
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public String getUsername(){
        return username; 
    }
    public void setUsername(String username){
        this.username = username; 
    }
    
    public String getPassword(){ 
        return password; 
    }
    
    public void setPassword(String password){ 
        this.password = password; 
    }
    
    public String getNamaLengkap(){ 
        return namaLengkap; 
    }
    
    public void setNamaLengkap(String namaLengkap){ 
        this.namaLengkap = namaLengkap; 
    }
    
    public String getEmail(){ 
        return email; 
    }
    
    public void setEmail(String email){ 
        this.email = email; 
    }
    
    public String getNoHp(){ 
        return noHp; 
    }
    
    public void setNoHp(String noHp){ 
        this.noHp = noHp; 
    }
}
