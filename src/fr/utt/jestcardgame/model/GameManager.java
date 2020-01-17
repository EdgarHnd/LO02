package fr.utt.jestcardgame.model;

import fr.utt.jestcardgame.observer.Observable;
import fr.utt.jestcardgame.observer.Observer;
import fr.utt.jestcardgame.view.ConsoleGameView;
import fr.utt.jestcardgame.view.ConsoleOutput;
import fr.utt.jestcardgame.view.ConsoleUserInput;
import fr.utt.jestcardgame.view.setupException;

/**
 * Principal class which manage the game.
 * It extends {@link Observable} class, so each instance of <code>GameManager</code> can be observed by the <code>View</code> package.
 * It displays the main menu of the game (<code>rules</code>, <code>options</code>).
 * It implements the main method of the game : <code>play()</code> ; and the others methods to run the process of the game with different rounds.
 *
 * @author Elina
 */
public class GameManager extends Observable {

    private static GameManager gm = null;
    private String gameState;
    private Observer board;

    /**
     * Return the <code>GameManager</code> instance already existing. If not, it will create a new one.
     *
     * @return gm
     */
    public static GameManager getInstance() {

        if (gm == null) {
            gm = new GameManager();
        }

        return gm;
    }

    /**
     * A <code>GameManager</code> instance does'nt have any specific attribute during the instantiation.
     */
    public GameManager() {

    }

    /**
     * Main method of the game. It begins a new game by updating the state of the game (<code>gameState</code>).
     * Then 4 methods are called to run the game : <code>firstRound</code>, <code>nextRounds</code>, <code>giveTrophy</code>, <code>finalScore</code>.
     */
    public void play() {
        updateGameState("started");
        System.out.println("Game started and notify");
        RoundsManager currentGame = RoundsManager.getInstance();
        currentGame.firstRound();
        currentGame.nextRounds();
        currentGame.giveTrophy();
        currentGame.finalScore();
    }

    /**
     * Executes the user choice put in the console.
     * It can be : open the options, the rules, or quit the game.
     * <p>
     * Choosing to play the game will first bring the user at the option menu, in order to configure his new game.
     *
     * @param userChoice User entered choice.
     * @throws setupException Exception from the <code>setup()</code> method of the {@link GameOptions} class handled by this {@link Exception} class
     */
    public void executeUserChoice(int userChoice) throws setupException {
        switch (userChoice) {
            case 1:
                GameOptions.setup();
                break;
            case 2:
                this.rules();
                break;
            case 3:
                this.setChanged();
                this.notifyObservers();
                System.exit(0);
                break;
            case 4:
                this.options();
                break;
            default:
                System.out.println("No input given");
                break;
        }
    }

    /**
     * Displays the main menu on the console, and changes the <code>gameState</code> to <em>"mainMenu"</em>.
     * Once the menu is displayed, this method will call the <code>executeUserChoice(...)</code> method in order to execute the user's choice.
     * At the end, it will automatically call the <code>play()</code> method, and then the game is beginning.
     *
     * @throws setupException Exception from the <code>setup()</code> method of the {@link GameOptions} class handled by this {@link Exception} class
     */
    public void mainMenu() throws setupException {
        System.out.println("Game launched");
        updateGameState("mainMenu");
        ConsoleGameView.display(ConsoleOutput.MainMenu);
        this.executeUserChoice(ConsoleUserInput.getInstance().nextInt());

        this.play();
    }

    /**
     * Displays the rules of the game, and changes the <code>gameState</code> to <em>"rules"</em>.
     * Allows the user to get back to the menu while he press "1", by calling the method <code>mainMenu()</code>.
     *
     * @throws setupException Exception from the <code>setup()</code> method of the {@link GameOptions} class handled by this {@link Exception} class.
     */
    public void rules() throws setupException {
        System.out.println("Rules, press 1 to get back to the menu");
        updateGameState("rules");
        int input = ConsoleUserInput.getInstance().nextInt();
        if (input == 1) {
            this.mainMenu();
        }
    }

    /**
     * Displays the rules of the game, and changes the <code>gameState</code> to <em>"options"</em>.
     *
     * @throws setupException Exception from the <code>setup()</code> method of the {@link GameOptions} class handled by this {@link Exception} class.
     */
    public void options() throws setupException {
        updateGameState("options");
        GameOptions.setup();
    }

    /**
     * Set the <code>gameState</code> to the new state put in the console by the user.
     *
     * @param newState New state of the game put by the user.
     */
    public void updateGameState(String newState) {
        gameState = newState;
        setChanged();
        notifyObservers();
    }

    /**
     * Gets the <code>gameState</code> of the current game (which is an instance of <code>GameManager</code>).
     *
     * @return gameState
     */
    public String getGameState() {
        return gameState;
    }

    /**
     * Gets the <code>board</code> of the current game (which is an instance Observer of <code>GameManager</code>).
     *
     * @return board
     */
    public Observer getBoard() {
        return board;
    }

    /**
     * Sets the <code>board</code> of the current game to the one put in the console by the user.
     *
     * @param board
     */
    public void setBoard(Observer board) {
        this.board = board;
    }
}