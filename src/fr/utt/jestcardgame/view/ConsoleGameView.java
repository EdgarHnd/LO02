
package fr.utt.jestcardgame.view;

import fr.utt.jestcardgame.model.GameOptions;

public abstract class ConsoleGameView {
	
	
	
	public static void display(ConsoleOutput co) {
		switch(co) {
		
		case MainMenu:
			System.out.println("Welcome to JestGame !");
	        System.out.println("____________________");
	        System.out.println("\n1 - Start game");
	        System.out.println("\n2 - Quit");
	        break;
		case OptionMenu:
			System.out.println("How many players for your game ? (You have the choice between 3 or 4.)");
			break;
		case PlayerNb:
			System.out.println(GameOptions.getNbPlayer() + " players will play the next game. \n");
			break;
		case RealPlayer:
			System.out.println("How many players are real for this game ?");
			break;
		case PlayVar:
			System.out.println(GameOptions.getNbRealPlayer() + " REAL PLAYER(S)");
			System.out.println(GameOptions.getNbVirtualPlayer() + " VIRTUAL PLAYER(S)");
			System.out.println("____________________");
			System.out.println("1 - Play");
			System.out.println("2 - Choose a variant");
			break;
		case Variant:
			System.out.println("We have different variants for your game. Make your choice");
			System.out.println("1- Variant 1");
			System.out.println("2- Variant 2");
			System.out.println("3- Variant 3");
			break;
		case SelectVar:
			System.out.println("You will play with the variant : " + GameOptions.getVariant() + "\n");
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
		//	System.out.println("Carte 1:" + this.hand.get(0));
		//	System.out.println("Carte 2:" + this.hand.get(1));
			System.out.println("Select your offer (1 or 2) ");
			break;
		case OfferMade:
		//	System.out.println(this.hand.get(0) + " card selected");
			break;
		case Picking:
			System.out.println("Select an opponent card you want to add to your Jest");
			System.out.println("You can choose between this cards : ");
			break;
		default:System.out.println("Nothing to display");
		}
		
	}
}
