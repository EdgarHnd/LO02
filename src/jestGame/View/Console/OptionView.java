package jestGame.View.Console;

public class OptionView {

    private int nbPlayer;
    private int nbRealPlayer;
    private int nbVirtualPlayer;

    public void setNbPlayer(int nbPlayer){
        this.nbPlayer = nbPlayer;
    }

    public int getNbPlayer() {
        return nbPlayer;
    }

    public int getNbRealPlayer() {
        return nbRealPlayer;
    }

    public void setNbRealPlayer(int nbRealPlayer) {
        this.nbRealPlayer = nbRealPlayer;
    }

    public int getNbVirtualPlayer() {
        return nbVirtualPlayer;
    }

    public void setNbVirtualPlayer(int nbVirtualPlayer) {
        this.nbVirtualPlayer = nbVirtualPlayer;
    }

    //public void upDate(){
    //    System.out.println("");
    //}
}
