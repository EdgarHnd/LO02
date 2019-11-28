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

	private int nbPlayer;
	private int nbRealPlayer;
	private int nbVirtualPlayer;
	protected int roundNb = 0;
	protected int currentPlayer;
	protected static ArrayList<Player> listPlayers = new ArrayList<>(4);
	
	//Getters
	public int getRoundNb() {
		return roundNb;
	}

	public ArrayList<Player> getListPlayers() {
		return listPlayers;
	}
	
	//Constructor to create the players based on the options of the Game
	public RoundsManager(int nbPlayer, int nbRealPlayer, int nbVirtualPlayer) {
		this.nbPlayer = nbPlayer;
		this.nbRealPlayer = nbRealPlayer;
		this.nbVirtualPlayer = nbVirtualPlayer;

		for(int i = 0; i < nbRealPlayer; i++) {
			listPlayers.add(i, new RealPlayer(GameOptions.getPlayersNames(i),i+1));
			System.out.println("\n"+listPlayers.get(i).getName() + " will play as Player "+ listPlayers.get(i).getNb());
		}
		
		for(int j = nbRealPlayer+1; j <nbPlayer+1; j++) {
			listPlayers.add(new VirtualPlayer("AI"+ j,j));
			System.out.println("\n"+listPlayers.get(j-1).getName() + " will play as AIPlayer "+ listPlayers.get(j-1).getNb());
		}
	}
	
	public void firstRound() {
		this.roundNb = 1;
		Deck deck = new Deck(nbPlayer);
		System.out.println("\n" + deck.getCards());
		deck.shuffle();
		System.out.println("\nDeck shuffled");
		System.out.println("\n" + deck.getCards());
		
		deck.deal();
		System.out.println("\n________________");
		
		//Revoir la syntaxe de tout ça
		for(int i = 0; i < nbPlayer; i++) {
			System.out.println("It's " + listPlayers.get(i).getName() + "'s turn ");
			listPlayers.get(i).makeOffer();
		}
		this.checkBestOffer().pickOffer();
	}

	//Will be called while the deck as enough cards to deal a new round
	public void nextRound() {
		if(this.roundNb > 0) {
			//Revoir la syntaxe de tout ça
			for(int i = 0; i < nbPlayer; i++) {
				listPlayers.get(i).makeOffer();
			}

			//soit un for soit un for each
			for(int j = 0; j < nbPlayer; j++) {
				listPlayers.get(j).pickOffer();
			}
			this.roundNb ++;
		}
	}
	
	//return the player with the best offer
	public Player checkBestOffer() {
		Player bestOfferPlayer = listPlayers.get(0);
		for(int i = 1; i < nbPlayer; i++) {
			//Il y a un problème avec la list de joueurs...
			if(listPlayers.get(i).offeredCard().cardValue() > bestOfferPlayer.offeredCard().cardValue()) {
				bestOfferPlayer = listPlayers.get(i);
			}
		}
		for(int h = 1; h < nbPlayer; h++) {
			if(listPlayers.get(h).offeredCard().cardValue() == bestOfferPlayer.offeredCard().cardValue()) {
				if(listPlayers.get(h).offeredCard().cardTiesValue() > bestOfferPlayer.offeredCard().cardTiesValue()) {
					bestOfferPlayer = listPlayers.get(h);
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
