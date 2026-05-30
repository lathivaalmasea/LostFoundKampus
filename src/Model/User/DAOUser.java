/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.User;

/**
 *
 * @author Ivaa
 */

import Model.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOUser
                implements InterfaceDAOUser {

        Connection connection;

        public DAOUser() {

                connection = DatabaseConnection
                                .getConnection();
        }

        @Override
        public void insert(ModelUser user) {

                try {

                        String query = "INSERT INTO users "
                                        + "(username,password,"
                                        + "nama,role)"
                                        + " VALUES (?,?,?,?)";

                        PreparedStatement ps = connection.prepareStatement(query);

                        ps.setString(1,
                                        user.getUsername());

                        ps.setString(2,
                                        user.getPassword());

                        ps.setString(3,
                                        user.getNama());

                        ps.setString(4,
                                        "user");

                        ps.executeUpdate();

                } catch (Exception e) {

                        System.out.println(e.getMessage());
                }
        }

        @Override
        public ModelUser getById(int id) {

                ModelUser user = null;

                try {

                        String query = "SELECT * FROM users "
                                        + "WHERE id=?";

                        PreparedStatement ps = connection.prepareStatement(query);

                        ps.setInt(1, id);

                        ResultSet rs = ps.executeQuery();

                        if (rs.next()) {

                                user = new ModelUser();

                                user.setId(
                                                rs.getInt("id"));

                                user.setUsername(
                                                rs.getString("username"));

                                user.setPassword(
                                                rs.getString("password"));

                                user.setNama(
                                                rs.getString("nama"));

                                user.setRole(
                                                rs.getString("role"));
                        }

                } catch (Exception e) {

                        System.out.println(e.getMessage());
                }

                return user;
        }

        @Override
        public ModelUser getByUsername(String username) {

                ModelUser user = null;

                try {

                        String query = "SELECT * FROM users "
                                        + "WHERE username=?";

                        PreparedStatement ps = connection.prepareStatement(query);

                        ps.setString(1, username);

                        ResultSet rs = ps.executeQuery();

                        if (rs.next()) {

                                user = new ModelUser();

                                user.setId(
                                                rs.getInt("id"));

                                user.setUsername(
                                                rs.getString("username"));

                                user.setPassword(
                                                rs.getString("password"));

                                user.setNama(
                                                rs.getString("nama"));

                                user.setRole(
                                                rs.getString("role"));
                        }

                } catch (Exception e) {

                        System.out.println(e.getMessage());
                }

                return user;
        }

        @Override
        public List<ModelUser> getAll() {

                List<ModelUser> list = new ArrayList<>();

                try {

                        String query = "SELECT * FROM users";

                        Statement st = connection.createStatement();

                        ResultSet rs = st.executeQuery(query);

                        while (rs.next()) {

                                ModelUser user = new ModelUser();

                                user.setId(
                                                rs.getInt("id"));

                                user.setUsername(
                                                rs.getString("username"));

                                user.setNama(
                                                rs.getString("nama"));

                                user.setRole(
                                                rs.getString("role"));

                                list.add(user);
                        }

                } catch (Exception e) {

                        System.out.println(e.getMessage());
                }

                return list;
        }

        @Override
        public void update(ModelUser user) {

                try {

                        String query = "UPDATE users SET "
                                        + "username=?,password=?,"
                                        + "nama=?,role=? "
                                        + "WHERE id=?";

                        PreparedStatement ps = connection.prepareStatement(query);

                        ps.setString(1,
                                        user.getUsername());

                        ps.setString(2,
                                        user.getPassword());

                        ps.setString(3,
                                        user.getNama());

                        ps.setString(4,
                                        user.getRole());

                        ps.setInt(5,
                                        user.getId());

                        ps.executeUpdate();

                } catch (Exception e) {

                        System.out.println(e.getMessage());
                }
        }

        @Override
        public void delete(int id) {

                try {

                        String query = "DELETE FROM users "
                                        + "WHERE id=?";

                        PreparedStatement ps = connection.prepareStatement(query);

                        ps.setInt(1, id);

                        ps.executeUpdate();

                } catch (Exception e) {

                        System.out.println(e.getMessage());
                }
        }
}