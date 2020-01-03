package fr.utt.jestcardgame.view;

import fr.utt.jestcardgame.controler.GameViewControler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Options extends Panel implements ItemListener {

    private JButton back;

    public Options(Dimension dim, GameViewControler gvc) {
        super(dim, gvc);
        initPanel();
    }

    @Override
    protected void initPanel() {
        JLabel titre = new JLabel("Options Menu");
        titre.setHorizontalAlignment(JLabel.CENTER);
        titre.setFont(comics40);
        titre.setBounds(350, 0, 500, 100);
        this.panel.add(titre);

        JLabel lbl = new JLabel("Number of players");
        this.panel.add(lbl);

        JRadioButton rb1 = new JRadioButton("3", true);
        JRadioButton rb2 = new JRadioButton("4");
        ButtonGroup group = new ButtonGroup();
        rb1.setBounds(90, 50, 70, 30 );
        rb2.setBounds(90, 30, 70, 30 );


        group.add(rb1);
        group.add(rb2);
        this.panel.add(rb1, BorderLayout.SOUTH);
        this.panel.add(rb2, BorderLayout.SOUTH);

        this.back = new JButton("BACK");
        this.back.setBounds(10, 0, 50, 50);
        this.back.addActionListener(this.gvc.getBack());
        this.panel.add(back);


    }

    @Override
    public void itemStateChanged(ItemEvent e) {

    }
}
