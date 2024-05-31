package view.Dialog;

import com.toedter.calendar.JDateChooser;
import controller.BUS.KhachHangBUS;
import controller.BUS.NhanVienBUS;
import controller.BUS.SanPhamBUS;
import model.HoaDon;
import model.KhachHang;
import model.NhanVien;
import model.SanPham;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.util.ArrayList;

public class DetailHoaDonDialog extends JDialog {

    NhanVienBUS nhanVienBUS = new NhanVienBUS();
    ArrayList<NhanVien> nvList = nhanVienBUS.getAll();
    KhachHangBUS khachHangBUS = new KhachHangBUS();
    ArrayList<KhachHang> khList = khachHangBUS.getAll();
    SanPhamBUS spBus = new SanPhamBUS();
    ArrayList<SanPham> spList = spBus.getAll();

    public DetailHoaDonDialog(JFrame parent, HoaDon hd) {
        super(parent, "Detail", false);
        // Tạo panel cho dialog
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(0xFFFFFF)); // Màu nền #4C83F0
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding

        // Thêm các nhãn và ô nhập
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1;
        gbc.weighty = 0.5;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Sản phẩm:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        JLabel spLabel = new JLabel(spBus.getSP(hd.getMasp()).getTensp());
        spLabel.setHorizontalAlignment(JTextField.LEFT);
        panel.add(spLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Nhan vien"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        JLabel nvLabel = new JLabel(nhanVienBUS.getNV(hd.getManv()).getTenNV());
        nvLabel.setHorizontalAlignment(JTextField.LEFT);
        panel.add(nvLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Khach hang:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        JLabel khachHangLabel = new JLabel(khachHangBUS.getKH(hd.getMakh()).getTenKH());
        khachHangLabel.setHorizontalAlignment(JTextField.LEFT);
        panel.add(khachHangLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Ngay nhap:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        JLabel dateLabel = new JLabel(hd.getDate().toString());
        dateLabel.setHorizontalAlignment(JTextField.LEFT);
        panel.add(dateLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(new JLabel("So luong:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        JLabel slLabel = new JLabel(String.valueOf(hd.getSoluong()));
        slLabel.setHorizontalAlignment(JTextField.LEFT);
        panel.add(slLabel, gbc);


        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(new JLabel("Thanh tien:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        JLabel ttLabel = new JLabel(String.valueOf(hd.getThanhtien()));
        ttLabel.setHorizontalAlignment(JTextField.LEFT);
        panel.add(ttLabel, gbc);

        // Thêm nút "Thêm" và "Hủy"
        JButton themButton = new JButton("Update");
        JButton huyButton = new JButton("Hủy");

        gbc.gridx = 2;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(themButton, gbc);

        gbc.gridx = 3;
        gbc.gridy = 5;
        panel.add(huyButton, gbc);

        // Thêm panel vào dialog
        getContentPane().add(panel);

        // Xử lý sự kiện cho nút "Thêm" và "Hủy"
        themButton.addActionListener(e -> {
            dispose();
        });

        huyButton.addActionListener(e -> dispose());

        // Set kích thước và vị trí cho dialog
        pack();
        setLocationRelativeTo(parent);
        setVisible(true);
    }
}
