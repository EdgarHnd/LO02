package fr.utt.jestcardgame.model;

import fr.utt.jestcardgame.visitor.Visitor;

import java.util.Iterator;

public class Score implements Visitor{
	
	private Player player;
	private int score;
	
	public Score() {
		this.score = 0;
	}
	
	@Override
	public void visit(Player p) {
		this.player = p;
	}
	
	public void giveScore() {
		this.calculateJestValue();
		this.player.setScore(score);
	}
	
	public void resetScore() {
		this.score = 0;
	}
	
	//Calculating player score
		
		public void calculateJestValue() {
			this.addBlack();
			this.blackBonus();
			this.addHeart();
			this.removeDiamonds();
			this.addJoker();
		}
		
		
		public void addBlack() {
			Iterator<Card> i = this.player.getJest().getJestCards().iterator();
		 while(i.hasNext()) {
				Card c = i.next();
				if (c.suit == Suit.Clubs || c.suit == Suit.Spades) {
					if(c.kind == Kind.Ace && this.player.getJest().isAlone(Kind.Ace, c.suit)) {
						this.score += 5;
					}
					else {
						this.score += c.cardValue();
					}
				}
				
			}
		 }
		
		public void blackBonus() {
			Iterator<Card> i = this.player.getJest().getJestCards().iterator();
		 while(i.hasNext()) {
				Card c = i.next();
				if(c.suit == Suit.Clubs) {
					Iterator<Card> j = this.player.getJest().getJestCards().iterator();
					 while(i.hasNext()) {
							Card b = j.next();
							if(b.suit == Suit.Spades) {
								
								if(c.kind == b.kind) {
									this.score += 1;
								}
							}
					 }
				}
				else if(c.suit == Suit.Spades) {
					Iterator<Card> j = this.player.getJest().getJestCards().iterator();
					 while(i.hasNext()) {
							Card b = j.next();
							if(b.suit == Suit.Clubs) {
								
								if(c.kind == b.kind) {
									this.score += 1;
								}
							}
					 }	
				}
			}
		 }
		
		
		public void removeDiamonds() {
			Iterator<Card> i = this.player.getJest().getJestCards().iterator();
		 while(i.hasNext()) {
				Card c = i.next();
				if(c.suit == Suit.Diamonds) {
					if(c.kind == Kind.Ace &&
							this.player.getJest().isAlone(Kind.Ace, c.suit)) {
						this.score -= 5;
					}
					else {
					this.score -= c.cardValue();
					}
				}
				
			}
		 }

		public void addHeart() {
			Iterator<Card> i = this.player.getJest().getJestCards().iterator();
		 while(i.hasNext()) {
				Card c = i.next();
				if(c.suit == Suit.Hearts && this.player.getJest().hasJoker()) {
					if(c.kind == Kind.Ace &&
							this.player.getJest().isAlone(Kind.Ace, c.suit)) {
						this.score -= 5;
					}
					else {
					this.score -= c.cardValue();
					}
				}
				else if(c.suit == Suit.Hearts &&
						this.player.getJest().hasJoker() && this.player.getJest().hasAllSuit(Suit.Hearts)) {
					if(c.kind == Kind.Ace &&
							this.player.getJest().hasSuit(c.suit) == false) {
						this.score += 5;
					}
					else {
					this.score += c.cardValue();
					}
				}
				
			}
		 }
		
		
		public void addJoker() {
			if(this.player.getJest().hasJoker() && this.player.getJest().hasSuit(Suit.Hearts)==false) {
				this.score += 4;
			}
		}
		
}
