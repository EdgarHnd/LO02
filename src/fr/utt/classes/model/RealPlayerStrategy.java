package fr.utt.classes.model;

import fr.utt.classes.view.ConsoleGameView;
import fr.utt.classes.view.ConsoleOutput;
import fr.utt.classes.view.ConsoleUserInput;
import fr.utt.classes.view.setupException;

import java.util.ArrayList;
import java.util.InputMismatchException;

/**
 * Class which implements the interface {@link fr.utt.classes.model.ChooseStrategy}.
 * It's a real player strategy, which means it executes the user's choice.
 * This class also determine the next player who will play for the round.
 *
 * @author Elina
 * @see Player
 */
public class RealPlayerStrategy implements ChooseStrategy {

    /**
     * Strategy method of the real player which consist to make an offer from its hand.
     * The offer is chosen according to the player's choice.
     *
     * @param player The player who make the offer
     */
    @Override
    public void makeOfferStrategy(Player player) {
        ConsoleGameView.display(ConsoleOutput.ReadyToOffer);

        ConsoleUserInput input = ConsoleUserInput.getInstance();
        if (input.nextString().equals("X")) {
            System.out.println("Carte 1:" + player.hand.get(0));
            System.out.println("Carte 2:" + player.hand.get(1));
            System.out.println("Select your offer (1 or 2) ");

            int selection = input.nextInt();


            if (selection == 1) {
                player.hand.get(0).hidden = false;
                System.out.println(player.hand.get(0) + " card selected");
            } else if (selection == 2) {
                player.hand.get(1).hidden = false;
                System.out.println(player.hand.get(1) + " card selected");
            }
        }
        player.newOffer();
    }

    @Override
    /**
     * Strategy method of the real player which consist to pick an offer from its hand.
     * The offer is chosen according to the player's choice.
     */
    public void pickOfferStrategy(Player player) {
        System.out.println("\nIt's " + player.name + "'s turn to pick a card");

        boolean correctInputPlayer = false;
        while (!correctInputPlayer) {
            RoundsManager.getInstance().showValidOffers();
            ConsoleGameView.display(ConsoleOutput.Picking);
            ArrayList<Integer> listNb = RoundsManager.getInstance().getListNbOffers();

            try {
                int playerSlct = ConsoleUserInput.getInstance().nextInt() - 1;
                ConsoleUserInput.getInstance().isCorrectInputList(listNb, playerSlct + 1, ConsoleOutput.Standard);
                Player playerSelect = RoundsManager.getInstance().listPlayers.get(playerSlct);

                correctInputPlayer = true;
                boolean correctInputCard = false;
                while (!correctInputCard) {
                    System.out.println("Please select the card you want to pick (1) The visible card / (2) The hidden card :");
                    try {
                        int cardSelect = ConsoleUserInput.getInstance().nextInt();
                        ConsoleUserInput.getInstance().isCorrectInputBetweenMinMax(1, 2, cardSelect, ConsoleOutput.Standard);
                        correctInputCard = true;
                        if (cardSelect == 1) {
                            player.getJest().addToJest(playerSelect.getOffer().pollFirst());
                        } else if (cardSelect == 2) {
                            player.getJest().addToJest(playerSelect.getOffer().pollLast());
                        }
                        System.out.println("Your Jest is now : " + player.getJest().getJestCards().toString());
                        player.hasPlayed = true;
                        player.isPicking = false;

                        setNextPlayer(playerSelect);
                    } catch (setupException e) {
                        e.getMessage();
                        System.out.println("Please choose wisely between 1 and 2 !");
                    } catch (InputMismatchException e) {
                        System.out.println("You have to put a number.");
                        System.exit(0);
                    }
                }
            } catch (setupException e) {
                e.getMessage();
                System.out.println("Please choose wisely between the propositions you have.");
            } catch (InputMismatchException e) {
                System.out.println("You should put a number.");
                System.exit(0);
            }
        }
    }

    /**
     * Sets the next player for this round, selected according to the action done before.
     *
     * @param playerSelect Player from whom we took a card (picking offer).
     */
    public void setNextPlayer(Player playerSelect) {
        if (playerSelect.getHasPlayed() == false) {
            playerSelect.isNext = true;
        } else {

            //check the number of player remaining
            int playerNotPlayed = 0;
            for (int i = 0; i < OptionsData.getNbPlayer(); i++) {
                if (RoundsManager.getInstance().listPlayers.get(i).hasPlayed == false) {
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
