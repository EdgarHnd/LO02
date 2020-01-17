package fr.utt.jestcardgame.model;

import fr.utt.jestcardgame.observer.Observable;
import fr.utt.jestcardgame.visitor.Visitor;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Class which extends Observable and implements Visitor, then it allows us to display it for the GUI.
 * Gather all the cards and players together.
 * Also, the <code>GameBoard</code> instance can give trophy to the player who wins it.
 *
 * @author Elina
 */
public class GameBoard extends Observable implements Visitor {

    private static GameBoard gb = null;
    private ArrayList<Card> trophys;
    private ArrayList<Player> listPlayer;

    /**
     * Return the <code>GameBoard</code> instance already existing. If not, it will create a new one.
     *
     * @return GameBoard instance "gb"
     */
    public static GameBoard getInstance() {

        if (gb == null) {
            gb = new GameBoard();
        }

        return gb;
    }

    /**
     * A <code>GameBoard</code> instance will have two attributes : <em>trophys</em> and <em>listPlayer</em>.
     * These are two ArrayLists with an initialCapacity respectively of 2 and 4.
     */
    public GameBoard() {
        trophys = new ArrayList<>(2);
        listPlayer = new ArrayList<>(4);
    }

    /**
     * Gets the list of trophys from the <code>GameBoard</code>, dealt during the beginning of the game.
     *
     * @return trophys
     */
    public ArrayList<Card> getTrophys() {
        return trophys;
    }

    /**
     * Gives the trophys to each players who win these trophys. It depends on several conditions from the rules of the game.
     *
     * @see fr.utt.jestcardgame.model.Trophys
     */
    public void giveTrophys() {
        Iterator<Card> j = this.trophys.iterator();
        while (j.hasNext()) {
            Card t = j.next();
            Iterator<Player> i = this.listPlayer.iterator();
            Player winner = new Player("Default2", 11, new RealPlayerStrategy());
            while (i.hasNext()) {
                Player p = i.next();
                if (t.trophy.deserveTrophy(p) > t.trophy.deserveTrophy(winner)) {
                    winner = p;
                }
                //If tie when BJest check the TieValues
                else if (t.trophy.deserveTrophy(p) == t.trophy.deserveTrophy(winner)
                        && (t.trophy == Trophys.BestJest || t.trophy == Trophys.BJnoJoke)) {
                    if (p.getJest().highestFaceValue() > winner.getJest().highestFaceValue()) {
                        winner = p;
                    } else if (p.getJest().highestFaceValue() == winner.getJest().highestFaceValue()) {
                        if (p.getJest().highestFaceTiesValue() > winner.getJest().highestFaceTiesValue()) {
                            winner = p;
                        }
                    }
                }
                //If tie when Majority check TieValues
                else if (t.trophy.deserveTrophy(p) == t.trophy.deserveTrophy(winner)) {
                    if (p.getJest().highestFaceTiesValue() > winner.getJest().highestFaceTiesValue()) {
                        winner = p;
                    }
                }
            }
            winner.getJest().addToJest(t);
            winner.receiveTrophy();
            System.out.println(winner.getName() + " receives the trophy : " + t);
        }
    }

    /**
     * Notify the observers of this class. It gather the setChanged() and notifyObservers() methods together.
     */
    public void notifyOb() {
        System.out.println("notify trophy" + this.trophys.get(0) + this.listObserver.get(0));
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * Visits a player put in parameters.
     * It will add the player put in parameters at the <code>listPlayer</code> from the <code>GameBoard</code> class.
     *
     * @param p The player visited.
     */
    @Override
    public void visit(Player p) {
        this.listPlayer.add(p);
    }
}
