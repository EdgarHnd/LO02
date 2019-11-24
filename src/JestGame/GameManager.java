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

    public boolean executeUserChoice(int userChoice) throws setupExeption {
        boolean startGame = false;
        switch (userChoice) {
            case 1:
                System.out.println("Open settings");
                try {
                    GameOptions.setup();
                }
                catch (setupExeption e){
                    System.out.println(e.getMessage());
                }
                //Récupérer la variable startGame de la méthode setup() pour déterminer si la partie est commencée
                if (GameOptions.setup()){
                    startGame = true;
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
        return startGame;
    }

    public void play(){
    	RoundsManager currentGame = new RoundsManager();
    	currentGame.firstRound();
    	currentGame.nextRound();
    }

	public static void main(String[] args) throws setupExeption {

        GameManager game = new GameManager();
		//Initialise all game components
		
		//Main menu
		System.out.println("Welcome to JestGame !");
		int userChoice = menu();
        game.executeUserChoice(userChoice);
        if (game.executeUserChoice(userChoice)){
            game.play();
        }
	}
}
