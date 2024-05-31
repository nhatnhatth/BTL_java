package view.Panel;

import javax.swing.*;

import com.formdev.flatlaf.FlatIntelliJLaf;
import controller.BUS.*;
import view.Component.TitledContentPanel;
import view.Main;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class HomePanel extends JPanel {

    String titleArr[] = {"San Pham", "Khach hang", "Nhan vien", "Nha cung cap", "Hoa don", "Doanh thu"};
    SanPhamBUS sanPhamBUS = new SanPhamBUS();
    KhachHangBUS khachHangBUS = new KhachHangBUS();
    NhanVienBUS nhanVienBUS = new NhanVienBUS();
    NhaCungCapBUS nhaCungCapBUS = new NhaCungCapBUS();
    HoaDonBUS hoaDonBUS = new HoaDonBUS();
    ArrayList<String> details = new ArrayList<String>();
    private Callback callback = null;
    private void initComponent() {
        setLayout(new GridLayout(2, 4, 30, 80));
        setBorder(BorderFactory.createEmptyBorder(100, 32, 32, 32)); // Set padding values
        intData();
        for (int i = 0; i < 6; i++) {
            JPanel subPanel = new TitledContentPanel(titleArr[i], details.get(i));
            subPanel.setBackground(getColor(i)); // Đặt màu cho panel con
            add(subPanel); // Thêm panel con vào panel gốc
            int finalI = i;
            subPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if(finalI!=5){
                        callback.onSelect(finalI);
                    }
                }
            });
        }
    }

    private void intData() {
        details.add(String.valueOf(sanPhamBUS.getAll().size()));
        details.add(String.valueOf(khachHangBUS.getAll().size()));
        details.add(String.valueOf(nhanVienBUS.getAll().size()));
        details.add(String.valueOf(nhaCungCapBUS.getAll().size()));
        details.add(String.valueOf(hoaDonBUS.getAll().size()));
        details.add(String.valueOf(hoaDonBUS.getDT()));
    }

    public HomePanel(Callback callback) {
        this.callback = callback;
        initComponent();
        FlatIntelliJLaf.registerCustomDefaultsSource("style");
        FlatIntelliJLaf.setup();
    }

    private static Color getColor(int index) {
        switch (index % 8) {
            case 0:
                return Color.RED;
            case 1:
                return Color.GREEN;
            case 2:
                return Color.MAGENTA;
            case 3:
                return Color.CYAN;
            case 4:
                return Color.YELLOW;
            case 5:
                return Color.PINK;
            case 7:
                return Color.ORANGE;
            default:
                return Color.WHITE;
        }
    }
    public interface Callback{
        void onSelect(int pos);
    }
}


