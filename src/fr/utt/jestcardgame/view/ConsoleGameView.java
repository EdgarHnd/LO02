
package fr.utt.jestcardgame.view;

import fr.utt.jestcardgame.model.OptionsData;
import fr.utt.jestcardgame.model.RoundsManager;
import fr.utt.jestcardgame.observer.Observable;
import fr.utt.jestcardgame.observer.Observer;
/**
 *This class is where all the different console output are stored 
 * @author Edgar
 */
public abstract class ConsoleGameView{
	/**
	 *Static method that can be call at any moment by the program in order to display a message to the console
	 *It uses the enum <code>ConsoleOutput</code> as a parameter
	 *@param co ConsoleOutput enumeration
	 */
	public static void display(ConsoleOutput co) {
        switch (co) {
            case MainMenu:
                System.out.println("Welcome to JestGame !");
                System.out.println("____________________");
                System.out.println("\n1 - Start game");
                System.out.println("\n2 - Rules");
                System.out.println("\n3 - Quit");
                break;
            case settingNbPlayer:
                System.out.println("How many players for your game ? (You have the choice between 3 or 4.)");
                break;
            case PlayerNb:
                System.out.println(OptionsData.getNbPlayer() + " players will play the next game. \n");
                break;
            case RealPlayer:
                System.out.println("How many players are real for this game ?");
                break;
            case PlayVar:
                System.out.println(OptionsData.getNbRealPlayer() + " REAL PLAYER(S)");
                System.out.println(OptionsData.getNbVirtualPlayer() + " VIRTUAL PLAYER(S)");
                System.out.println("____________________");
                System.out.println("1 - Play");
                System.out.println("2 - Choose a variant");
                break;
            case Variant:
                System.out.println("We have different variants for your game. Make your choice");
                System.out.println("1- Variant 1 : Super Joker");
                System.out.println("2- Variant 2 : Power of Heart");
                System.out.println("3- Variant 3 : Bad Clubs");
                break;
            case SelectVar:
                System.out.println("You will play with the variant : " + OptionsData.getVariant() + "\n");
                break;
            case NewGame:
                System.out.println("Starting a new game where :");
                break;
            case BestOffer:
                System.out.println("\nThe player with the best offer is : " + RoundsManager.getInstance().checkBestOffer().getName());
                break;
            case Dealing:
                System.out.println("\nStart dealing cards to the players");
                break;
            case NoMoreCard:
                System.out.println("Not enough cards to deal");
                break;
            case ReadyToOffer:
                System.out.println("Are you ready to make your offer ?");
                System.out.println("Keep your distance from other players to not let them know your cards.");
                System.out.println("[Press X to continue]");
                break;
            case OfferMaking:
                System.out.println("Select your offer (1 or 2) ");
                break;
            case Picking:
                System.out.println("Select the opponent you want to pick a card from");
                break;
            case Standard:
                System.out.println(" ");
                break;
            default:
                System.out.println("Nothing to display");
        }

    }
}
