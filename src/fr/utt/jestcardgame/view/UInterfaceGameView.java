package fr.utt.jestcardgame.view;

import fr.utt.jestcardgame.observer.Observable;
import fr.utt.jestcardgame.observer.Observer;

public abstract class UInterfaceGameView implements Observer {
    @Override
    public void update(Observable o, Object arg) {

    }

    public static void display(UInterfaceOutput co) {
        switch (co) {
            case PlayerNb3:
                System.out.println("3 players will play the next game.");
                break;
            case PlayerNb4 :
                System.out.println("4 players will play the next game.");
                break;
        }
    }
}
