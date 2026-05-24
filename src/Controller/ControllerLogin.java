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

public class ControllerLogin {

    DAOUser daoUser;

    public static ModelUser userLogin;

    public ControllerLogin(){

        daoUser =
                new DAOUser();
    }

    public ModelUser login(
            String username,
            String password
    ){

        userLogin =
                daoUser.login(
                        username,
                        password
                );

        return userLogin;
    }
}