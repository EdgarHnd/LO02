package fr.utt.jestcardgame.visitor;

public interface Visitable {
	
	public void addVisitor(Visitor obs);
	  public void removeVisitor();
	  public void notifyVisitor(String str);
}
