package fr.utt.jestcardgame.view;


import java.awt.EventQueue;

import javax.swing.JFrame;

import fr.utt.jestcardgame.controler.AbstractControler;
import fr.utt.jestcardgame.model.GameManager;
import fr.utt.jestcardgame.observer.Observable;
import fr.utt.jestcardgame.observer.Observer;
import fr.utt.jestcardgame.view.Window;

//Class principal de l'interface graphique héritant de la classe window qui correspond à une fenetre

public class GameView extends Window implements Observer{
	
	public GameView(AbstractControler gvc){
		super(gvc);
		
	}
	/* public static void main(String[] args){
		 EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						JFrame win = new Window();
						//win.pack();
						win.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		              
		  }*/

	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof GameManager) {
			switch (((GameManager)o).getUserChoice()) {
			 case 1: 
				 this.initOptions();
			 case 2: 
				 this.initRules();
			 case 3: 
				 System.exit(0);

			 }
		}
		
	}  
	
	
}
