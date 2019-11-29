package fr.utt.jestcardgame;

import fr.utt.jestcardgame.controler.*;
import fr.utt.jestcardgame.model.*;
import fr.utt.jestcardgame.view.*;

public class Main {

	    public static void main(String[] args) throws setupException {
	       
	    	//Instantiate the game model
	    	AbstractGameManager gm = new GameManager();
	    	
	    	//Create a controler linked to the model
	    	//AbstractControler cCtrl = new ConsoleControler(gm);
	    	
	    	//Create a view the game in the Console linked to the ConsoleControler
	    	//GameView cv =  new ConsoleGameView(cCtrl);
	    	
	    	//Our view can now visit the game model in order to update
	    	//gm.addVisitor(cv);
	    	
	    
	    	//cv.display();
	    	gm.mainMenu();
	    	
	   
	    }
	    	

}
