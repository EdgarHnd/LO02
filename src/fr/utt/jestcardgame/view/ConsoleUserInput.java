package fr.utt.jestcardgame.view;

import java.util.Scanner;

	public class ConsoleUserInput {


	    private static ConsoleUserInput instance = null;
	    private Scanner entree;

	    private ConsoleUserInput(){
	        entree = new Scanner(System.in);
	    }

	    public static ConsoleUserInput getInstance(){
	        if(instance == null) {
	            instance = new ConsoleUserInput();
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
