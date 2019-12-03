package fr.utt.jestcardgame.model;

public class DefensiveStrategy implements ChooseStrategy {

	private int weakestCardValue;
	private int index;

	@Override
	public void makeOfferStrategy(Player player) {
		weakestCardValue = 5;
		for (int i = 0; i < player.hand.size(); i++){
			if (player.hand.get(i).cardValue() <= weakestCardValue){
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
		System.out.println("DEFENSIVE STRATEGY");

		int nbCompleteOffers = countNbCompleteOffers(player);
		switch (nbCompleteOffers){
			case 0 :
				addMyOwnOfferToMyJest(player);
				break;
			case 1 :
				addTheOnlyCardAvailableToMyJest(player);
				break;
			default:
				addTheBestCardToMyJest(player);
		}
	}
	public int countNbCompleteOffers(Player me){
		int nbCompleteOffers = 0;
		for (int i = 0 ; i < RoundsManager.getInstance().listPlayers.size(); i++) {
			if (RoundsManager.getInstance().listPlayers.get(i).completeOffer() && RoundsManager.getInstance().listPlayers.get(i)!= me){
				nbCompleteOffers ++;
			}
		}
		return nbCompleteOffers;
	}

	public void addMyOwnOfferToMyJest(Player me){
		me.jest.add(me.offer.get(0));
		me.offer.remove(0);
		System.out.println("The AI's Jest is now : " + me.jest.toString());

		me.hasPlayed = true;
		me.isPicking = false;
		//Set the next player
		setNextPlayer(me);
	}

	public void addTheOnlyCardAvailableToMyJest(Player me){
		int index = 0;
		for (int i = 0 ; i < RoundsManager.getInstance().listPlayers.size(); i++) {
			if (RoundsManager.getInstance().listPlayers.get(i).completeOffer() && RoundsManager.getInstance().listPlayers.get(i)!= me){
				Card cardSelected = RoundsManager.getInstance().listPlayers.get(i).offer.get(0);
				me.jest.add(cardSelected);
				index = i;
			}
		}
		Player playerSelected = RoundsManager.getInstance().listPlayers.get(index);
		System.out.println("The AI's Jest is now : " + me.jest.toString());
		me.hasPlayed = true;
		me.isPicking = false;
		//Set the next player
		setNextPlayer(playerSelected);
	}

	public void addTheBestCardToMyJest(Player me){
		int bestCardValueToPick = 5;
		for (int i = 0 ; i < RoundsManager.getInstance().listPlayers.size() ; i ++){
			int cardValueIndex = RoundsManager.getInstance().listPlayers.get(i).offer.get(0).cardValue();
			Card cardIndex = RoundsManager.getInstance().listPlayers.get(i).offer.get(0);

			if (RoundsManager.getInstance().listPlayers.get(i).completeOffer()) {
				if (cardValueIndex < bestCardValueToPick && cardIndex != me.offer.get(0)) {
					bestCardValueToPick = RoundsManager.getInstance().listPlayers.get(i).offer.get(0).cardValue();
					index = i;
				}
			}
		}
		Card cardSelected = RoundsManager.getInstance().listPlayers.get(index).offer.get(0);
		Player playerSelected = RoundsManager.getInstance().listPlayers.get(index);

		me.jest.add(cardSelected);
		playerSelected.offer.remove(cardSelected);
		System.out.println("The AI's Jest is now : " + me.jest.toString());

		me.hasPlayed = true;
		me.isPicking = false;
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