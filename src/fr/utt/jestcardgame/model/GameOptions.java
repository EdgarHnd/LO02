package fr.utt.jestcardgame.model;


import fr.utt.jestcardgame.view.ConsoleGameView;
import fr.utt.jestcardgame.view.ConsoleOutput;
import fr.utt.jestcardgame.view.ConsoleUserInput;
import fr.utt.jestcardgame.view.setupException;


/**
 * Class which allows the user to setup different parameters of the game, such as :
 * The number of players, and more precisely the number of real and virtual players
 * The name of each player
 * The user can choose one variant
 *
 * @author Elina
 * @version 1.0
 */
public abstract class GameOptions {

	//variables pouvant être utiles
	protected static int nbPlayer;
	protected static int nbRealPlayer;
	protected static int nbVirtualPlayer;
	protected static int variant;
	
	//Des noms pour tester
	protected static String[] playersNames = {"Edgar","Elina","Patrick","Bernard"};
	
	//Getters
	public static int getNbPlayer(){
			return nbPlayer;
	}

	public static String getPlayersNames(int i) {
		return playersNames[i];
	}

	public static int getNbRealPlayer() {
		return nbRealPlayer;
	}

	public static int getNbVirtualPlayer() {
		return nbVirtualPlayer;
	}

	public static int getVariant() {
		return variant;
	}

	public static int setNbPlayer() throws setupException {
			boolean correctChoice = false;
			while (!correctChoice) {
				int nbPlayer = ConsoleUserInput.getInstance().nextInt();
				GameOptions.nbPlayer = nbPlayer;
				if (nbPlayer == 3 || nbPlayer == 4) {
					correctChoice = true;
					ConsoleGameView.display(ConsoleOutput.PlayerNb);
				} else {
					//Gestion d'exception à appronfondir : faire en sorte que l'utilisateur recommence, au lieu d'exit le programme
					throw new setupException();
				}
			}
			return nbPlayer;
	}

	

	public static int setNbRealPlayer( int nbPlayer) throws setupException {
			GameOptions.nbPlayer = nbPlayer;
			boolean correctNumber = false;
			while (!correctNumber) {
				
				ConsoleGameView.display(ConsoleOutput.RealPlayer);	
				
				int nbRealPlayer = ConsoleUserInput.getInstance().nextInt();
				GameOptions.nbRealPlayer = nbRealPlayer;
				if (nbRealPlayer > nbPlayer) {
					//Gestion d'exception à appronfondir : faire en sorte que l'utilisateur recommence, au lieu d'exit le programme
					throw new setupException();
				} else {
					correctNumber = true;
				}
			}
			return nbRealPlayer;
	}

	public static int setNbVirtualPlayer() throws setupException {
			int nbVirtualPlayer = GameOptions.nbPlayer - GameOptions.nbRealPlayer;
			return nbVirtualPlayer;
	}

	public static int chooseVariant () {
			ConsoleGameView.display(ConsoleOutput.Variant);
			ConsoleUserInput input = new ConsoleUserInput();
			//Scanner sc = new Scanner(System.in);
			variant = input.nextInt();
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
			ConsoleGameView.display(ConsoleOutput.PlayVar);
			return ConsoleUserInput.getInstance().nextInt();
	}

	public static void setup() throws setupException {
		
		ConsoleGameView.display(ConsoleOutput.OptionMenu);

		nbPlayer = setNbPlayer();
		nbRealPlayer = setNbRealPlayer(nbPlayer);
		nbVirtualPlayer = setNbVirtualPlayer();
		//String[] playerName = gameOp.setNamePlayer(nbRealPlayer);
		//playerName.toString();

		
		boolean startGame = false;
		while (!startGame) {
			int playerChoice = selectionOptionMenu();
			switch (playerChoice) {
				case 2:
					
					GameOptions.chooseVariant();
					ConsoleGameView.display(ConsoleOutput.SelectVar);
					break;
				case 1:
					startGame = true;
					ConsoleGameView.display(ConsoleOutput.NewGame);
					break;
				default:
					throw new setupException();
					//Gestion d'exceptions à faire
			}
		}
	}
}