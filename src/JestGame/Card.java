package JestGame;

public class Card {	
	
	protected Kind kind;
	protected Suit suit;
	protected Effect trophy;
	protected boolean hidden = true;
	
	public Card(Kind k, Suit s/*, Effect e*/) {
		this.kind = k;
		this.suit = s;
	/*	this.trophy = e.apply(e);*/
	}
	public static void createCard() {
		Card aceClubs = new Card(Kind.Ace,Suit.Clubs/*,Effect.apply(hightest)*/);
	}
}
