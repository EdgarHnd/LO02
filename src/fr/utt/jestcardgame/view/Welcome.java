package fr.utt.jestcardgame.view;


import fr.utt.jestcardgame.controler.GameViewControler;
import fr.utt.jestcardgame.observer.Observable;
import fr.utt.jestcardgame.observer.Observer;

import javax.swing.*;
import java.awt.*;

/**
 * Class representing the Welcome panel where the player can navigate throught the main menu
 * This class extends from the class <code>Panel</code> in order to set basic attributes
 * @author Edgar
 */
public class Welcome extends Panel {

	private JButton optionsB;
	private JButton quitB;
	private JButton rulesB;
	private CardLabel card;
	private CardLabel cardback;
	/**
     * Constructor for this class
     * @param dim Dimension for the panel
     * @param gvc GameViewController to control the players input
     */
	public Welcome(Dimension dim, GameViewControler gvc) {
		super(dim,gvc);
		this.initPanel();
	}
	/**
     * Method to create and place all the graphic elements needed in our panel
     * The elements consist of the buttons to interacte with the menu,
     * JLabel, JTextArea and CardLabel in order to make everything look nicer
     */
	@Override
	protected void initPanel() {
		this.panel.setLayout(null);
		JLabel titre = new JLabel("Welcome to JEST Game\n");
		titre.setHorizontalAlignment(JLabel.CENTER);
		titre.setFont(comics40);
		titre.setBounds(400, 0, 500, 100);
		
		this.panel.add(titre);

		optionsB = new JButton("START");
		optionsB.setBounds(600, 350, 120, 40);
		quitB = new JButton("QUIT");
		quitB.setBounds(600, 450, 120, 40);
		rulesB = new JButton("RULES");
		rulesB.setBounds(600, 400, 120, 40);

		this.panel.add(rulesB);
		this.panel.add(quitB);
		this.panel.add(optionsB);

		this.rulesB.addActionListener(this.gvc.getRules());
		this.quitB.addActionListener(this.gvc.getQuit());
		this.optionsB.addActionListener(this.gvc.getOptions());
		

		JTextArea texte = new JTextArea(	"Welcome to Jest Game\n" +
											"To start a new game press START\n" +
											"To check the game rules press RULES\n" +
                      						"Enjoy !");
		texte.setFont(arial);
		texte.setEditable(false);
		texte.setBackground(Color.white);
		texte.setBounds(550, 150, 300, 100);
		
		this.panel.add(texte);
		
		this.card = new CardLabel("pictures/CardsPng/rulescard.png");
		this.card.setPreferredSize(new Dimension(this.card.getFactor() * 34,this.card.getFactor() * 48));
		this.card.setVerticalAlignment(JLabel.CENTER);
		this.card.setBounds(50, 300, this.card.getFactor() * 34, this.card.getFactor() * 48);
		this.panel.add(card);
		
		this.cardback = new CardLabel("pictures/CardsPng/back.jpg");
		this.cardback.setPreferredSize(new Dimension(this.cardback.getFactor() * 34,this.cardback.getFactor() * 48));
		this.cardback.setVerticalAlignment(JLabel.CENTER);
		this.cardback.setBounds(200, 300, this.cardback.getFactor() * 34, this.cardback.getFactor() * 48);
		this.panel.add(cardback);
	}
}
