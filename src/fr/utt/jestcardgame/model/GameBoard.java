package fr.utt.jestcardgame.model;

import fr.utt.jestcardgame.visitor.Visitor;

import java.util.ArrayList;

public class GameBoard implements Visitor {
	
	private ArrayList<Card> trophys;
	private static GameBoard gb= null;
	
	public static GameBoard getInstance(){
		
		if(gb == null){
			gb = new GameBoard();
		}
		
		return gb;
	}
	
	public GameBoard() {
		trophys = new ArrayList<Card>(2);
	}
	
	public ArrayList<Card> getTrophys() {
		return trophys;
	}
	
	//Give the trophys to the players
	public void giveTrophys() {
		for(int j=0; j < this.trophys.size(); j++) {
			Player winner = new Player("Dummy", -1);
			int maj = 0;
			switch(this.trophys.get(j).trophy) {	
			case BJnoJoke:
				for(int i = 0; i < GameOptions.getNbPlayer(); i++) {
					int jest = 0;
					if(
							RoundsManager.getInstance().listPlayers.get(i).jestValue > jest &&
							RoundsManager.getInstance().listPlayers.get(i).hasJoker() == false
					) {
						jest = RoundsManager.getInstance().listPlayers.get(i).jestValue;
						winner = RoundsManager.getInstance().listPlayers.get(i);
					}
				}
				winner.jest.add(this.trophys.get(j));
				System.out.println(winner.getName()+" recieve the trophy : "+this.trophys.get(j));
				break;
			case BestJest:
				for(int i = 0; i < GameOptions.getNbPlayer(); i++) {
					int jest = 0;
					if(RoundsManager.getInstance().listPlayers.get(i).jestValue > jest) {
						jest = RoundsManager.getInstance().listPlayers.get(i).jestValue;
						winner = RoundsManager.getInstance().listPlayers.get(i);
					}
				}
				winner.jest.add(this.trophys.get(j));
				System.out.println(winner.getName()+" recieve the trophy : "+this.trophys.get(j));
				break;
			case Joker:
				for(int i = 0; i < GameOptions.getNbPlayer(); i++) {
					if(RoundsManager.getInstance().listPlayers.get(i).hasJoker()) {
						winner = RoundsManager.getInstance().listPlayers.get(i);
					}
				}
				winner.jest.add(this.trophys.get(j));
				System.out.println(winner.getName()+" recieve the trophy : "+this.trophys.get(j));
				break;
			case HighestClub:
				for(int i = 0; i < GameOptions.getNbPlayer(); i++) {
					int high = 0;
					if(RoundsManager.getInstance().listPlayers.get(i).highestSuit(Suit.Clubs) > high) {
						high = RoundsManager.getInstance().listPlayers.get(i).highestSuit(Suit.Clubs);
						winner = RoundsManager.getInstance().listPlayers.get(i);
					}
				}
				winner.jest.add(this.trophys.get(j));
				System.out.println(winner.getName()+" recieve the trophy : "+this.trophys.get(j));
				break;
			case HighestDiamond:
				for(int i = 0; i < GameOptions.getNbPlayer(); i++) {
					int high = 0;
					if(RoundsManager.getInstance().listPlayers.get(i).highestSuit(Suit.Diamonds) > high) {
						high = RoundsManager.getInstance().listPlayers.get(i).highestSuit(Suit.Diamonds);
						winner = RoundsManager.getInstance().listPlayers.get(i);
					}
				}
				winner.jest.add(this.trophys.get(j));
				System.out.println(winner.getName()+" recieve the trophy : "+this.trophys.get(j));
				break;
			case HighestHeart:
				for(int i = 0; i < GameOptions.getNbPlayer(); i++) {
					int high = 0;
					if(RoundsManager.getInstance().listPlayers.get(i).highestSuit(Suit.Hearts) > high) {
						high = RoundsManager.getInstance().listPlayers.get(i).highestSuit(Suit.Hearts);
						winner = RoundsManager.getInstance().listPlayers.get(i);
					}
				}
				winner.jest.add(this.trophys.get(j));
				System.out.println(winner.getName()+" recieve the trophy : "+this.trophys.get(j));
				break;
			case HighestSpade:
				for(int i = 0; i < GameOptions.getNbPlayer(); i++) {
					int high = 0;
					if(RoundsManager.getInstance().listPlayers.get(i).highestSuit(Suit.Spades) > high) {
						high = RoundsManager.getInstance().listPlayers.get(i).highestSuit(Suit.Spades);
						winner = RoundsManager.getInstance().listPlayers.get(i);
					}
				}
				winner.jest.add(this.trophys.get(j));
				System.out.println(winner.getName()+" recieve the trophy : "+this.trophys.get(j));
				break;
			
			case LowestClub:
				for(int i = 0; i < GameOptions.getNbPlayer(); i++) {
					int low = 5;
					if(RoundsManager.getInstance().listPlayers.get(i).lowestSuit(Suit.Clubs) < low) {
						low = RoundsManager.getInstance().listPlayers.get(i).lowestSuit(Suit.Clubs);
						winner = RoundsManager.getInstance().listPlayers.get(i);
					}
				}
				winner.jest.add(this.trophys.get(j));
				System.out.println(winner.getName()+" recieve the trophy : "+this.trophys.get(j));
				break;
			case LowestDiamond:
				for(int i = 0; i < GameOptions.getNbPlayer(); i++) {
					int low = 5;
					if(RoundsManager.getInstance().listPlayers.get(i).lowestSuit(Suit.Diamonds) < low) {
						low = RoundsManager.getInstance().listPlayers.get(i).lowestSuit(Suit.Diamonds);
						winner = RoundsManager.getInstance().listPlayers.get(i);
					}
				}
				winner.jest.add(this.trophys.get(j));
				System.out.println(winner.getName()+" recieve the trophy : "+this.trophys.get(j));
				break;
			case LowestHeart:
				for(int i = 5; i < GameOptions.getNbPlayer(); i++) {
					int low = 5;
					if(RoundsManager.getInstance().listPlayers.get(i).lowestSuit(Suit.Hearts) < low) {
						low = RoundsManager.getInstance().listPlayers.get(i).lowestSuit(Suit.Hearts);
						winner = RoundsManager.getInstance().listPlayers.get(i);
					}
				}
				winner.jest.add(this.trophys.get(j));
				System.out.println(winner.getName()+" recieve the trophy : "+this.trophys.get(j));
				break;
			case LowestSpade:
				for(int i = 0; i < GameOptions.getNbPlayer(); i++) {
					int low = 5;
					if(RoundsManager.getInstance().listPlayers.get(i).lowestSuit(Suit.Spades) < low) {
						low = RoundsManager.getInstance().listPlayers.get(i).lowestSuit(Suit.Spades);
						winner = RoundsManager.getInstance().listPlayers.get(i);
					}
				}
				winner.jest.add(this.trophys.get(j));
				System.out.println(winner.getName()+" recieve the trophy : "+this.trophys.get(j));
				break;
			case MajorityFour:
				for(int i = 0; i < GameOptions.getNbPlayer(); i++) {
					if(RoundsManager.getInstance().listPlayers.get(i).majority(Kind.Four) > maj) {
						maj = RoundsManager.getInstance().listPlayers.get(i).majority(Kind.Four);
						winner = RoundsManager.getInstance().listPlayers.get(i);
					}
				}
				for(int h = 0; h < GameOptions.getNbPlayer(); h++) {
					if(RoundsManager.getInstance().listPlayers.get(j).majority(Kind.Four) == maj) {
						if(RoundsManager.getInstance().listPlayers.get(j).tieMajority(Kind.Four) > winner.tieMajority(Kind.Four)) {
							winner = RoundsManager.getInstance().listPlayers.get(j);		
						}
					}	
				}
				winner.jest.add(this.trophys.get(j));
				System.out.println(winner.getName()+" recieve the trophy : "+this.trophys.get(j));
				break;
			case MajorityTree:
				for(int i = 0; i < GameOptions.getNbPlayer(); i++) {
					if(RoundsManager.getInstance().listPlayers.get(i).majority(Kind.Three) > maj) {
						maj = RoundsManager.getInstance().listPlayers.get(i).majority(Kind.Three);
						winner = RoundsManager.getInstance().listPlayers.get(i);
					}
				}
				for(int h = 0; h < GameOptions.getNbPlayer(); h++) {
					if(RoundsManager.getInstance().listPlayers.get(j).majority(Kind.Three) == maj) {
						if(RoundsManager.getInstance().listPlayers.get(j).tieMajority(Kind.Three) > winner.tieMajority(Kind.Three)) {
							winner = RoundsManager.getInstance().listPlayers.get(j);		
						}
					}	
				}
				winner.jest.add(this.trophys.get(j));
				System.out.println(winner.getName()+" recieve the trophy : "+this.trophys.get(j));
				break;
			case MajorityTwo:
				for(int i = 0; i < GameOptions.getNbPlayer(); i++) {
					if(RoundsManager.getInstance().listPlayers.get(i).majority(Kind.Two) > maj) {
						maj = RoundsManager.getInstance().listPlayers.get(i).majority(Kind.Two);
						winner = RoundsManager.getInstance().listPlayers.get(i);
					}
				}
				for(int h = 0; h < GameOptions.getNbPlayer(); h++) {
					if(RoundsManager.getInstance().listPlayers.get(j).majority(Kind.Two) == maj) {
						if(RoundsManager.getInstance().listPlayers.get(j).tieMajority(Kind.Two) > winner.tieMajority(Kind.Two)) {
							winner = RoundsManager.getInstance().listPlayers.get(j);		
						}
					}	
				}
				winner.jest.add(this.trophys.get(j));
				System.out.println(winner.getName()+" recieve the trophy : "+this.trophys.get(j));
				break;
			case None:
				break;
			default:
				break;
			}
		}
	}
	
	
	@Override
	public void update(String str) {
		// TODO Auto-generated method stub
		
	}
}
