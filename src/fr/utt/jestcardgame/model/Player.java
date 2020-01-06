package fr.utt.jestcardgame.model;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

import fr.utt.jestcardgame.observer.Observable;
import fr.utt.jestcardgame.visitor.Visitable;
import fr.utt.jestcardgame.visitor.Visitor;

public class Player extends Observable implements Visitable{

	ChooseStrategy strategy;

	protected int nb;
	protected String name;
	protected LinkedList<Card> hand;
	protected LinkedList<Card> offer;
	protected Jest jest;
	protected int finalBoolean;
	protected Score score;
	protected boolean hasPlayed = false;
	protected boolean isPicking = false;
	protected boolean isNext = false;
	
	public boolean getHasPlayed() {
		return hasPlayed;
	}
	public void setHasPlayed(boolean hasPlayed) {
		this.hasPlayed = hasPlayed;
	}
	public boolean getIsPicking() {
		return isPicking;
	}
	public void setIsPicking(boolean bestOffer) {
		this.isPicking = bestOffer;
	}
	//Getters
	public String getName() {
		return this.name;
	}
	public int getNb() {
		return nb;
	}
	public LinkedList<Card> getHand() {
		return hand;
	}
	public LinkedList<Card> getOffer() {
		return offer;
	}
	public Score getScore() {
		return score;
	}
	public void setScore(Score s) {
		this.score = s;
	}
	public Jest getJest() {
		return jest;
	}
	//Constructor
	public Player (String name, int nb, ChooseStrategy strategy) {
		this.name = name;
		this.nb = nb;
		
		this.hand = new LinkedList<Card>();
		this.jest = new Jest();
		this.offer = new LinkedList<Card>();
		this.strategy = strategy;
		this.score = new Score();
	}
	
	//Players methods
	
	public void receiveCard(Card c) {
		this.hand.add(c);
		this.setChanged();
		this.notifyObservers(c);
		System.out.println("notify receivedCard");
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Card offeredCard() {
		Card oCard = null;
			for(int j = 0; j < 2; j++) {
				if(this.hand.get(j).isHidden() == false) {
					oCard = this.hand.get(j);
				}
			}
		return oCard;
	}
	public Card hiddenCard() {
		Card oCard = null;
			for(int j = 0; j < 2; j++) {
				if(this.hand.get(j).isHidden()) {
					oCard = this.hand.get(j);
				}
			}
		return oCard;
	}
	
	public void newOffer() {
		this.offer.add(this.offeredCard());
		this.offer.add(this.hiddenCard());
	}
	
	public boolean hasCompleteOffer() {
		if(this.offer.size()==2) {
			return true;
		}
		return false;
	}

	public void makeOffer() {
		strategy.makeOfferStrategy(this);
	}
	
	public void pickOffer() {
		strategy.pickOfferStrategy(this);
		this.setChanged();
		this.notifyObservers(this.jest);
	}
	@Override
	public void acceptVisitor(Visitor v) {
		v.visit(this);
	}
	public void receiveTrophy() {
		this.setChanged();
		this.notifyObservers(this.jest);
	}
}
