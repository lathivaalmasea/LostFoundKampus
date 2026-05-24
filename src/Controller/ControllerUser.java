/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author karina
 */

import Model.User.DAOUser;
import Model.User.ModelUser;

public class ControllerUser {

    DAOUser daoUser;

    public ControllerUser(){

        daoUser =
                new DAOUser();
    }

    public void insert(ModelUser user){

        daoUser.insert(user);
    }
}