package JestGame;

import java.util.LinkedList;

public abstract class Player {
	private String name;
	private LinkedList<Card> hand;
	private LinkedList<Card> jest;
	private int finalScore;
	private int finalBoolean;
	private boolean hasPlayed;
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String newName) {
		this.name = newName;
	}
	
	public Player (String name) {
		this.name = name;
	}
	
	//Players methods
	
	public void receiveCard(Card c) {
		this.hand.add(c);
	}
	
	abstract void makeOffer();
	
	abstract void pickOffer();

}
