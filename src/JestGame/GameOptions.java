package JestGame;

import java.util.Scanner;

/**
 * Class which allows the user to setup different parameters of the game, such as :
 * The number of players, and more precisely the number of real and virtual players
 * The name of each player
 * The user can choose one variant
 *
 * @author Elina
 * @version 1.0
 */
public class GameOptions {

	//variables pouvant être utiles
	protected static int nbPlayer;
	protected static int nbRealPlayer;
	protected static int nbVirtualPlayer;
	protected static int variant;
	
	//Des noms pour tester
	protected static String[] playersNames = {"Edgar","Elina"};
	

	public static int getNbPlayer() throws setupExeption {
			return nbPlayer;
	}

	public int setNbPlayer() throws setupExeption {
			boolean correctChoice = false;
			while (!correctChoice) {
				Scanner sc = new Scanner(System.in);
				System.out.println("How many players for your game ? (You have the choice between 3 or 4.)");
				int nbPlayer = sc.nextInt();
				this.nbPlayer = nbPlayer;
				if (nbPlayer == 3 || nbPlayer == 4) {
					correctChoice = true;
					System.out.println(nbPlayer + " players will play the next game. \n");
				} else {
					//Gestion d'exception à appronfondir : faire en sorte que l'utilisateur recommence, au lieu d'exit le programme
					throw new setupExeption();
				}
			}
			return nbPlayer;
	}

	public int setNbRealPlayer( int nbPlayer) throws setupExeption {
			this.nbPlayer = nbPlayer;
			boolean correctNumber = false;
			while (!correctNumber) {
				Scanner sc = new Scanner(System.in);
				System.out.println("How many players are real for this game ?");
				int nbRealPlayer = sc.nextInt();
				this.nbRealPlayer = nbRealPlayer;
				if (nbRealPlayer == 0 || nbRealPlayer > nbPlayer) {
					//Gestion d'exception à appronfondir : faire en sorte que l'utilisateur recommence, au lieu d'exit le programme
					throw new setupExeption();
				} else {
					correctNumber = true;
					System.out.println(nbRealPlayer + " REAL PLAYER(S)");
					int nbVirtualPlayer = nbPlayer - nbRealPlayer;
					System.out.println(nbVirtualPlayer + " VIRTUAL PLAYER(S)");
				}
			}
			return nbRealPlayer;
	}

	public int setNbVirtualPlayer(int nbPlayer, int nbRealPlayer) throws setupExeption {
			this.nbPlayer = nbPlayer;
			this.nbRealPlayer = nbRealPlayer;
			int nbVirtualPlayer = nbPlayer - nbRealPlayer;
			return nbVirtualPlayer;
	}

	public static int chooseVariant () {
			System.out.println("We have different variants for your game. Make your choice ! :)");
			System.out.println("1- Variant 1");
			System.out.println("2- Variant 2");
			System.out.println("3- Variant 3");
			Scanner sc = new Scanner(System.in);
			variant = sc.nextInt();
			return variant;
	}
		//Menu des options
	public static int selectionOptionMenu () {
			Scanner input = new Scanner(System.in);
			System.out.println("1 - Choose a variant");
			System.out.println("2 - Play !");
			int selection = input.nextInt();
			return selection;
	}

	public static boolean setup () throws setupExeption {
		GameOptions gameOp = new GameOptions();
		System.out.println("First, enter the number of players !");
		nbPlayer = gameOp.setNbPlayer();
		nbRealPlayer = gameOp.setNbRealPlayer(nbPlayer);
		nbVirtualPlayer = gameOp.setNbVirtualPlayer(nbPlayer, nbRealPlayer);
		System.out.println("Alright, now you can choose from these choices : ");

		boolean startGame = false;
		while (!startGame) {
			int playerChoice = gameOp.selectionOptionMenu();
			switch (playerChoice) {
				case 1:
					System.out.println("Choose variant");
					gameOp.chooseVariant();
					System.out.println("You will play with the variant : " + variant + "\n");
					break;
				case 2:
					startGame = true;
					System.out.println("Start game...");
					//gameOp.startGame();
					break;
				default:
					throw new setupExeption();
					//Gestion d'exceptions à faire
			}
		}
		return startGame;
	}
}
