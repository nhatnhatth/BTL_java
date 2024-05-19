package view.Component;

import javax.swing.*;
import java.awt.*;

public class TitledContentPanel extends JPanel {

    private JLabel titleLabel;
    private JLabel contentLable;
    Color tColor = new Color(0x3c3c3c);


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(getBackground());
        g2d.fillRoundRect(0, 0, width - 1, height - 1, 12, 12);
    }

    public TitledContentPanel(String title, String content) {
        super(new BorderLayout());
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(32, 32, 32, 32)); // Set padding values
        Font myFont1 = new Font("Arial", Font.BOLD, 24);
        Font myFont2 = new Font("Arial", Font.PLAIN, 30);
        titleLabel = new JLabel(title);
        contentLable = new JLabel(content);
        titleLabel.setFont(myFont1);
        titleLabel.setForeground(tColor);
        contentLable.setFont(myFont2);
        contentLable.setForeground(tColor);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        contentLable.setHorizontalAlignment(JLabel.CENTER);
        add(titleLabel, BorderLayout.NORTH);
        add(contentLable, BorderLayout.CENTER);
    }
}