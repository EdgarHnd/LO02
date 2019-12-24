package fr.utt.jestcardgame.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import fr.utt.jestcardgame.observer.Observer;

/**
 * This class is where a new party is created and managed once the user has entered all the options.
 * The RoundsManager will create the players, instantiate and control the deck actions and
 * it will tell which player what to do and check their actions.
 * This is also where the score and ranking will be stored for the current game.
 *
 * @author Edgar
 * @version 1.0
 */

public class RoundsManager implements Observer {
	
	private static RoundsManager rm= null;
	protected int roundNb = 0;
	protected int currentPlayer;
	protected ArrayList<Player> listPlayers;
	private boolean turnOver = false;
	
	public static RoundsManager getInstance(){
		
		if(rm == null){
			rm = new RoundsManager();
		}
		
		return rm;
	}
	
	//Getters
	
	public void setTurnOver(boolean turnOver) {
		this.turnOver = turnOver;
	}
	public int getRoundNb() {
		return roundNb;
	}

	public ArrayList<Player> getListPlayers() {
		return this.listPlayers;
	}
	
	//Constructor to create the players based on the options of the Game
	public RoundsManager() {
		this.listPlayers = new ArrayList<>(4);
		for(int i = 0; i < GameOptions.getNbRealPlayer(); i++) {
			this.listPlayers.add(i, new Player(GameOptions.getPlayersNames(i),i+1, new RealPlayerStrategy()));
			System.out.println("\n"+listPlayers.get(i).getName() + " will play as Player "+ this.listPlayers.get(i).getNb());
		}
		
		for(int j = GameOptions.getNbRealPlayer()+1; j < GameOptions.getNbPlayer()+1; j++) {
			Random rand = new Random();
			int randomNb = rand.nextInt(2) + 1;
			switch (randomNb){
				case 1 :
					this.listPlayers.add(new Player("AI"+ j,j, new OffensiveStrategy()));
					break;
				case 2 :
					this.listPlayers.add(new Player("AI"+ j,j, new DefensiveStrategy()));
					break;
				/*case 3 :
					this.listPlayers.add(new Player("AI"+ j,j, new ModerateStrategy()));
					break;*/
				default:
					break;
			}
			System.out.println("\n"+this.listPlayers.get(j-1).getName() + " will play as AIPlayer "+ this.listPlayers.get(j-1).getNb());
		}
	}
	
	public void firstRound() {
		this.roundNb = 1;
		Deck deck = Deck.getInstance();
		
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
		
		//This is the player with the best offer
		this.showAllOffers();
		this.checkBestOffer().pickOffer();
		
		
		for(int i = 1; i < GameOptions.getNbPlayer(); i++) {
			if(!this.turnOver) {
			this.nextPlayer().pickOffer();
			}
		}
	}


	//Will be call while the deck as enough cards to deal a new round
	public void nextRounds() {
		while (Deck.getInstance().getCards().size() >= this.listPlayers.size()){
			System.out.println("\nThe deck still has :" + Deck.getInstance().getCards());
			Deck.getInstance().gather();
			Deck.getInstance().dealStack();
			for(int i = 0; i < GameOptions.getNbPlayer(); i++) {
				System.out.println("It's " + this.listPlayers.get(i).getName() + "'s turn ");
				this.listPlayers.get(i).makeOffer();
			}
			
			this.turnOver = false;
			//This is the player with the best offer
			this.showAllOffers();
			this.checkBestOffer().pickOffer();
			
			
			for(int i = 1; i < GameOptions.getNbPlayer(); i++) {
				if(this.turnOver == false) {
				this.nextPlayer().pickOffer();
				}
			}
		}
		System.out.println("No more cards, time to show your JESTS !");
		for(int i = 0; i < GameOptions.getNbPlayer(); i++) {
			//add visitor			
			this.listPlayers.get(i).getScore().visit(this.listPlayers.get(i));
			//add last card one the board to the jest
			this.listPlayers.get(i).getJest().addToJest(this.listPlayers.get(i).getOffer().pollFirst());
			this.listPlayers.get(i).getScore().giveScore();
			System.out.println(this.listPlayers.get(i).getName()+" Jest is : "+this.listPlayers.get(i).getJest().getJestCards().toString()
					+" with a value of : "+this.listPlayers.get(i).getScore().getScore());
		}
	}
	
	public void giveTrophy() {
		System.out.println("The Trophys for this game are : "+GameBoard.getInstance().getTrophys());
		//add visitor
		Iterator<Player> j= this.listPlayers.iterator();
		while(j.hasNext()) {
			Player p = j.next();
			GameBoard.getInstance().visit(p);
		}
		GameBoard.getInstance().giveTrophys();
		for(int i = 0; i < GameOptions.getNbPlayer(); i++) {
			this.listPlayers.get(i).getScore().resetScore();;
			this.listPlayers.get(i).getScore().giveScore();;
			System.out.println(this.listPlayers.get(i).getName()+" Final Jest is : "+this.listPlayers.get(i).getJest().getJestCards().toString()
					+" with a value of : "+this.listPlayers.get(i).getScore().getScore());
			}
	}
	
