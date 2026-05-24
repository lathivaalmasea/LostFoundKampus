/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Model.User;

/**
 *
 * @author Ivaa
 */

import java.util.List;

public interface InterfaceDAOUser {

    public void insert(ModelUser user);

    public ModelUser login(
            String username,
            String password
    );

    public List<ModelUser> getAll();
}