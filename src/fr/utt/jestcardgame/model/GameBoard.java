package fr.utt.jestcardgame.model;

import java.util.ArrayList;

import fr.utt.jestcardgame.visitor.Visitor;

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
		for(int i=0; i < this.trophys.size(); i++) {
			switch(this.trophys.get(i).trophy) {
			case HighestClub:
				break;
			case BJnoJoke:
				break;
			case BestJest:
				break;
			case HighestDiamond:
				break;
			case HighestHeart:
				break;
			case HighestSpade:
				break;
			case Joker:
				break;
			case LowestClub:
				break;
			case LowestDiamond:
				break;
			case LowestHeart:
				break;
			case LowestSpade:
				break;
			case MajorityFour:
				break;
			case MajorityTree:
				break;
			case MajorityTwo:
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
