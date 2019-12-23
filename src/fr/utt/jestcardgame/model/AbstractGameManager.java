package fr.utt.jestcardgame.model;

import fr.utt.jestcardgame.observer.Observable;
import fr.utt.jestcardgame.observer.Observer;
import fr.utt.jestcardgame.view.setupException;

import java.util.ArrayList;

public abstract class AbstractGameManager implements Observable{
	
	 private ArrayList<Observer> listObserver = new ArrayList<Observer>();   
	 
	 //Implementing visitor patern
	  public void addObserver(Observer vis) {
	    this.listObserver.add(vis);
	  }

	  public void notifyObserver(String str) {
	    
		  for(Observer obs : listObserver)
	      obs.update(str);
	  }

	  public void removeObserver() {
	    listObserver = new ArrayList<Observer>();
	  }  
	
	public abstract void mainMenu() throws setupException;
	
	public abstract void play();
	
}
