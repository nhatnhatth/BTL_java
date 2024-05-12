package view.Dialog;

import model.SanPham;

import javax.swing.*;
import java.awt.*;

public class AddSPDialog extends JDialog {

    private JTextField tenSPField;
    private JTextField giaField;
    private JTextField soLuongField;
    private JTextField loaiSPField;
    private Callback callback;

    public AddSPDialog(JFrame parent, Callback callback) {
        super(parent, "Thêm sản phẩm", false);
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
        panel.add(new JLabel("Tên sản phẩm:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        tenSPField = new JTextField(20);
        tenSPField.setHorizontalAlignment(JTextField.LEFT);
        panel.add(tenSPField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Giá:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        giaField = new JTextField(10);
        giaField.setHorizontalAlignment(JTextField.LEFT);
        giaField.setHorizontalAlignment(JTextField.RIGHT);
        panel.add(giaField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Số lượng:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        soLuongField = new JTextField(10);
        soLuongField.setHorizontalAlignment(JTextField.LEFT);
        soLuongField.setHorizontalAlignment(JTextField.RIGHT);
        panel.add(soLuongField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Loại sản phẩm:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        loaiSPField = new JTextField(20);
        loaiSPField.setHorizontalAlignment(JTextField.LEFT);
        panel.add(loaiSPField, gbc);

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
            String tenSP = tenSPField.getText();
            int gia = Integer.parseInt(giaField.getText());
            int soLuong = Integer.parseInt(soLuongField.getText());
            String loaiSP = loaiSPField.getText();
            callback.addSP(new SanPham(0, tenSP, gia, soLuong, loaiSP, 1));
            dispose();
        });

        huyButton.addActionListener(e -> dispose());

        // Set kích thước và vị trí cho dialog
        pack();
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    public String getTenSP() {
        return tenSPField.getText();
    }

    public int getGia() {
        return Integer.parseInt(giaField.getText());
    }

    public int getSoLuong() {
        return Integer.parseInt(soLuongField.getText());
    }

    public String getLoaiSP() {
        return loaiSPField.getText();
    }

    public interface Callback{
        void addSP(SanPham sp);
    }
}
