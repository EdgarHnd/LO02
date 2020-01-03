package fr.utt.jestcardgame.view;

import java.awt.Dimension;

import javax.swing.JLabel;

import fr.utt.jestcardgame.controler.GameViewControler;
import fr.utt.jestcardgame.model.GameBoard;
import fr.utt.jestcardgame.model.GameManager;
import fr.utt.jestcardgame.model.GameOptions;
import fr.utt.jestcardgame.observer.Observable;
import fr.utt.jestcardgame.observer.Observer;

public class Board extends Panel implements Observer{
	
	private CardLabel trophy1;
	private CardLabel trophy2;
	private CardLabel trophy;
	private final int trophysSize = 2;

	public Board(Dimension dim, GameViewControler gvc) {
		super(dim, gvc);
		this.initPanel();
	}

	@Override
	protected void initPanel() {
		this.panel.setLayout(null);
		
		//titre
		JLabel titre = new JLabel("GameBoard");
		titre.setHorizontalAlignment(JLabel.CENTER);
		titre.setFont(comics40);
		titre.setBounds(0, 0, 250, 50);
		this.panel.add(titre);
		
		//trophys
		//trophy1
		this.trophy1 = new CardLabel("pictures/CardsPng/rulescard.png");
		this.trophy1.setFactor(trophysSize);
		this.trophy1.setPreferredSize(new Dimension(this.trophy1.getFactor() * 34,this.trophy1.getFactor() * 48));
		this.trophy1.setVerticalAlignment(JLabel.CENTER);
		this.trophy1.setBounds(500, 250, this.trophy1.getFactor() * 34, this.trophy1.getFactor() * 48);
		//this.panel.add(trophy1);
		
		//trophy2
		this.trophy2 = new CardLabel("pictures/CardsPng/rulescard.png");
		this.trophy2.setFactor(trophysSize);
		this.trophy2.setPreferredSize(new Dimension(this.trophy1.getFactor() * 34,this.trophy2.getFactor() * 48));
		this.trophy2.setVerticalAlignment(JLabel.CENTER);
		this.trophy2.setBounds(600, 250, this.trophy2.getFactor() * 34, this.trophy2.getFactor() * 48);
		//this.panel.add(trophy2);
		
		//aloneTrophy
		this.trophy = new CardLabel("pictures/CardsPng/rulescard.png");
		this.trophy.setFactor(trophysSize);
		this.trophy.setPreferredSize(new Dimension(this.trophy.getFactor() * 34,this.trophy.getFactor() * 48));
		this.trophy.setVerticalAlignment(JLabel.CENTER);
		this.trophy.setBounds(550, 250, this.trophy.getFactor() * 34, this.trophy.getFactor() * 48);
		//this.panel.add(trophy);
	}

	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof GameBoard) {
			if(GameOptions.getNbPlayer() == 3) {
				this.panel.add(trophy1);
				this.trophy1.setImagePath(((GameBoard) o).getTrophys().get(0).getImagePath());
				this.panel.add(trophy2);
				this.trophy2.setImagePath(((GameBoard) o).getTrophys().get(1).getImagePath());
			}
			else if(GameOptions.getNbPlayer() == 4) {
				this.panel.add(trophy);
				this.trophy.setImagePath(((GameBoard) o).getTrophys().get(0).getImagePath());
			}
		}
		
	}

}
