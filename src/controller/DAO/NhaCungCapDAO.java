/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.DAO;

import config.JDBCUtil;
import model.NhaCungCap;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NhaCungCapDAO implements DAO<NhaCungCap> {

    public static NhaCungCapDAO getInstance() {
        return new NhaCungCapDAO();
    }

    @Override
    public void insert(NhaCungCap t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "INSERT INTO `nhacungcap`(`tenncc`, `diachi`,`sdt`,`email`) VALUES (?,?,?,?)";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t.getTenncc());
            pst.setString(2, t.getDiachi());
            pst.setString(3, t.getSdt());
            pst.setString(4, "");
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(NhaCungCapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(NhaCungCap t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "UPDATE `nhacungcap` SET `mancc`=?,`tenncc`=?,`diachi`=?,`sdt`=? WHERE mancc=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t.getMancc());
            pst.setString(2, t.getTenncc());
            pst.setString(3, t.getDiachi());
            pst.setString(4, t.getSdt());
            pst.setInt(5, t.getMancc());
            
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(NhaCungCapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(String t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "DELETE FROM  `nhacungcap` WHERE `mancc` = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(NhaCungCapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList<NhaCungCap> selectAll() {
        ArrayList<NhaCungCap> result = new ArrayList<NhaCungCap>();
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM nhacungcap";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int ma = rs.getInt("mancc");
                String ten = rs.getString("tenncc");
                String diachi = rs.getString("diachi");
                String sdt = rs.getString("sdt");
                NhaCungCap kh = new NhaCungCap(ma, ten, diachi, sdt);
                result.add(kh);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    @Override
    public NhaCungCap selectById(String t) {
        NhaCungCap result = null;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM nhacungcap WHERE mancc=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int ma = rs.getInt("mancc");
                String ten = rs.getString("tenncc");
                String diachi = rs.getString("diachi");
                String sdt = rs.getString("sdt");
                result = new NhaCungCap(ma, ten, sdt, diachi);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
}
