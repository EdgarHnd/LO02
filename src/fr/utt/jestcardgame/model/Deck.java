package fr.utt.jestcardgame.model;


import fr.utt.jestcardgame.view.ConsoleGameView;
import fr.utt.jestcardgame.view.ConsoleOutput;

import java.util.Collections;
import java.util.LinkedList;

/**
 * This class is where the Deck countaining the <code>Card</code> for one game is created.
 * It contains all the method needed to interact with the deck.
 *
 * @author Edgar
 */

public class Deck {
	
	private LinkedList<Card> cards;
	private static Deck dk= null;
	private LinkedList<Card> topCards;
	
	/**
	 * Method use to make this object a singleton
	 * @return dk
	 */
	public static Deck getInstance(){
		
		if(dk == null){
			dk = new Deck();
		}
		
		return dk;
	}
	/**
	 * Create the deck for the game
	 * This is were the card list is creaded and all the cards for the game are created and added to the list
	 * This is also where the list topCards is created
	 */
	public Deck() {
		
		cards = new LinkedList<Card>();
		
		getCards().add(new Card(Kind.Joker,Suit.None,Trophys.BestJest,"pictures/CardsPng/joker.png"));
		getCards().add(new Card(Kind.Ace,Suit.Hearts,Trophys.Joker,"pictures/CardsPng/Aheart.png"));
		getCards().add(new Card(Kind.Ace,Suit.Spades,Trophys.HighestClub,"pictures/CardsPng/Aspade.png"));
		getCards().add(new Card(Kind.Ace,Suit.Clubs,Trophys.HighestSpade,"pictures/CardsPng/Aclub.png"));
		getCards().add(new Card(Kind.Ace,Suit.Diamonds,Trophys.MajorityFour,"pictures/CardsPng/Adiamond.png"));
		getCards().add(new Card(Kind.Four,Suit.Spades,Trophys.LowestClub,"pictures/CardsPng/4spade.png"));
		getCards().add(new Card(Kind.Four,Suit.Clubs,Trophys.LowestSpade,"pictures/CardsPng/4club.png"));
		getCards().add(new Card(Kind.Four,Suit.Diamonds,Trophys.BJnoJoke,"pictures/CardsPng/4diamond.png"));
		getCards().add(new Card(Kind.Four,Suit.Hearts,Trophys.Joker,"pictures/CardsPng/4heart.png"));
		getCards().add(new Card(Kind.Three,Suit.Spades,Trophys.MajorityTwo,"pictures/CardsPng/3spade.png"));
		getCards().add(new Card(Kind.Three,Suit.Clubs,Trophys.HighestHeart,"pictures/CardsPng/3club.png"));
		getCards().add(new Card(Kind.Three, Suit.Diamonds, Trophys.LowestDiamond,"pictures/CardsPng/3diamond.png"));
		getCards().add(new Card(Kind.Three,Suit.Hearts,Trophys.Joker,"pictures/CardsPng/3heart.png"));
		getCards().add(new Card(Kind.Two,Suit.Hearts,Trophys.Joker,"pictures/CardsPng/2heart.png"));		
		getCards().add(new Card(Kind.Two,Suit.Spades,Trophys.MajorityThree,"pictures/CardsPng/2spade.png"));
		getCards().add(new Card(Kind.Two,Suit.Clubs,Trophys.LowestHeart,"pictures/CardsPng/2club.png"));
		getCards().add(new Card(Kind.Two,Suit.Diamonds,Trophys.HighestDiamond,"pictures/CardsPng/2diamond.png"));
		
		
		topCards = new LinkedList<Card>();
	}
	/**
	 * Return the card list of the deck
	 * @return this.cards
	 */
	public LinkedList<Card> getCards() {
		return this.cards;
	}
	/*Shuffle the cards in the list cards of the deck
	 */
	public void shuffle() {
		Collections.shuffle(cards);
	}
	/**
	 * Return the topCard from the deck
	 * @return this.cards.pop()
	 */
	public Card topCard() {
		return this.cards.pop();
	}
	/**
	 * Deal 2 cards to each players playing the game.
	 * Also print a message if their is not enought cards left.
	 */
	public void deal(){
		ConsoleGameView.display(ConsoleOutput.Dealing);
		
		if(this.cards.size() >= 2*OptionsData.nbPlayer) {
			System.out.println("First Deal");
			for(int i = 0; i < OptionsData.nbPlayer;i++) {
				RoundsManager.getInstance().listPlayers.get(i).receiveCard(this.topCard());
				RoundsManager.getInstance().setState("Dealing");
				System.out.println(RoundsManager.getInstance().listPlayers.get(i).getName()+" received "+RoundsManager.getInstance().listPlayers.get(i).getHand());
			}
			System.out.println("\nSecond Deal");
			for(int i = 0; i < OptionsData.nbPlayer;i++) {
				RoundsManager.getInstance().listPlayers.get(i).receiveCard(this.topCard());
				RoundsManager.getInstance().setState("Dealing");
				System.out.println(RoundsManager.getInstance().listPlayers.get(i).getName()+" received "+RoundsManager.getInstance().listPlayers.get(i).getHand());
			}
		}
		else {
			ConsoleGameView.display(ConsoleOutput.NoMoreCard);
		}
	}
	/**
	 * Deal the trophy cards for the game,
	 * 2 if their is 3 players or 1 if their is 4.
	 */
	public void dealTrophys(){
		System.out.println("Dealing trophys");
		if(OptionsData.nbPlayer == 3) {
			GameBoard.getInstance().getTrophys().add(this.topCard());
			GameBoard.getInstance().getTrophys().add(this.topCard());
			System.out.println("The Trophys for this game are : "+GameBoard.getInstance().getTrophys().get(0)
					+" and "+GameBoard.getInstance().getTrophys().get(1));
		}
		else if(OptionsData.nbPlayer == 4) {
			GameBoard.getInstance().getTrophys().add(this.topCard());
			System.out.println("The Trophys for this game are : "+GameBoard.getInstance().getTrophys().get(0));
		}
	}
	/**
	 * Gather the left over cards from the players after they made their offers and reset their hand state
	 * Mix those cards with a matching number of cards from the deck
	 * Then store all these cards in the list topCards in order to deal them to the players the next turn
	 */
	public void gather(){
		for(int i = 0; i < OptionsData.nbPlayer;i++) {
		this.topCards.add(this.topCard());
		RoundsManager.getInstance().listPlayers.get(i).hand.clear();
		RoundsManager.getInstance().listPlayers.get(i).setHasPlayed(false);
		RoundsManager.getInstance().listPlayers.get(i).setIsPicking(false);
		this.topCards.add(RoundsManager.getInstance().listPlayers.get(i).offer.pollFirst());
		}
		Collections.shuffle(this.topCards);
		System.out.println("The stack is now : "+this.topCards);
	}
	/**
	 * Deal the cards from the topCards list to the players at the begining of a new turn
	 * Print a message if their isn't enought card to deal
	 */
	public void dealStack(){
		
		System.out.println("Dealing stack");
		
		if(this.topCards.size() >= 2*OptionsData.nbPlayer) {
			System.out.println("First StackDeal");
			for(int i = 0; i < OptionsData.nbPlayer;i++) {
				this.topCards.getFirst().setHidden(true);
				RoundsManager.getInstance().listPlayers.get(i).receiveCard(this.topCards.pop());
				RoundsManager.getInstance().setState("Dealing");
				System.out.println(RoundsManager.getInstance().listPlayers.get(i).getName()+" received "+RoundsManager.getInstance().listPlayers.get(i).getHand());
			}
			System.out.println("\nSecond StackDeal");
			for(int i = 0; i < OptionsData.nbPlayer;i++) {
				this.topCards.getFirst().setHidden(true);
				RoundsManager.getInstance().listPlayers.get(i).receiveCard(this.topCards.pop());
				RoundsManager.getInstance().setState("Dealing");
				System.out.println(RoundsManager.getInstance().listPlayers.get(i).getName()+" received "+RoundsManager.getInstance().listPlayers.get(i).getHand());
			}
		}
		else {
			ConsoleGameView.display(ConsoleOutput.NoMoreCard);
		}
	}

}
