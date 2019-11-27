package jestGame.Model;

import java.util.ArrayList;

/**
 * This class is where a new party is created and managed once the user has entered all the options.
 * The RoundsManager will create the players, instantiate and control the deck actions and
 * it will tell which player what to do and check their actions.
 * This is also where the score and ranking will be stored for the current game.
 *
 * @author Edgar
 * @version 1.0
 */

public class RoundsManager {
	
	protected int roundNb = 0;
	protected int currentPlayer;
	protected static ArrayList<Player> listPlayers;
	
	//Getters
	public int getRoundNb() {
		return roundNb;
	}

	public ArrayList<Player> getListPlayers() {
		return listPlayers;
	}
	
	//Constructor to create the players based on the options of the Game
	public RoundsManager() {
		RoundsManager.listPlayers = new ArrayList<Player>(4);
		GameOptions gameOp = new GameOptions();
		for(int i = 0; i < gameOp.getNbRealPlayer(); i++) {
			listPlayers.add(i, new RealPlayer(gameOp.getPlayersNames(i),i+1));
			System.out.println("\n"+listPlayers.get(i).getName() + " will play as Player "+ listPlayers.get(i).getNb());
		}
		
		for(int j = gameOp.getNbRealPlayer()+1; j < gameOp.getNbPlayer()+1; j++) {
			listPlayers.add(new VirtualPlayer("AI"+ j,j));
			System.out.println("\n"+listPlayers.get(j-1).getName() + " will play as AIPlayer "+ listPlayers.get(j-1).getNb());
		}
	}
	
	public void firstRound() {
		this.roundNb = 1;
		Deck deck = new Deck();
		System.out.println("\n" + deck.getCards());
		deck.shuffle();
		System.out.println("\nDeck shuffled");
		System.out.println("\n" + deck.getCards());
		
		deck.deal();
		System.out.println("\n________________");
		
		//Revoir la syntaxe de tout ça
		GameOptions gameOp = new GameOptions();
		for(int i = 0; i < gameOp.getNbPlayer(); i++) {
			System.out.println("It's " + listPlayers.get(i).getName() + "'s turn ");
			RoundsManager.listPlayers.get(i).makeOffer();
		}
		this.checkBestOffer().pickOffer();
	}

	//Will be call while the deck as enough cards to deal a new round
	public void nextRound() {
		if(this.roundNb > 0) {
			//Revoir la syntaxe de tout ça
			GameOptions gameOp = new GameOptions();
			for(int i = 0; i < gameOp.getNbPlayer(); i++) {
				RoundsManager.listPlayers.get(i).makeOffer();
			}

			//soit un for soit un for each
			for(int j = 0; j < gameOp.getNbPlayer(); j++) {
				RoundsManager.listPlayers.get(j).pickOffer();
			}
			this.roundNb ++;
		}
	}
	
	//return the player with the best offer
	public Player checkBestOffer() {
		Player bestOfferPlayer = RoundsManager.listPlayers.get(0);
		GameOptions gameOp = new GameOptions();
		for(int i = 0; i < gameOp.getNbPlayer(); i++) {
			for(int j = 0; j < 2; j++) {
				if(RoundsManager.listPlayers.get(i).hand.get(j).isHidden() == false) {

					if(RoundsManager.listPlayers.get(i).hand.get(j).cardValue() > bestOfferPlayer.hand.get(j).cardValue()) {
						bestOfferPlayer = RoundsManager.listPlayers.get(i);				
					}
					else if(RoundsManager.listPlayers.get(i).hand.get(j).cardValue() == bestOfferPlayer.hand.get(j).cardValue()) {
						if(RoundsManager.listPlayers.get(i).hand.get(j).cardTiesValue() > bestOfferPlayer.hand.get(j).cardTiesValue()) {
							bestOfferPlayer = RoundsManager.listPlayers.get(i);				
						}
					}
				}
			}
		}
		System.out.println("\nThe player with the best offer is : " + bestOfferPlayer.getName());
		return bestOfferPlayer;
	}
	
	
	public void countPlayerScore() {
		
	}

	public void printFinalRanking() {
		
	}
}
