package fr.utt.jestcardgame.controler;

import fr.utt.jestcardgame.model.GameManager;

/**
 * This class is where all the controllers inherit from.
 * It'is only made in case you want to add another controller but it's not used in the code yet.
 * We left it here to make the code easier to improove.
 *
 * @author Edgar
 */
public abstract class AbstractControler {
	
	protected GameManager gm;
	/**
	 * Create an <code>AbstractControler</code> object
	 *  @param gm GameManager the name of the main class of our model{@link fr.utt.jestcardgame.model.GameManager}
	 */
	public AbstractControler(GameManager gm) {
		this.gm = gm;
	}

	
}
