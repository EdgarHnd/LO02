package JestGame;

import java.util.HashMap;

public class CardsCollection {
	
HashMap<String,Card> cards;
	
	public CardsCollection() {
		
		cards = new HashMap<String,Card>();
		
		cards.put("Joker",new Card(Kind.Joker,Suit.None,Trophy.None));
		cards.put("aceHeart",new Card(Kind.Ace,Suit.Hearts,Trophy.Joker));
		cards.put("fourSpade",new Card(Kind.Four,Suit.Spades,Trophy.Lowest));
		cards.put("fourClub",new Card(Kind.Four,Suit.Clubs,Trophy.Lowest));
		cards.put("twoHeart",new Card(Kind.Two,Suit.Hearts,Trophy.Joker));
		cards.put("fourDiamond",new Card(Kind.Four,Suit.Diamonds,Trophy.BJnoJoke));
		cards.put("fourHeart",new Card(Kind.Four,Suit.Hearts,Trophy.Joker));
		cards.put("treeHeart",new Card(Kind.Tree,Suit.Hearts,Trophy.Joker));
		cards.put("aceSpade",new Card(Kind.Ace,Suit.Spades,Trophy.Highest));
		cards.put("aceClub",new Card(Kind.Ace,Suit.Clubs,Trophy.Highest));
		cards.put("aceDiamond",new Card(Kind.Ace,Suit.Diamonds,Trophy.Majority));
		cards.put("twoSpade",new Card(Kind.Two,Suit.Spades,Trophy.Majority));
		cards.put("twoClub",new Card(Kind.Two,Suit.Clubs,Trophy.Lowest));
		cards.put("twoDiamond",new Card(Kind.Two,Suit.Diamonds,Trophy.Highest));
		cards.put("treeSpade",new Card(Kind.Tree,Suit.Spades,Trophy.Majority));
		cards.put("treeClub",new Card(Kind.Tree,Suit.Clubs,Trophy.Lowest));
		
	}
	public static void main(String[] args) {
		CardsCollection cardsColl = new  CardsCollection();
		System.out.println(cardsColl.cards.get("fourClub"));
	}
}
