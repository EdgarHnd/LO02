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
	private Board board/* = new Board(size,gvc)*/;
	
	public Board getBoard() {
		return board;
	}

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
		System.out.println("Showing Options");
		container.removeAll();
		container.add(new Options(size, gvc).getPanel(), BorderLayout.CENTER);
		container.revalidate();
	}

	public void initNewGame() {
		System.out.println("Showing Board");
		container.removeAll();
		this.board = new Board(size,gvc);
		GameManager.getInstance().setBoard(this.board);
		System.out.println("getboard "+GameManager.getInstance().getBoard());
		container.add(this.board.getPanel(), BorderLayout.CENTER);
		container.revalidate();
	}

	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof GameManager) {
			switch (((GameManager)o).getGameState()) {
			case "mainMenu":
				System.out.println("update menu");
				this.initWelcome();
				break;
			case "started":
				System.out.println("update show newGame");
				this.initNewGame();
				break;
			case "options":
				this.initOptions();
				break;
			 case "rules": 
			     System.out.println("update rules");
				 this.initRules();
				 break;
			 case "exit":
				 System.exit(0);
				 break;
			 }
		}
		
	}
}
