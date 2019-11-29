package fr.utt.jestcardgame.model;


import java.util.LinkedList;

/**
 * This class is where the Deck for one game is created.
 * It contains all the method needed to interact with the deck.
 *
 * @author Edgar
 * @version 1.0
 */

public class Deck {
	
	private LinkedList<Card> cards;
	
	public Deck() {
		
		cards = new LinkedList<Card>();
		
		getCards().add(new Card(Kind.Joker,Suit.None,Trophy.None));
		getCards().add(new Card(Kind.Ace,Suit.Hearts,Trophy.Joker));
		getCards().add(new Card(Kind.Four,Suit.Spades,Trophy.Lowest));
		getCards().add(new Card(Kind.Four,Suit.Clubs,Trophy.Lowest));
		getCards().add(new Card(Kind.Two,Suit.Hearts,Trophy.Joker));
		getCards().add(new Card(Kind.Four,Suit.Diamonds,Trophy.BJnoJoke));
		getCards().add(new Card(Kind.Four,Suit.Hearts,Trophy.Joker));
		getCards().add(new Card(Kind.Three,Suit.Hearts,Trophy.Joker));
		getCards().add(new Card(Kind.Ace,Suit.Spades,Trophy.Highest));
		getCards().add(new Card(Kind.Ace,Suit.Clubs,Trophy.Highest));
		getCards().add(new Card(Kind.Ace,Suit.Diamonds,Trophy.Majority));
		getCards().add(new Card(Kind.Two,Suit.Spades,Trophy.Majority));
		getCards().add(new Card(Kind.Two,Suit.Clubs,Trophy.Lowest));
		getCards().add(new Card(Kind.Two,Suit.Diamonds,Trophy.Highest));
		getCards().add(new Card(Kind.Three,Suit.Spades,Trophy.Majority));
		getCards().add(new Card(Kind.Three,Suit.Clubs,Trophy.Lowest));
		getCards().add(new Card(Kind.Three, Suit.Diamonds, Trophy.Lowest));
	}

	public LinkedList<Card> getCards() {
		return this.cards;
	}
	
	public void shuffle() {
		for (int i = 0; i < getCards().size(); i++) {
			int position = (int) Math.round((getCards().size() - 1)* Math.random());
			Card cardShuffled = getCards().pop();
			getCards().add(position,cardShuffled);
		}
	}

	public Card topCard() {
		return getCards().pop();
	}

	public void deal(){
		System.out.println("\nStart dealing cards to the players");
		
		if(this.cards.size() >= 2*GameOptions.nbPlayer) {
			System.out.println("First Deal");
			for(int i = 0; i < GameOptions.nbPlayer;i++) {
				RoundsManager.listPlayers.get(i).receiveCard(this.topCard());
				System.out.println(RoundsManager.listPlayers.get(i).getName()+" received "+RoundsManager.listPlayers.get(i).getHand());
			}
			System.out.println("\nSecond Deal");
			for(int i = 0; i < GameOptions.nbPlayer;i++) {
				RoundsManager.listPlayers.get(i).receiveCard(this.topCard());
				System.out.println(RoundsManager.listPlayers.get(i).getName()+" received "+RoundsManager.listPlayers.get(i).getHand());
			}
		}
		else {
			System.out.println("Not enough cards to deal");
		}
	}
}
