package JestGame;

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
	protected ArrayList<Player> listPlayers;
	
	
	//Constructor to create the players based on the options of the Game
	public RoundsManager() {
		
		this.listPlayers = new ArrayList<Player>(4);
		
		for(int i = 0; i < GameOptions.getNbRealPlayer(); i++) {
			
			this.listPlayers.add(i,new RealPlayer(GameOptions.getPlayersNames(i),i+1));
			System.out.println(this.listPlayers.get(i).getName() + " will play as Player "+ this.listPlayers.get(i).getNb());
			
		}
		
		for(int j = GameOptions.getNbRealPlayer()+1; j < GameOptions.getNbPlayer()+1; j++) {
			
			this.listPlayers.add(new VirtualPlayer("AI"+ j,j));
			System.out.println(this.listPlayers.get(j-1).getName() + " will play as AIPlayer "+ this.listPlayers.get(j-1).getNb());
			
		}
	}
	
	public void firstRound() {
		Deck deck = new Deck();
		deck.shuffle();
		deck.deal();
		
		//Revoir la syntaxe de tout ça
		for(int i = 0; i < GameOptions.getNbPlayer(); i++) {
			this.listPlayers.get(i).makeOffer();
		}
		//soit un for soit un for each
		for(int j = 0; j < GameOptions.getNbPlayer(); j++) {
			this.listPlayers.get(j).pickOffer();
		}
		
		this.roundNb = 1;
		
	}
	
	//Will be call while the deck as enought cards to deal a new round
	public void nextRound() {
		if(this.roundNb > 0) {
			//Revoir la syntaxe de tout ça
			for(int i = 0; i < GameOptions.getNbPlayer(); i++) {
				this.listPlayers.get(i).makeOffer();
			}
			//soit un for soit un for each
			for(int j = 0; j < GameOptions.getNbPlayer(); j++) {
				this.listPlayers.get(j).pickOffer();
			}
			
			this.roundNb ++;
		}
	}
	
	//change the players order in the list based on their offer strengh
	public void checkBestOffer() {
		
	}
	
	
	public void countPlayerScore() {
		
	}

	public void printFinalRanking() {
		
	}
}
