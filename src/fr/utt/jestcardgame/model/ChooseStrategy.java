package fr.utt.jestcardgame.model;

/**
 * Interface which declares both methods a player will use during the game.
 * It's part of the Strategy design pattern, then this interface is common to all concrete strategies.
 * (See {@link fr.utt.jestcardgame.model.RealPlayerStrategy}, {@link fr.utt.jestcardgame.model.DefensiveStrategy}, {@link fr.utt.jestcardgame.model.OffensiveStrategy}.
 *
 * @author Elina
 */
public interface ChooseStrategy {

    /**
     * Declaration of the method which allows a player to make an offer at the beginning of a round.
     *
     * @param player
     */
    void makeOfferStrategy(Player player);

    /**
     * Declaration of the method which allows a player to pick an offer to another player during a round.
     *
     * @param player
     */
    void pickOfferStrategy(Player player);

}
