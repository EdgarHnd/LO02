package JestGame;

import java.util.LinkedList;
import java.util.Scanner;

public class RealPlayer extends Player {

	
	protected LinkedList<Card> hand;
	protected LinkedList<Card> jest;
	protected int finalScore;
	protected int finalBoolean;
	protected boolean hasPlayed;
	
	
		public RealPlayer(String n,int i) {
		super(n, i);
		}
		
		//Getters and setters
		public int getNb() {
			return nb;
		}

		public String getName() {
		return name;
		}

	@Override
	public void makeOffer() {
		System.out.println("Carte 1:" + this.hand.get(1));
		System.out.println("Carte 2:" + this.hand.get(2));
		System.out.println("Select your offer ! (1 or 2) ");
        Scanner select = new Scanner(System.in);
        if(select.nextInt() == 1) {
        	this.hand.get(1).hidden = false;
        }
        else if(select.nextInt() == 2) {
        	this.hand.get(2).hidden = false;
        }
	}
	
	@Override
	public void pickOffer() {
		System.out.println("Select an opponant card you want to add to your Jest");
		System.out.println("You can choose between this cards : ");
		
	}

}
