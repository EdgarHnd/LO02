package jestGame.Model;

import jestGame.UserInput;

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

	private int nbPlayer;
	private int nbRealPlayer;
	private int nbVirtualPlayer;
	private static int variant;
	
	//Des noms pour tester
	private static String[] playersNames = {"Edgar","Elina","Patrick","Bernard"};
	
	//Getters
	public int getNbPlayer(){
			return nbPlayer;
	}

	public static String getPlayersNames(int i) {
		return playersNames[i];
	}

	public int getNbRealPlayer() {
		return nbRealPlayer;
	}

	public int getNbVirtualPlayer() {
		return nbVirtualPlayer;
	}

	public int getVariant() {
		return variant;
	}

	public int setNbPlayer() throws setupException {
			boolean correctChoice = false;
			while (!correctChoice) {
				System.out.println("How many players for your game ? (You have the choice between 3 or 4.)");
				this.nbPlayer = UserInput.getInstance().nextInt();
				if (nbPlayer == 3 || nbPlayer == 4) {
					correctChoice = true;
					System.out.println(nbPlayer + " players will play the next game. \n");
				} else {
					//Gestion d'exception à appronfondir : faire en sorte que l'utilisateur recommence, au lieu d'exit le programme
					throw new setupException();
				}
			}
			return nbPlayer;
	}

	

	public int setNbRealPlayer(int nbPlayer) throws setupException {
			this.nbPlayer = nbPlayer;
			boolean correctNumber = false;
			while (!correctNumber) {
				System.out.println("How many players are real for this game ?");
				this.nbRealPlayer = UserInput.getInstance().nextInt();
				if (nbRealPlayer == 0 || nbRealPlayer > nbPlayer) {
					throw new setupException();
				} else {
					correctNumber = true;
					System.out.println(nbRealPlayer + " REAL PLAYER(S)");
					int nbVirtualPlayer = nbPlayer - nbRealPlayer;
					System.out.println(nbVirtualPlayer + " VIRTUAL PLAYER(S)");
				}
			}
			return nbRealPlayer;
	}


	public int setNbVirtualPlayer(int nbPlayer, int nbRealPlayer) throws setupException {
		//@TODO Séparer en deux fonctions :)
			this.nbPlayer = nbPlayer;
			this.nbRealPlayer = nbRealPlayer;
			this.nbVirtualPlayer = nbPlayer - nbRealPlayer;
			return nbVirtualPlayer;
	}

	public static int chooseVariant () {
			System.out.println("We have different variants for your game. Make your choice ! :)");
			System.out.println("1- Variant 1");
			System.out.println("2- Variant 2");
			System.out.println("3- Variant 3");
			variant = UserInput.getInstance().nextInt();
			return variant;
	}

	/*public String[] setNamePlayer(int nbPlayer){
		System.out.println("Please put a name on you, we don't want to call you by a number ;)");
		String [] playerName = new String[0];
		for (int j = 0; j < nbRealPlayer; j++){
			System.out.println("What's your name, player " + j + "?");
			Scanner sc = new Scanner(System.in);
			String name = sc.nextLine();
			playerName[j] = name;
		}
		return playerName;
	}*/

		//Menu des options
	public static int selectionOptionMenu () {
			System.out.println("1 - Play !");
			System.out.println("2 - Choose a variant");
			int selection = UserInput.getInstance().nextInt();
			return selection;
	}

	public void setup() throws setupException {
		System.out.println("First, enter the number of players !");
		nbPlayer = setNbPlayer();
		nbRealPlayer = setNbRealPlayer(nbPlayer);
		nbVirtualPlayer = setNbVirtualPlayer(nbPlayer, nbRealPlayer);
		//String[] playerName = gameOp.setNamePlayer(nbRealPlayer);
		//playerName.toString();

		System.out.println("Alright, now you can choose from these choices : ");
		boolean startGame = false;
		while (!startGame) {
			int playerChoice = GameOptions.selectionOptionMenu();
			switch (playerChoice) {
				case 2:
					System.out.println("Choose variant");
					GameOptions.chooseVariant();
					System.out.println("You will play with the variant : " + variant + "\n");
					break;
				case 1:
					startGame = true;
					System.out.println("Starting a new game where :");
					break;
				default:
					throw new setupException();
					//Gestion d'exceptions à faire
			}
		}
	}
}