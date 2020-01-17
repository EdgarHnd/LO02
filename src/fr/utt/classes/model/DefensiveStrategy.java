package fr.utt.classes.model;

import java.util.Iterator;

/**
 * Class which implements the interface {@link ChooseStrategy}.
 * It's a virtual player defensive strategy, it means this player will choose the weakest card to offer or to pick.
 * This class also determine the next player who will play for the round.
 *
 * @author Elina
 */
public class DefensiveStrategy implements ChooseStrategy {

    private int weakestCardValue;
    private int index;
    private Player playerSelected;

    /**
     * Strategy method of the virtual player which consist to make an offer from its hand, here it's the weakest card.
     *
     * @param player Player who use these methods to do an action during the game.
     */
    @Override
    public void makeOfferStrategy(Player player) {
        weakestCardValue = 5;
        for (int i = 0; i < player.hand.size(); i++) {
            if (player.hand.get(i).cardValue() <= weakestCardValue) {
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
     * @param player Player who use these methods to do an action during the game.
     * @author Elina
     */
    @Override
    public void pickOfferStrategy(Player player) {
        System.out.println("\nIt's " + player.name + "'s turn to pick a card");
        System.out.println("DEFENSIVE STRATEGY");

        int nbCompleteOffers = countNbCompleteOffers(player);
        if (nbCompleteOffers == 0) {
            addMyOwnOfferToMyJest(player);
        } else if (nbCompleteOffers == 1) {
            addTheOnlyCardAvailableToMyJest(player);
        } else if (nbCompleteOffers > 1 && nbCompleteOffers < RoundsManager.getInstance().listPlayers.size() + 1) {
            addTheBestCardToMyJest(player);
        }
    }

    /**
     * Counts the number of complete offers available on the game board.
     * It's an important information in order to make a good choice during the game.
     *
     * @param me Player who use these methods to do an action during the game.
     * @return nbCompleteOffers
     */
    public int countNbCompleteOffers(Player me) {
        int nbCompleteOffers = 0;
        for (int i = 0; i < RoundsManager.getInstance().listPlayers.size(); i++) {
            if (RoundsManager.getInstance().listPlayers.get(i).hasCompleteOffer() && RoundsManager.getInstance().listPlayers.get(i) != me) {
                nbCompleteOffers++;
            }
        }
        return nbCompleteOffers;
    }

    /**
     * Adds the player's available card to his own offer, in case there is no more complete offer.
     *
     * @param me Player who use these methods to do an action during the game.
     */
    public void addMyOwnOfferToMyJest(Player me) {
        me.getJest().addToJest(me.offer.pollFirst());
        System.out.println("The AI's Jest is now : " + me.getJest().getJestCards().toString());

        me.hasPlayed = true;
        me.isPicking = false;
        RoundsManager.getInstance().setTurnOver(true);
    }

    /**
     * Adds the only card available to the player's jest, iin case there is just one complete offer.
     *
     * @param me Player who use these methods to do an action during the game.
     */
    public void addTheOnlyCardAvailableToMyJest(Player me) {
        int index = 0;
        for (int i = 0; i < RoundsManager.getInstance().listPlayers.size(); i++) {
            if (RoundsManager.getInstance().listPlayers.get(i).hasCompleteOffer() && RoundsManager.getInstance().listPlayers.get(i) != me) {
                me.getJest().addToJest(RoundsManager.getInstance().listPlayers.get(i).offer.pollFirst());
                index = i;
            }
        }
        Player playerSelected = RoundsManager.getInstance().listPlayers.get(index);
        System.out.println("The AI's Jest is now : " + me.getJest().getJestCards().toString());
        me.hasPlayed = true;
        me.isPicking = false;
        setNextPlayer(playerSelected);
    }

    /**
     * Adds the best card (here it's the weakest card from the game board) to the player's jest.
     *
     * @param me Player who use these methods to do an action during the game.
     */
    public void addTheBestCardToMyJest(Player me) {
        int bestCardValueToPick = 0;
        Iterator<Player> itr = RoundsManager.getInstance().listPlayers.iterator();
        while (itr.hasNext()) {
            Player element = (Player) itr.next();
            int cardValueIndex = element.offer.get(0).cardValue();
            Card cardIndex = element.offer.get(0);

            if (element.hasCompleteOffer()) {
                if (cardValueIndex > bestCardValueToPick && cardIndex != me.offer.get(0)) {
                    bestCardValueToPick = element.offer.get(0).cardValue();
                    playerSelected = element;
                }
            }
        }

        Card cardSelected = playerSelected.offer.pollFirst();

        me.jest.addToJest(cardSelected);

        System.out.println("The AI's Jest is now : " + me.getJest().getJestCards().toString());

        me.hasPlayed = true;
        me.isPicking = false;
        setNextPlayer(playerSelected);
    }

    /**
     * Sets the next player for this round, selected according to the action done before.
     *
     * @param playerSelected Player from whom we took a card (picking offer).
     */
    public void setNextPlayer(Player playerSelected) {
        if (!playerSelected.getHasPlayed()) {
            playerSelected.isNext = true;
        } else {
            //check the number of player remaining
            int playerNotPlayed = 0;
            for (int i = 0; i < OptionsData.getNbPlayer(); i++) {
                if (!RoundsManager.getInstance().listPlayers.get(i).hasPlayed) {
                    playerNotPlayed++;
                }
            }

            if (playerNotPlayed >= 1) {
                //if the player selected already played compare the remaining player's offers
                Player nextPlayer = RoundsManager.getInstance().checkBestOffer();
                nextPlayer.isNext = true;
                if (playerNotPlayed == 1) {
                    //if only one player remain allow him to pick his own cards
                    nextPlayer.setIsPicking(false);
                }
            } else if (playerNotPlayed == 0) {
                //No more remaining player and the turn
                RoundsManager.getInstance().setTurnOver(true);
            }
        }
    }
}