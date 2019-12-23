package fr.utt.jestcardgame.model;

import fr.utt.jestcardgame.view.ConsoleGameView;
import fr.utt.jestcardgame.view.ConsoleOutput;
import fr.utt.jestcardgame.view.ConsoleUserInput;
import fr.utt.jestcardgame.view.setupException;

import java.util.InputMismatchException;

public class RealPlayerStrategy implements ChooseStrategy{

    @Override
    public void makeOfferStrategy(Player player) {
        ConsoleGameView.display(ConsoleOutput.ReadyToOffer);

        ConsoleUserInput input = ConsoleUserInput.getInstance();
        if (input.nextString().equals("X")){
            System.out.println("Carte 1:" + player.hand.get(0));
            System.out.println("Carte 2:" + player.hand.get(1));
            System.out.println("Select your offer (1 or 2) ");

            int selection = input.nextInt();


            if(selection == 1) {
                player.hand.get(0).hidden = false;
                System.out.println(player.hand.get(0) + " card selected");
            }
            else if(selection == 2) {
                player.hand.get(1).hidden = false;
                System.out.println(player.hand.get(1) + " card selected");
            }
        } else {
            //Gestion d'exception à faire
        }
        player.newOffer();
}

    @Override
    public void pickOfferStrategy(Player player) {
        System.out.println("\nIt's " + player.name + "'s turn to pick a card");

        boolean correctInputPlayer = false;
        while (!correctInputPlayer) {
            RoundsManager.getInstance().showValidOffers();
            ConsoleGameView.display(ConsoleOutput.Picking);
            int max = RoundsManager.getInstance().getMaxValidOffer();
            int min = RoundsManager.getInstance().getMinValidOffer();
            System.out.println("MIN : " + min);
            System.out.println("MAX : " + max);
            try{
                int playerSlct = ConsoleUserInput.getInstance().nextInt()-1;
                System.out.println("HELLO U : " + playerSlct);
                // @TODO Have to change this way to handle the exceptions, not appropriate :)
                ConsoleUserInput.getInstance().isCorrectInputBetweenMinMax(min, max, playerSlct+1, ConsoleOutput.Standard);
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
                            player.jest.add(playerSelect.getOffer().pollFirst());
                        } else if (cardSelect == 2) {
                            player.jest.add(playerSelect.getOffer().pollLast());
                        }
                        System.out.println("Your Jest is now : " + player.jest.toString());
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
            } catch (setupException e){
                e.getMessage();
                System.out.println("Please choose wisely between the propositions you have.");
            } catch (InputMismatchException e){
                System.out.println("You should put a number.");
                System.exit(0);
            }
        }
    }

    public void setNextPlayer(Player playerSelect) {
        if (playerSelect.getHasPlayed() == false) {
            playerSelect.isNext = true;
        } else {

            //check the number of player remaining
            int playerNotPlayed = 0;
            for (int i = 0; i < GameOptions.getNbPlayer(); i++) {
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
