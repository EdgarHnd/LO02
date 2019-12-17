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
	private LinkedList<Card> topCards;
	
	public static Deck getInstance(){
		
		if(dk == null){
			dk = new Deck();
		}
		
		return dk;
	}
	
	public Deck() {
		
		cards = new LinkedList<Card>();
		
		getCards().add(new Card(Kind.Joker,Suit.None,Trophy.BestJest));
		getCards().add(new Card(Kind.Ace,Suit.Hearts,Trophy.Joker));
		getCards().add(new Card(Kind.Four,Suit.Spades,Trophy.LowestClub));
		getCards().add(new Card(Kind.Four,Suit.Clubs,Trophy.LowestSpade));
		getCards().add(new Card(Kind.Two,Suit.Hearts,Trophy.Joker));
		getCards().add(new Card(Kind.Four,Suit.Diamonds,Trophy.BJnoJoke));
		getCards().add(new Card(Kind.Four,Suit.Hearts,Trophy.Joker));
		getCards().add(new Card(Kind.Three,Suit.Hearts,Trophy.Joker));
		getCards().add(new Card(Kind.Ace,Suit.Spades,Trophy.HighestClub));
		getCards().add(new Card(Kind.Ace,Suit.Clubs,Trophy.HighestSpade));
		getCards().add(new Card(Kind.Ace,Suit.Diamonds,Trophy.MajorityFour));
		getCards().add(new Card(Kind.Two,Suit.Spades,Trophy.MajorityTree));
		getCards().add(new Card(Kind.Two,Suit.Clubs,Trophy.LowestHeart));
		getCards().add(new Card(Kind.Two,Suit.Diamonds,Trophy.HighestDiamond));
		getCards().add(new Card(Kind.Three,Suit.Spades,Trophy.MajorityTwo));
		getCards().add(new Card(Kind.Three,Suit.Clubs,Trophy.HighestHeart));
		getCards().add(new Card(Kind.Three, Suit.Diamonds, Trophy.LowestDiamond));
		
		topCards = new LinkedList<Card>();
	}

	public LinkedList<Card> getCards() {
		return this.cards;
	}
	
	public void shuffle() {
		Collections.shuffle(cards);
	}

	public Card topCard() {
		return this.cards.pop();
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
	public void dealTrophys(){
		System.out.println("Dealing trophys");
		if(GameOptions.nbPlayer == 3) {
			GameBoard.getInstance().getTrophys().add(this.topCard());
			GameBoard.getInstance().getTrophys().add(this.topCard());
			System.out.println("The Trophys for this game are : "+GameBoard.getInstance().getTrophys().get(0)
					+" and "+GameBoard.getInstance().getTrophys().get(1));
		}
		else {
			GameBoard.getInstance().getTrophys().add(this.topCard());
			System.out.println("The Trophys for this game are : "+GameBoard.getInstance().getTrophys().get(0));
		}
	}
	
	public void gather(){
		//Gather the top cards of the deck to give them to the player
		for(int i = 0; i < GameOptions.nbPlayer;i++) {
		this.topCards.add(this.topCard());
		RoundsManager.getInstance().listPlayers.get(i).hand.clear();
		//Reset players states
		RoundsManager.getInstance().listPlayers.get(i).setHasPlayed(false);
		RoundsManager.getInstance().listPlayers.get(i).setIsPicking(false);
		//gather the left over offered card of the players
		this.topCards.add(RoundsManager.getInstance().listPlayers.get(i).offer.pollFirst());
		}
		Collections.shuffle(this.topCards);
		System.out.println("The stack is now : "+this.topCards);
	}
	
	public void dealStack(){
		
		System.out.println("Dealing stack");
		
		if(this.topCards.size() >= 2*GameOptions.nbPlayer) {
			System.out.println("First StackDeal");
			for(int i = 0; i < GameOptions.nbPlayer;i++) {
				this.topCards.getFirst().setHidden(true);
				RoundsManager.getInstance().listPlayers.get(i).receiveCard(this.topCards.pop());
				System.out.println(RoundsManager.getInstance().listPlayers.get(i).getName()+" received "+RoundsManager.getInstance().listPlayers.get(i).getHand());
			}
			System.out.println("\nSecond StackDeal");
			for(int i = 0; i < GameOptions.nbPlayer;i++) {
				this.topCards.getFirst().setHidden(true);
				RoundsManager.getInstance().listPlayers.get(i).receiveCard(this.topCards.pop());
				System.out.println(RoundsManager.getInstance().listPlayers.get(i).getName()+" received "+RoundsManager.getInstance().listPlayers.get(i).getHand());
			}
		}
		else {
			ConsoleGameView.display(ConsoleOutput.NoMoreCard);
		}
	}

}
