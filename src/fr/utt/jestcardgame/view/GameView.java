package fr.utt.jestcardgame.view;


import javax.swing.JFrame;

import fr.utt.jestcardgame.observer.Observer;
import fr.utt.jestcardgame.view.Window;

//Class principal de l'interface graphique héritant de la classe window qui correspond à une fenetre

public class GameView extends Window implements Observer{
	
	public GameView(){
		
	}
	 public static void main(String[] args){

		    JFrame win = new GameView();            
		  }

	@Override
	public void update(String str) {
		// TODO Auto-generated method stub
		
	}  
	
	
}
