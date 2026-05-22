/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Barang.*;
import java.util.List;
/**
 *
 * @author karina
 */
public class ControllerBarang {
    DAOBarang daoBarang;

    public ControllerBarang(){
        daoBarang = new DAOBarang();
    }

    public void insert(ModelBarang barang){
        daoBarang.insert(barang);
    }

    public void update(ModelBarang barang){
        daoBarang.update(barang);
    }

    public void delete(int id){
        daoBarang.delete(id);
    }

    public List<ModelBarang> getAll(){
        return daoBarang.getAll();
    }

    public List<ModelBarang> search(String keyword){
        return daoBarang.search(keyword);
    }
}
