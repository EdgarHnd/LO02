package fr.utt.classes.model;

import fr.utt.classes.visitor.Visitor;

import java.util.Iterator;

/**
 * Class which calculate the score of each {@link Player}, according to its jest.
 * It implements {@link Visitor} Interface.
 *
 * @author Elina
 * @see Player
 */
public class Score implements Visitor {

    private Player player;
    private int score;

	/**
	 * Creating a <code>Score</code> instance sets the player's score at 0.
	 */
	public Score() {
        this.score = 0;
    }

	/**
	 * Visits method from the {@link Visitor} interface.
     *
	 * @param p Player visited
	 */
	@Override
    public void visit(Player p) {
        System.out.println("Visiting : " + p.getName());
        this.player = p;
    }

    /**
     * Calculate the score of the player concerned.
     */
    public void giveScore() {
        this.calculateJestValue();
    }

    /**
     * Sets the score of the player concerned to 0.
     */
    public void resetScore() {
        this.score = 0;
    }

    /**
     * Gets the player's score.
     *
     * @return the player's score.
     */
    public int getScore() {
        return this.score;
    }

    //Calculating player score

    /**
     * Calculate the jest value of the player concerned.
     */
    public void calculateJestValue() {
        this.addBlack();
        this.blackBonus();
        this.addHeart();
        this.removeDiamonds();
        this.addJoker();
    }

    /**
     * Adds a condition score to the jest of the player concerned.
     */
    public void addBlack() {
        Iterator<Card> i = this.player.getJest().getJestCards().iterator();
        while (i.hasNext()) {
            Card c = i.next();
            if (c.suit == Suit.Clubs || c.suit == Suit.Spades) {
                if (c.kind == Kind.Ace && this.player.getJest().isAlone(Kind.Ace, c.suit)) {
                    this.score += 5;
                } else {
                    this.score += c.cardValue();
                }
            }
        }
    }

    /**
     * Adds a condition score to the jest of the player concerned.
     */
    public void blackBonus() {
        Iterator<Card> i = this.player.getJest().getJestCards().iterator();
        while (i.hasNext()) {
            Card c = i.next();
            if (c.suit == Suit.Clubs) {
                Iterator<Card> j = this.player.getJest().getJestCards().iterator();
                while (j.hasNext()) {
                    Card b = j.next();
                    if (b.suit == Suit.Spades) {

                        if (c.kind == b.kind) {
                            this.score += 1;
                        }
                    }
                }
            } else if (c.suit == Suit.Spades) {
                Iterator<Card> j = this.player.getJest().getJestCards().iterator();
                while (j.hasNext()) {
                    Card b = j.next();
                    if (b.suit == Suit.Clubs) {

                        if (c.kind == b.kind) {
                            this.score += 1;
                        }
                    }
                }
            }
        }
    }

    /**
     * Adds a condition score to the jest of the player concerned.
     */
    public void removeDiamonds() {
        Iterator<Card> i = this.player.getJest().getJestCards().iterator();
        while (i.hasNext()) {
            Card c = i.next();
            if (c.suit == Suit.Diamonds) {
                if (c.kind == Kind.Ace &&
                        this.player.getJest().isAlone(Kind.Ace, c.suit)) {
                    this.score -= 5;
                } else {
                    this.score -= c.cardValue();
                }
            }

        }
    }

    /**
     * Adds a condition score to the jest of the player concerned.
     * Also, the variant number 2 has an effect here.
     */
    public void addHeart() {
        Iterator<Card> i = this.player.getJest().getJestCards().iterator();
        while (i.hasNext()) {
            Card c = i.next();
            if (c.suit == Suit.Hearts && this.player.getJest().hasJoker()) {
                if (c.kind == Kind.Ace &&
                        this.player.getJest().isAlone(Kind.Ace, c.suit)) {
                    if (OptionsData.getVariant() == 2) {
                        this.score += 5;
                    } else {
                        this.score -= 5;
                    }
                } else {
                    if (OptionsData.getVariant() == 2) {
                        this.score += c.cardValue();
                    } else {
                        this.score -= c.cardValue();
                    }
                }
            } else if (c.suit == Suit.Hearts &&
                    this.player.getJest().hasJoker() && this.player.getJest().hasAllSuit(Suit.Hearts)) {
                if (c.kind == Kind.Ace &&
                        this.player.getJest().hasSuit(c.suit) == false) {
                    this.score += 5;
                } else {
                    this.score += c.cardValue();
                }
            }

        }
    }

    /**
     * Adds a condition score to the jest of the player concerned.
     * Also, the variant number 1 has an effect here.
     */
    public void addJoker() {
        if (this.player.getJest().hasJoker() && this.player.getJest().hasSuit(Suit.Hearts) == false) {
            if (OptionsData.getVariant() == 1) {
                this.score += 10;
            } else {
                this.score += 4;
            }
        }
    }
}
