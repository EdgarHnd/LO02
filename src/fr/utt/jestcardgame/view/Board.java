package fr.utt.jestcardgame.view;

import java.awt.Dimension;

import javax.swing.JLabel;

import fr.utt.jestcardgame.controler.GameViewControler;

public class Board extends Panel{

	public Board(Dimension dim, GameViewControler gvc) {
		super(dim, gvc);
		this.initPanel();
	}

	@Override
	protected void initPanel() {
		this.panel.setLayout(null);
		JLabel titre = new JLabel("GameBoard");
		titre.setHorizontalAlignment(JLabel.CENTER);
		titre.setFont(comics40);
		titre.setBounds(350, 0, 500, 100);
		
		this.panel.add(titre);
		
	}

}
