/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.DAO;

import config.JDBCUtil;
import model.HoaDon;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HoaDonDAO implements DAO<HoaDon> {

    public static HoaDonDAO getInstance() {
        return new HoaDonDAO();
    }

    @Override
    public void insert(HoaDon t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "INSERT INTO `hoadon`(`manv`, `makh`,`masp`,`ngaylap`, `thanhtien`, `soluong`) VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t.getManv());
            pst.setInt(2, t.getMakh());
            pst.setInt(3, t.getMasp());
            pst.setDate(4, t.getDate());
            pst.setInt(5, t.getThanhtien());
            pst.setInt(6, t.getSoluong());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(HoaDon t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "UPDATE `hoadon` SET `manv`=?,`makh`=?,`masp`=?,`ngaylap`=?,`thanhtien`=?, `soluong`=? WHERE mahd=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t.getManv());
            pst.setInt(2, t.getMakh());
            pst.setInt(3, t.getMasp());
            pst.setDate(4, t.getDate());
            pst.setInt(5, t.getThanhtien());
            pst.setInt(6, t.getSoluong());
            pst.setInt(7, t.getMahd());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(String t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "DELETE FROM  `hoadon` WHERE `mahd` = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList<HoaDon> selectAll() {
        ArrayList<HoaDon> result = new ArrayList<HoaDon>();
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM hoadon";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int mahd = rs.getInt("mahd");
                int manv = rs.getInt("manv");
                int makh = rs.getInt("makh");
                int masp = rs.getInt("masp");
                Date date =rs.getDate("ngaylap");
                int thanhtien = rs.getInt("thanhtien");
                int soluong = rs.getInt("soluong");

                HoaDon kh = new HoaDon(mahd, manv, makh, masp, date, thanhtien, soluong);
                result.add(kh);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    @Override
    public HoaDon selectById(String t) {
        HoaDon result = null;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM hoadon WHERE mahd=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int mahd = rs.getInt("mahd");
                int manv = rs.getInt("manv");
                int makh = rs.getInt("makh");
                int masp = rs.getInt("masp");
                Date date =rs.getDate("ngaylap");
                int thanhtien = rs.getInt("thanhtien");
                int soluong = rs.getInt("soluong");

                result = new HoaDon(mahd, manv, makh, masp, date, thanhtien, soluong);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    public int getDT() {
        int dt = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT SUM(thanhtien) as dt from hoadon";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                dt = rs.getInt("dt");
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            System.out.println(e);
        }
        return dt;
    }
}
