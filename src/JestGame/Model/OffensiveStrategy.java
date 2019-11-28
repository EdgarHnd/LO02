package jestGame.Model;

public class OffensiveStrategy implements ChooseStrategy {

    @Override
    public void makeOfferStrategy() {

        System.out.println("Make my best offer");

    }

    @Override
    public void pickOfferStrategy(){

        System.out.println("Pick the best offer");

    }
}
