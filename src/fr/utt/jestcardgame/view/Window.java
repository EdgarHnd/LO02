package fr.utt.jestcardgame.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import fr.utt.jestcardgame.controler.GameViewControler;

//Class de la fenetre principale de l'interface permettant d'initialliser les différents panneaux

public class Window extends JFrame{
	
	private Dimension size;
	private JPanel container = new JPanel();
	protected GameViewControler gvc;
	
	public Window(GameViewControler gvc) {
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
	
}
