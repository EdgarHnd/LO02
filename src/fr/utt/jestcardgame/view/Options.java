package fr.utt.jestcardgame.view;

import fr.utt.jestcardgame.controler.GameViewControler;
import fr.utt.jestcardgame.model.GameOptions;
import fr.utt.jestcardgame.model.OptionsData;
import fr.utt.jestcardgame.observer.Observable;
import fr.utt.jestcardgame.observer.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Options extends Panel implements ItemListener, Observer {

    private GameOptions gameOp;
    private JButton back;
    private JRadioButton rb1;
    private JRadioButton rb2;
    private JButton validate;
    private JLabel showNbPlayers;

    private int nbPlayer;

    public Options(Dimension dim, GameViewControler gvc) {
        super(dim, gvc);
        initPanel();
        //this.gameOp = gameOp;
        //gameOp.addObserver(this);
    }

    @Override
    protected void initPanel() {
        this.panel.setLayout(null);
        JLabel titre = new JLabel("Options Menu");
        titre.setHorizontalAlignment(JLabel.CENTER);
        titre.setFont(comics40);
        titre.setBounds(350, 0, 500, 100);
        panel.add(titre);

        JLabel lbl = new JLabel("Number of players");
        lbl.setFont(comics20);
        lbl.setHorizontalAlignment(JLabel.CENTER);
        lbl.setBounds(350, 100, 500, 100);
        panel.add(lbl);

        validate = new JButton("OK");
        validate.setBounds(550, 300, 70, 50);
        validate.setEnabled(false);
        validate.addActionListener(new ActionValidateNumberOfPlayer());

        rb1 = new JRadioButton("3");
        rb1.addActionListener(new ActionChooseNumberOfPlayer(3, this, validate));
        rb2 = new JRadioButton("4");
        rb2.addActionListener(new ActionChooseNumberOfPlayer(4, this, validate));
        ButtonGroup group = new ButtonGroup();
        rb1.setBounds(550, 200, 100, 30);
        rb2.setBounds(550, 250, 100, 30);

        showNbPlayers = new JLabel(" ");
        showNbPlayers.setBounds(120, 200, 3000, 30);
        showNbPlayers.setFont(comics20);
        showNbPlayers.setVisible(true);
        panel.add(showNbPlayers);

        group.add(rb1);
        group.add(rb2);
        group.add(validate);

        panel.add(rb1);
        panel.add(rb2);
        panel.add(validate);

        back = new JButton("BACK");
        this.back.setBounds(10, 0, 50, 50);
        back.addActionListener(this.gvc.getBack());
        panel.add(back);

    }

    @Override
    public void itemStateChanged(ItemEvent e) {

    }

    public void setNbPlayer(int nbPlayer) {
        this.nbPlayer = nbPlayer;
    }

    @Override
    public void update(Observable o, Object arg) {

    }

    private class ActionValidateNumberOfPlayer implements ActionListener {

        public ActionValidateNumberOfPlayer() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (rb1.isSelected()) {
                OptionsData.setNbPlayer(3);
                showNbPlayers.setText("3 players will play the next game.");
            } else if (rb2.isSelected()) {
                OptionsData.setNbPlayer(4);
                showNbPlayers.setText("4 players will play the next game.");
            }
        }
    }

    private class ActionChooseNumberOfPlayer implements ActionListener {
        private Options optionView;
        private int nbPlayer;
        private JButton validate;

        public ActionChooseNumberOfPlayer(int number, Options optionView, JButton validate) {
            nbPlayer = number;
            this.optionView = optionView;
            this.validate = validate;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            optionView.setNbPlayer(nbPlayer);
            validate.setEnabled(true);
        }
    }
}
