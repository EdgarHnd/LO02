package fr.utt.jestcardgame.model;

import fr.utt.jestcardgame.view.ConsoleGameView;
import fr.utt.jestcardgame.view.ConsoleOutput;
import fr.utt.jestcardgame.view.ConsoleUserInput;


public class RealPlayer extends Player {
	
	public RealPlayer(String name,int nb) {
		super(name, nb);
		}
		
	@Override
	public void makeOffer() {
		ConsoleGameView.display(ConsoleOutput.ReadyToOffer);
		
		ConsoleUserInput input = ConsoleUserInput.getInstance();
		if (input.nextString().equals("X")){
			System.out.println("Carte 1:" + this.hand.get(0));
			System.out.println("Carte 2:" + this.hand.get(1));
			System.out.println("Select your offer (1 or 2) ");
		
			int selection = input.nextInt();
			if(selection == 1) {
				this.hand.get(0).hidden = false;
				System.out.println(this.hand.get(0) + " card selected");
			}
			else if(selection == 2) {
				this.hand.get(1).hidden = false;
				System.out.println(this.hand.get(1) + " card selected");
			}
		} else {
			//Gestion d'exception à faire
		}
		this.newOffer();
	}
	
	@Override
	public void pickOffer() {
	
		System.out.println("\nIt's "+this.name+"'s turn to pick a card");
		
		RoundsManager.getInstance().showValidOffers();
		
		ConsoleGameView.display(ConsoleOutput.Picking);
		
		Player playerSelect = RoundsManager.getInstance().listPlayers.get(ConsoleUserInput.getInstance().nextInt()-1);
		
		
		System.out.println("Now select the card you want to pick (1/2):");
		
		int cardSelect = ConsoleUserInput.getInstance().nextInt();
		
		if(cardSelect == 1) {
			this.jest.add(playerSelect.getOffer().pollFirst());
		}
		else if(cardSelect == 2){
		this.jest.add(playerSelect.getOffer().pollLast());
		}
		
		System.out.println("Your Jest is now : "+this.jest.toString());
		this.hasPlayed = true;
		this.isPicking = false;
		
		//Set the next player
		if(playerSelect.getHasPlayed()==false) {
			playerSelect.isNext = true;
		}
		else {
			//if the player selected already played compare the remaining player's offers
			Player nextPlayer = RoundsManager.getInstance().checkBestOffer();
			nextPlayer.isNext = true;
			//check the number of player remaining
			int playerNotPlayed = 0;
			for(int i = 0; i < GameOptions.getNbPlayer(); i++) {
				if(RoundsManager.getInstance().listPlayers.get(i).hasPlayed == false){
				playerNotPlayed ++ ;
				}
			}
			if(playerNotPlayed == 1) {
				//if only one player remain allow him to pick his own cards
				nextPlayer.setIsPicking(false);
			}
		}
	}
}
