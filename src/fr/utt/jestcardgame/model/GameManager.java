package fr.utt.jestcardgame.model;


import fr.utt.jestcardgame.controler.setupException;
import fr.utt.jestcardgame.view.ConsoleUserInput;


public class GameManager extends AbstractGameManager{
	


	
	/*@Override
	public void mainMenu() {
		StringBuffer sb = new StringBuffer();
		sb.append("Wellcome to the JestGame");
		sb.append("\nChoose from these choices");
		sb.append("\n____________________");
		sb.append("\n1 - Start game");
		sb.append("\n2 - Quit");
		notifyVisitor(sb.toString());
		
		
		
	}*/
    
	@Override
    public void play(){
    	RoundsManager currentGame = new RoundsManager();
    	currentGame.firstRound();
    	//currentGame.nextRound();
    	//currentGame.giveTrophy();
    	//currentGame.finalScore();
    	//currentGame.showRanking();
    }

    
    
    
    //Menu selection method
    public static int menu() {

      //  int selection;
      //  Scanner input = new Scanner(System.in);
        System.out.println("Choose from these choices");
        System.out.println("____________________");
        System.out.println("1 - Start game");
        System.out.println("2 - Quit");
     //   selection = input.nextInt();
        ConsoleUserInput input = new ConsoleUserInput();
        return input.nextInt();
    }

    public void executeUserChoice(int userChoice) throws setupException {
        switch (userChoice) {
            case 1:
                System.out.println("Open settings");
                try {
                    GameOptions.setup();
                }
                catch (setupException e){
                    System.out.println(e.getMessage());
                }
                break;
            case 2:
                System.out.println("Quiting..");
                System.exit(0);
                break;
            default:
                //Gestion d'exception à faire
                System.out.println("Une exception est levée");
        }
    }
    
    
    
    	public void mainMenu() throws setupException {
		//Main menu
		System.out.println("Welcome to JestGame !");
		int userChoice = menu();
		
        executeUserChoice(userChoice);
        
        //Start a game
        play();
        
	}

	

	
}