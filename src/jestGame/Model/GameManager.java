package jestGame.Model;

import jestGame.UserInput;

public class GameManager {

    //Menu selection method
    public static int menu() {
        System.out.println("Choose from these choices");
        System.out.println("____________________");
        System.out.println("1 - Start game");
        System.out.println("2 - Quit");
        return UserInput.getInstance().nextInt();
    }

    public void executeUserChoice(int userChoice) throws setupException {
        switch (userChoice) {
            case 1:
                System.out.println("Open settings");
                    GameOptions gameOp = new GameOptions();
                    gameOp.setup();
                break;
            case 2:
                System.out.println("Quiting..");
                System.exit(0);
                break;
            default:
                //Gestion d'exception à faire
                System.out.println("Une exception est levée");
        }
    }

    public void play(){
    	RoundsManager currentGame = new RoundsManager();
    	currentGame.firstRound();
    	//currentGame.nextRound();
    }

}