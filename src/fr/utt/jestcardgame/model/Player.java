package fr.utt.jestcardgame.model;

import java.util.LinkedList;

public abstract class Player {
	
	protected int nb;
	protected String name;
	protected LinkedList<Card> hand;
	protected LinkedList<Card> offer;
	protected LinkedList<Card> jest;
	protected int finalScore;
	protected int finalBoolean;
	protected boolean hasPlayed = false;
	protected boolean isPicking = false;
	protected boolean isNext = false;
	
	public boolean getHasPlayed() {
		return hasPlayed;
	}
	public void setHasPlayed(boolean hasPlayed) {
		this.hasPlayed = hasPlayed;
	}
	public boolean getIsPicking() {
		return isPicking;
	}
	public void setIsPicking(boolean bestOffer) {
		this.isPicking = bestOffer;
	}
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
	public LinkedList<Card> getOffer() {
		return offer;
	}
	//Constructor
	public Player (String name, int nb) {
		this.name = name;
		this.nb = nb;
		
		this.hand = new LinkedList<Card>();
		this.jest = new LinkedList<Card>();
		this.offer = new LinkedList<Card>();
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
	
	public void newOffer() {
		this.offer.add(this.offeredCard());
		this.offer.add(this.hiddenCard());
	}
	
	public boolean completeOffer() {
		if(this.offer.size()==2) {
			return true;
		}
		return false;
	}
	abstract void makeOffer();
	
	abstract void pickOffer();

}
