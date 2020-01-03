package fr.utt.jestcardgame.view;

import fr.utt.jestcardgame.controler.GameViewControler;
import fr.utt.jestcardgame.model.GameManager;
import fr.utt.jestcardgame.observer.Observable;
import fr.utt.jestcardgame.observer.Observer;

import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame implements Observer{
	
	private Dimension size;
	private JPanel container = new JPanel();
	protected GameViewControler gvc;
	
	public GameView(GameViewControler gvc) {
		this.gvc = gvc;
		this.setTitle("Jest Game");
		this.setSize(1200, 700);
	    this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);             
	    this.setResizable(false);
	    this.size = new Dimension(this.getWidth(), this.getHeight());
	    
	    this.container.setPreferredSize(this.size);
	    this.container.setBackground(Color.white);
	    this.initWelcome();
	    this.setContentPane(this.container);
	}
	
	public void initWelcome() {
		System.out.println("Showing Welcome");
		container.removeAll();
		container.add(new Welcome(size,gvc).getPanel(), BorderLayout.CENTER);
		container.revalidate();
	}
	
	public void initRules() {
		System.out.println("Showing Rules");
		container.removeAll();
		container.add(new Rules(size,gvc).getPanel(), BorderLayout.CENTER);
		container.revalidate();
	}
	
	public void initOptions() {
		this.initNewGame();
	}
	
	public void initNewGame() {
		System.out.println("Showing New Game");
		container = new NewGame(size, gvc).getPanel();
	}

	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof GameManager) {
			switch (((GameManager)o).getGameState()) {
			case "mainMenu":
				System.out.println("back");
				this.initWelcome();
				break;
			 case "options":
				 this.initOptions();
			 case "rules": 
			     System.out.println("initrule");
				 this.initRules();
				 break;
			 case "exit":
				 System.exit(0);
				 break;
			 }
		}
		
	}
}
