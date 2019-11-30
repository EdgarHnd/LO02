package fr.utt.jestcardgame.model;

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
	
	private static RoundsManager rm= null;
	protected int roundNb = 0;
	protected int currentPlayer;
	protected ArrayList<Player> listPlayers;
	
	public static RoundsManager getInstance(){
		
		if(rm == null){
			rm = new RoundsManager();
		}
		
		return rm;
	}
	
	//Getters
	public int getRoundNb() {
		return roundNb;
	}

	public ArrayList<Player> getListPlayers() {
		return this.listPlayers;
	}
	
	//Constructor to create the players based on the options of the Game
	public RoundsManager() {
		System.out.println("\nNew Game Created");
		this.listPlayers = new ArrayList<Player>(4);
		for(int i = 0; i < GameOptions.getNbRealPlayer(); i++) {
			this.listPlayers.add(i, new RealPlayer(GameOptions.getPlayersNames(i),i+1));
			System.out.println("\n"+listPlayers.get(i).getName() + " will play as Player "+ this.listPlayers.get(i).getNb());
		}
		
		for(int j = GameOptions.getNbRealPlayer()+1; j < GameOptions.getNbPlayer()+1; j++) {
			this.listPlayers.add(new VirtualPlayer("AI"+ j,j));
			System.out.println("\n"+this.listPlayers.get(j-1).getName() + " will play as AIPlayer "+ this.listPlayers.get(j-1).getNb());
		}
	}
	
	public void firstRound() {
		this.roundNb = 1;
		Deck deck = Deck.getInstance();
		GameBoard gameboard = GameBoard.getInstance();
		
		System.out.println("\n" + deck.getCards());
		deck.shuffle();
		System.out.println("\nDeck shuffled");
		System.out.println("\n" + deck.getCards());
		
		deck.deal();
		deck.dealTrophys();
		System.out.println("\n________________");
		
		for(int i = 0; i < GameOptions.getNbPlayer(); i++) {
			System.out.println("It's " + this.listPlayers.get(i).getName() + "'s turn ");
			this.listPlayers.get(i).makeOffer();
		}
		
		for(int i = 0; i < GameOptions.getNbPlayer(); i++) {
			
		}
		
		//Store the offers in the gameboard
		gameboard.gatherOffers();
		//This is the player with the best offer
		this.checkBestOffer().pickOffer();
		//Show him the offers available
		
		
	}

	//Will be call while the deck as enough cards to deal a new round
	public void nextRound() {
		while (Deck.getInstance() != null) {
			if (this.roundNb > 0) {
				//Revoir la syntaxe de tout ça
				for (int i = 0; i < GameOptions.getNbPlayer(); i++) {
					this.listPlayers.get(i).makeOffer();

				}

				//soit un for soit un for each
				for (int j = 0; j < GameOptions.getNbPlayer(); j++) {

					this.listPlayers.get(j).pickOffer();
				}
				this.roundNb++;
			}
		}
	}
	
	//return the player with the best offer
	public Player checkBestOffer() {

		Player bestOfferPlayer = this.listPlayers.get(0);
		for(int i = 1; i < GameOptions.getNbPlayer(); i++) {
					if(this.listPlayers.get(i).offeredCard().cardValue() > bestOfferPlayer.offeredCard().cardValue()) {
						bestOfferPlayer =this.listPlayers.get(i);					
				}
		}
		for(int h = 1; h < GameOptions.getNbPlayer(); h++) {
					if(this.listPlayers.get(h).offeredCard().cardValue() == bestOfferPlayer.offeredCard().cardValue()) {
						if(this.listPlayers.get(h).offeredCard().cardTiesValue() > bestOfferPlayer.offeredCard().cardTiesValue()) {
							bestOfferPlayer = this.listPlayers.get(h);				
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
