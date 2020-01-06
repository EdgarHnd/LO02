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

public class Options extends Panel implements Observer {

    private GameOptions gameOp;
    private JButton back;
    private JRadioButton rb1;
    private JRadioButton rb2;
    private JButton validate;
    private JLabel showNbPlayers;
    private JComboBox comboBoxRealPlayer;

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

        JLabel labelNbPlayer = new JLabel("Number of players");
        labelNbPlayer.setFont(comics20);
        labelNbPlayer.setHorizontalAlignment(JLabel.CENTER);
        labelNbPlayer.setBounds(350, 80, 500, 100);
        panel.add(labelNbPlayer);

        validate = new JButton("OK");
        validate.setBounds(560, 280, 70, 40);
        validate.setEnabled(false);
        validate.addActionListener(new ActionValidateNumberOfPlayer());

        rb1 = new JRadioButton("3");
        rb1.addActionListener(new ActionChooseNumberOfPlayer(3, this, validate));
        rb2 = new JRadioButton("4");
        rb2.addActionListener(new ActionChooseNumberOfPlayer(4, this, validate));
        ButtonGroup group = new ButtonGroup();
        rb1.setBounds(550, 180, 100, 30);
        rb2.setBounds(550, 230, 100, 30);

        showNbPlayers = new JLabel(" ");
        showNbPlayers.setBounds(120, 190, 3000, 30);
        showNbPlayers.setFont(comics20);
        showNbPlayers.setVisible(true);
        panel.add(showNbPlayers);

        JLabel labelNbRealPlayers = new JLabel("How many players are real for this game ?");
        labelNbRealPlayers.setBounds(400, 350, 3000, 30);
        labelNbRealPlayers.setFont(comics20);
        labelNbRealPlayers.setVisible(true);
        panel.add(labelNbRealPlayers);

        String[] listNbRealPlayers3 = {"1", "2", "3"};
        comboBoxRealPlayer = new JComboBox(listNbRealPlayers3);
        comboBoxRealPlayer.setBounds(550, 400, 100, 30);
        comboBoxRealPlayer.setVisible(true);
        comboBoxRealPlayer.setEnabled(false);
        comboBoxRealPlayer.addActionListener(new ActionChooseNumberRealPLayer());
        panel.add(comboBoxRealPlayer);

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

    public void setNbPlayer(int nbPlayer) {
        this.nbPlayer = nbPlayer;
    }

    @Override
    public void update(Observable o, Object arg) {

    }

    private class ActionChooseNumberRealPLayer implements ActionListener {

        public ActionChooseNumberRealPLayer(){

        }

        @Override
        public void actionPerformed(ActionEvent e) {
            OptionsData.setNbRealPlayer(comboBoxRealPlayer.getSelectedIndex() + 1);
            System.out.println(OptionsData.getNbRealPlayer());
        }
    }

    private class ActionValidateNumberOfPlayer implements ActionListener {

        public ActionValidateNumberOfPlayer() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String four = "4";
            if (rb1.isSelected()) {
                OptionsData.setNbPlayer(3);
                System.out.println(OptionsData.getNbPlayer());
                showNbPlayers.setText("3 players will play the next game.");
                comboBoxRealPlayer.removeItem(four);
                comboBoxRealPlayer.setEnabled(true);
                //update(new Observable(), 0);
            } else if (rb2.isSelected()) {
                OptionsData.setNbPlayer(4);
                System.out.println(OptionsData.getNbPlayer());
                showNbPlayers.setText("4 players will play the next game.");
                comboBoxRealPlayer.addItem(four);
                comboBoxRealPlayer.setEnabled(true);
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
