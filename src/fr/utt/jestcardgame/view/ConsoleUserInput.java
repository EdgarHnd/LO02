package fr.utt.jestcardgame.view;

import java.util.Scanner;

	public class ConsoleUserInput {


	    private static ConsoleUserInput instance = null;
	    private Scanner entree;
		private int input;

		public ConsoleUserInput(){
	        entree = new Scanner(System.in);
	    }

	    public static ConsoleUserInput getInstance(){
	        if(instance == null) {
	            instance = new ConsoleUserInput();
	        }
	        return instance;
	    }

	    public void isCorrectInputBetweenMinMax(int min, int max, int input) throws setupException {
	    	if (input >= min && input <= max){
				ConsoleGameView.display(ConsoleOutput.PlayerNb);
			} else {
	    		throw new setupException("The value is not correct.");
			}
		}

	    public int nextInt() {
	        return entree.nextInt();
	    }

	    public String nextString() {
	        return entree.next();
	    }

}
