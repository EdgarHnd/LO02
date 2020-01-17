package fr.utt.jestcardgame.model;

import fr.utt.jestcardgame.observer.Observable;
import fr.utt.jestcardgame.visitor.Visitable;
import fr.utt.jestcardgame.visitor.Visitor;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * Determine the actors of the game : the players.
 * This class extends {@link Observable} and implements {@link Visitable}, in order to allow the others packages to see its characteristics during the game.
 * A <code>Player</code> instance is defined with a name, a number, and it has a hand of cards, a jest, a score and an offer.
 * A player will receive or not trophys with the method <code>receiveTrophy</code>, depending on his jest.
 *
 * @author Elina
 * @see Card
 */
public class Player extends Observable implements Visitable {

    ChooseStrategy strategy;

    protected int nb;
    protected String name;
    protected LinkedList<Card> hand;
    protected LinkedList<Card> offer;
    protected Jest jest;
    protected Score score;
    protected boolean hasPlayed = false;
    protected boolean isPlaying = false;
    protected boolean isPicking = false;
    protected boolean isNext = false;

    /**
     * Gets a boolean to know if the player has already played.
     *
     * @return hasPlayed
     */
    public boolean getHasPlayed() {
        return hasPlayed;
    }

    /**
     * Determine if a player has already played or not with a boolean specified in the parameters.
     *
     * @param hasPlayed Boolean value to determine if a player has already played or not
     */
    public void setHasPlayed(boolean hasPlayed) {
        this.hasPlayed = hasPlayed;
    }

    /**
     * Determine if a player is picking or not.
     *
     * @param bestOffer boolean value
     */
    public void setIsPicking(boolean bestOffer) {
        this.isPicking = bestOffer;
    }

    /**
     * Gets the name of the player.
     *
     * @return name of the player
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns a boolean value to know if the player is playing or not.
     *
     * @return A boolean value
     */
    public boolean isPlaying() {
        return isPlaying;
    }

    /**
     * Determine if the player is playing or not.
     *
     * @param isPlaying boolean value
     */
    public void setPlaying(boolean isPlaying) {
        this.isPlaying = isPlaying;
        this.hasChanged();
        this.notifyObservers();
    }

    /**
     * Gets the number of the player.
     *
     * @return number of the player
     */
    public int getNb() {
        return nb;
    }

    /**
     * Gets the hand, collection of cards, of the player.
     *
     * @return hand of the player
     */
    public LinkedList<Card> getHand() {
        return hand;
    }

    /**
     * Returns the offer of the player, which is a LinkedList of Cards.
     *
     * @return offer of the player
     * @see Card
     * @see LinkedList
     */
    public LinkedList<Card> getOffer() {
        return offer;
    }

    /**
     * Gets the score of the player.
     *
     * @return score of the player
     */
    public Score getScore() {
        this.setChanged();
        this.notifyObservers(score);
        return score;
    }

    /**
     * Gets the jest (type {@link Jest}) of the player.
     *
     * @return jest (type <code>Jest</code>)
     */
    public Jest getJest() {
        return jest;
    }

    //Constructor

    /**
     * Create a <code>Player</code> instance determine also its name, number in the list of players, and its strategy.
     * We also create its score by instantiate it with the class {@link Score}.
     *
     * @param name     Name of the player
     * @param nb       Number of the player
     * @param strategy Strategy associated with the player
     */
    public Player(String name, int nb, ChooseStrategy strategy) {
        this.name = name;
        this.nb = nb;

        this.hand = new LinkedList<>();
        this.jest = new Jest();
        this.offer = new LinkedList<>();
        this.strategy = strategy;
        this.score = new Score();
    }

    //Players methods

    /**
     * Enable a player to receive a specific card put in the parameters.
     * It also set the changes for its observers.
     *
     * @param c The card a player will receive.
     */
    public void receiveCard(Card c) {
        this.hand.add(c);
        this.setChanged();
        System.out.println("notify receivedCard");
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Returns the offered card by the player.
     *
     * @return the offered card
     */
    public Card offeredCard() {
        Card oCard = null;
        for (int j = 0; j < 2; j++) {
            if (this.hand.get(j).isHidden() == false) {
                oCard = this.hand.get(j);
            }
        }
        return oCard;
    }

    /**
     * Returns the hidden card of the player.
     *
     * @return the hidden card
     */
    public Card hiddenCard() {
        Card oCard = null;
        for (int j = 0; j < 2; j++) {
            if (this.hand.get(j).isHidden()) {
                oCard = this.hand.get(j);
            }
        }
        return oCard;
    }

    /**
     * Determine the player's offer with its hidden and offered card.
     */
    public void newOffer() {
        this.offer.add(this.offeredCard());
        this.offer.add(this.hiddenCard());
    }

    /**
     * Returns a boolean to know if the offer of the player is complete or not, according to the size of its offer.
     * It has to be composed by 2 cards.
     *
     * @return A boolean value
     */
    public boolean hasCompleteOffer() {
        if (this.offer.size() == 2) {
            return true;
        }
        return false;
    }

    /**
     * Enables a player to make an offer and to put it on the <code>GameBoard</code>.
     * It sets the player to <code>isPlaying(true)</code>, and call the <code>makeOfferStrategy()</code> method.
     */
    public void makeOffer() {
        this.setPlaying(true);
        strategy.makeOfferStrategy(this);
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.setPlaying(false);
    }

    /**
     * Enables a player to pick an offer from another player.
     * It sets the player to <code>isPlaying(true)</code>, and call the <code>pickOfferStrategy()</code> method.
     */
    public void pickOffer() {
        this.setPlaying(true);
        strategy.pickOfferStrategy(this);
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.setChanged();
        this.notifyObservers(this.jest);
        this.setPlaying(false);
    }

    /**
     * Allows visitors to visit this class.
     * @param v the visitor
     */
    @Override
    public void acceptVisitor(Visitor v) {
        v.visit(this);
    }

    /**
     * Sets and notifies the player's observers of the changes made, during a distribution of a trophy.
     */
    public void receiveTrophy() {
        this.setChanged();
        this.notifyObservers(this.jest);
    }

    /**
     * Sets and notifies the player's observers of the changes made, after we change the player's jest.
     */
    public void updateJest() {
        this.setChanged();
        this.notifyObservers(this.jest);
    }
}
