package fr.utt.jestcardgame.model;

import java.util.ArrayList;
import java.util.LinkedList;

import fr.utt.jestcardgame.visitor.Visitable;
import fr.utt.jestcardgame.visitor.Visitor;

public class Player implements Visitable{
	
	protected int nb;
	protected String name;
	protected LinkedList<Card> hand;
	protected LinkedList<Card> offer;
	protected Jest jest;
	protected int finalBoolean;
	protected int score;
	protected boolean hasPlayed = false;
	protected boolean isPicking = false;
	protected boolean isNext = false;
	private ArrayList<Visitor> listVisitor = new ArrayList<Visitor>(); 
	
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
		this.jest = new Jest();
		this.offer = new LinkedList<Card>();
		this.score = 0;
		
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
	public void makeOffer() {
		
	}
	
	public void pickOffer() {
		
	}
	@Override
	public void acceptVisitor(Visitor v) {
		this.listVisitor.add(v);

		
	}
	public int getScore() {
		return score;
	}
	public void setScore(int finalScore) {
		this.score = finalScore;
	}
	public Jest getJest() {
		return jest;
	}

}
