package fr.utt.jestcardgame.model;


import fr.utt.jestcardgame.view.ConsoleGameView;
import fr.utt.jestcardgame.view.ConsoleOutput;
import fr.utt.jestcardgame.view.ConsoleUserInput;
import fr.utt.jestcardgame.view.setupException;


public class GameManager extends AbstractGameManager{

	private static GameManager gm= null;
	
	public static GameManager getInstance(){
		
		if(gm == null){
			gm = new GameManager();
		}
		
		return gm;
	}
	@Override
    public void play(){
		RoundsManager currentGame = RoundsManager.getInstance();
    	currentGame.firstRound();
    	//currentGame.nextRound();
    	//currentGame.giveTrophy();
    	//currentGame.finalScore();
    	//currentGame.showRanking();
    }    

    public void executeUserChoice(int userChoice) throws setupException {
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
                System.exit(0);
                break;
            default:
                //Gestion d'exception à faire
        }
    }

    	public void mainMenu() throws setupException {
		//Main menu
		ConsoleGameView.display(ConsoleOutput.MainMenu);	    
        executeUserChoice(ConsoleUserInput.getInstance().nextInt());
        //Start a game
        play();
        
	}
}