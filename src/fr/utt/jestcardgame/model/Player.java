package fr.utt.jestcardgame.model;

import java.util.LinkedList;

public abstract class Player {
	
	protected int nb;
	protected String name;
	protected LinkedList<Card> hand;
	protected LinkedList<Card> jest;
	protected int finalScore;
	protected int finalBoolean;
	protected boolean hasPlayed;
	
	//Getters
	public String getName() {
		return this.name;
	}
	public int getNb() {
		return nb;
	}
	public LinkedList<Card> getHand() {
		return hand;
	}
	
	//Constructor
	public Player (String name, int nb) {
		this.name = name;
		this.nb = nb;
		
		this.hand = new LinkedList<Card>();
		this.jest = new LinkedList<Card>();
		this.finalScore = 0;
		
	}
	
	//Players methods
	
	public void receiveCard(Card c) {
		this.hand.add(c);
	}
	public Card offeredCard() {
		Card oCard = null;
			for(int j = 0; j < 2; j++) {
				if(this.hand.get(j).isHidden() == false) {
					oCard = this.hand.get(j);
				}
			}
		return oCard;
	}
	public Card hiddenCard() {
		Card oCard = null;
			for(int j = 0; j < 2; j++) {
				if(this.hand.get(j).isHidden()) {
					oCard = this.hand.get(j);
				}
			}
		return oCard;
	}
	abstract void makeOffer();
	
	abstract void pickOffer();

}
