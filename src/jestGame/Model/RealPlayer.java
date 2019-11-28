package jestGame.Model;


import jestGame.UserInput;

public class RealPlayer extends Player {
	
	public RealPlayer(String name,int nb) {
		super(name, nb);
		}
		
		
	@Override
	public void makeOffer() {
		System.out.println("Are you ready to make your offer ?");
		System.out.println("Keep your distance from other players to not let them know your cards.");
		System.out.println("[Press X to continue]");

		if (UserInput.getInstance().nextString().equals("X")){
			System.out.println("Carte 1:" + this.hand.get(0));
			System.out.println("Carte 2:" + this.hand.get(1));
			System.out.println("Select your offer (1 or 2) ");
			int selection = UserInput.getInstance().nextInt();
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
	}
	
	@Override
	public void pickOffer() {
		System.out.println("Select an opponant card you want to add to your Jest");
		System.out.println("You can choose between this cards : ");
		
	}
}