package fr.utt.jestcardgame.model;

import java.util.ArrayList;

public class GameBoard {
	
//	private ArrayList<Card> offers;
//	private ArrayList<Card> jests;
	private ArrayList<Card> trophys;
	private static GameBoard gb= null;
	
	public static GameBoard getInstance(){
		
		if(gb == null){
			gb = new GameBoard();
		}
		
		return gb;
	}
	
	public GameBoard() {
//		offers = new ArrayList<Card>(8);
//		jests = new ArrayList<Card>(40);
		trophys = new ArrayList<Card>(2);
	}
	
	public ArrayList<Card> getTrophys() {
		return trophys;
	}

/*	public ArrayList<Card> getOffers() {
		return offers;
	}

	
	public ArrayList<Card> getJests() {
		return jests;
	}
	
	public void gatherOffers() {
		for(int i = 0; i < GameOptions.getNbPlayer(); i++) {
			this.offers.add(RoundsManager.getInstance().listPlayers.get(i).offeredCard());
			this.offers.add(RoundsManager.getInstance().listPlayers.get(i).hiddenCard());
		}
	}
	public void showOffers() {
		for(int i = 0; i < GameOptions.getNbPlayer(); i++) {
			if((RoundsManager.getInstance().listPlayers.get(i).hiddenCard()!=null && 
					RoundsManager.getInstance().listPlayers.get(i).offeredCard()!=null)){
				System.out.println(RoundsManager.getInstance().listPlayers.get(i).getName()+" offer : "+ 
				"("+((i+i)+1)+")"+RoundsManager.getInstance().listPlayers.get(i).offeredCard()+"("+((i+1)*2)+") and a hidden card");
			}
			else if((RoundsManager.getInstance().listPlayers.get(i).hiddenCard()==null && 
					RoundsManager.getInstance().listPlayers.get(i).offeredCard()!=null)) {
				System.out.println(RoundsManager.getInstance().listPlayers.get(i).getName()+" offer : "+ 
				RoundsManager.getInstance().listPlayers.get(i).offeredCard());
			}
			else if((RoundsManager.getInstance().listPlayers.get(i).hiddenCard()!=null && 
					RoundsManager.getInstance().listPlayers.get(i).offeredCard()==null)) {
				System.out.println(RoundsManager.getInstance().listPlayers.get(i).getName()+" offer : a hidden card");
			}
			else {
				System.out.println(RoundsManager.getInstance().listPlayers.get(i).getName()+" has nothing to offer");
			}
		}
	}*/
}
