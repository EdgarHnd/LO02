package fr.utt.jestcardgame.model;

import fr.utt.jestcardgame.visitor.Visitor;

public class Score implements Visitor{
	
	private Jest jest;
	public Score() {
		
	}
	@Override
	public void visit(Player p) {
		this.jest = p.jest;
	}
	
}
