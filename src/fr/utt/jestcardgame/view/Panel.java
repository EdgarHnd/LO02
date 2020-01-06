package fr.utt.jestcardgame.view;

import fr.utt.jestcardgame.controler.GameViewControler;
import fr.utt.jestcardgame.observer.Observable;

import javax.swing.*;
import java.awt.*;

//Class centrale panneau permettant de regrouper les attributs communs

public abstract class Panel extends Observable {
	protected JPanel panel;
	protected GameViewControler gvc;
	
	protected Font comics30 = new Font("Comics Sans MS", Font.BOLD, 30);
	protected Font comics40 = new Font("Comics Sans MS", Font.BOLD, 40);
	protected Font comics20 = new Font("Comics Sans MS", Font.BOLD, 20);
	protected Font arial = new Font("Arial", Font.BOLD, 15);
	protected Font dialog = new Font("Dialog", Font.BOLD + Font.ITALIC, 15);
	
	public Panel(Dimension dim, GameViewControler gvc){
		this.panel = new JPanel();
		this.panel.setPreferredSize(dim);
		this.panel.setBackground(Color.white);
		this.gvc = gvc;
	}
	
	protected JPanel getPanel(){
		return this.panel;
	}
	
	protected abstract void initPanel();

}
