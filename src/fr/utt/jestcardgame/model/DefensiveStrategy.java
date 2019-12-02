package fr.utt.jestcardgame.model;

public class DefensiveStrategy implements ChooseStrategy {

	private int weakestCardValue;
	private int index;
	private int weakestCardValueToPick;

	@Override
	public void makeOfferStrategy(Player player) {
		weakestCardValue = player.hand.get(0).cardValue();
		for (int i = 0; i < player.hand.size(); i++){
			if (player.hand.get(i).cardValue() < weakestCardValue){
				weakestCardValue = player.hand.get(i).cardValue();
				index = i;
			}
		}
		System.out.println(player.hand.get(index) + " card selected");
		player.hand.get(index).hidden = false;
		player.newOffer();
	}

	/**
	 * Strategy method of the virtual player which consist to pick the weakest offer visible on the game board.
	 * It means the AI player will automatically pick a faced up card.
	 *
	 * @author Elina
	 * @param player
	 */
	@Override
	public void pickOfferStrategy(Player player){
		System.out.println("\nIt's "+ player.name +"'s turn to pick a card");

		weakestCardValueToPick = RoundsManager.getInstance().listPlayers.get(0).offeredCard().cardValue();
		for (int i = 0 ; i < RoundsManager.getInstance().listPlayers.size() ; i ++){
			if (RoundsManager.getInstance().listPlayers.get(i).offeredCard().cardValue() < weakestCardValueToPick) {
				weakestCardValueToPick = RoundsManager.getInstance().listPlayers.get(i).offeredCard().cardValue();
				index = i;
			}
		}
		Card cardSelected = RoundsManager.getInstance().listPlayers.get(index).offeredCard();
		player.jest.add(cardSelected);
		System.out.println(cardSelected + "YES");

		Player playerSelected = RoundsManager.getInstance().listPlayers.get(index);

		System.out.println("The AI's Jest is now : "+ player.jest.toString());
		player.hasPlayed = true;
		player.isPicking = false;

		//Set the next player
		setNextPlayer(playerSelected);
	}

	public void setNextPlayer(Player playerSelected){
		if(!playerSelected.getHasPlayed()) {
			playerSelected.isNext = true;
		}
		else {
			//check the number of player remaining
			int playerNotPlayed = 0;
			for(int i = 0; i < GameOptions.getNbPlayer(); i++) {
				if(!RoundsManager.getInstance().listPlayers.get(i).hasPlayed){
					playerNotPlayed ++ ;
				}
			}

			if(playerNotPlayed >= 1) {
				//if the player selected already played compare the remaining player's offers
				Player nextPlayer = RoundsManager.getInstance().checkBestOffer();
				nextPlayer.isNext = true;
				if(playerNotPlayed == 1) {
					//if only one player remain allow him to pick his own cards
					nextPlayer.setIsPicking(false);
				}
			}

			else if(playerNotPlayed == 0) {
				//No more remaining player and the turn
				RoundsManager.getInstance().setTurnOver(true);
			}
		}
	}
}