/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import controller.config.JDBCUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.NhanVien;

public class NhanVienDAO implements DAO<NhanVien> {
    public static NhanVienDAO getInstance(){
        return new NhanVienDAO();
    }

    @Override
    public void insert(NhanVien t) {
        int result = 0 ;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "INSERT INTO `nhanvien`(`tennv`, `diachi`,`sdt`,`email`) VALUES (?,?,?,?)";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t.getTenNV());
            pst.setString(2, t.getDiachi());
            pst.setString(3, t.getSdt());
            pst.setString(4, "");
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(NhanVien t) {
        int result = 0 ;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "UPDATE `nhanvien` SET `manv`=?,`tennv`=?,`diachi`=?,`sdt`=? WHERE manv=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t.getMaNV());
            pst.setString(2, t.getTenNV());
            pst.setString(3, t.getDiachi());
            pst.setString(4, t.getSdt());
            pst.setInt(5, t.getMaNV());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(String t) {
        int result = 0 ;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "DELETE FROM nhanvien WHERE manv = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList<NhanVien> selectAll() {
        ArrayList<NhanVien> result = new ArrayList<NhanVien>();
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM nhanvien";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int manv = rs.getInt("manv");
                String tenkhachhang = rs.getString("tennv");
                String diachi = rs.getString("diachi");
                String sdt = rs.getString("sdt");
                NhanVien nv = new NhanVien(manv, tenkhachhang, diachi, sdt);
                result.add(nv);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    @Override
    public NhanVien selectById(String t) {
        NhanVien result = null;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM nhanvien WHERE manv=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int makh = rs.getInt("manv");
                String tennv = rs.getString("tennv");
                String diachi = rs.getString("diachi");
                String sdt = rs.getString("sdt");
                result = new NhanVien(makh, tennv, sdt, diachi);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
        }
        return result;
    }
    
    public NhanVien selectByEmail(String t) {
        NhanVien result = null;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM khachhang WHERE makh=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int makh = rs.getInt("manv");
                String tennv = rs.getString("tennv");
                String diachi = rs.getString("diachi");
                String sdt = rs.getString("sdt");
                result = new NhanVien(makh, tennv, sdt, diachi);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
        }
        return result;
    }
}
