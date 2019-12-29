package fr.utt.jestcardgame.controler;

import fr.utt.jestcardgame.model.GameManager;

public abstract class AbstractControler {
	
	protected GameManager gm;
	
	public AbstractControler(GameManager gm) {
		this.gm = gm;
	}

	
}
