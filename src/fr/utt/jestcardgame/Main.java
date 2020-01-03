package fr.utt.jestcardgame;

import fr.utt.jestcardgame.controler.GameViewControler;
import fr.utt.jestcardgame.model.GameManager;
import fr.utt.jestcardgame.view.GameView;
import fr.utt.jestcardgame.view.setupException;


public class Main {

	    public static void main(String[] args) throws setupException, InterruptedException {
	       
	    	//Instantiate the game model
	    	GameManager gm = new GameManager();
	    	
	    	/*Thread t = new Thread(new Runnable() {
	    		public void run() {
	    			try {
						gm.mainMenu();
					} catch (setupException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    		}
	    	});*/
	    	
	    	
	    	//Create a controler linked to the model
	    	GameViewControler cCtrl = new GameViewControler(gm);
	    	GameView gv =  new GameView(cCtrl);
	    	
	    	//Our view can now visit the game model in order to update
			gm.addObserver(gv);
			//gm.setBoard(gv.getBoard());
			
	    	Thread t = new Thread(new Runnable() {
	    		public void run() {
	    			
	    	    	//Create a view the game in the Console linked to the ConsoleControler
	    	    	
	    			gv.setVisible(true);
	    			
	    		}
	    	});
	    	/*EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
	    	    	
	    	    	//Create a view of the game in the Console linked to the ConsoleControler
	    	    	GameView gv =  new GameView(cCtrl);
	    	    	
	    	    	//Our view can now visit the game model in order to update
	    			gm.addObserver(gv);
					gv.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});*/
	    	
	    	//TextView tv = new TextView(gm);
	    	//gm.addObserver(tv);
	    	t.start();
	    	gm.mainMenu();
	    	/*Thread t3 = new Thread(new Runnable() {
	    		public void run() {
	    			ConsoleUserInput c = new ConsoleUserInput();
	    		}
	    	});*/
	    	
	    	
	    	//t2.start();
	    	//t3.start();
	    	
	    	
	    	
	   
	    }
	    	

}
