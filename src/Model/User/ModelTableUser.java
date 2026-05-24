/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.User;

/**
 *
 * @author Ivaa
 */

import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ModelTableUser
extends AbstractTableModel {

    List<ModelUser> listUser;

    public ModelTableUser(
            List<ModelUser> listUser
    ){

        this.listUser = listUser;
    }

    String kolom[] = {
            "ID",
            "Username",
            "Nama",
            "Role"
    };

    @Override
    public int getRowCount() {

        return listUser.size();
    }

    @Override
    public int getColumnCount() {

        return kolom.length;
    }

    @Override
    public String getColumnName(int column) {

        return kolom[column];
    }

    @Override
    public Object getValueAt(
            int rowIndex,
            int columnIndex
    ) {

        switch(columnIndex){

            case 0:
                return listUser
                        .get(rowIndex)
                        .getId();

            case 1:
                return listUser
                        .get(rowIndex)
                        .getUsername();

            case 2:
                return listUser
                        .get(rowIndex)
                        .getNama();

            case 3:
                return listUser
                        .get(rowIndex)
                        .getRole();

            default:
                return null;
        }
    }
}