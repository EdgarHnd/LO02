package fr.utt.jestcardgame.view;

import fr.utt.jestcardgame.controler.GameViewControler;
import fr.utt.jestcardgame.model.GameBoard;
import fr.utt.jestcardgame.model.OptionsData;
import fr.utt.jestcardgame.model.RoundsManager;
import fr.utt.jestcardgame.observer.Observable;
import fr.utt.jestcardgame.observer.Observer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Board extends Panel implements Observer{
	
	private final int trophysSize = 2;
	private final int handSize = 3;
	private CardLabel trophy1;
	private CardLabel trophy2;
	private CardLabel trophy;
	private JLabel player1;
	private CardLabel hand1p1;
	private CardLabel hand2p1;
	private ArrayList<CardLabel> jestp1 = new ArrayList<CardLabel>();

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
		this.trophy1 = new CardLabel("pictures/CardsPng/back.jpg");
		this.trophy1.setFactor(trophysSize);
		this.trophy1.setPreferredSize(new Dimension(this.trophy1.getFactor() * 34,this.trophy1.getFactor() * 48));
		this.trophy1.setVerticalAlignment(JLabel.CENTER);
		this.trophy1.setBounds(500, 250, this.trophy1.getFactor() * 34, this.trophy1.getFactor() * 48);

		
		//trophy2
		this.trophy2 = new CardLabel("pictures/CardsPng/back.jpg");
		this.trophy2.setFactor(trophysSize);
		this.trophy2.setPreferredSize(new Dimension(this.trophy1.getFactor() * 34,this.trophy2.getFactor() * 48));
		this.trophy2.setVerticalAlignment(JLabel.CENTER);
		this.trophy2.setBounds(600, 250, this.trophy2.getFactor() * 34, this.trophy2.getFactor() * 48);

		
		//aloneTrophy
		this.trophy = new CardLabel("pictures/CardsPng/back.jpg");
		this.trophy.setFactor(trophysSize);
		this.trophy.setPreferredSize(new Dimension(this.trophy.getFactor() * 34,this.trophy.getFactor() * 48));
		this.trophy.setVerticalAlignment(JLabel.CENTER);
		this.trophy.setBounds(550, 250, this.trophy.getFactor() * 34, this.trophy.getFactor() * 48);
		
		//players
		//player1
		this.player1 = new JLabel("Player 1");
		this.player1.setHorizontalAlignment(JLabel.CENTER);
		this.player1.setFont(comics20);
		this.player1.setBounds(550, 630, 100, 50);
		this.panel.add(this.player1);
		
		this.hand1p1 = new CardLabel("pictures/CardsPng/back.jpg");
		this.hand1p1.setFactor(handSize);
		this.hand1p1.setPreferredSize(new Dimension(this.hand1p1.getFactor() * 34,this.hand1p1.getFactor() * 48));
		this.hand1p1.setVerticalAlignment(JLabel.CENTER);
		this.hand1p1.setBounds(500, 400, this.hand1p1.getFactor() * 34, this.hand1p1.getFactor() * 48);
		this.panel.add(this.hand1p1);
		
		this.hand2p1 = new CardLabel("pictures/CardsPng/back.jpg");
		this.hand2p1.setFactor(handSize);
		this.hand2p1.setPreferredSize(new Dimension(this.hand2p1.getFactor() * 34,this.hand2p1.getFactor() * 48));
		this.hand2p1.setVerticalAlignment(JLabel.CENTER);
		this.hand2p1.setBounds(600, 400, this.hand2p1.getFactor() * 34, this.hand2p1.getFactor() * 48);
		this.panel.add(this.hand2p1);
		
		for(int i = 1; i < 6; i++) {
			CardLabel jestCard = new CardLabel("pictures/CardsPng/back.jpg");
			jestCard.setFactor(trophysSize);
			jestCard.setPreferredSize(new Dimension(jestCard.getFactor() * 34,jestCard.getFactor() * 48));
			jestCard.setVerticalAlignment(JLabel.CENTER);
			jestCard.setBounds(300+i*100, 550, jestCard.getFactor() * 34, jestCard.getFactor() * 48);
			this.jestp1.add(jestCard);
		}
		for(CardLabel j:jestp1){
		    this.panel.add(j);
		}
		
	}

	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof GameBoard) {
			if(OptionsData.getNbPlayer() == 3) {
				this.panel.add(trophy1);
				this.trophy1.setImagePath(((GameBoard) o).getTrophys().get(0).getImagePath());
				this.panel.add(trophy2);
				this.trophy2.setImagePath(((GameBoard) o).getTrophys().get(1).getImagePath());
			}
			else if(OptionsData.getNbPlayer() == 4) {
				this.panel.add(trophy);
				this.trophy.setImagePath(((GameBoard) o).getTrophys().get(0).getImagePath());
			}
		}
		else if(o instanceof RoundsManager) {
			if(OptionsData.getNbPlayer() == 3) {
				
			}
			else if(OptionsData.getNbPlayer() == 4) {
				
			}
		}
	}

}
