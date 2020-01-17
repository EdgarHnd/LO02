package fr.utt.jestcardgame.view;

import fr.utt.jestcardgame.model.GameManager;
import fr.utt.jestcardgame.observer.Observable;
import fr.utt.jestcardgame.observer.Observer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * This class is made in order to implement a concurancial console interface which has not yet been implemented
 * We keep it their as a work in progress
 *
 * @author Edgar
 */
public class TextView implements Observer, Runnable {

    public static String QUIT = "Quit";
    public static String PROMPT = ">";
    private InputStream input;
    private GameManager gm;

    public TextView(GameManager gm) {
        // A compléter
        this.input = System.in;
        Thread t = new Thread(this);
        t.start();
    }

    public void run() {
        String saisie = null;

        do {
            saisie = this.readString();
            if (saisie != null) {
                if (gm.getGameState() == "mainMenu" && saisie == "1") {
                    try {
                        gm.executeUserChoice(1);
                    } catch (setupException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Wrong input");
                }
            }
        } while (saisie.equalsIgnoreCase(QUIT) == false);
        System.exit(0);
    }

    private String readString() {
        BufferedReader br = new BufferedReader(new InputStreamReader(input));
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
    public void update(Observable o, Object a) {
        if (o instanceof GameManager) {
            switch (((GameManager) o).getGameState()) {
                case "mainMenu":
                    ConsoleGameView.display(ConsoleOutput.MainMenu);
                    break;
                case "rules":
                    System.out.println("initrule");
                    break;
                case "exit":
                    System.exit(0);
                    break;
            }
        }
    }

}
   
