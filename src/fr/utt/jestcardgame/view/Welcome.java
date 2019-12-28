package fr.utt.jestcardgame.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;

//Class Accueil héritant de panneau qui correspond à l'écran principale du jeu

public class Welcome extends Panel{
	
	private JButton startB;
	private JButton quitB;
	private JButton rulesB;
	private CardLabel card;
	
	public Welcome(Dimension dim) {
		super(dim);
		this.initPanel();
	}

	@Override
	protected void initPanel() {
		JLabel titre = new JLabel("Welcome to JEST Game\n");
		titre.setHorizontalAlignment(JLabel.CENTER);
		titre.setFont(comics40);
		
		this.panel.add(titre);
		
		startB = new JButton("START");
		//startB.setBounds(300, 100, 120, 40);
		quitB = new JButton("QUIT");
		rulesB = new JButton("RULES");
		
		
		this.panel.add(startB, BorderLayout.SOUTH);
		this.panel.add(rulesB);
		this.panel.add(quitB);
		
		quitB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				System.exit(0);
			}
		});
		
		//this.panel.add(new JLabel(new ImageIcon("images/accueil.jpg")), BorderLayout.CENTER);
		
		JTextArea texte = new JTextArea(	"Welcome to Jest Game\n" +
											"To start a new game press START\n" +
											"To check the game rules press RULES\n" +
                      						"Enjoy !");
		texte.setFont(arial);
		texte.setEditable(false);
		texte.setBackground(Color.white);
		
		this.panel.add(texte, BorderLayout.SOUTH);
		
		this.card = new CardLabel();
		this.card.setImagePath("pictures/CardsPng/rulescard.png");
		this.panel.add(card);
		
	}

}
