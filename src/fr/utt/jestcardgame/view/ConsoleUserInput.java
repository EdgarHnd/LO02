package fr.utt.jestcardgame.view;

import java.util.ArrayList;
import java.util.Scanner;

	public class ConsoleUserInput {

	    private static ConsoleUserInput instance = null;
	    private Scanner entree;

		public ConsoleUserInput(){
	        entree = new Scanner(System.in);
	    }

	    public static ConsoleUserInput getInstance(){
	        if(instance == null) {
	            instance = new ConsoleUserInput();
	        }
	        return instance;
	    }

	    public void isCorrectInputBetweenMinMax(int min, int max, int input, ConsoleOutput output) throws setupException {
	    	if (input >= min && input <= max){
				ConsoleGameView.display(output);
			} else {
	    		throw new setupException("The value is not correct.");
			}
		}

		public void isCorrectInputList(ArrayList list, int input, ConsoleOutput output) throws setupException {
			if (list.contains(input)){
				ConsoleGameView.display(output);
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
