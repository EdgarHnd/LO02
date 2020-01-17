package fr.utt.jestcardgame.model;

import fr.utt.jestcardgame.observer.Observable;
import fr.utt.jestcardgame.view.ConsoleGameView;
import fr.utt.jestcardgame.view.ConsoleOutput;
import fr.utt.jestcardgame.view.ConsoleUserInput;
import fr.utt.jestcardgame.view.setupException;

import java.util.InputMismatchException;

/**
 * Class which allows the user to setup different parameters of the game, such as :
 * The number of players, and more precisely the number of real and virtual players
 * The user can also choose one variant among the three variants available.
 * <p>
 * It extends {@link Observable} class, so each instance of <code>GameOptions</code> can be observed by the <code>View</code> package.
 *
 * @author Elina
 */
public abstract class GameOptions extends Observable {

    /**
     * Sets the number of players for the game with the user input on the console, by managing it with the {@link ConsoleUserInput} and {@link ConsoleOutput} classes.
     * It returns the number of player chosen.
     *
     * @return nbPlayer
     * @see ConsoleUserInput
     */
    public static int consolePutNbPlayer() {
        boolean correctNumber = false;
        while (!correctNumber) {
            ConsoleGameView.display(ConsoleOutput.settingNbPlayer);
            try {
                int nbPlayer = ConsoleUserInput.getInstance().nextInt();
                OptionsData.nbPlayer = nbPlayer;
                ConsoleUserInput.getInstance().isCorrectInputBetweenMinMax(3, 4, nbPlayer, ConsoleOutput.PlayerNb);
                correctNumber = true;
            } catch (setupException e) {
                e.getMessage();
                System.out.println("Please choose wisely between 3 and 4 !");
            } catch (InputMismatchException e) {
                System.out.println("You have to put a number.");
                //@TODO manage a new input from the user
                System.exit(0);
            }
        }
        return OptionsData.nbPlayer;
    }

    /**
     * Sets the number of real players for the game with the user input on the console, by managing it with the {@link ConsoleUserInput} and {@link ConsoleOutput} classes.
     *
     * @param nbPlayer Number of players chosen by the user.
     * @return nbRealPlayer
     * @see ConsoleUserInput
     */
    public static int consolePutNbRealPlayer(int nbPlayer) {
        OptionsData.nbPlayer = nbPlayer;
        boolean correctNumber = false;
        while (!correctNumber) {

            ConsoleGameView.display(ConsoleOutput.RealPlayer);

            try {
                int nbRealPlayer = ConsoleUserInput.getInstance().nextInt();
                OptionsData.nbRealPlayer = nbRealPlayer;
                ConsoleUserInput.getInstance().isCorrectInputBetweenMinMax(0, nbPlayer, nbRealPlayer, ConsoleOutput.Standard);
                correctNumber = true;
            } catch (setupException e) {
                e.getMessage();
                System.out.println("The number of Real Player cannot be superior to the number of players.");
            } catch (InputMismatchException e) {
                System.out.println("You have to put a number.");
                //@TODO manage a new input from the user
                System.exit(0);
            }
        }
        return OptionsData.nbRealPlayer;
    }

    /**
     * Calculate the number of virtual players (or AI) depending on the number of players and real players.
     *
     * @return nbVirtualPlayer
     * @see ConsoleUserInput
     */
    public static int consolePutNbVirtualPlayer() {
        int nbVirtualPlayer = OptionsData.nbPlayer - OptionsData.nbRealPlayer;
        OptionsData.nbVirtualPlayer = nbVirtualPlayer;
        return nbVirtualPlayer;
    }

    /**
     * Sets a variant for the game according to the user's choice.
     * It's managed with the {@link ConsoleUserInput} and {@link ConsoleOutput} classes.
     *
     * @return variant
     * @see ConsoleUserInput
     */
    public static int chooseVariant() {
        boolean correctNumber = false;
        while (!correctNumber) {
            ConsoleGameView.display(ConsoleOutput.Variant);
            try {
                int nbVariant = ConsoleUserInput.getInstance().nextInt();
                OptionsData.variant = nbVariant;
                ConsoleUserInput.getInstance().isCorrectInputBetweenMinMax(1, 3, nbVariant, ConsoleOutput.SelectVar);
                correctNumber = true;
            } catch (setupException e) {
                e.getMessage();
                System.out.println("Please choose wisely between 1 and 3 !");
            } catch (InputMismatchException e) {
                System.out.println("You have to put a number.");
                //@TODO manage a new input from the user
                System.exit(0);
            }
        }
        return OptionsData.variant;
    }

    /**
     * Displays the parameters of the game on the console.
     *
     * @return ConsoleUserInput.getInstance().nextInt()
     * @see ConsoleGameView
     * @see ConsoleUserInput
     */
    public static int selectionOptionMenu() {
        ConsoleGameView.display(ConsoleOutput.PlayVar);
        return ConsoleUserInput.getInstance().nextInt();
    }

    /**
     * Main method of the <code>GameOptions</code> class.
     * It runs all methods that sets the number of players, the variant, and then begin a new game according to the user's input.
     *
     * @see ConsoleGameView
     * @see ConsoleUserInput
     */
    public static void setup() {

        OptionsData.nbPlayer = consolePutNbPlayer();
        OptionsData.nbRealPlayer = consolePutNbRealPlayer(OptionsData.nbPlayer);
        OptionsData.nbVirtualPlayer = consolePutNbVirtualPlayer();

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