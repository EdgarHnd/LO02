package jestGame;

import jestGame.Model.GameManager;
import jestGame.Model.setupException;

public class main {

    public static void main(String[] args) throws setupException {
        GameManager game = new GameManager();
        //Main menu
        System.out.println("Welcome to JestGame !");
        int userChoice = game.menu();
        game.executeUserChoice(userChoice);

        //Start a game
        game.play();
    }
}
