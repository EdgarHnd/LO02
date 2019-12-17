package fr.utt.jestcardgame.model;

import java.util.LinkedList;

public class Jest {
	
	private LinkedList<Card> jest;
	
	public Jest() {
		this.jest = new LinkedList<Card>();
	}
	
	//Getter/Setters
	public LinkedList<Card> getJest() {
		return jest;
	}

	public void addToJest(Card c) {
		this.jest.add(c);
	}

	//Booleans to analyse the player jest
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
	
}
