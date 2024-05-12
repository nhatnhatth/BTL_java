package view.Component;

import java.awt.*;
import java.util.HashMap;
import javax.swing.*;

public final class MainFunction extends JToolBar {

    public HashMap<String, ButtonToolBar> btn = new HashMap<>();

    public MainFunction(String[] listBtn) {
        initData();
        initComponent(listBtn);
    }

    public void initData() {
        btn.put("create", new ButtonToolBar("THÊM", "add.svg", "create"));
        btn.put("delete", new ButtonToolBar("XÓA", "delete.svg", "delete"));
        btn.put("update", new ButtonToolBar("SỬA", "edit.svg", "update"));
        btn.put("detail", new ButtonToolBar("CHI TIẾT", "detail.svg", "view"));
    }

    private void initComponent(String[] listBtn) {
        this.setBackground(Color.WHITE);
        this.setRollover(true);
        initData();
        for (String item : listBtn) {
            this.add(btn.get(item));
            btn.get(item).setEnabled(true);
        }
    }
}
