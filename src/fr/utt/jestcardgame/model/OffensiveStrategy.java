package fr.utt.jestcardgame.model;

public class OffensiveStrategy implements ChooseStrategy {

    private int bestCardValue;
    private int index;
    private int bestCardValueToPick;

    @Override
    public void makeOfferStrategy(Player player) {
        bestCardValue = 0;
        for (int i = 0; i < player.hand.size(); i++){
            if (player.hand.get(i).cardValue() > bestCardValue){
                bestCardValue = player.hand.get(i).cardValue();
                index = i;
            }
        }
        System.out.println(player.hand.get(index) + " card selected");
        player.hand.get(index).hidden = false;
        player.newOffer();
    }

    /**
     * Strategy method of the virtual player which consist to pick the best offer visible on the game board.
     * It means the AI player will automatically pick a faced up card.
     *
     * @author Elina
     * @param player
     */
    @Override
    public void pickOfferStrategy(Player player){
        System.out.println("\nIt's "+ player.name +"'s turn to pick a card");
        System.out.println("OFFENSIVE STRATEGY");

        bestCardValueToPick = 0;
        for (int i = 0 ; i < RoundsManager.getInstance().listPlayers.size() ; i ++){
            int cardValueIndex = RoundsManager.getInstance().listPlayers.get(i).offeredCard().cardValue();
            Card cardIndex = RoundsManager.getInstance().listPlayers.get(i).offeredCard();

            if (cardValueIndex > bestCardValueToPick && cardIndex != player.offeredCard()) {
                bestCardValueToPick = RoundsManager.getInstance().listPlayers.get(i).offeredCard().cardValue();
                index = i;
            }
        }
        Card cardSelected = RoundsManager.getInstance().listPlayers.get(index).offeredCard();
        player.jest.add(cardSelected);
        RoundsManager.getInstance().listPlayers.get(index).offer.remove(cardSelected);

        System.out.println("This is the card selected by the AI : " + cardSelected);
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
