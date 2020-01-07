package fr.utt.jestcardgame.model;

import fr.utt.jestcardgame.observer.Observable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * This class is where a new party is created and managed once the user has entered all the options.
 * The RoundsManager will create the players, instantiate and control the deck actions and
 * it will tell which player what to do and check their actions.
 * This is also where the score and ranking will be stored for the current game.
 *
 * @author Edgar
 * @version 1.0
 */

public class RoundsManager extends Observable{
	
	private static RoundsManager rm= null;
	protected int roundNb = 0;
	protected int currentPlayer;
	protected ArrayList<Player> listPlayers;
	private boolean turnOver = false;
	private GameBoard gb = GameBoard.getInstance();
	private Player finalWinner;
	private String state;
	
	public static RoundsManager getInstance(){
		
		if(rm == null){
			rm = new RoundsManager();
		}
		
		return rm;
	}
	
	//Getters
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
		this.setChanged();
		this.notifyObservers();
	}
	
	public void setTurnOver(boolean turnOver) {
		this.turnOver = turnOver;
	}
	public int getRoundNb() {
		return roundNb;
	}
	public Player getFinalWinner() {
		return finalWinner;
	}

	public ArrayList<Player> getListPlayers() {
		return this.listPlayers;
	}
	
	//Constructor to create the players based on the options of the Game
	public RoundsManager() {
		this.listPlayers = new ArrayList<>(4);
		for(int i = 0; i < OptionsData.getNbRealPlayer(); i++) {
			this.listPlayers.add(i, new Player(OptionsData.getPlayersNames(i),i+1, new RealPlayerStrategy()));
			System.out.println("\n"+listPlayers.get(i).getName() + " will play as Player "+ this.listPlayers.get(i).getNb());
		}
		
		for(int j = OptionsData.getNbRealPlayer()+1; j < OptionsData.getNbPlayer()+1; j++) {
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
		
		
		
		//workinprogress
		System.out.println("gm ob" + GameManager.getInstance().getBoard());
		this.addObserver(GameManager.getInstance().getBoard());
		
		for(int i = 0; i < OptionsData.getNbPlayer(); i++) {
			//add obsever
			this.listPlayers.get(i).addObserver(this.listObserver.get(0));
			}
		deck.deal();
		deck.dealTrophys();
		System.out.println("add ob to gb "+this.listObserver.get(0));
		this.gb.addObserver(this.listObserver.get(0));
		this.gb.notifyOb();
		this.state = "Make offer";
		this.setChanged();
		this.notifyObservers();
		//----------------
		
		System.out.println("\n________________");
		
		for(int i = 0; i < OptionsData.getNbPlayer(); i++) {
			System.out.println("It's " + this.listPlayers.get(i).getName() + "'s turn ");
			this.listPlayers.get(i).makeOffer();
		}
		
		//This is the player with the best offer
		this.showAllOffers();
		this.setState("Pick offer");
		this.setChanged();
		this.notifyObservers();
		this.checkBestOffer().pickOffer();
		
		
		for(int i = 1; i < OptionsData.getNbPlayer(); i++) {
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
			this.setState("Make offer");
			for(int i = 0; i < OptionsData.getNbPlayer(); i++) {
				System.out.println("It's " + this.listPlayers.get(i).getName() + "'s turn ");
				this.listPlayers.get(i).makeOffer();
			}
			
			this.turnOver = false;
			//This is the player with the best offer
			this.showAllOffers();
			this.setState("Pick offer");
			this.checkBestOffer().pickOffer();
			
			
			for(int i = 1; i < OptionsData.getNbPlayer(); i++) {
				if(this.turnOver == false) {
				this.nextPlayer().pickOffer();
				}
			}
		}
		this.state = "GameOver";
		this.setChanged();
		this.notifyObservers();
		System.out.println("No more cards, time to show your JESTS !");
		for(int i = 0; i < OptionsData.getNbPlayer(); i++) {
			//add visitor			
			this.listPlayers.get(i).getScore().visit(this.listPlayers.get(i));
			//add last card one the board to the jest
			this.listPlayers.get(i).getJest().addToJest(this.listPlayers.get(i).getOffer().pollFirst());
			this.listPlayers.get(i).updateJest();
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
			this.gb.visit(p);
		}
		this.gb.giveTrophys();
		for(int i = 0; i < OptionsData.getNbPlayer(); i++) {
			this.listPlayers.get(i).getScore().resetScore();;
			this.listPlayers.get(i).getScore().giveScore();;
			System.out.println(this.listPlayers.get(i).getName()+" Final Jest is : "+this.listPlayers.get(i).getJest().getJestCards().toString()
					+" with a value of : "+this.listPlayers.get(i).getScore().getScore());
			}
	}
	
	public void finalScore() {
		int bestJest = 0;
		Player finalWinner = null;
		for(int i = 0; i < OptionsData.getNbPlayer(); i++) {
			if(this.listPlayers.get(i).getScore().getScore() > bestJest) {
				bestJest = this.listPlayers.get(i).getScore().getScore();
				finalWinner = this.listPlayers.get(i);
			}
		}
		System.out.println(finalWinner.getName()+" win this game with a Jest value of : "+finalWinner.getScore().getScore());
		this.finalWinner = finalWinner;
		this.setChanged();
		this.notifyObservers(finalWinner);
	}

	//return the player with the best offer
	public Player checkBestOffer() {
		//just a default value
		Player bestOfferPlayer = new Player("Default",10, new RealPlayerStrategy());
		bestOfferPlayer.hand.add(new Card(Kind.Default,Suit.None,Trophys.None,"pictures/CardsPng/rulescard.png"));
		bestOfferPlayer.hand.add(new Card(Kind.Default,Suit.None,Trophys.None,"pictures/CardsPng/rulescard.png"));
		bestOfferPlayer.hand.get(0).setHidden(false);
		
		
		for(int i = 0; i < OptionsData.getNbPlayer(); i++) {
					if(this.listPlayers.get(i).offeredCard().cardValue() > bestOfferPlayer.offeredCard().cardValue() 
							&& this.listPlayers.get(i).hasPlayed == false) {
						bestOfferPlayer =this.listPlayers.get(i);					
				}
		}
		for(int h = 0; h < OptionsData.getNbPlayer(); h++) {
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
		for(int i = 0; i < OptionsData.getNbPlayer(); i++) {
			if(this.listPlayers.get(i).isNext) {
				nextToPlay =this.listPlayers.get(i);					
			}
		}
		nextToPlay.isNext = false;
		return nextToPlay;
	}
	
	public void showAllOffers() {
		for(int i = 0; i < OptionsData.getNbPlayer(); i++) {
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
		for(int i = 0; i < OptionsData.getNbPlayer(); i++) {
			if((this.listPlayers.get(i).hasCompleteOffer() && this.listPlayers.get(i).isPicking == false)){
				System.out.println("("+this.listPlayers.get(i).getNb()+") : "+this.listPlayers.get(i).getName()+" offer : "+ 
				this.listPlayers.get(i).offeredCard() + " and a hidden card");
			}
		}
	}

	public ArrayList<Integer> getListNbOffers(){
		ArrayList<Integer> listNb = new ArrayList<>(4);
		for(int i = 0; i < OptionsData.getNbPlayer(); i++) {
			if((this.listPlayers.get(i).hasCompleteOffer() && this.listPlayers.get(i).isPicking == false)){
				listNb.add(this.listPlayers.get(i).getNb());
			}
		}
		return listNb;
	}
	
}
