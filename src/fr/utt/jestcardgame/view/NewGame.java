package fr.utt.jestcardgame.view;

import fr.utt.jestcardgame.controler.GameViewControler;

import javax.swing.*;
import java.awt.*;

public class NewGame extends Panel{

    public NewGame(Dimension dim, GameViewControler gvc) {
        super(dim, gvc);
    }

    @Override
    protected void initPanel() {
        JPanel background = new TestImagePanel(new ImageIcon("pictures/Other/green-felt-background-6.jpg").getImage());
        this.panel = background;

        JLabel title = new JLabel("JEST Game\n");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setFont(comics40);
        title.setBounds(350, 0, 500, 100);
        this.panel.add(title);

    }
}
