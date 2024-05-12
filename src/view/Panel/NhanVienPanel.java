package view.Panel;

import controller.BUS.NhanVienBUS;
import view.Component.IntegratedSearch;
import view.Component.MainFunction;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import view.Component.PanelBorderRadius;
import view.Dialog.*;
import view.Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public final class NhanVienPanel extends JPanel implements ActionListener {

    public JFrame owner = (JFrame) SwingUtilities.getWindowAncestor(this);
    NhanVienBUS nvBus = new NhanVienBUS();
    PanelBorderRadius main, functionBar;
    JPanel pnlBorder1, pnlBorder2, pnlBorder3, pnlBorder4, contentCenter;
    JTable tableNhanVien;
    JScrollPane scrollTableSanPham;
    MainFunction mainFunction;
    public IntegratedSearch search;
    Main m;
    ArrayList<model.NhanVien> listnv = nvBus.getAll();

    Color BackgroundColor = new Color(240, 247, 250);
    private DefaultTableModel tblModel;

    private void initComponent() {
        this.setBackground(BackgroundColor);
        this.setLayout(new BorderLayout(0, 0));
        this.setOpaque(true);

        // pnlBorder1, pnlBorder2, pnlBorder3, pnlBorder4 chỉ để thêm contentCenter ở giữa mà contentCenter không bị dính sát vào các thành phần khác
        pnlBorder1 = new JPanel();
        pnlBorder1.setPreferredSize(new Dimension(0, 10));
        pnlBorder1.setBackground(BackgroundColor);
        this.add(pnlBorder1, BorderLayout.NORTH);

        pnlBorder2 = new JPanel();
        pnlBorder2.setPreferredSize(new Dimension(0, 10));
        pnlBorder2.setBackground(BackgroundColor);
        this.add(pnlBorder2, BorderLayout.SOUTH);

        pnlBorder3 = new JPanel();
        pnlBorder3.setPreferredSize(new Dimension(10, 0));
        pnlBorder3.setBackground(BackgroundColor);
        this.add(pnlBorder3, BorderLayout.EAST);

        pnlBorder4 = new JPanel();
        pnlBorder4.setPreferredSize(new Dimension(10, 0));
        pnlBorder4.setBackground(BackgroundColor);
        this.add(pnlBorder4, BorderLayout.WEST);

        contentCenter = new JPanel();
        contentCenter.setPreferredSize(new Dimension(1100, 600));
        contentCenter.setBackground(BackgroundColor);
        contentCenter.setLayout(new BorderLayout(10, 10));
        this.add(contentCenter, BorderLayout.CENTER);

        // functionBar là thanh bên trên chứa các nút chức năng như thêm xóa sửa, và tìm kiếm
        functionBar = new PanelBorderRadius();
        functionBar.setPreferredSize(new Dimension(0, 100));
        functionBar.setLayout(new GridLayout(1, 2, 50, 0));
        functionBar.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentCenter.add(functionBar, BorderLayout.NORTH);

        String[] action = {"create", "update", "delete", "detail"};
        mainFunction = new MainFunction(action);
        for (String ac : action) {
            mainFunction.btn.get(ac).addActionListener(this);
        }
        functionBar.add(mainFunction);
        search = new IntegratedSearch(new String[]{"Tất cả", "Họ tên", "Email"});
        functionBar.add(search);
        search.btnReset.addActionListener(this);
        search.cbxChoose.addActionListener(this);

        // main là phần ở dưới để thống kê bảng biểu
        main = new PanelBorderRadius();
        BoxLayout boxly = new BoxLayout(main, BoxLayout.Y_AXIS);
        main.setLayout(boxly);
//        main.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentCenter.add(main, BorderLayout.CENTER);

        tableNhanVien = new JTable();
        scrollTableSanPham = new JScrollPane();
        tableNhanVien = new JTable();
        tblModel = new DefaultTableModel();
        String[] header = new String[]{"Ma Nhan vien", "Ten nhan vien", "Dia chi", "So dien thoai"};

        tblModel.setColumnIdentifiers(header);
        tableNhanVien.setModel(tblModel);
        tableNhanVien.setFocusable(false);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        tableNhanVien.setDefaultRenderer(Object.class, centerRenderer);
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableNhanVien.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tableNhanVien.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tableNhanVien.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tableNhanVien.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        scrollTableSanPham.setViewportView(tableNhanVien);
        main.add(scrollTableSanPham);
    }

    

    public NhanVienPanel(Main m) {
        this.m = m;
        initComponent();
        tableNhanVien.setDefaultEditor(Object.class, null);
        loadTable(listnv);
    }

    public void loadTable(ArrayList<model.NhanVien> list) {
        listnv = list;
        tblModel.setRowCount(0);
        for (model.NhanVien nhanVien : listnv) {
            tblModel.addRow(new Object[]{
                nhanVien.getMaNV(), nhanVien.getTenNV(), nhanVien.getDiachi(), nhanVien.getSdt()
            });
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mainFunction.btn.get("create")) {
            new AddNVDialog(owner, new AddNVDialog.Callback() {
                @Override
                public void addNV(model.NhanVien nv) {
                    nvBus.add(nv);
                    listnv = nvBus.getAll();
                    JOptionPane.showMessageDialog(owner, "Thêm sản phẩm thành công !");
                    loadTable(listnv);
                }
            });
        } else if (e.getSource() == mainFunction.btn.get("update")) {
            int index = getRowSelected();
            if (index != -1) {
                new UpdateNVDialog(owner, listnv.get(index), new UpdateNVDialog.Callback() {
                    @Override
                    public void update(model.NhanVien nhanVien) {
                        nvBus.update(nhanVien);
                        listnv = nvBus.getAll();
                        JOptionPane.showMessageDialog(owner, "Update sản phẩm thành công !");
                        loadTable(listnv);
                    }
                });
            }
        } else if (e.getSource() == mainFunction.btn.get("delete")) {
            int index = getRowSelected();
            if (index != -1) {
                int input = JOptionPane.showConfirmDialog(null,
                        "Bạn có chắc chắn muốn xóa khách hàng?", "Xóa khách hàng",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (input == 0) {
                    nvBus.delete(listnv.get(index));
                    listnv = nvBus.getAll();
                    JOptionPane.showMessageDialog(owner, "Update sản phẩm thành công !");
                    loadTable(listnv);
                }
            }
        } else if (e.getSource() == mainFunction.btn.get("detail")) {
            int index = getRowSelected();
            if (index != -1) {
                new DetailNVDialog(owner, listnv.get(index));            }
        }
    }

    public int getRowSelected() {
        int index = tableNhanVien.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn khách hàng");
        }
        return index;
    }
}
