package com.mycompany.quanlybanhangsieuthi.dao;

import com.mycompany.quanlybanhangsieuthi.controller.ConnectionDB;
import com.mycompany.quanlybanhangsieuthi.model.User;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAO {
    public UserDAO() {
    }

    public ArrayList<User> readDB() {
        ArrayList<User> users = new ArrayList<>();
        Connection conn = ConnectionDB.getConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(null, "-- ERROR! Lỗi đọc dữ liệu bảng tài khoản");
            return users;
        }
        try {
            String qry = "SELECT * FROM user";
            ResultSet r = conn.createStatement().executeQuery(qry);
            if (r != null) {
                while (r.next()) {
                    int id = r.getInt("user_id");
                    String name = r.getString("username");
                    String password = r.getString("password");
                    String email = r.getString("email");

                    users.add(new User(id, name, password, email));
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "-- ERROR! Lỗi đọc dữ liệu bảng tài khoản");
        } finally {
            ConnectionDB.closeConnection(ConnectionDB.getConnection());
        }
        return users;
    }
}
