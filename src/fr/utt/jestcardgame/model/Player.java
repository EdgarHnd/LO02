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
	protected LinkedList<Card> jest;
	protected int finalScore;
	protected int finalBoolean;
	protected int jestValue = 0;
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
	public void makeOffer() {
		
	}
	
	public void pickOffer() {
		
	}
	
	//----------------------------------------------------
	//Calculating player score
	
	public void calculateJestValue() {
		this.addBlack();
		this.blackBonus();
		this.addHeart();
		this.removeDiamonds();
		this.addJoker();
	}
	
	public void resetJest() {
		this.jestValue = 0;
	}
	
	public void addBlack() {
		for(int i=0; i<this.jest.size();i++) {
			if(this.jest.get(i).suit == Suit.Clubs || this.jest.get(i).suit == Suit.Spades) {
				if(this.jest.get(i).kind == Kind.Ace && this.isAlone(Kind.Ace, this.jest.get(i).suit)) {
					this.jestValue += 5;
				}
				else {
				this.jestValue += this.jest.get(i).cardValue();
				}
			}
		}
	}
	
	public void blackBonus() {
		for(int i=0; i<this.jest.size();i++) {
			if(this.jest.get(i).suit == Suit.Clubs) {
				
				for(int j=0; j<this.jest.size();j++) {
					if(this.jest.get(j).suit == Suit.Spades) {
						
						if(this.jest.get(i).kind == this.jest.get(j).kind) {
							this.jestValue += 1;
						}
					}
				}
			}
			else if(this.jest.get(i).suit == Suit.Spades) {
				
				for(int j=0; j<this.jest.size();j++) {
					if(this.jest.get(j).suit == Suit.Clubs) {
						
						if(this.jest.get(i).kind == this.jest.get(j).kind) {
							this.jestValue += 1;
						}
					}
				}
			}
		}
	}
	
	public void removeDiamonds() {
		for(int i=0; i<this.jest.size();i++) {
			if(this.jest.get(i).suit == Suit.Diamonds) {
				if(this.jest.get(i).kind == Kind.Ace && this.isAlone(Kind.Ace, this.jest.get(i).suit)) {
					this.jestValue -= 5;
				}
				else {
				this.jestValue -= this.jest.get(i).cardValue();
				}
			}
		}
	}
	
	public void addHeart() {
		for(int i=0; i<this.jest.size();i++) {
			if(this.jest.get(i).suit == Suit.Hearts && this.hasJoker()) {
				if(this.jest.get(i).kind == Kind.Ace && this.isAlone(Kind.Ace, this.jest.get(i).suit)) {
					this.jestValue -= 5;
				}
				else {
				this.jestValue -= this.jest.get(i).cardValue();
				}
			}
			else if(this.jest.get(i).suit == Suit.Hearts && this.hasJoker() && this.hasAllHeart()) {
				if(this.jest.get(i).kind == Kind.Ace && this.hasSuit(this.jest.get(i).suit) == false) {
					this.jestValue += 5;
				}
				else {
				this.jestValue += this.jest.get(i).cardValue();
				}
			}
		}
	}
	
	public void addJoker() {
		if(this.hasJoker() && this.hasSuit(Suit.Hearts)==false) {
			this.jestValue += 4;
		}
	}
	
	public boolean hasAllHeart() {
		int count = 0;
		for(int i=0; i<this.jest.size();i++) {
			if(this.jest.get(i).suit == Suit.Hearts) {
				count ++;
			}
		}
		if(count == 4) {
			return true;
		}
		return false;
	}
	
	public boolean isAlone(Kind k,Suit s) {
		for(int i=0; i<this.jest.size();i++) {
			if(this.jest.get(i).kind != k) {
				if(this.jest.get(i).suit == s) {
					return false;
				}
			}
		}
		return true;
	}
	
	
	//Trophys conditions
	public boolean hasJoker() {
		for(int i=0; i<this.jest.size();i++) {
			if(this.jest.get(i).kind == Kind.Joker) {
				return true;
			}
		}
		return false;
	}
	
	public boolean hasSuit(Suit s) {
		for(int i=0; i<this.jest.size();i++) {
			if(this.jest.get(i).suit == s) {
				return true;
			}
		}
		return false;
	}
	
	public int majority(Kind k) {
		int nbKind = 0;
		for(int i=0; i<this.jest.size();i++) {
			if(this.jest.get(i).kind == k) {
				nbKind ++;
			}
		}
		return nbKind;
	}
	
	public int tieMajority(Kind k) {
		int high = 0;
		for(int i=0; i<this.jest.size();i++) {
			if(this.jest.get(i).kind == k) {
				if(this.jest.get(i).cardTiesValue() > high) {
					high = this.jest.get(i).cardTiesValue();
					}
			}
		}
		return high;
	}
	
	public int highestSuit(Suit s) {
		int high = 0;
		Card c;
		for(int i=0; i<this.jest.size();i++) {
			if(this.jest.get(i).suit == s) {
				c = this.jest.get(i);
				if(c.cardValue() > high) {
					high = c.cardValue();
				}
			}
		}
		return high;
	}
	
	public int lowestSuit(Suit s) {
		int low = 0;
		Card c;
		for(int i=0; i<this.jest.size();i++) {
			if(this.jest.get(i).suit == s) {
				c = this.jest.get(i);
				if(c.cardValue() < low) {
					low = c.cardValue();
				}
			}
		}
		return low;
	}
	
	public int highestFaceValue() {
		int high = 0;
		for(int i=0; i<this.jest.size();i++) {
				if(this.jest.get(i).cardValue() > high) {
					high = this.jest.get(i).cardValue();
				}
			}
		return high;
	}
	
	public int highestFaceTiesValue() {
		int high = 0;
		for(int i=0; i<this.jest.size();i++) {
			if(this.jest.get(i).cardValue() == this.highestFaceValue()) {
				if(this.jest.get(i).cardTiesValue() > high) {
					high = this.jest.get(i).cardTiesValue();
				}
			}
		}
		return high;
	}
	
	//----------------------------------------------------
	//Maybe add the visitor later 
	@Override
	public void addVisitor(Visitor vis) {
		// TODO Auto-generated method stub
		this.listVisitor.add(vis);
	}

	@Override
	public void removeVisitor() {
		// TODO Auto-generated method stub
		this.listVisitor = new ArrayList<Visitor>(); 
	}

	@Override
	public void notifyVisitor(String str) {
		// TODO Auto-generated method stub
		 for(Visitor vis : listVisitor)
		      vis.update(str);
	}

}
