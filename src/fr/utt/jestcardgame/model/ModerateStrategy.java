package fr.utt.jestcardgame.model;

import java.util.Random;

public class ModerateStrategy implements ChooseStrategy {

    private Player playerSelected;
    private boolean isCorrectCard;

    @Override
    public void makeOfferStrategy(Player player) {
        Random rand = new Random();
        int randomNb = rand.nextInt(player.hand.size()) + 1;
        System.out.println(player.hand.get(randomNb-1) + " card selected");
        player.hand.get(randomNb-1).hidden = false;
        player.newOffer();
    }

    /**
     * Strategy method of the virtual player which consist to pick a random card on the game board.
     * It means the AI player can pick either a faced up card or a faced down card.
     * @param player
     */
    @Override
    public void pickOfferStrategy(Player player){
        System.out.println("\nIt's "+ player.name +"'s turn to pick a card");
        System.out.println("MODERATE STRATEGY");

        int nbCompleteOffers = countNbCompleteOffers(player);
        System.out.println("NOMBRE OFFRES COMPLETES " + nbCompleteOffers );
        if (nbCompleteOffers == 0) {
            addMyOwnOfferToMyJest(player);
        } else if (nbCompleteOffers == 1) {
            addTheOnlyCardAvailableToMyJest(player);
        } else if (nbCompleteOffers > 1 && nbCompleteOffers < RoundsManager.getInstance().listPlayers.size() +1){
            addRandomCardToMyJest(player);
        }
    }

    public int countNbCompleteOffers(Player me){
        int nbCompleteOffers = 0;
        for (int i = 0 ; i < RoundsManager.getInstance().listPlayers.size(); i++) {
            if (RoundsManager.getInstance().listPlayers.get(i).hasCompleteOffer() && RoundsManager.getInstance().listPlayers.get(i)!= me){
                nbCompleteOffers ++;
            }
        }
        return nbCompleteOffers;
    }

    public void addMyOwnOfferToMyJest(Player me){
        me.jest.addToJest(me.offer.pollFirst());
        System.out.println("The AI's Jest is now : " + me.jest.toString());

        me.hasPlayed = true;
        me.isPicking = false;
        RoundsManager.getInstance().setTurnOver(true);
    }

    public void addTheOnlyCardAvailableToMyJest(Player me){
        int index = 0;
        for (int i = 0 ; i < RoundsManager.getInstance().listPlayers.size(); i++) {
            if (RoundsManager.getInstance().listPlayers.get(i).hasCompleteOffer() && RoundsManager.getInstance().listPlayers.get(i)!= me){
                me.jest.addToJest(RoundsManager.getInstance().listPlayers.get(i).offer.pollFirst());
                index = i;
            }
        }
        Player playerSelected = RoundsManager.getInstance().listPlayers.get(index);
        System.out.println("The AI's Jest is now : " + me.jest.toString());
        me.hasPlayed = true;
        me.isPicking = false;
        setNextPlayer(playerSelected);
    }

    public void addRandomCardToMyJest(Player me){
        Random rand = new Random();
        int randomNb = rand.nextInt(RoundsManager.getInstance().listPlayers.size());
        int randomNb2 = rand.nextInt(2);

        System.out.println("RandomNB 1 " + randomNb);
        System.out.println("RandomNB 2 " + randomNb2);
        playerSelected = RoundsManager.getInstance().listPlayers.get(randomNb);

        while (playerSelected == me || !playerSelected.hasCompleteOffer()){
            randomNb = rand.nextInt(RoundsManager.getInstance().listPlayers.size());
            playerSelected = RoundsManager.getInstance().listPlayers.get(randomNb);
        }

        Card cardSelected = playerSelected.offer.get(randomNb2);
        me.jest.addToJest(cardSelected);

        System.out.println("The AI's Jest is now : " + me.jest.toString());
        me.hasPlayed = true;
        me.isPicking = false;
        setNextPlayer(playerSelected);
    }

    public void setNextPlayer(Player playerSelected){
        if(!playerSelected.getHasPlayed()) {
            playerSelected.isNext = true;
        }
        else {
            //check the number of player remaining
            int playerNotPlayed = 0;
            for(int i = 0; i < OptionsData.getNbPlayer(); i++) {
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