package fr.utt.jestcardgame.controler;

import fr.utt.jestcardgame.model.GameManager;
import fr.utt.jestcardgame.view.setupException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameViewControler extends AbstractControler {
	
	public GameViewControler(GameManager gm) {
		super(gm);
		
	}
	/*public void start() {
		try {
			this.gm.mainMenu();
		} catch (setupException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	/*public void rules() {
		try {
			this.gm.executeUserChoice(2);
		} catch (setupException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	public ActionListener getRules() {
		return rules;
	}

	public ActionListener getQuit() {
		return quit;
	}

	public ActionListener getBack() {
		return back;
	}

	private ActionListener rules = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			Thread rules = new Thread(new Runnable() {
				public void run() {
					try {
						gm.executeUserChoice(2);
					} catch (setupException e) {
						e.printStackTrace();
					}
					System.out.println("button rules pressed");
				}
			}); 
			rules.start();
		}
		
	};
	
	private ActionListener back = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			Thread back = new Thread(new Runnable() {
				public void run() {
					try {
						gm.mainMenu();
					} catch (setupException e) {
						e.printStackTrace();
					}
					System.out.println("button rules pressed");
				}	
			});
			back.start();
		}
		};
	
	private ActionListener quit = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	};
	
}
