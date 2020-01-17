package fr.utt.jestcardgame.model;

import java.util.LinkedList;

/**
 * Class representing the 'Jest' of each player, that is the collection of cards of each player, also their future score.
 * It contains a <code>LinkedList</code> of {@link Card} that constitute the <code>jestCards</code> attribute.
 * Also, this class defines the trophys conditions in order to win a trophy.
 *
 * @author Elina
 */
public class Jest {
	
	private LinkedList<Card> jestCards;

	/**
	 * Creating a <code>Jest</code> instance will create a new LinkedList of cards, which is its <code>jestCards</code> attribute.
	 *
	 * @see LinkedList
	 */
	public Jest() {
		this.jestCards = new LinkedList<>();
	}

	/**
	 * Gets the LinkedList <code>jestCards</code> attribute of the <code>Jest</code> instance.
	 *
	 * @see LinkedList
	 * @return jestCards
	 */
	public LinkedList<Card> getJestCards() {
		return jestCards;
	}

	/**
	 * Adds a card put in the parameters to the player's jest.
	 *
	 * @see LinkedList
	 * @param c Card added to the player's jest
	 */
	public void addToJest(Card c) {
		this.jestCards.add(c);
	}

	/**
	 * Returns a boolean value to know if the player's jest has all the card of the suit put in parameters.
	 *
	 * @param s Suit analysed
	 * @return boolean
	 */
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

	/**
	 * Returns a boolean value to know if the player's jest
	 *
	 * @param k Kind analysed
	 * @param s Suit analysed
	 * @return boolean
	 */
	public boolean isAlone(Kind k,Suit s) {
		for(int i=0; i<this.jestCards.size();i++) {
			if(this.jestCards.get(i).kind != k && 
					this.jestCards.get(i).suit == s) {
					return false;
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
			if(this.jestCards.get(i).cardValue() == this.highestFaceValue() && 
					this.jestCards.get(i).cardTiesValue() > high) {
					high = this.jestCards.get(i).cardTiesValue();
				}
			}
		return high;
	}
	
}
