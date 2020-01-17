package fr.utt.jestcardgame.model;

/**
 * This class define all the Cards which constitute the game.
 * It describes the kind, value, suit and trophy associated with one card.
 * A <code>Card</code> instance is created during the beginning of the game, with these attributes.
 *
 * @author Elina
 */
public class Card {	
	
	protected Kind kind;
	protected Suit suit;
	protected Trophys trophy;
	protected String imagePath;
	protected boolean hidden = true;

	/**
	 * Method which determine if an object of <code>Card</code> is hidden during the game.
	 * A hidden card is an important parameter of the game, in order to connect each action with the right card.
	 * Then, it allows the players to interact with the graphic interface easily.
	 *
	 * @return hidden
	 */
	public boolean isHidden() {
		return hidden;
	}

	/**
	 * Sets an object of <code>Card</code> hidden or not, depending on the boolean value entered in the parameters.
	 *
	 * @param hidden A boolean value which describe if the card is hidden or not.
	 */
	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	/**
	 * Creates a <code>Card</code> instance with the specified kind, suit, trophy and for its GUI representation, an image path inside the folder associated.
	 *
	 * @param kind The Kind of this card, which is an element from {@link fr.utt.jestcardgame.model.Kind}.
	 * @param suit The Suit of this card, which is an element from {@link fr.utt.jestcardgame.model.Suit}.
	 * @param trophys The Throphy of this card, which is an element from {@link fr.utt.jestcardgame.model.Trophys}.
	 * @param imgPath The path of its GUI representation, placed inside a intern folder.
	 */
	public Card(Kind kind, Suit suit, Trophys trophys, String imgPath) {
		this.kind = kind;
		this.suit = suit;
		this.trophy = trophys;
		this.imagePath = imgPath;
	}

	/**
	 * Returns the value of the <code>Card</code> instance. It appears as an int, to correspond with the number written on the card.
	 *
	 * @return value
	 */
	public int cardValue() {
		int value = 0;
		switch(this.kind) {
		case Ace:
			value = 1;
			 break;
		case One:
			value = 1;
			 break;
		case Two:
			value = 2;
			 break;
		case Three:
			value = 3;
			 break;
		case Four:
			value = 4;
			 break;
		case Joker:
			value = 0;
			 break;
		case Default:
			value = -1;
			break;
		}
		return value;
	}

	/**
	 * Returns the Suit value as an int, if 2 or more players have equally
	 * valuable face-up cards in their offers. It's a way to break the ties.
	 *
	 * @return tieValue
	 */
	public int cardTiesValue() {
		int tieValue = 0;
		switch(this.suit) {
		case Spades:
			tieValue = 4;
			 break;
		case Clubs:
			tieValue = 3;
			 break;
		case Diamonds:
			tieValue = 2;
			 break;
		case Hearts:
			tieValue = 1;
			 break;
		case None:
			tieValue = 0;
			 break;
		}
		return tieValue;
	}

	/**
	 * Gets the path of the <code>Card</code> instance image.
	 * @return this.imagePath
	 */
	public String getImagePath() {
		return this.imagePath;
	}

	/**
	 * Returns the characteristics of a <code>Card</code> instance, such as its kind, suit and trophy.
	 * @return this.trophy.toString()
	 */
	public String toString() {
		return this.kind.toString() + " " +this.suit.toString() + " " + 
	this.trophy.toString();
	}
	
}
