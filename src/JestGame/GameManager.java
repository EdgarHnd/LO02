package JestGame;
import java.util.Scanner;

public class GameManager {

	public static void main(String[] args) {

		
		//Main menu
		System.out.println("Welcome to JestGame !");
		
		
		int userChoice;

        userChoice = menu();
        
        switch (userChoice) {
        case 1:
        	System.out.println("Open settings");
        	break;
        case 2:
        	System.out.println("Quiting..");
        	System.exit(0);
        	break;
        
        }

	}
	
		//Menu selection method
	
	public static int menu() {

        int selection;
        Scanner input = new Scanner(System.in);

        

        System.out.println("Choose from these choices");
        System.out.println("-------------------------\n");
        System.out.println("1 - Start game");
        System.out.println("2 - Quit");
        

        selection = input.nextInt();
        input.close();
        return selection;    
    }
}
