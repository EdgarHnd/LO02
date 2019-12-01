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
		ConsoleGameView.display(ConsoleOutput.Picking);
	//	GameBoard.getInstance().showOffers();
	//	this.jest.add(GameBoard.getInstance().getOffers().get(ConsoleUserInput.getInstance().nextInt()-1));
		RoundsManager.getInstance().showValidOffers();
		
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
	}
}
