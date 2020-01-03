package fr.utt.jestcardgame.view;

import javax.swing.*;
import java.awt.*;

class TestImagePanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private Image img;

    public TestImagePanel(String img) {
        this(new ImageIcon(img).getImage());
    }

    public TestImagePanel(Image img) {
        this.img = img;
    }

    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, this);
    }
}
