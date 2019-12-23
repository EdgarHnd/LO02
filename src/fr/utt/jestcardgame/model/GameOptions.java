package fr.utt.jestcardgame.model;


import fr.utt.jestcardgame.view.ConsoleGameView;
import fr.utt.jestcardgame.view.ConsoleOutput;
import fr.utt.jestcardgame.view.ConsoleUserInput;
import fr.utt.jestcardgame.view.setupException;

import java.util.InputMismatchException;


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

	public static int setNbPlayer() {
		boolean correctNumber = false;
		while (!correctNumber){
			ConsoleGameView.display(ConsoleOutput.settingNbPlayer);
			try {
				int nbPlayer = ConsoleUserInput.getInstance().nextInt();
				GameOptions.nbPlayer = nbPlayer;
				ConsoleUserInput.getInstance().isCorrectInputBetweenMinMax(3, 4, nbPlayer, ConsoleOutput.PlayerNb);
				correctNumber = true;
			} catch (setupException e){
				e.getMessage();
				System.out.println("Please choose wisely between 3 and 4 !");
			} catch (InputMismatchException e) {
				System.out.println("You have to put a number.");
				//@TODO manage a new input from the user
				System.exit(0);
			}
		}
		return nbPlayer;
	}

	public static int setNbRealPlayer( int nbPlayer) throws setupException {
			GameOptions.nbPlayer = nbPlayer;
			boolean correctNumber = false;
			while (!correctNumber) {
				
				ConsoleGameView.display(ConsoleOutput.RealPlayer);

				try {
					int nbRealPlayer = ConsoleUserInput.getInstance().nextInt();
					GameOptions.nbRealPlayer = nbRealPlayer;
					ConsoleUserInput.getInstance().isCorrectInputBetweenMinMax(0, nbPlayer, nbRealPlayer, ConsoleOutput.Standard);
					correctNumber = true;
				} catch (setupException e){
					e.getMessage();
					System.out.println("The number of Real Player cannot be superior to the number of players.");
				} catch (InputMismatchException e){
					System.out.println("You have to put a number.");
					//@TODO manage a new input from the user
					System.exit(0);
				}
			}
			return nbRealPlayer;
	}

	public static int setNbVirtualPlayer() {
			int nbVirtualPlayer = GameOptions.nbPlayer - GameOptions.nbRealPlayer;
			return nbVirtualPlayer;
	}

	public static int chooseVariant () {
		boolean correctNumber = false;
		while (!correctNumber){
			ConsoleGameView.display(ConsoleOutput.Variant);
			try {
				int nbVariant = ConsoleUserInput.getInstance().nextInt();
				variant = nbVariant;
				ConsoleUserInput.getInstance().isCorrectInputBetweenMinMax(1, 3, nbVariant, ConsoleOutput.SelectVar);
				correctNumber = true;
			} catch (setupException e){
				e.getMessage();
				System.out.println("Please choose wisely between 1 and 3 !");
			} catch (InputMismatchException e) {
				System.out.println("You have to put a number.");
				//@TODO manage a new input from the user
				System.exit(0);
			}
		}
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
					chooseVariant();
					break;
				case 1:
					startGame = true;
					ConsoleGameView.display(ConsoleOutput.NewGame);
					break;
				default:
					System.out.println("Your value is not correct.");
			}
		}
	}
}