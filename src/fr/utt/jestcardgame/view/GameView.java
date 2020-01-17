package fr.utt.jestcardgame.view;

import fr.utt.jestcardgame.controler.GameViewControler;
import fr.utt.jestcardgame.model.GameManager;
import fr.utt.jestcardgame.observer.Observable;
import fr.utt.jestcardgame.observer.Observer;

import javax.swing.*;
import java.awt.*;
/**
 *This is the main class for the graphic interface where all the different panel will be created and selected
 *It extends the java class JFrame and implements our observer design pattern
 * @author Edgar
 */
public class GameView extends JFrame implements Observer{
	
	private Dimension size;
	private JPanel container = new JPanel();
	protected GameViewControler gvc;
	private Board board;
	/**
	 *Return the object board the class
	 *@return this.board
	 */
	public Board getBoard() {
		return this.board;
	}
	/**
	 * Instantiate the GameView with all of the settings
	 * Initialize a countainer and call the initWelcome() methode to fill it with a panel
	 * @param gvc GameViewControler for this view {@link fr.utt.jestcardgame.controler.GameViewControler}.
	 */
	public GameView(GameViewControler gvc) {
		this.gvc = gvc;
		this.setTitle("Jest Game");
		this.setSize(1300, 800);
	    this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);             
	    this.setResizable(false);
	    this.size = new Dimension(this.getWidth(), this.getHeight());    
	    this.container.setPreferredSize(this.size);
	    this.container.setBackground(Color.white);
	    this.initWelcome();
	    this.setContentPane(this.container);
	}
	/**
	 * Create and place the panel <code>Welcome</code> into the countainer of this JFrame
	 */
	public void initWelcome() {
		System.out.println("Showing Welcome");
		container.removeAll();
		container.add(new Welcome(size,gvc).getPanel(), BorderLayout.CENTER);
		container.revalidate();
	}
	/**
	 * Create and place the panel <code>Rules</code> into the countainer of this JFrame
	 */
	public void initRules() {
		System.out.println("Showing Rules");
		container.removeAll();
		container.add(new Rules(size,gvc).getPanel(), BorderLayout.CENTER);
		container.revalidate();
	}
	/**
	 * Create and place the panel <code>Rules</code> into the countainer of this JFrame
	 */
	public void initOptions() {
		System.out.println("Showing Options");
		System.out.println("Showing Options");
		container.removeAll();
		container.add(new Options(size, gvc).getPanel(), BorderLayout.CENTER);
		container.revalidate();
	}
	/**
	 * Create and place the panel <code>Board</code> into the countainer of this JFrame
	 * Also share the board object to the GameManager (trick to pass one the board observer to the model)
	 */
	public void initNewGame() {
		System.out.println("Showing Board");
		container.removeAll();
		this.board = new Board(size,gvc);
		GameManager.getInstance().setBoard(this.board);
		System.out.println("getboard "+GameManager.getInstance().getBoard());
		container.add(this.board.getPanel(), BorderLayout.CENTER);
		container.revalidate();
	}
	/**
	 * Update method from the Observer design pattern
	 * Will call the different init method base on the current state of the <code>GameManager</code>
	 * @param o Observable
	 * @param arg Object
	 */
	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof GameManager) {
			switch (((GameManager)o).getGameState()) {
			case "mainMenu":
				System.out.println("update menu");
				this.initWelcome();
				break;
			case "started":
				System.out.println("update show newGame");
				this.initNewGame();
				break;
			case "options":
				this.initOptions();
				break;
			 case "rules": 
			     System.out.println("update rules");
				 this.initRules();
				 break;
			 case "exit":
				 System.exit(0);
				 break;
			 }
		}
		
	}
}
