package view.Panel;

import java.awt.*;
import javax.swing.*;
import view.Component.PanelShadow;
import com.formdev.flatlaf.FlatIntelliJLaf;

public class HomePanel extends JPanel {


    private void initComponent() {

    }

    public HomePanel() {
        initComponent();
        FlatIntelliJLaf.registerCustomDefaultsSource("style");
        FlatIntelliJLaf.setup();
    }


}
