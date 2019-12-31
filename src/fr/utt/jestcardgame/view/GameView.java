package fr.utt.jestcardgame.view;

import fr.utt.jestcardgame.controler.GameViewControler;
import fr.utt.jestcardgame.model.GameManager;
import fr.utt.jestcardgame.observer.Observable;
import fr.utt.jestcardgame.observer.Observer;
import fr.utt.jestcardgame.view.Window;

//Class principal de l'interface graphique héritant de la classe window qui correspond à une fenetre

public class GameView extends Window implements Observer{
	
	public GameView(GameViewControler gvc){
		super(gvc);
		
	}

	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof GameManager) {
			switch (((GameManager)o).getUserChoice()) {
			case 0:
				System.out.println("back");
				this.initWelcome();
				break;
			// case 1: 
			//	 this.initOptions();
			 case 2: 
			     System.out.println("initrule");
				 this.initRules();
				 break;
			 case 3:
				 System.exit(0);
				 break;
			 }
		}
		
	}
	
}
