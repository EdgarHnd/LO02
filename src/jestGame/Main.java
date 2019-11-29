package jestGame;

import jestGame.Model.GameManager;
import jestGame.Model.setupException;

public class Main {

    public static void main(String[] args) throws setupException {
        GameManager game = new GameManager();
        System.out.println("Welcome to JestGame !");
        int userChoice = game.menu();
        game.executeUserChoice(userChoice);
    }
}
