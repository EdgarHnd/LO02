package JestGame;

import java.util.LinkedList;

public class Player {
	
	protected int nb;
	protected String name;
	protected LinkedList<Card> hand;
	protected LinkedList<Card> jest;
	protected int finalScore;
	protected int finalBoolean;
	protected boolean hasPlayed;
	
	public String getName() {
		return this.name;
	}
	public int getNb() {
		return nb;
	}
	//Constructor
	public Player (String name, int nb) {
		this.name = name;
		this.nb = nb;
	}
	
	//Players methods
	
	public void receiveCard(Card c) {
		this.hand.add(c);
	}
	
	public void makeOffer(){
		
	}
	
	public void pickOffer() {
		
	}

}
