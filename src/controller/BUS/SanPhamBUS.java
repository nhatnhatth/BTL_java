package controller.BUS;

import controller.DAO.SanPhamDAO;
import model.SanPham;

import java.util.ArrayList;

public class SanPhamBUS {

    public final SanPhamDAO spDAO = new SanPhamDAO();
    private ArrayList<SanPham> listSP = new ArrayList<>();

    public SanPhamBUS() {
        listSP = spDAO.selectAll();
    }

    public ArrayList<SanPham> getAll() {

        return this.listSP;
    }

    public void add(SanPham sp) {
        spDAO.insert(sp);
        listSP = spDAO.selectAll();
    }

    public void delete(SanPham lh) {
        spDAO.delete(Integer.toString(lh.getMasp()));
        listSP = spDAO.selectAll();
    }

    public void update(SanPham sp) {
        spDAO.update(sp);
        listSP = spDAO.selectAll();
    }

    public ArrayList<SanPham> search(String text) {
        text = text.toLowerCase();
        ArrayList<SanPham> result = new ArrayList<>();
        for (SanPham i : this.listSP) {
            if (Integer.toString(i.getMasp()).toLowerCase().contains(text) || i.getTensp().toLowerCase().contains(text)) {
                result.add(i);
            }
        }
        return result;
    }
}
