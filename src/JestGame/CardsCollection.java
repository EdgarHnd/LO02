package JestGame;

import java.util.ArrayList;

public class CardsCollection {
	
ArrayList<Card> cards;
	
	public CardsCollection() {
		
		cards = new ArrayList<Card>();
		
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
	public void printCollection() {
		for(int i=0; i < this.cards.size(); i++) {
			System.out.println(this.cards.get(i));
		}
	}
	public static void main(String[] args) {
		
		CardsCollection cardsColl = new  CardsCollection();
		System.out.println(cardsColl.cards.get(5));
		cardsColl.printCollection();
		
	}
	
}
