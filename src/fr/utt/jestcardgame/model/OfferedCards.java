package fr.utt.jestcardgame.model;

import java.util.ArrayList;

public class OfferedCards {
	private ArrayList<Card> offers;
	
	public OfferedCards() {
		offers = new ArrayList<Card>(8);
	}

	public ArrayList<Card> getOffers() {
		return offers;
	}

	public void setOffers(ArrayList<Card> offers) {
		this.offers = offers;
	}
	
	
	
}
