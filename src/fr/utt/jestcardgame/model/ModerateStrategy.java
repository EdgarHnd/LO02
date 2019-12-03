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
        //Card Selected by default
        Card cardSelected = null;
        isCorrectCard = false;
        while (!isCorrectCard) {
            //Pick a random card from the game board : cardSelected
            Random rand = new Random();
            int randomNb = rand.nextInt(RoundsManager.getInstance().listPlayers.size()) + 1;
            int randomNb2 = rand.nextInt(2);
            System.out.println("Numéro joueur : " + randomNb);
            System.out.println("Numéro carte : " + randomNb2);

            playerSelected = RoundsManager.getInstance().listPlayers.get(randomNb);
            cardSelected = playerSelected.offer.get(randomNb2);

            //if this cardSelected is different from the AI's offer, then we continue
            if (cardSelected != player.hiddenCard() && cardSelected != player.offeredCard()) {
                player.jest.add(cardSelected);

                System.out.println("This is the card selected by the AI : " + cardSelected);
                playerSelected.offer.remove(cardSelected);

                isCorrectCard = true;
            }
        }
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