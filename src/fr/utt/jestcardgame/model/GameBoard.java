package fr.utt.jestcardgame.model;

import java.util.ArrayList;
import java.util.Iterator;
import fr.utt.jestcardgame.visitor.Visitor;

public class GameBoard implements Visitor {
	
	private static GameBoard gb= null;
	private ArrayList<Card> trophys;
	private ArrayList<Player> listPlayer;
	private Player trophyWinner;
	
	
	public static GameBoard getInstance(){
		
		if(gb == null){
			gb = new GameBoard();
		}
		
		return gb;
	}
	
	public GameBoard() {
		trophys = new ArrayList<Card>(2);
		listPlayer = new ArrayList<Player>(4);
	}
	
	public ArrayList<Card> getTrophys() {
		return trophys;
	}
	public void giveTrophys() {
		Iterator<Card> j = this.trophys.iterator();
		while(j.hasNext()) {
			Card t = j.next();
			Iterator<Player> i = this.listPlayer.iterator();
			Player winner = new Player("Default2",11, new RealPlayerStrategy());
			while(i.hasNext()) {
				Player p = i.next();
				if(t.trophy.deserveTrophy(p) > t.trophy.deserveTrophy(winner)) {
					winner = p;
				}
				//If tie when BJest check the TieValues
				else if(t.trophy.deserveTrophy(p) == t.trophy.deserveTrophy(winner) 
						&& (t.trophy == Trophys.BestJest || t.trophy == Trophys.BJnoJoke)) {
					if(p.getJest().highestFaceValue() > winner.getJest().highestFaceValue()) {
						winner = p;
					}
					else if(p.getJest().highestFaceValue() == winner.getJest().highestFaceValue()) {
						if(p.getJest().highestFaceTiesValue() > winner.getJest().highestFaceTiesValue()) {
							winner = p;
						}
					}
				}
				//If tie when Majority check TieValues
				else if(t.trophy.deserveTrophy(p) == t.trophy.deserveTrophy(winner)) {
					if(p.getJest().highestFaceTiesValue() > winner.getJest().highestFaceTiesValue()) {
						winner = p;
					}
				}
			}
			winner.getJest().addToJest(t);
			System.out.println(winner.getName()+" receives the trophy : "+t);
		}
	}

	@Override
	public void visit(Player p) {
		this.listPlayer.add(p);
	}

	public Player getTrophyWinner() {
		return trophyWinner;
	}

	public void setTrophyWinner(Player trophyWinner) {
		this.trophyWinner = trophyWinner;
	}
}
