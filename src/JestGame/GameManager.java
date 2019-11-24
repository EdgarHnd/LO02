package JestGame;
import java.util.Scanner;

public class GameManager {

    //Menu selection method
    public static int menu() {

        int selection;
        Scanner input = new Scanner(System.in);
        System.out.println("Choose from these choices");
        System.out.println("____________________");
        System.out.println("1 - Start game");
        System.out.println("2 - Quit");
        selection = input.nextInt();
        return selection;
    }

    public void executeUserChoice(int userChoice) throws setupException {
        boolean startGame = false;
        switch (userChoice) {
            case 1:
                System.out.println("Open settings");
                try {
                    GameOptions.setup();
                    startGame = true;
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

    public void play(){
    	RoundsManager currentGame = new RoundsManager();
    	currentGame.firstRound();
    	//currentGame.nextRound();
    }

    
    
    
    
    
    
    //------------------------------------
    //The only execution code of the program
  
	public static void main(String[] args) throws setupException {

        GameManager game = new GameManager();
        
		//Main menu
		System.out.println("Welcome to JestGame !");
		int userChoice = menu();
        game.executeUserChoice(userChoice);
        
        //Start a game
        game.play();
        
	}
}