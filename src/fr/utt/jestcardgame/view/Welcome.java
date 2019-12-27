package fr.utt.jestcardgame.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;

//Class Accueil héritant de panneau qui correspond à l'écran principale du jeu

public class Welcome extends Panel{
	
	private JButton startB;
	private JButton quitB;
	
	public Welcome(Dimension dim) {
		super(dim);
		this.initPanel();
	}

	@Override
	protected void initPanel() {
		JLabel titre = new JLabel("Welcome to JEST Game\n");
		titre.setHorizontalAlignment(JLabel.CENTER);
		titre.setFont(comics40);
		this.panel.add(titre, BorderLayout.NORTH);
		
		startB = new JButton("START");
		
		//startB.setBounds(300, 100, 120, 40);
		this.panel.add(startB, BorderLayout.SOUTH);
		//this.panel.add(new JLabel(new ImageIcon("images/accueil.jpg")), BorderLayout.CENTER);
		
		/*JTextArea texte = new JTextArea(	"Vous avez sept coups pour trouver le mot cach�. Si vous r�ussissez, on recommence !\n" +
											"Plus vous trouvez de mots, plus votre score augmente. Alors, � vous de jouer !\n" +
											"Proverbe :\t� Pas vu, pas pris !\n" +
                      						"\tPris ! PENDU ! �");
		texte.setFont(arial);
		texte.setEditable(false);
		texte.setBackground(Color.white);
		
		this.panel.add(texte, BorderLayout.SOUTH);*/
		
	}

}
