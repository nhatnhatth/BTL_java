/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.BUS;

import controller.DAO.NhanVienDAO;
import model.NhanVien;

import java.util.ArrayList;

/**
 *
 * @author robot
 */
public class NhanVienBUS {
    public ArrayList<NhanVien> listNv = NhanVienDAO.getInstance().selectAll();
    public NhanVienDAO nhanVienDAO = NhanVienDAO.getInstance();

    public NhanVienBUS() {
    }

    public ArrayList<NhanVien> getAll() {
        return this.listNv;
    }

    public void add(NhanVien nv) {
        NhanVienDAO.getInstance().insert(nv);
        listNv = NhanVienDAO.getInstance().selectAll();
    }

    public void update(NhanVien nv) {
        NhanVienDAO.getInstance().update(nv);
        listNv = NhanVienDAO.getInstance().selectAll();
    }

    public void delete(NhanVien nv) {
        NhanVienDAO.getInstance().delete(String.valueOf(nv.getMaNV()));
        listNv = NhanVienDAO.getInstance().selectAll();
    }


    public ArrayList<NhanVien> search(String text) {
        ArrayList<NhanVien> result = new ArrayList<>();
        return result;
    }
}
