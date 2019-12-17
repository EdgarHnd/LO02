package fr.utt.jestcardgame.model;

import java.util.LinkedList;

public class Jest {
	
	private LinkedList<Card> jestCards;
	
	public Jest() {
		this.jestCards = new LinkedList<Card>();
	}
	
	//Getter/Setters
	public LinkedList<Card> getJestCards() {
		return jestCards;
	}

	public void addToJest(Card c) {
		this.jestCards.add(c);
	}

	//Booleans to analyse the player jest
	public boolean hasAllSuit(Suit s) {
		int count = 0;
		for(int i=0; i<this.jestCards.size();i++) {
			if(this.jestCards.get(i).suit == s) {
				count ++;
			}
		}
		if(count == 4) {
			return true;
		}
		return false;
	}
	
	public boolean isAlone(Kind k,Suit s) {
		for(int i=0; i<this.jestCards.size();i++) {
			if(this.jestCards.get(i).kind != k) {
				if(this.jestCards.get(i).suit == s) {
					return false;
				}
			}
		}
		return true;
	}
	
	
	//Trophys conditions
	public boolean hasJoker() {
		for(int i=0; i<this.jestCards.size();i++) {
			if(this.jestCards.get(i).kind == Kind.Joker) {
				return true;
			}
		}
		return false;
	}
	
	public boolean hasSuit(Suit s) {
		for(int i=0; i<this.jestCards.size();i++) {
			if(this.jestCards.get(i).suit == s) {
				return true;
			}
		}
		return false;
	}
	
	public int majority(Kind k) {
		int nbKind = 0;
		for(int i=0; i<this.jestCards.size();i++) {
			if(this.jestCards.get(i).kind == k) {
				nbKind ++;
			}
		}
		return nbKind;
	}
	
	public int tieMajority(Kind k) {
		int high = 0;
		for(int i=0; i<this.jestCards.size();i++) {
			if(this.jestCards.get(i).kind == k) {
				if(this.jestCards.get(i).cardTiesValue() > high) {
					high = this.jestCards.get(i).cardTiesValue();
					}
			}
		}
		return high;
	}
	
	public int highestSuit(Suit s) {
		int high = 0;
		Card c;
		for(int i=0; i<this.jestCards.size();i++) {
			if(this.jestCards.get(i).suit == s) {
				c = this.jestCards.get(i);
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
		for(int i=0; i<this.jestCards.size();i++) {
			if(this.jestCards.get(i).suit == s) {
				c = this.jestCards.get(i);
				if(c.cardValue() < low) {
					low = c.cardValue();
				}
			}
		}
		return low;
	}
	
	public int highestFaceValue() {
		int high = 0;
		for(int i=0; i<this.jestCards.size();i++) {
				if(this.jestCards.get(i).cardValue() > high) {
					high = this.jestCards.get(i).cardValue();
				}
			}
		return high;
	}
	
	public int highestFaceTiesValue() {
		int high = 0;
		for(int i=0; i<this.jestCards.size();i++) {
			if(this.jestCards.get(i).cardValue() == this.highestFaceValue()) {
				if(this.jestCards.get(i).cardTiesValue() > high) {
					high = this.jestCards.get(i).cardTiesValue();
				}
			}
		}
		return high;
	}
	
}
