package fr.utt.jestcardgame.model;


import fr.utt.jestcardgame.view.ConsoleGameView;
import fr.utt.jestcardgame.view.ConsoleOutput;

import java.util.Collections;
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
	private static Deck dk= null;
	
	public static Deck getInstance(){
		
		if(dk == null){
			dk = new Deck();
		}
		
		return dk;
	}
	
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
		Collections.shuffle(cards);
	}

	public Card topCard() {
		return getCards().pop();
	}

	public void deal(){
		ConsoleGameView.display(ConsoleOutput.Dealing);
		
		if(this.cards.size() >= 2*GameOptions.nbPlayer) {
			System.out.println("First Deal");
			for(int i = 0; i < GameOptions.nbPlayer;i++) {
				RoundsManager.getInstance().listPlayers.get(i).receiveCard(this.topCard());
				System.out.println(RoundsManager.getInstance().listPlayers.get(i).getName()+" received "+RoundsManager.getInstance().listPlayers.get(i).getHand());
			}
			System.out.println("\nSecond Deal");
			for(int i = 0; i < GameOptions.nbPlayer;i++) {
				RoundsManager.getInstance().listPlayers.get(i).receiveCard(this.topCard());
				System.out.println(RoundsManager.getInstance().listPlayers.get(i).getName()+" received "+RoundsManager.getInstance().listPlayers.get(i).getHand());
			}
		}
		else {
			ConsoleGameView.display(ConsoleOutput.NoMoreCard);
		}
	}
}
