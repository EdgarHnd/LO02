package JestGame;

public class Card {	
	
	protected Kind kind;
	protected Suit suit;
	protected Trophy trophy;
	protected boolean hidden = true;
	
	
	public boolean isHidden() {
		return hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	public Card(Kind k, Suit s, Trophy t) {
		this.kind = k;
		this.suit = s;
		this.trophy = t;
	}
	
	public int cardValue() {
		int value = 0;
		switch(this.kind) {
		case Ace:
			value = 1;
		case One:
			value = 1;
		case Two:
			value = 2;
		case Three:
			value = 3;
		case Four:
			value = 4;
		case Joker:
			value = 0;
		
		}
		return value;
	}
	public int cardTiesValue() {
		int tieValue = 0;
		switch(this.suit) {
		case Spades:
			tieValue = 4;
		case Clubs:
			tieValue = 3;
		case Diamonds:
			tieValue = 2;
		case Hearts:
			tieValue = 1;
		case None:
			tieValue = 0;
		}
		return tieValue;
	}
	
	public String toString() {
		return this.kind.toString() + " " +this.suit.toString() + " " + this.trophy.toString() + " " + this.hidden;
	}
	
}
