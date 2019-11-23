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

    public void executeUserChoice(int userChoice){
        switch (userChoice) {
            case 1:
                System.out.println("Open settings");
                try {
                    GameOptions.setup();
                }
                catch (NbPlayerException e){
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
	public static void main(String[] args) throws NbPlayerException {

        GameManager game = new GameManager();
		//Initialise all game components
		
		//Main menu
		System.out.println("Welcome to JestGame !");
		int userChoice = menu();
        game.executeUserChoice(userChoice);

	}
}
