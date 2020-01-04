package fr.utt.jestcardgame.model;

import fr.utt.jestcardgame.observer.Observable;

public class OptionsData extends Observable {
    protected static int nbPlayer;
    protected static int nbRealPlayer;
    protected static int nbVirtualPlayer;
    protected static int variant;
    protected static String[] playersNames = {"Edgar","Elina","Patrick","Bernard"};

    public static void setNbPlayer(int numbPlayer){
        nbPlayer = numbPlayer;
    }

    public static int getNbPlayer() {
        return nbPlayer;
    }

    public static int getNbRealPlayer() {
        return nbRealPlayer;
    }

    public static int getNbVirtualPlayer() {
        return nbVirtualPlayer;
    }

    public static int getVariant() {
        return variant;
    }

    public static String getPlayersNames(int i) {
        return playersNames[i];
    }
}
