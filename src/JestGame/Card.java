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
	
	public String toString() {
		return this.kind.toString() + " " +this.suit.toString() + " " + this.trophy.toString();
	}
	
}
