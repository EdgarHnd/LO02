package fr.utt.jestcardgame.visitor;

import fr.utt.jestcardgame.model.Player;

/**
 * Visitor interface that declare the visit method, in order to visit any player.
 */
public interface Visitor {
    /**
     * Visits the player specified in the parameters.
     *
     * @param p Player who will be visited
     */
    void visit(Player p);
}
