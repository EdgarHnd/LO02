package fr.utt.jestcardgame.model;

public class OptionsData {
    protected static int nbPlayer;
    protected static int nbRealPlayer;
    protected static int nbVirtualPlayer;
    protected static int variant;
    protected static String[] playersNames = {"Edgar","Elina","Patrick","Bernard"};

    public static void setNbPlayer(int numbPlayer){
        nbPlayer = numbPlayer;
    }

    public static void setNbRealPlayer(int numbRealPlayer) {
        nbRealPlayer = numbRealPlayer;
    }

    public static void setVariant(int numVariant) {variant = numVariant;}

    public static int getNbPlayer() {
        return nbPlayer;
    }

    public static int getNbRealPlayer() {
        return nbRealPlayer;
    }

    public static int getNbVirtualPlayer() {
        nbVirtualPlayer = OptionsData.nbPlayer - OptionsData.nbRealPlayer;
        return nbVirtualPlayer;
    }

    public static int getVariant() {
        return variant;
    }

    public static String getPlayersNames(int i) {
        return playersNames[i];
    }
}
