package com.mycompany.quanlybanhangsieuthi.controller;

import javax.swing.*;
import java.sql.*;

public class ConnectionDB {

    public static Connection getConnection() {
        Connection result = null;
        checkDriver();
        try {
            // Dang ky MySQL Driver voi DriverManager
            //Cac thong so
            String url = "jdbc:mysql://localhost:3306/quanlysieuthi";
            String userName = "root";
            String password = "";
            //Tao ket noi
            result = DriverManager.getConnection(url, userName, password);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Không thể kết nối đến cơ sở dữ liệu !", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return result;
    }

    public static void closeConnection(Connection c) {
        try {
            if (c != null) {
                c.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void checkDriver() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "-- ERROR! Không tìm thấy Driver mySql");
        }
    }
}
