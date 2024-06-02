package controller.BUS;

import controller.DAO.NhaCungCapDAO;
import model.NhaCungCap;

import java.util.ArrayList;

public class NhaCungCapBUS {

    private final NhaCungCapDAO nhaCungCapDAO = new NhaCungCapDAO();
    public ArrayList<NhaCungCap> listNcc = new ArrayList<>();

    public NhaCungCapBUS() {
        listNcc = nhaCungCapDAO.selectAll();
    }

    public ArrayList<NhaCungCap> getAll() {
        return this.listNcc;
    }

    public void add(NhaCungCap ncc) {
        nhaCungCapDAO.insert(ncc);
        listNcc = nhaCungCapDAO.selectAll();
    }

    public void delete(NhaCungCap ncc) {
        nhaCungCapDAO.delete(Integer.toString(ncc.getMancc()));
        listNcc = nhaCungCapDAO.selectAll();
    }

    public void update(NhaCungCap ncc) {
        nhaCungCapDAO.update(ncc);
        listNcc = nhaCungCapDAO.selectAll();
        listNcc = nhaCungCapDAO.selectAll();
    }

    public NhaCungCap getNcc(int mancc) {
        return nhaCungCapDAO.selectById(String.valueOf(mancc));
    }

    public ArrayList<NhaCungCap> search(String text, String type) {
        ArrayList<NhaCungCap> result = new ArrayList<>();
        text = text.toLowerCase();
        switch (type) {
            case "Tất cả" -> {
                for (NhaCungCap i : this.listNcc) {
                    if (Integer.toString(i.getMancc()).toLowerCase().contains(text) || i.getTenncc().toLowerCase().contains(text) || i.getDiachi().toLowerCase().contains(text) || i.getSdt().toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
            }
            case "Mã nhà cung cấp" -> {
                for (NhaCungCap i : this.listNcc) {
                    if (Integer.toString(i.getMancc()).toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
            }
            case "Tên nhà cung cấp" -> {
                for (NhaCungCap i : this.listNcc) {
                    if (i.getTenncc().toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
            }
            case "Địa chỉ" -> {
                for (NhaCungCap i : this.listNcc) {
                    if (i.getDiachi().toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
            }
            case "Số điện thoại" -> {
                for (NhaCungCap i : this.listNcc) {
                    if (i.getSdt().toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
            }
        }

        return result;
    }
}
