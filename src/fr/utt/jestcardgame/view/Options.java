package fr.utt.jestcardgame.view;

import fr.utt.jestcardgame.controler.GameViewControler;
import fr.utt.jestcardgame.model.OptionsData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class define the Options panel where the main player can select the different settings that he wants for the game
 * This class extends the class Panel in order to get the basic settings for our panels
 *
 * @author Edgar
 */
public class Options extends Panel {

    private JButton back;
    private JRadioButton rb1;
    private JRadioButton rb2;
    private JButton validate;
    private JLabel showNbPlayers;
    private JComboBox comboBoxRealPlayer;
    private JComboBox comboBoxVariant;
    private JButton startB;

    private int nbPlayer;

    /**
     * Constructor for the class
     *
     * @param dim Dimension the size we want
     * @param gvc GameViewController to control the players input (not yet implemented)
     */
    public Options(Dimension dim, GameViewControler gvc) {
        super(dim, gvc);
        initPanel();
    }

    /**
     * Method where all the graphical components needed for the game will be created and placed on the panel.
     * This is where all the parameters are set for our JLabels, Buttons and CheckBoxs.
     */
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

        String[] listNbRealPlayers3 = {"0", "1", "2", "3"};
        comboBoxRealPlayer = new JComboBox(listNbRealPlayers3);
        comboBoxRealPlayer.setSelectedIndex(0);
        comboBoxRealPlayer.setBounds(550, 400, 100, 30);
        comboBoxRealPlayer.setVisible(true);
        comboBoxRealPlayer.setEnabled(false);
        comboBoxRealPlayer.addActionListener(new ActionChooseNumberRealPLayer());
        panel.add(comboBoxRealPlayer);

        String[] listVariant = {"Choose a variant...", "No variant", "Variant 1 : Super Joker", "Variant 2 : Power of Heart", "Variant 3 : Bad Clubs"};
        comboBoxVariant = new JComboBox(listVariant);
        comboBoxVariant.setSelectedIndex(0);
        comboBoxVariant.setBounds(500, 500, 200, 30);
        comboBoxVariant.setVisible(true);
        comboBoxVariant.addActionListener(new ActionChooseVariant());
        panel.add(comboBoxVariant);

        startB = new JButton("START A NEW GAME");
        startB.setBounds(530, 600, 150, 40);
        startB.addActionListener(this.gvc.getStart());
        panel.add(startB);

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

    /**
     * Setter for the number of player
     *
     * @param nbPlayer int representing the number of player we want for the game
     */
    public void setNbPlayer(int nbPlayer) {
        this.nbPlayer = nbPlayer;
    }

    /**
     * Inner class implementing ActionListener to send the settings for the variant to the model
     */
    private class ActionChooseVariant implements ActionListener {

        public ActionChooseVariant() {

        }

        /**
         * method to link the player input to the model
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            OptionsData.setVariant(comboBoxVariant.getSelectedIndex() - 1);
            System.out.println("Variant for this game : " + OptionsData.getVariant());
        }
    }

    /**
     * Inner class implementing ActionListener to send the settings for the number of real players to the model
     */
    private class ActionChooseNumberRealPLayer implements ActionListener {

        public ActionChooseNumberRealPLayer() {

        }

        /**
         * method to link the player input to the model
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            OptionsData.setNbRealPlayer(comboBoxRealPlayer.getSelectedIndex());
            System.out.println(OptionsData.getNbRealPlayer() + " REAL PLAYER(S)");
            System.out.println(OptionsData.getNbVirtualPlayer() + " VIRTUAL PLAYER(S)");
        }
    }

    /**
     * Inner class implementing ActionListener to validate the settings for the number of players and inform the model
     */
    private class ActionValidateNumberOfPlayer implements ActionListener {

        public ActionValidateNumberOfPlayer() {
        }

        public void UInterfacePrintNbPlayers(int nbPlayer) {
            OptionsData.setNbPlayer(nbPlayer);
            System.out.println(OptionsData.getNbPlayer() + " players will play the next game. \n");
            showNbPlayers.setText(OptionsData.getNbPlayer() + " players will play the next game.");
            comboBoxRealPlayer.setEnabled(true);
        }

        /**
         * method to link the player input to the model
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            String four = "4";
            if (rb1.isSelected()) {
                UInterfacePrintNbPlayers(3);
                comboBoxRealPlayer.removeItem(four);
            } else if (rb2.isSelected()) {
                UInterfacePrintNbPlayers(4);
                comboBoxRealPlayer.addItem(four);
            }
        }
    }

    /**
     * Inner class implementing ActionListener to send the settings for the number of players to the model
     */
    private class ActionChooseNumberOfPlayer implements ActionListener {
        private Options optionView;
        private int nbPlayer;
        private JButton validate;

        public ActionChooseNumberOfPlayer(int number, Options optionView, JButton validate) {
            nbPlayer = number;
            this.optionView = optionView;
            this.validate = validate;
        }

        /**
         * method to link the player input to the model
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            optionView.setNbPlayer(nbPlayer);
            validate.setEnabled(true);
        }
    }
}
