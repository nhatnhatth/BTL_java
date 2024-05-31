package model;

import java.sql.Date;

public class HoaDon {
    private int mahd;
    private int manv;
    private int makh;
    private int masp;
    private Date date;
    private int thanhtien;
    private int soluong;

    public HoaDon(int mahd, int manv, int makh, int masp, Date date, int thanhtien, int soluong) {
        this.mahd = mahd;
        this.manv = manv;
        this.makh = makh;
        this.masp = masp;
        this.date = date;
        this.thanhtien = thanhtien;
        this.soluong = soluong;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getMahd() {
        return mahd;
    }

    public void setMahd(int mahd) {
        this.mahd = mahd;
    }

    public int getManv() {
        return manv;
    }

    public void setManv(int manv) {
        this.manv = manv;
    }

    public int getMakh() {
        return makh;
    }

    public void setMakh(int makh) {
        this.makh = makh;
    }

    public int getMasp() {
        return masp;
    }

    public void setMasp(int masp) {
        this.masp = masp;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getThanhtien() {
        return thanhtien;
    }

    public void setThanhtien(int thanhtien) {
        this.thanhtien = thanhtien;
    }
}
