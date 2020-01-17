package fr.utt.jestcardgame;

import fr.utt.jestcardgame.controler.GameViewControler;
import fr.utt.jestcardgame.model.GameManager;
import fr.utt.jestcardgame.view.GameView;
import fr.utt.jestcardgame.view.setupException;

/**
 * This is the main class of our program.
 * It is only use to instantiate the differents objects for the game :
 * @author Edgar
 */
public class Main {
	
	/**
	 * This is the main method of the program that will be executed on the launch of the program.
	 * This is where we create the objects of the MVC for the game :
	 * the model : <code>GameManager</code>
	 * the controller : <code>GameViewController</code>
	 * the view :<code>GameView</code>
	 * 
	 * Add the observer GameView to the GameManager
	 * Create and start a new Thread for the graphical interface
	 * Start the console interface (not yet in a separated thread)
	 */
	    public static void main(String[] args) throws setupException, InterruptedException {
	    	GameManager gm = new GameManager();	    	
	    	GameViewControler cCtrl = new GameViewControler(gm);
	    	GameView gv =  new GameView(cCtrl);
			gm.addObserver(gv);
	    	Thread t = new Thread(new Runnable() {
	    		public void run() {
	    			
	    			gv.setVisible(true);
	    			
	    		}
	    	});
	    	t.start();
	    	gm.mainMenu();		   
	    }
}
