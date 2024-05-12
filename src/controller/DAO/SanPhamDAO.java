package controller.DAO;

import model.SanPham;
import config.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SanPhamDAO implements DAO<SanPham> {

    public static SanPhamDAO getInstance() {
        return new SanPhamDAO();
    }

    @Override
    public void insert(SanPham t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "INSERT INTO `sanpham`(`tensp`, `gia`, `soluong`, `loaisp`) VALUES (?,?,?,?)";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t.getTensp());
            pst.setInt(2, t.getGia());
            pst.setInt(3, t.getSoluong());
            pst.setString(4, t.getLoaisp());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(SanPham t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "UPDATE `sanpham` SET `tensp`=?,`gia`=?,`soluong`=?,`loaisp`=? WHERE `masp`=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t.getTensp());
            pst.setInt(2, t.getGia());
            pst.setInt(3, t.getSoluong());
            pst.setString(4, t.getLoaisp());
            pst.setInt(5, t.getMasp());

            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(String t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "DELETE FROM `sanpham` WHERE masp = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList<SanPham> selectAll() {
        ArrayList<SanPham> result = new ArrayList<SanPham>();
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM sanpham";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int masp = rs.getInt("masp");
                String tensp = rs.getString("tensp");
                int gia = rs.getInt("gia");
                int soluong = rs.getInt("soluong");
                String loaisp = rs.getString("loaisp");
                int mancc = rs.getInt("mancc");
                SanPham sp = new SanPham(masp, tensp, gia, soluong, loaisp, mancc);
                result.add(sp);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
        }
        return result;
    }

    @Override
    public SanPham selectById(String t) {
        SanPham result = null;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM sanpham WHERE masp=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int masp = rs.getInt("masp");
                String tensp = rs.getString("tensp");
                int gia = rs.getInt("gia");
                int soluong = rs.getInt("soluong");
                String loaisp = rs.getString("loaisp");
                int mancc = rs.getInt("mancc");
                result = new SanPham(masp, tensp, gia, soluong, loaisp, mancc);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
        }
        return result;
    }
}
