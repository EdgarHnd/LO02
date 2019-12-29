package fr.utt.jestcardgame.view;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
		this.panel.setLayout(null);
		JLabel titre = new JLabel("Welcome to JEST Game\n");
		titre.setHorizontalAlignment(JLabel.CENTER);
		titre.setFont(comics40);
		titre.setBounds(350, 0, 500, 100);
		
		this.panel.add(titre);
		
		startB = new JButton("START");
		startB.setBounds(550, 250, 120, 40);
		quitB = new JButton("QUIT");
		quitB.setBounds(550, 350, 120, 40);
		rulesB = new JButton("RULES");
		rulesB.setBounds(550, 300, 120, 40);
		
		
		this.panel.add(startB);
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
		texte.setBounds(500, 100, 300, 100);
		
		this.panel.add(texte);
		
		this.card = new CardLabel("pictures/CardsPng/rulescard.png");
		//this.card.setImagePath("pictures/CardsPng/Aheart.png");
		this.card.setPreferredSize(new Dimension(this.card.getFactor() * 34,this.card.getFactor() * 48));
		this.card.setVerticalAlignment(JLabel.CENTER);
		this.card.setBounds(100, 300, this.card.getFactor() * 34, this.card.getFactor() * 48);
		this.panel.add(card);
		
	}

}