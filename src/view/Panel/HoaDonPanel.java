package view.Panel;

import controller.BUS.HoaDonBUS;
import controller.BUS.KhachHangBUS;
import controller.BUS.NhanVienBUS;
import controller.BUS.SanPhamBUS;
import model.HoaDon;
import view.Component.IntegratedSearch;
import view.Component.MainFunction;
import view.Component.PanelBorderRadius;
import view.Component.TableSorter;
import view.Dialog.AddHoaDonDialog;
import view.Dialog.DetailHoaDonDialog;
import view.Dialog.UpdateHoaDonDialog;
import view.Main;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class HoaDonPanel extends JPanel implements ActionListener, ItemListener {

    PanelBorderRadius main, functionBar;
    JPanel pnlBorder1, pnlBorder2, pnlBorder3, pnlBorder4, contentCenter;
    JTable table;
    JScrollPane scrollTable;
    MainFunction mainFunction;
    JFrame owner = (JFrame) SwingUtilities.getWindowAncestor(this);
    IntegratedSearch search;
    DefaultTableModel tblModel;
    public HoaDonBUS hoaDonBUS = new HoaDonBUS();
    public NhanVienBUS nhanVienBUS = new NhanVienBUS();
    public SanPhamBUS sanPhamBUS = new SanPhamBUS();
    public KhachHangBUS khachHangBUS = new KhachHangBUS();
    public ArrayList<HoaDon> list = hoaDonBUS.getAll();
    Main m;
    Color BackgroundColor = new Color(240, 247, 250);

    private void initComponent() {
        this.setBackground(BackgroundColor);
        this.setLayout(new BorderLayout(0, 0));
        this.setOpaque(true);

        table = new JTable();
        scrollTable = new JScrollPane();
        tblModel = new DefaultTableModel();
        String[] header = new String[]{"Mã hóa đơn", "Tên Sản phẩm", "Tên Nhân viên", "Tên Khách hàng", "Số lượng", "Ngày nhập", "Thành tiền"};
        tblModel.setColumnIdentifiers(header);
        table.setModel(tblModel);
        table.setFocusable(false);
        scrollTable.setViewportView(table);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);

        table.setAutoCreateRowSorter(true);
        TableSorter.configureTableColumnSorter(table, 0, TableSorter.INTEGER_COMPARATOR);

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

        String[] action = {"create", "update", "delete", "detail"};
        mainFunction = new MainFunction(action);
        for (String ac : action) {
            mainFunction.btn.get(ac).addActionListener(this);
        }
        functionBar.add(mainFunction);

        search = new IntegratedSearch(new String[]{"Tất cả", "Mã khách hàng", "Tên khách hàng", "Địa chỉ", "Số điện thoại"});
        search.cbxChoose.addItemListener(this);
        search.txtSearchForm.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String type = (String) search.cbxChoose.getSelectedItem();
                String txt = search.txtSearchForm.getText();
                list = hoaDonBUS.search(txt, type);
                loadDataTable(list);
            }
        });

        search.btnReset.addActionListener((ActionEvent e) -> {
            search.txtSearchForm.setText("");
            list = hoaDonBUS.getAll();
            loadDataTable(list);
        });
        functionBar.add(search);

        contentCenter.add(functionBar, BorderLayout.NORTH);

        // main là phần ở dưới để thống kê bảng biểu
        main = new PanelBorderRadius();
        BoxLayout boxly = new BoxLayout(main, BoxLayout.Y_AXIS);
        main.setLayout(boxly);
//        main.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentCenter.add(main, BorderLayout.CENTER);

        main.add(scrollTable);
    }

    public HoaDonPanel(Main m) {
        this.m = m;
        initComponent();
        table.setDefaultEditor(Object.class, null);
        loadDataTable(list);
    }

    public void loadDataTable(ArrayList<HoaDon> result) {
        tblModel.setRowCount(0);
        for (HoaDon item : result) {
//            {"Mã hoa don", "Tên San Pham", "Ten Nhan vien", "Ten Khach Hang", "Ngay lap", "Thanhtien"}
            tblModel.addRow(new Object[]{
                    item.getMahd(),
                    sanPhamBUS.getSP(item.getMasp()).getTensp(),
                    nhanVienBUS.getNV(item.getManv()).getTenNV(),
                    khachHangBUS.getKH(item.getMakh()).getTenKH(),
                    item.getSoluong(),
                    item.getDate(),
                    item.getThanhtien()
            });
        }
    }

    public int getRowSelected() {
        int index = table.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn");
        }
        return index;
    }


    public static boolean isPhoneNumber(String str) {
        // Loại bỏ khoảng trắng và dấu ngoặc đơn nếu có
        str = str.replaceAll("\\s+", "").replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("\\-", "");

        // Kiểm tra xem chuỗi có phải là một số điện thoại hợp lệ hay không
        if (str.matches("\\d{10}")) { // Kiểm tra số điện thoại 10 chữ số
            return true;
        } else if (str.matches("\\d{3}-\\d{3}-\\d{4}")) { // Kiểm tra số điện thoại có dấu gạch ngang
            return true;
        } else if (str.matches("\\(\\d{3}\\)\\d{3}-\\d{4}")) { // Kiểm tra số điện thoại có dấu ngoặc đơn
            return true;
        } else {
            return false; // Trả về false nếu chuỗi không phải là số điện thoại hợp lệ
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mainFunction.btn.get("create")) {
            new AddHoaDonDialog(owner, new AddHoaDonDialog.Callback() {
                @Override
                public void add(HoaDon hd) {
                    hoaDonBUS.add(hd);
                    list = hoaDonBUS.getAll();
                    JOptionPane.showMessageDialog(owner, "Thêm hóa đơn thành công !");
                    loadDataTable(list);
                }
            });
        } else if (e.getSource() == mainFunction.btn.get("update")) {
            int index = getRowSelected();
            if (index != -1) {
                new UpdateHoaDonDialog(owner, list.get(index), new UpdateHoaDonDialog.Callback() {

                    @Override
                    public void update(HoaDon hd) {
                        hoaDonBUS.update(hd);
                        list = hoaDonBUS.getAll();
                        JOptionPane.showMessageDialog(owner, "Update hóa đơn thành công !");
                        loadDataTable(list);
                    }
                });
            }
        } else if (e.getSource() == mainFunction.btn.get("delete")) {
            int index = getRowSelected();
            if (index != -1) {
                int input = JOptionPane.showConfirmDialog(null,
                        "Bạn có chắc chắn muốn xóa hóa đơn?", "Xóa hóa đơn",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (input == 0) {
                    hoaDonBUS.delete(list.get(index));
                    list = hoaDonBUS.getAll();
                    JOptionPane.showMessageDialog(owner, "Update hóa đơn thành công !");
                    loadDataTable(list);
                }
            }
        } else if (e.getSource() == mainFunction.btn.get("detail")) {
            int index = getRowSelected();
            if (index != -1) {
                new DetailHoaDonDialog(owner, list.get(index));
            }
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        String type = (String) search.cbxChoose.getSelectedItem();
        String txt = search.txtSearchForm.getText();
        list = hoaDonBUS.search(txt, type);
        loadDataTable(list);
    }
}
