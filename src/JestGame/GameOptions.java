package JestGame;

import java.util.Scanner;

public class GameOptions {
	
	public static int optionMenu() {

        int selection;
        Scanner input = new Scanner(System.in);

        System.out.println("Choose from these choices");
        System.out.println("____________________\n");
        System.out.println("1 - Enter the number of players");
        System.out.println("2 - Choose a variante");
        System.out.println("3 - Quit");

        selection = input.nextInt();
       	return selection;
        
    }
	
	public static void main(String []args) {
		int userChoice;
		userChoice = optionMenu();
		
		switch (userChoice) {
		case 1 : //PENSER A RENTRER LEUR NOM AUSSI
			
			Scanner sc = new Scanner(System.in);
			System.out.println("How many player ?");
			int numberPlayers;
			numberPlayers = sc.nextInt();
			System.out.println(numberPlayers + " players will play the next game. \n");
			System.out.println("Good luck !");
			break;
			
		case 2 :
			System.out.println("Choose a variante ");
		}
	}
}
