package JestGame;

public class Card {	
	
	protected Kind kind;
	protected Suit suit;
	protected Trophy trophy;
	protected boolean hidden = true;
	
	public Card(Kind k, Suit s, Trophy t) {
		this.kind = k;
		this.suit = s;
		this.trophy = t;
	}
	public static void createCard() {
		
		Card Joker = new Card(Kind.Joker,Suit.None,Trophy.None);
		Card aceHeart = new Card(Kind.Ace,Suit.Hearts,Trophy.Joker);
		Card fourSpade = new Card(Kind.Four,Suit.Spades,Trophy.Lowest);
		Card fourClub = new Card(Kind.Four,Suit.Clubs,Trophy.Lowest);
		Card twoHeart = new Card(Kind.Two,Suit.Hearts,Trophy.Joker);
		Card fourDiamond = new Card(Kind.Four,Suit.Diamonds,Trophy.BJnoJoke);
		Card fourHeart = new Card(Kind.Four,Suit.Hearts,Trophy.Joker);
		Card treeHeart = new Card(Kind.Tree,Suit.Hearts,Trophy.Joker);
		Card aceSpade = new Card(Kind.Ace,Suit.Spades,Trophy.Highest);
		Card aceClub = new Card(Kind.Ace,Suit.Clubs,Trophy.Highest);
		Card aceDiamond = new Card(Kind.Ace,Suit.Diamonds,Trophy.Majority);
		Card twoSpade = new Card(Kind.Two,Suit.Spades,Trophy.Majority);
		Card twoClub = new Card(Kind.Two,Suit.Clubs,Trophy.Lowest);
		Card twoDiamond = new Card(Kind.Two,Suit.Diamonds,Trophy.Highest);
		Card treeSpade = new Card(Kind.Tree,Suit.Spades,Trophy.Majority);
		Card treeClub = new Card(Kind.Tree,Suit.Clubs,Trophy.Lowest);
	}
	public String toString() {
		return this.kind.toString() + " " +this.suit.toString() + " " + this.trophy.toString();
	}
	
}
