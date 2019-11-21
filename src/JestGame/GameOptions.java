package JestGame;

import java.util.HashMap;
import java.util.Scanner;

public class GameOptions {
	
	//variables pouvant être utiles
	protected int nbPlayer;
	protected int nbRealPlayer;
	protected int nbVirtualPlayer;
	protected int variant;
	
	//getters and setters
	public int getNbPlayer() {
		return nbPlayer;
	}

	public void setNbPlayer(int nbPlayer) {
		this.nbPlayer = nbPlayer;
	}
	
	//Menu des options
	public static int optionMenu() {

        int selection;
        Scanner input = new Scanner(System.in);

        System.out.println("Choose from these choices");
        System.out.println("____________________\n");
        System.out.println("1 - Enter the number of players and their names");
        System.out.println("2 - Choose a variant");
        System.out.println("3 - Quit");

        selection = input.nextInt();
       	return selection;
        
    }
	
	//Interface qui gère le menu
	public interface IAction{
		public void execute();
	}
	
	public static class SetPlayer implements IAction{
		
		public void execute() {
			
			boolean correctChoice = false;
			while(!correctChoice) {
				
				Scanner sc = new Scanner(System.in);
				System.out.println("How many players for your game ? You have the choice between 3 or 4.");
				int nbPlayer;
				nbPlayer = sc.nextInt();
						
				switch(nbPlayer) {
				
					case 3 :
						
						correctChoice = true;
						System.out.println("3 players will play the next game. \n");
						
						boolean correctNumber = false;
						while(!correctNumber) {
							
							System.out.println("How many players are real for this game ?");
							int nbRealPlayer3;
							nbRealPlayer3 = sc.nextInt();
							
								if (nbRealPlayer3 < 4 && nbRealPlayer3 > 0) {
									correctNumber = true;
									System.out.println(nbRealPlayer3 + " real player(s) will play the next game.");
									int numberVirtualPlayer = nbPlayer - nbRealPlayer3;
									System.out.println("So there will be " + numberVirtualPlayer + " virtual player(s).");
									
									System.out.println("Enter each real player's name :");
									for (int i = 1; i < nbRealPlayer3 + 1; i ++) {
										System.out.println("Name of Player " + i + " :");
										String newName = sc.nextLine();
										RealPlayer player = new RealPlayer(newName); //Instantiation for each RealPlayer
										System.out.println(player.getName());
									}
								}
								
								else {
									System.out.println("Your value is not correct... Please enter a number between 1 and 3.");
								}
							}
						
					break;
						
					case 4 :
						
						correctChoice = true;
						System.out.println("4 players will play the next game. \n");
						
						boolean correctNb = false;
						while(!correctNb) {
							System.out.println("How many players are real for this game ?");
							int nbRealPlayer4;
							nbRealPlayer4 = sc.nextInt();
							
							
								if (nbRealPlayer4 < 5 && nbRealPlayer4 > 0) {
									correctNb = true;
									System.out.println(nbRealPlayer4 + " real player(s) will play the next game.");
									int nbVirtualPlayer = nbPlayer - nbRealPlayer4;
									System.out.println("So there will be " + nbVirtualPlayer + " virtual player(s).");
									
									System.out.println("Enter each real player's name :");
									for (int i = 1; i < nbRealPlayer4 + 1; i ++) {
										System.out.println("Name of Player " + i + " :");
										String newName = sc.nextLine();
										RealPlayer player = new RealPlayer(newName); //Instantiation for each RealPlayer
										System.out.println(player.getName());
									}
									
								}
								else {
									System.out.println("Your value is not correct... Please enter a number between 1 and 4.");
								}
							}
					break;
						
					default :
						System.out.println("Your value is not correct ! You have only two choices : 3 or 4 players. It's not hard, trust me.");
				}
			}
		}
	}
	
	
	//Child class of the menu interface
	public static class ChooseVariant implements IAction{
		public void execute() {
			System.out.println("Choose your variant");
		}
	}
	
	public static class QuitOptions implements IAction{
		public void execute() {
			System.out.println("Quit option menu...");
		
		}
	}
	
	
	public static void setup() {
						
		HashMap<Integer, IAction> playerAction = new HashMap<Integer, IAction>();
		playerAction.put(1, new SetPlayer());
		playerAction.put(2, new ChooseVariant());
		playerAction.put(3, new QuitOptions());
		
		GameOptions gameOp = new GameOptions();
		int playerChoice = gameOp.optionMenu();
		playerAction.get(playerChoice).execute();
		}
		
	}

