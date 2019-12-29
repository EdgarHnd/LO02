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
                try {
                    GameOptions.setup();
                }
                catch (setupException e){
                    System.out.println(e.getMessage());
                }
                break;
            case 2:
            	System.out.println("Show the rules");
            	this.mainMenu();
            case 3:
                System.exit(0);
                break;
            default:
                //Gestion d'exception à faire
        }
        this.setChanged();
        this.notifyObservers();
    }

    	public void mainMenu() throws setupException {
		//Main menu
		ConsoleGameView.display(ConsoleOutput.MainMenu);	    
        this.executeUserChoice(ConsoleUserInput.getInstance().nextInt());
        //Start a game
        this.play();
        
	}
		public int getUserChoice() {
			return userChoice;
		}
}