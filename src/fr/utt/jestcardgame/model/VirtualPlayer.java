package fr.utt.jestcardgame.model;

import java.util.Random;

public class VirtualPlayer extends Player {

	Random rand = new Random();
	int randomNb = rand.nextInt(3) + 1;
	
	public VirtualPlayer(String name, int i) {
		super(name,i);
	}

	public void makeOffer() {
		switch (randomNb){
			case 1 :
				DefensiveStrategy defensiveStrategy = new DefensiveStrategy();
				defensiveStrategy.makeOfferStrategy(this);
				break;
			case 2 :
				OffensiveStrategy offensiveStrategy = new OffensiveStrategy();
				offensiveStrategy.makeOfferStrategy(this);
				break;
			case 3 :
				ModerateStrategy moderateStrategy = new ModerateStrategy();
				moderateStrategy.makeOfferStrategy(this);
				break;
			default :
				break;
		}
	}		
	
	public void pickOffer() {
		switch (randomNb){
			case 1 :
				DefensiveStrategy defensiveStrategy = new DefensiveStrategy();
				defensiveStrategy.pickOfferStrategy(this);
				break;
			case 2 :
				OffensiveStrategy offensiveStrategy = new OffensiveStrategy();
				offensiveStrategy.pickOfferStrategy(this);
				break;
			case 3 :
				ModerateStrategy moderateStrategy = new ModerateStrategy();
				moderateStrategy.pickOfferStrategy(this);
				break;
			default:
				break;
		}
	}		


}
