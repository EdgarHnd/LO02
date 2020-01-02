package fr.utt.jestcardgame.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import fr.utt.jestcardgame.controler.GameViewControler;
import fr.utt.jestcardgame.model.GameManager;
import fr.utt.jestcardgame.observer.Observable;
import fr.utt.jestcardgame.observer.Observer;

//Class principal de l'interface graphique héritant de la classe window qui correspond à une fenetre

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
		
	}
	
	public void initNewGame() {
		
	}

	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof GameManager) {
			switch (((GameManager)o).getGameState()) {
			case "mainMenu":
				System.out.println("back");
				this.initWelcome();
				break;
			// case 1: 
			//	 this.initOptions();
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