	public void finalScore() {
		int bestJest = 0;
		Player finalWinner = null;
		for(int i = 0; i < GameOptions.getNbPlayer(); i++) {
			if(this.listPlayers.get(i).getScore().getScore() > bestJest) {
				bestJest = this.listPlayers.get(i).getScore().getScore();
				finalWinner = this.listPlayers.get(i);
			}
		}
		System.out.println(finalWinner.getName()+" win this game with a Jest value of : "+finalWinner.getScore().getScore());
	}
	
	//return the player with the best offer
	public Player checkBestOffer() {
		//just a default value
		Player bestOfferPlayer = new Player("Default",10, new RealPlayerStrategy());
		bestOfferPlayer.hand.add(new Card(Kind.Default,Suit.None,Trophys.None));
		bestOfferPlayer.hand.add(new Card(Kind.Default,Suit.None,Trophys.None));
		bestOfferPlayer.hand.get(0).setHidden(false);
		
		
		for(int i = 0; i < GameOptions.getNbPlayer(); i++) {
					if(this.listPlayers.get(i).offeredCard().cardValue() > bestOfferPlayer.offeredCard().cardValue() 
							&& this.listPlayers.get(i).hasPlayed == false) {
						bestOfferPlayer =this.listPlayers.get(i);					
				}
		}
		for(int h = 0; h < GameOptions.getNbPlayer(); h++) {
					if(this.listPlayers.get(h).offeredCard().cardValue() == bestOfferPlayer.offeredCard().cardValue()
							&& this.listPlayers.get(h).hasPlayed == false) {
						if(this.listPlayers.get(h).offeredCard().cardTiesValue() > bestOfferPlayer.offeredCard().cardTiesValue()) {
							bestOfferPlayer = this.listPlayers.get(h);				
						}
					}	
		}
		if(bestOfferPlayer.getName()!="Default") {
			System.out.println("\nThe player with the best offer is : " + bestOfferPlayer.getName());
			bestOfferPlayer.setIsPicking(true);
		}
		return bestOfferPlayer;
	}
	
	public Player nextPlayer() {
		Player nextToPlay = null;
		for(int i = 0; i < GameOptions.getNbPlayer(); i++) {
			if(this.listPlayers.get(i).isNext) {
				nextToPlay =this.listPlayers.get(i);					
			}
		}
		nextToPlay.isNext = false;
		return nextToPlay;
	}
	
	public void showAllOffers() {
		for(int i = 0; i < GameOptions.getNbPlayer(); i++) {
			if((this.listPlayers.get(i).hiddenCard()!=null && 
					this.listPlayers.get(i).offeredCard()!=null)){
				System.out.println(this.listPlayers.get(i).getName()+" offer : "+ this.listPlayers.get(i).getOffer().get(0)
				+" and a hidden card");
			}
			else if((this.listPlayers.get(i).hiddenCard()==null && 
					this.listPlayers.get(i).offeredCard()!=null)) {
				System.out.println(this.listPlayers.get(i).getName()+" offer : "+ 
						this.listPlayers.get(i).offeredCard());
			}
			else if((this.listPlayers.get(i).hiddenCard()!=null && 
					this.listPlayers.get(i).offeredCard()==null)) {
				System.out.println(this.listPlayers.get(i).getName()+" offer : a hidden card");
			}
			else {
				System.out.println(this.listPlayers.get(i).getName()+" has nothing left to offer");
			}
		}
	}
	
	public void showValidOffers() {
		for(int i = 0; i < GameOptions.getNbPlayer(); i++) {
			if((this.listPlayers.get(i).hasCompleteOffer() && this.listPlayers.get(i).isPicking == false)){
				System.out.println("("+this.listPlayers.get(i).getNb()+") : "+this.listPlayers.get(i).getName()+" offer : "+ 
				this.listPlayers.get(i).offeredCard() + " and a hidden card");
			}
		}
	}

	public int getMinValidOffer() {
		int min = 10;
		for (int i = 0; i < GameOptions.getNbPlayer(); i++) {
			Player playerSelected = this.listPlayers.get(i);
			if (playerSelected.hasCompleteOffer() && playerSelected.isPicking == false && playerSelected.getNb() < min) {
				min = this.listPlayers.get(i).getNb();
			}
		}
		return min;
	}

	public int getMaxValidOffer(){
		int max = 0;
		for (int i = 0; i < GameOptions.getNbPlayer(); i++) {
			Player playerSelected = this.listPlayers.get(i);
			if (playerSelected.hasCompleteOffer() && playerSelected.isPicking == false && playerSelected.getNb() > max) {
				max = this.listPlayers.get(i).getNb();
			}
		}
		return max;
	}

	public void printFinalRanking() {
		
	}

	@Override
	public void update(String str) {
		// TODO Auto-generated method stub
		
	}
}
