/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import controller.config.JDBCUtil;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.KhachHang;

public class KhachHangDAO implements DAO<KhachHang> {

    public static KhachHangDAO getInstance() {
        return new KhachHangDAO();
    }

    @Override
    public void insert(KhachHang t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "INSERT INTO `khachhang`(`tenKH`, `diachi`,`sdt`,`email`) VALUES (?,?,?,?)";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t.getTenKH());
            pst.setString(2, t.getDiachi());
            pst.setString(3, t.getSdt());
            pst.setString(4, "");
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(KhachHang t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "UPDATE `khachhang` SET `makh`=?,`tenkh`=?,`diachi`=?,`sdt`=? WHERE makh=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t.getMaKH());
            pst.setString(2, t.getTenKH());
            pst.setString(3, t.getDiachi());
            pst.setString(4, t.getSdt());
            pst.setInt(5, t.getMaKH());
            
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(String t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "DELETE FROM  `khachhang` WHERE `makh` = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList<KhachHang> selectAll() {
        ArrayList<KhachHang> result = new ArrayList<KhachHang>();
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM khachhang";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int makh = rs.getInt("makh");
                String tenkhachhang = rs.getString("tenkh");
                String diachi = rs.getString("diachi");
                String sdt = rs.getString("sdt");
                KhachHang kh = new KhachHang(makh, tenkhachhang, diachi, sdt);
                result.add(kh);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    @Override
    public KhachHang selectById(String t) {
        KhachHang result = null;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM khachhang WHERE makh=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int makh = rs.getInt("makh");
                String tenkhachhang = rs.getString("tenkh");
                String diachi = rs.getString("diachi");
                String sdt = rs.getString("sdt");
                result = new KhachHang(makh, tenkhachhang, sdt, diachi);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
}
