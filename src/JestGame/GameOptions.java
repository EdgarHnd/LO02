package JestGame;

import java.util.Scanner;

public class GameOptions extends GameManager {
	
	public static int optionMenu() {

        int selection;
        Scanner input = new Scanner(System.in);

        System.out.println("Choose from these choices");
        System.out.println("____________________\n");
        System.out.println("1 - Enter the number of players and their name");
        System.out.println("2 - Choose a variante");
        System.out.println("3 - Quit");

        selection = input.nextInt();
       	return selection;
        
    }
	
		
	public static void main(String []args) {
		
		boolean validChoice = false;
		int userChoice;
		
		// WHILE LOOP in order to choose once again in case of wrong type from the user
		while (validChoice == false) {
			userChoice = optionMenu();
			
			switch (userChoice) {
				case 1 :
					
					validChoice = true;
					// The user writes the number of players
					Scanner sc = new Scanner(System.in);
					System.out.println("How many players in total ?");
					int numberPlayer;
					numberPlayer = sc.nextInt();
					System.out.println(numberPlayer + " players will play the next game. \n");
					
					System.out.println("How many players are real for this game ?");
					int numberRealPlayer;
					numberRealPlayer = sc.nextInt();
					System.out.println(numberRealPlayer + " real player(s) will play the next game.");
					int numberVirtualPlayer = numberPlayer - numberRealPlayer;
					System.out.println("So there will be " + numberVirtualPlayer + " virtual player(s). \n");
					
					// The user writes each player's name
					System.out.println("Enter each real player's name :");
					for (int i = 1; i < numberRealPlayer + 1; i ++) {
						RealPlayer player = new RealPlayer(); //Instantiation for each RealPlayer
						System.out.println("Name Player " + i + " :");
						String newName = sc.nextLine();
						player.setName(newName);
						System.out.println(player.getName());
						}
					
					break;
				case 2 :
					validChoice = true;
					System.out.println("Choose a variante ");
					break;
					
				case 3 :
					validChoice = true;
					System.out.println("Return to Menu...");
					break;
					
				default :
					System.out.println("Invalid choice. Please choose again.");
					validChoice = false;
					break;
				}
		}
		
	}
}
