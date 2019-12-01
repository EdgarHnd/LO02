package fr.utt.jestcardgame.model;

import java.util.ArrayList;

public class GameBoard {
	
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
}
