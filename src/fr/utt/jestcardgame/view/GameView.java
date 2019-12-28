package fr.utt.jestcardgame.view;


import java.awt.EventQueue;

import javax.swing.JFrame;

import fr.utt.jestcardgame.observer.Observer;
import fr.utt.jestcardgame.view.Window;

//Class principal de l'interface graphique héritant de la classe window qui correspond à une fenetre

public class GameView extends Window implements Observer{
	
	public GameView(){
		
	}
	 public static void main(String[] args){
		 
		 EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						JFrame win = new GameView();
						win.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		              
		  }

	@Override
	public void update(String str) {
		// TODO Auto-generated method stub
		
	}  
	
	
}
