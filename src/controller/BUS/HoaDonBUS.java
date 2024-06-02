package controller.BUS;


import controller.DAO.HoaDonDAO;
import model.HoaDon;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class HoaDonBUS {

    private final HoaDonDAO dao = new HoaDonDAO();
    public ArrayList<HoaDon> list = new ArrayList<>();

    public HoaDonBUS() {
        list = dao.selectAll();
    }

    public ArrayList<HoaDon> getAll() {
        return this.list;
    }

    public void add(HoaDon kh) {
        dao.insert(kh);
        list = dao.selectAll();
    }

    public void delete(HoaDon kh) {
        dao.delete(Integer.toString(kh.getMahd()));
        list = dao.selectAll();
    }

    public void update(HoaDon kh) {
        dao.update(kh);
        list = dao.selectAll();
        list = dao.selectAll();
    }

    public ArrayList<HoaDon> search(String text, String type) {
        ArrayList<HoaDon> result = new ArrayList<>();
        text = text.toLowerCase();
        return result;
    }

    public ArrayList<HoaDon> search(String text) {
        try {
            return this.list.stream()
                    .filter((hd) -> String.valueOf(hd.getMahd()).contains(text))
                    .collect(Collectors.toCollection(ArrayList::new));
        } catch (NumberFormatException e) {
            return new ArrayList<>();
        }
    }



    public int getDT(){
        return dao.getDT();
    }
}
