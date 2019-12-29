package fr.utt.jestcardgame.observer;

import java.util.ArrayList;
import java.util.Iterator;


public class Observable {
	
	protected ArrayList<Observer> listObserver = new ArrayList<Observer>();
	protected boolean changed = false;
	
	// Gestion des observateurs
	 public void addObserver (Observer o) {
		 this.listObserver.add(o);
	 }
	 /*public void deleteObserver (Observer o) {
		 
	 }*/
	 public void deleteObservers () {
		 this.listObserver = new ArrayList<Observer>();
	 }/*
	 public int countObservers() {
		return 0;
		 
	 }*/
	 // Gestion du changement
	 protected void clearChanged() {
		 this.changed = false;
	 }
	 protected void setChanged() {
		 this.changed = true;
	 }
	 public boolean hasChanged() {
		 return this.changed;
	 }
	 // Notification des observateurs
	 public void notifyObservers() {
		 if(this.changed) {
		 Iterator<Observer> i = this.listObserver.iterator();
			 while(i.hasNext()) {
					Observer o = (Observer)i.next();
					o.update(this,null);
				}
			 }
	 }
	 
	}
