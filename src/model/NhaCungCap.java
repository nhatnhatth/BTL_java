package model;

public class NhaCungCap {
    private int mancc;
    private String tenncc;
    private String diachi;
    private String sdt;

    public NhaCungCap(int mancc, String tenncc, String diachi, String sdt) {
        this.mancc = mancc;
        this.tenncc = tenncc;
        this.diachi = diachi;
        this.sdt = sdt;
    }

    public int getMancc() {
        return mancc;
    }

    public void setMancc(int mancc) {
        this.mancc = mancc;
    }

    public String getTenncc() {
        return tenncc;
    }

    public void setTenncc(String tenncc) {
        this.tenncc = tenncc;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
}
