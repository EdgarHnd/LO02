package JestGame;

import java.util.LinkedList;


public class Deck {
	
	private LinkedList<Card> cards;
	
	public Deck() {
		
		cards = new LinkedList<Card>();
		
		cards.add(new Card(Kind.Joker,Suit.None,Trophy.None));
		cards.add(new Card(Kind.Ace,Suit.Hearts,Trophy.Joker));
		cards.add(new Card(Kind.Four,Suit.Spades,Trophy.Lowest));
		cards.add(new Card(Kind.Four,Suit.Clubs,Trophy.Lowest));
		cards.add(new Card(Kind.Two,Suit.Hearts,Trophy.Joker));
		cards.add(new Card(Kind.Four,Suit.Diamonds,Trophy.BJnoJoke));
		cards.add(new Card(Kind.Four,Suit.Hearts,Trophy.Joker));
		cards.add(new Card(Kind.Tree,Suit.Hearts,Trophy.Joker));
		cards.add(new Card(Kind.Ace,Suit.Spades,Trophy.Highest));
		cards.add(new Card(Kind.Ace,Suit.Clubs,Trophy.Highest));
		cards.add(new Card(Kind.Ace,Suit.Diamonds,Trophy.Majority));
		cards.add(new Card(Kind.Two,Suit.Spades,Trophy.Majority));
		cards.add(new Card(Kind.Two,Suit.Clubs,Trophy.Lowest));
		cards.add(new Card(Kind.Two,Suit.Diamonds,Trophy.Highest));
		cards.add(new Card(Kind.Tree,Suit.Spades,Trophy.Majority));
		cards.add(new Card(Kind.Tree,Suit.Clubs,Trophy.Lowest));
	}
	
	public void shuffle() {
		for (int i = 0; i < cards.size(); i++) {
			int position = (int) Math.round((cards.size() - 1)* Math.random());
			Card cardShuffled = cards.pop();
			cards.add(position,cardShuffled);
		}
	}
	
	public Card topCard() {
		return cards.pop();
	}
	
	
	public void deal() {
		System.out.println("Start dealing cards to the player");
		for(i = 0; i < (GameOptions.getNbPlayer() * 2); i++ ) {
		Player p;
		p.receiveCard(this.topCard());
		}
	}
	
	  public static void main(String[] args) {
		  
		 Deck cardsColl = new  Deck();
		 System.out.println(cardsColl.cards);
		 cardsColl.shuffle();
		 System.out.println(cardsColl.cards);
			
	}
}
