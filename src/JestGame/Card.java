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
			 break;
		case One:
			value = 1;
			 break;
		case Two:
			value = 2;
			 break;
		case Three:
			value = 3;
			 break;
		case Four:
			value = 4;
			 break;
		case Joker:
			value = 0;
			 break;
		
		}
		return value;
	}
	public int cardTiesValue() {
		int tieValue = 0;
		switch(this.suit) {
		case Spades:
			tieValue = 4;
			 break;
		case Clubs:
			tieValue = 3;
			 break;
		case Diamonds:
			tieValue = 2;
			 break;
		case Hearts:
			tieValue = 1;
			 break;
		case None:
			tieValue = 0;
			 break;
		}
		return tieValue;
	}
	
	public String toString() {
		return this.kind.toString() + " " +this.suit.toString() + " " + 
	this.trophy.toString() + " " + this.hidden+" value "+this.cardValue()+" tieValue "+this.cardTiesValue();
	}
	
}
