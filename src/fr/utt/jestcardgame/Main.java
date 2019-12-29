package fr.utt.jestcardgame;

import fr.utt.jestcardgame.controler.AbstractControler;
import fr.utt.jestcardgame.controler.GameViewControler;
import fr.utt.jestcardgame.model.GameManager;
import fr.utt.jestcardgame.view.GameView;
import fr.utt.jestcardgame.view.setupException;

public class Main {

	    public static void main(String[] args) throws setupException {
	       
	    	//Instantiate the game model
	    	GameManager gm = new GameManager();
	    	
	    	//Create a controler linked to the model
	    	AbstractControler cCtrl = new GameViewControler(gm);
	    	
	    	//Create a view the game in the Console linked to the ConsoleControler
	    	GameView cv =  new GameView(cCtrl);
	    	
	    	//Our view can now visit the game model in order to update
	    	gm.addObserver(cv);
	    	cv.setVisible(true);
	    	

	    	//gm.mainMenu();
	    	
	   
	    }
	    	

}
