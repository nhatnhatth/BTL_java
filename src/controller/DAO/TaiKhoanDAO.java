package controller.DAO;

import config.JDBCUtil;
import model.TaiKhoan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class TaiKhoanDAO implements DAO<TaiKhoan> {
    
    public static TaiKhoanDAO getInstance(){
        return new TaiKhoanDAO();
    }

    @Override
    public void insert(TaiKhoan t) {

    }

    @Override
    public void update(TaiKhoan t) {

    }
    

    @Override
    public void delete(String t) {

    }

    @Override
    public ArrayList<TaiKhoan> selectAll() {
        ArrayList<TaiKhoan> result = new ArrayList<TaiKhoan>();
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM taikhoan WHERE trangthai = '0' OR trangthai = '1'";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("email");
                TaiKhoan tk = new TaiKhoan(id, username, password, email);
                result.add(tk);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
        }
        return result;
    }

    @Override
    public TaiKhoan selectById(String t) {
        TaiKhoan result = null;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM taikhoan WHERE manv=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("email");
                result = new TaiKhoan(id, username, password, email);
                return result;
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
        }
        return result;
    }
    
    public TaiKhoan selectByUser(String t) {
        TaiKhoan result = null;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM taikhoan WHERE username=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("email");
                result = new TaiKhoan(id, username, password, email);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
        }
        return result;
    }
}
