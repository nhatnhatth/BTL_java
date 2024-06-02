package controller.BUS;

import controller.DAO.KhachHangDAO;
import model.KhachHang;
import model.SanPham;

import java.util.ArrayList;

public class KhachHangBUS {

    private final KhachHangDAO khDAO = new KhachHangDAO();
    public ArrayList<KhachHang> listKhachHang = new ArrayList<>();

    public KhachHangBUS() {
        listKhachHang = khDAO.selectAll();
    }

    public ArrayList<KhachHang> getAll() {
        return this.listKhachHang;
    }

    public void add(KhachHang kh) {
        khDAO.insert(kh);
        listKhachHang = khDAO.selectAll();
    }

    public boolean delete(KhachHang kh) {
        boolean success = khDAO.deleteCheck(Integer.toString(kh.getMaKH()));
        if(success){
            listKhachHang = khDAO.selectAll();
        }
        return success;
    }

    public void update(KhachHang kh) {
        khDAO.update(kh);
        listKhachHang = khDAO.selectAll();
    }

    public KhachHang getKH(int makh) {
        return khDAO.selectById(String.valueOf(makh));
    }

    public ArrayList<KhachHang> search(String text, String type) {
        ArrayList<KhachHang> result = new ArrayList<>();
        text = text.toLowerCase();
        switch (type) {
            case "Tất cả" -> {
                for (KhachHang i : this.listKhachHang) {
                    if (Integer.toString(i.getMaKH()).toLowerCase().contains(text) || i.getTenKH().toLowerCase().contains(text) || i.getDiachi().toLowerCase().contains(text) || i.getSdt().toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
            }
            case "Mã khách hàng" -> {
                for (KhachHang i : this.listKhachHang) {
                    if (Integer.toString(i.getMaKH()).toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
            }
            case "Tên khách hàng" -> {
                for (KhachHang i : this.listKhachHang) {
                    if (i.getTenKH().toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
            }
            case "Địa chỉ" -> {
                for (KhachHang i : this.listKhachHang) {
                    if (i.getDiachi().toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
            }
            case "Số điện thoại" -> {
                for (KhachHang i : this.listKhachHang) {
                    if (i.getSdt().toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
            }
        }

        return result;
    }
}
