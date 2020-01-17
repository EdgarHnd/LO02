package fr.utt.jestcardgame.model;

/**
 * This is where all the data concerning the options of the game is stored.
 * It allows a good communication between the <code>View</code> package and the <code>Model</code> package.
 *
 * @author Elina
 * @see fr.utt.jestcardgame.view.Options
 * @see GameOptions
 */
public class OptionsData {
    protected static int nbPlayer;
    protected static int nbRealPlayer;
    protected static int nbVirtualPlayer;
    protected static int variant;
    protected static String[] playersNames = {"Edgar", "Elina", "Patrick", "Bernard"};

    /**
     * Sets the number of players for the next game according to the int value specified in the parameters.
     *
     * @param numbPlayer Number of players for the next game.
     */
    public static void setNbPlayer(int numbPlayer) {
        nbPlayer = numbPlayer;
    }

    /**
     * Sets the number of real players for the next game according to the int value specified in the parameters.
     *
     * @param numbRealPlayer Number of real players for the next game.
     */
    public static void setNbRealPlayer(int numbRealPlayer) {
        nbRealPlayer = numbRealPlayer;
    }

    /**
     * Sets the number of the variant chosen by the user, specified in the parameters.
     *
     * @param numVariant Number of the variant
     */
    public static void setVariant(int numVariant) {
        variant = numVariant;
    }

    /**
     * Returns the number of players for the current game.
     *
     * @return nbPlayer
     */
    public static int getNbPlayer() {
        return nbPlayer;
    }

    /**
     * Returns the number of real players for the current game.
     *
     * @return nbRealPlayer
     */
    public static int getNbRealPlayer() {
        return nbRealPlayer;
    }

    /**
     * Returns the number of virtual players for the current game.
     *
     * @return nbVirtualPlayer
     */
    public static int getNbVirtualPlayer() {
        nbVirtualPlayer = OptionsData.nbPlayer - OptionsData.nbRealPlayer;
        return nbVirtualPlayer;
    }

    /**
     * Gets the variant for the current game, as an int value.
     *
     * @return variant
     */
    public static int getVariant() {
        return variant;
    }

    /**
     * @param i Number associated with a player in the players list.
     * @return The player's name (String)
     */
    public static String getPlayersNames(int i) {
        return playersNames[i];
    }
}
