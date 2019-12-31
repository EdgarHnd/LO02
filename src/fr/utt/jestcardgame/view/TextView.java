package fr.utt.jestcardgame.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import fr.utt.jestcardgame.model.GameManager;
import fr.utt.jestcardgame.observer.Observable;
import fr.utt.jestcardgame.observer.Observer;

public class TextView implements Observer, Runnable{

	public static String QUITTER = "Quit";
    public static String COMMUTER = "C";
    public static String PROMPT = ">";
    private GameManager gm;
    
    public TextView(GameManager gm) {  
      // A compléter     
    Thread t = new Thread(this);
    t.start();
    }
    
    public void run() {
    	String saisie = null;
    	boolean quitter = false;
    	
    	do {
      saisie = this.lireChaine();
      if (saisie != null) {
    	  // A compléter     
      }
    	} while (quitter == false);
    	System.exit(0);
    }
    
    private String lireChaine() {
    	BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
    	String resultat = null;
    	try {
      System.out.print(TextView.PROMPT);
      resultat = br.readLine();
    	} catch (IOException e) {
      System.err.println(e.getMessage());
    	}
    	return resultat;   
    }
    @Override
    public void update(Observable arg0, Object arg1) {
    	// A compléter     
    }

}
