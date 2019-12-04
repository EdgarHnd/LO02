package fr.utt.jestcardgame.model;

import fr.utt.jestcardgame.visitor.Visitor;

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

public class RoundsManager implements Visitor {
	
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
			System.out.println("\n" + Deck.getInstance().getCards());
			Deck.getInstance().gather();
			Deck.getInstance().dealStack();
			for(int i = 0; i < GameOptions.getNbPlayer(); i++) {
				System.out.println("It's " + this.listPlayers.get(i).getName() + "'s turn ");
				this.listPlayers.get(i).makeOffer();
			}
			
			//This is the player with the best offer
			this.showAllOffers();
			this.checkBestOffer().pickOffer();
			
			
			for(int i = 1; i < GameOptions.getNbPlayer(); i++) {
				this.nextPlayer().pickOffer();
			}
		}
		System.out.println("No more cards, time to show your JESTS !");
		for(int i = 0; i < GameOptions.getNbPlayer(); i++) {
			this.listPlayers.get(i).jest.add(this.listPlayers.get(i).getOffer().pollFirst());
			this.listPlayers.get(i).calculateJestValue();
			System.out.println(this.listPlayers.get(i).getName()+" Jest is : "+this.listPlayers.get(i).jest.toString()
					+" with a value of : "+this.listPlayers.get(i).jestValue);
			}
	}
	
	public void giveTrophy() {
		System.out.println("The Trophys for this game are : "+GameBoard.getInstance().getTrophys());
		GameBoard.getInstance().giveTrophys();
		for(int i = 0; i < GameOptions.getNbPlayer(); i++) {
			this.listPlayers.get(i).resetJest();
			this.listPlayers.get(i).calculateJestValue();
			System.out.println(this.listPlayers.get(i).getName()+" Final Jest is : "+this.listPlayers.get(i).jest.toString()
					+" with a value of : "+this.listPlayers.get(i).jestValue);
			}
	}
	
	public void finalScore() {
		int bestJest = 0;
		Player finalWinner = null;
		for(int i = 0; i < GameOptions.getNbPlayer(); i++) {
			if(this.listPlayers.get(i).jestValue > bestJest) {
				bestJest = this.listPlayers.get(i).jestValue;
				finalWinner = this.listPlayers.get(i);
			}
		}
		System.out.println(finalWinner.getName()+" win this game with a Jest value of : "+finalWinner.jestValue);
	}
	
	//return the player with the best offer
	public Player checkBestOffer() {
		//just a default value
		Player bestOfferPlayer = new RealPlayer("Default",10);
		bestOfferPlayer.hand.add(new Card(Kind.Default,Suit.None,Trophy.None));
		bestOfferPlayer.hand.add(new Card(Kind.Default,Suit.None,Trophy.None));
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
			if((this.listPlayers.get(i).completeOffer() && this.listPlayers.get(i).isPicking == false)){
				
				System.out.println("("+this.listPlayers.get(i).getNb()+") : "+this.listPlayers.get(i).getName()+" offer : "+ 
				this.listPlayers.get(i).offeredCard() + " and a hidden card");
			}
		}
	}
	
	
	public void countPlayerScore() {
		
	}

	public void printFinalRanking() {
		
	}

	@Override
	public void update(String str) {
		// TODO Auto-generated method stub
		
	}
}
