package fr.utt.jestcardgame.visitor;

import fr.utt.jestcardgame.model.Player;

public interface Visitor {
	public void visit(Player p);
}
