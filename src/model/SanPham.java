/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class SanPham {

    private int masp;
    private String tensp;
    private int gia;
    private int soluong;
    private String loaisp;
    private int mancc;

    public SanPham(int masp, String tensp, int gia, int soluong, String loaisp, int mancc) {
        this.masp = masp;
        this.tensp = tensp;
        this.gia = gia;
        this.soluong = soluong;
        this.loaisp = loaisp;
        this.mancc = mancc;
    }

    public int getMancc() {
        return mancc;
    }

    public void setMancc(int mancc) {
        this.mancc = mancc;
    }

    public int getMasp() {
        return masp;
    }

    public void setMasp(int masp) {
        this.masp = masp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public String getLoaisp() {
        return loaisp;
    }

    public void setLoaisp(String loaisp) {
        this.loaisp = loaisp;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SanPham other = (SanPham) obj;
        return this.masp == other.masp;
    }

    @Override
    public String toString() {
        return "SanPham{" +
                "masp=" + masp +
                ", tensp='" + tensp + '\'' +
                ", gia=" + gia +
                ", soluong=" + soluong +
                ", loaisp='" + loaisp + '\'' +
                '}';
    }
}
