package fr.utt.jestcardgame.controler;

import fr.utt.jestcardgame.model.GameManager;
import fr.utt.jestcardgame.view.setupException;

public class GameViewControler extends AbstractControler{
	
	public GameViewControler(GameManager gm) {
		super(gm);
		
	}
	
	public void rules() {
		try {
			this.gm.executeUserChoice(2);
		} catch (setupException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
