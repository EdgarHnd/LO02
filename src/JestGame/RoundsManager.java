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


		for(int i = 0; i < GameOptions.getNbRealPlayer(); i++) {
			Player rplayer = new RealPlayer(GameOptions.getPlayersNames()[i]);
			System.out.println(GameOptions.getPlayersNames()[i]);
			System.out.println(rplayer.getName());
			this.listPlayers.add(rplayer);
		}
		for(int j = 0; j < GameOptions.getNbVirtualPlayer(); j++) {
			Player vplayer = new VirtualPlayer("AI"+ j);
			this.listPlayers.add(vplayer);
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
