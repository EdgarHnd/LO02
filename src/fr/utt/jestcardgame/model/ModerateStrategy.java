package fr.utt.jestcardgame.model;

import java.util.Random;

public class ModerateStrategy implements ChooseStrategy {

    @Override
    public void makeOfferStrategy(Player player) {
        Random rand = new Random();
        int randomNb = rand.nextInt(player.hand.size()) + 1;
        System.out.println(randomNb);
        player.hand.get(randomNb).hidden = false;
    }

    @Override
    public void pickOfferStrategy(Player player){

    }

}
