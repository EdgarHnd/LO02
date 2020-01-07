package fr.utt.jestcardgame.view;


import fr.utt.jestcardgame.controler.GameViewControler;
import fr.utt.jestcardgame.observer.Observable;
import fr.utt.jestcardgame.observer.Observer;

import javax.swing.*;
import java.awt.*;

//Classe Accueil héritant de panneau qui correspond à l'écran principal du jeu

public class Welcome extends Panel implements Observer {

	private JButton optionsB;
	private JButton quitB;
	private JButton rulesB;
	private CardLabel card;
	private CardLabel cardback;
	
	
	public Welcome(Dimension dim, GameViewControler gvc) {
		super(dim,gvc);
		this.initPanel();
	}

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
		
		//this.panel.add(new JLabel(new ImageIcon("images/accueil.jpg")), BorderLayout.CENTER);
		

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

	@Override
	public void update(Observable o, Object arg) {

	}
}
