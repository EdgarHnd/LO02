package fr.utt.jestcardgame.model;

import java.util.ArrayList;

import fr.utt.jestcardgame.controler.setupException;
import fr.utt.jestcardgame.visitor.Visitable;
import fr.utt.jestcardgame.visitor.Visitor;

public abstract class AbstractGameManager implements Visitable{
	
	 private ArrayList<Visitor> listVisitor = new ArrayList<Visitor>();   
	 
	 //Implementing visitor patern
	  public void addVisitor(Visitor vis) {
	    this.listVisitor.add(vis);
	  }

	  public void notifyVisitor(String str) {
	    
		  for(Visitor obs : listVisitor)
	      obs.update(str);
	  }

	  public void removeVisitor() {
	    listVisitor = new ArrayList<Visitor>();
	  }  
	
	public abstract void mainMenu() throws setupException;
	
	public abstract void play();
	
}
