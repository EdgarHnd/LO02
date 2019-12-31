package fr.utt.jestcardgame.model;


import fr.utt.jestcardgame.observer.Observable;
import fr.utt.jestcardgame.view.ConsoleGameView;
import fr.utt.jestcardgame.view.ConsoleOutput;
import fr.utt.jestcardgame.view.ConsoleUserInput;
import fr.utt.jestcardgame.view.setupException;


public class GameManager extends Observable{

	private static GameManager gm= null;
	private int userChoice;
	
	public static GameManager getInstance(){
		
		if(gm == null){
			gm = new GameManager();
		}
		
		return gm;
	}
	public void play(){
		RoundsManager currentGame = RoundsManager.getInstance();
    	currentGame.firstRound();
    	currentGame.nextRounds();
    	currentGame.giveTrophy();
    	currentGame.finalScore();
    }    

    public void executeUserChoice(int userChoice) throws setupException {
    	this.userChoice = userChoice;
        switch (userChoice) {
            case 1:
            	this.setChanged();
            	this.notifyObservers();
                try {
                    GameOptions.setup();
                }
                catch (setupException e){
                    System.out.println(e.getMessage());
                }
                break;
            case 2:
            	this.setChanged();
                this.notifyObservers();
            	this.rules();
                System.out.println("notify");
            	this.mainMenu();
            	break;
            case 3:
            	this.setChanged();
            	this.notifyObservers();
                System.exit(0);
                break;
                //Gestion d'exception à faire
        }
    }

    	public void mainMenu() throws setupException {
		//Main menu
    	this.userChoice = 0;
    	this.setChanged();
    	this.notifyObservers();
		ConsoleGameView.display(ConsoleOutput.MainMenu);
        this.executeUserChoice(ConsoleUserInput.getInstance().nextInt());
       
        //Start a game
        this.play();
        
	}
    	public void rules() throws setupException {
    		System.out.println("Showing rules, press 1 to get back to the menu");
    		int input = ConsoleUserInput.getInstance().nextInt();
    		if(input == 1) {
    			this.mainMenu();
    		}
    		
    		
    	}
		public int getUserChoice() {
			return userChoice;
		}
}