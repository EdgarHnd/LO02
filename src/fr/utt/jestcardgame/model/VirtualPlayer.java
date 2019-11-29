package fr.utt.jestcardgame.model;

import java.util.Random;

public class VirtualPlayer extends Player {

	
	
	public VirtualPlayer(String name, int i) {
		super(name,i);
		
	}

	public void makeOffer() {

		Random rand = new Random();
		int randomNb = rand.nextInt(3) + 1;
		System.out.println(randomNb);
		/*switch (randomNb){
			case 1 :
				DefensiveStrategy defensiveStrategy = new DefensiveStrategy();
				defensiveStrategy.makeOfferStrategy();
				break;
			case 2 :
				OffensiveStrategy offensiveStrategy = new OffensiveStrategy();
				offensiveStrategy.makeOfferStrategy();
				break;
			case 3 :
				ModerateStrategy moderateStrategy = new ModerateStrategy();
				moderateStrategy.makeOfferStrategy();
				break;
			default :
				break;
		}*/
	}		
	
	public void pickOffer() {
		Random rand = new Random();
		int randomNb = rand.nextInt(3) + 1;
		switch (randomNb){
			case 1 :
				DefensiveStrategy defensiveStrategy = new DefensiveStrategy();
				defensiveStrategy.pickOfferStrategy();
				break;
			case 2 :
				OffensiveStrategy offensiveStrategy = new OffensiveStrategy();
				offensiveStrategy.pickOfferStrategy();
				break;
			case 3 :
				ModerateStrategy moderateStrategy = new ModerateStrategy();
				moderateStrategy.pickOfferStrategy();
				break;
			default:
				break;
		}
	}		


}
