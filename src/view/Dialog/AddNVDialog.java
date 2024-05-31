package view.Dialog;

import model.NhanVien;

import javax.swing.*;
import java.awt.*;

public class AddNVDialog extends JDialog {

    private JTextField tenNVField;
    private JTextField diachiField;
    private JTextField sdtField;
    private Callback callback;

    public AddNVDialog(JFrame parent, Callback callback) {
        super(parent, "Thêm khach hang", false);
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
        panel.add(new JLabel("Tên khach hang:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        tenNVField = new JTextField(20);
        tenNVField.setHorizontalAlignment(JTextField.LEFT);
        panel.add(tenNVField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Dia chi:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        diachiField = new JTextField(10);
        diachiField.setHorizontalAlignment(JTextField.LEFT);
        panel.add(diachiField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Số dien thoai:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        sdtField = new JTextField(10);
        sdtField.setHorizontalAlignment(JTextField.LEFT);
        panel.add(sdtField, gbc);

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
            String tenNV = tenNVField.getText();
            String diachi = diachiField.getText();
            String sdt = sdtField.getText();
            callback.addNV(new NhanVien(0, tenNV, diachi, sdt));
            dispose();
        });

        huyButton.addActionListener(e -> dispose());

        // Set kích thước và vị trí cho dialog
        pack();
        setLocationRelativeTo(parent);
        setVisible(true);
    }


    public interface Callback{
        void addNV(NhanVien nv);
    }
}
