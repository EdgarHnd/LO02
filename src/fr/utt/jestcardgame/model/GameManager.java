package fr.utt.jestcardgame.model;


import fr.utt.jestcardgame.observer.Observable;
import fr.utt.jestcardgame.view.ConsoleGameView;
import fr.utt.jestcardgame.view.ConsoleOutput;
import fr.utt.jestcardgame.view.ConsoleUserInput;
import fr.utt.jestcardgame.view.setupException;


public class GameManager extends Observable{

	//private static GameManager gm= null;
	private String gameState;
	private int userChoice;
	
	/*public static GameManager getInstance(){
		
		if(gm == null){
			gm = new GameManager();
		}
		
		return gm;
	}*/
	
	public GameManager(){
		
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
                	this.options();
                    //GameOptions.setup();
                }
                catch (setupException e){
                    System.out.println(e.getMessage());
                }
                break;
            case 2:
            	this.rules();
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
    	System.out.println("Game launched");
    	this.gameState = "mainMenu";
    	this.setChanged();
    	this.notifyObservers();
		ConsoleGameView.display(ConsoleOutput.MainMenu);
        this.executeUserChoice(ConsoleUserInput.getInstance().nextInt());
       
    	//this.executeUserChoice(TextView.nextString());
        
    	//Start a game
        this.play();
        
	}

	public void rules() throws setupException {
		System.out.println("Rules, press 1 to get back to the menu");
		this.gameState = "rules";
		this.setChanged();
		this.notifyObservers();
		int input = ConsoleUserInput.getInstance().nextInt();
		if(input == 1) {
			this.mainMenu();
		}
	}

	public void options() throws setupException {
		System.out.println("Options \n Press 1 to get back to the menu");
		System.out.println("Or press 2 to play !");
		this.gameState = "options";
		this.setChanged();
		this.notifyObservers();
		int input = ConsoleUserInput.getInstance().nextInt();
		if(input == 1) {
			this.mainMenu();
		} else if (input == 2) {
			this.startGame();
		}
	}

	public void startGame() {
		System.out.println("New Game !");
		this.setChanged();
		this.notifyObservers();
	}

	public int getUserChoice() {
			return userChoice;
		}

	public String getGameState() {
		return gameState;
	}
}