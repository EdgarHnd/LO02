package jestGame;

import java.util.Scanner;

public class UserInput {

    private static UserInput instance = null;
    private Scanner entree;

    private UserInput(){
        entree = new Scanner(System.in);
    }

    public static UserInput getInstance(){
        if(instance == null) {
            instance = new UserInput();
        }
        return instance;
    }

    public int nextInt() {
        return entree.nextInt();
    }

    public String nextString() {
        return entree.next();
    }

}
