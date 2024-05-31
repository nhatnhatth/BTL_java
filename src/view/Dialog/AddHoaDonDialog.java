package view.Dialog;

import com.toedter.calendar.JDateChooser;
import controller.BUS.KhachHangBUS;
import controller.BUS.NhanVienBUS;
import controller.BUS.SanPhamBUS;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.util.ArrayList;

public class AddHoaDonDialog extends JDialog {

    private JTextField slField;
    private JDateChooser jDateChooser;
    JComboBox<String> comboBoxNv;
    JComboBox<String> comboBoxKh;
    JComboBox<String> comboBoxSp;
    private Callback callback;
    DefaultComboBoxModel<String> model;
    NhanVienBUS nhanVienBUS = new NhanVienBUS();
    ArrayList<NhanVien> nvList = nhanVienBUS.getAll();
    KhachHangBUS khachHangBUS = new KhachHangBUS();
    ArrayList<KhachHang> khList = khachHangBUS.getAll();
    SanPhamBUS spBus = new SanPhamBUS();
    ArrayList<SanPham> spList = spBus.getAll();

    public AddHoaDonDialog(JFrame parent, Callback callback) {
        super(parent, "Thêm", false);
        this.callback = callback;
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
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (SanPham item : spList) {
            model.addElement(item.getTensp());
        }
        comboBoxSp = new JComboBox<>(model);
        panel.add(comboBoxSp, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Nhan vien"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        DefaultComboBoxModel<String> model2 = new DefaultComboBoxModel<>();
        for (NhanVien item : nvList) {
            model2.addElement(item.getTenNV());
        }
        comboBoxNv = new JComboBox<>(model2);
        panel.add(comboBoxNv, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Khach hang:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        DefaultComboBoxModel<String> model3 = new DefaultComboBoxModel<>();
        for (KhachHang item : khList) {
            model3.addElement(item.getTenKH());
        }
        comboBoxKh = new JComboBox<>(model3);
        panel.add(comboBoxKh, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Ngay nhap:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        jDateChooser = new JDateChooser();
        panel.add(jDateChooser, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(new JLabel("So luong:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        slField = new JTextField(20);
        slField.setHorizontalAlignment(JTextField.LEFT);
        panel.add(slField, gbc);

        // Thêm nút "Thêm" và "Hủy"
        JButton themButton = new JButton("Thêm");
        JButton huyButton = new JButton("Hủy");

        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(themButton, gbc);

        gbc.gridx = 3;
        gbc.gridy = 4;
        panel.add(huyButton, gbc);

        // Thêm panel vào dialog
        getContentPane().add(panel);

        // Xử lý sự kiện cho nút "Thêm" và "Hủy"
        themButton.addActionListener(e -> {
            int maSP = -1;
            int maNV = -1;
            int maKH = -1;
            SanPham sp = null;
            for (SanPham item : spList) {
                if (item.getTensp().equals(comboBoxSp.getSelectedItem())) {
                    maSP = item.getMasp();
                    sp = item;
                    break;
                }
            }
            for (NhanVien item : nvList) {
                if (item.getTenNV().equals(comboBoxNv.getSelectedItem())) {
                    maNV = item.getMaNV();
                    break;
                }
            }
            for (KhachHang item : khList) {
                if (item.getTenKH().equals(comboBoxKh.getSelectedItem())) {
                    maKH = item.getMaKH();
                    break;
                }
            }
            Date ngaynhap = new Date(jDateChooser.getDate().getTime());
            int soluong = Integer.parseInt(slField.getText());
            int thanhTien = soluong*sp.getGia();
            callback.add(new HoaDon(0, maNV, maKH, maSP, ngaynhap, thanhTien, soluong));
            dispose();
        });

        huyButton.addActionListener(e -> dispose());

        // Set kích thước và vị trí cho dialog
        pack();
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    public interface Callback {
        void add(HoaDon hd);
    }
}
