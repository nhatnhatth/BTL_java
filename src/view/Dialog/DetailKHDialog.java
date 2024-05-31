package view.Dialog;

import model.KhachHang;

import javax.swing.*;
import java.awt.*;

public class DetailKHDialog extends JDialog {

    private JLabel tenKHField;
    private JLabel diachiField;
    private JLabel sdtField;

    public DetailKHDialog(JFrame parent, KhachHang kh) {
        super(parent, "Detail khach hang", false);
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
        tenKHField = new JLabel(kh.getTenKH());
        tenKHField.setHorizontalAlignment(JTextField.LEFT);
        panel.add(tenKHField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Dia chi:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        diachiField = new JLabel(kh.getDiachi());
        diachiField.setHorizontalAlignment(JTextField.LEFT);
        panel.add(diachiField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Số dien thoai:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        sdtField = new JLabel(kh.getSdt());
        sdtField.setHorizontalAlignment(JTextField.LEFT);
        panel.add(sdtField, gbc);

        // Thêm nút "Thêm" và "Hủy"
        JButton themButton = new JButton("Ok");
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
            dispose();
        });

        huyButton.addActionListener(e -> dispose());

        // Set kích thước và vị trí cho dialog
        pack();
        setLocationRelativeTo(parent);
        setVisible(true);
    }
}
