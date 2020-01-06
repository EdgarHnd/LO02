package fr.utt.jestcardgame.view;

import fr.utt.jestcardgame.controler.GameViewControler;
import fr.utt.jestcardgame.model.Card;
import fr.utt.jestcardgame.model.GameBoard;
import fr.utt.jestcardgame.model.Jest;
import fr.utt.jestcardgame.model.OptionsData;
import fr.utt.jestcardgame.model.Player;
import fr.utt.jestcardgame.model.RoundsManager;
import fr.utt.jestcardgame.observer.Observable;
import fr.utt.jestcardgame.observer.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class Board extends Panel implements Observer{
	
	private final int trophysSize = 2;
	private final int handSize = 3;
	private CardLabel trophy1;
	private CardLabel trophy2;
	private CardLabel trophy;
	private JLabel player1;
	private JLabel player2;
	private JLabel player3;
	private JLabel player4;
	private CardLabel hand1p1;
	private CardLabel hand2p1;
	private ArrayList<CardLabel> jestp1 = new ArrayList<CardLabel>();
	private CardLabel hand1p2;
	private CardLabel hand2p2;
	private ArrayList<CardLabel> jestp2 = new ArrayList<CardLabel>();
	private CardLabel hand1p3;
	private CardLabel hand2p3;
	private ArrayList<CardLabel> jestp3 = new ArrayList<CardLabel>();
	private CardLabel hand1p4;
	private CardLabel hand2p4;
	private ArrayList<CardLabel> jestp4 = new ArrayList<CardLabel>();

	public Board(Dimension dim, GameViewControler gvc) {
		super(dim, gvc);
		this.initPanel();
	}

	@Override
	protected void initPanel() {
		this.panel.setLayout(null);
		
		//titre
		JLabel titre = new JLabel("GameBoard");
		titre.setHorizontalAlignment(JLabel.CENTER);
		titre.setFont(comics40);
		titre.setBounds(0, 0, 250, 50);
		this.panel.add(titre);
		
		//trophys
		//trophy1
		this.trophy1 = new CardLabel("pictures/CardsPng/back.jpg");
		this.trophy1.setFactor(trophysSize);
		this.trophy1.setPreferredSize(new Dimension(this.trophy1.getFactor() * 34,this.trophy1.getFactor() * 48));
		this.trophy1.setVerticalAlignment(JLabel.CENTER);
		this.trophy1.setBounds(570, 340, this.trophy1.getFactor() * 34, this.trophy1.getFactor() * 48);

		
		//trophy2
		this.trophy2 = new CardLabel("pictures/CardsPng/back.jpg");
		this.trophy2.setFactor(trophysSize);
		this.trophy2.setPreferredSize(new Dimension(this.trophy1.getFactor() * 34,this.trophy2.getFactor() * 48));
		this.trophy2.setVerticalAlignment(JLabel.CENTER);
		this.trophy2.setBounds(670, 340, this.trophy2.getFactor() * 34, this.trophy2.getFactor() * 48);

		
		//aloneTrophy
		this.trophy = new CardLabel("pictures/CardsPng/back.jpg");
		this.trophy.setFactor(trophysSize);
		this.trophy.setPreferredSize(new Dimension(this.trophy.getFactor() * 34,this.trophy.getFactor() * 48));
		this.trophy.setVerticalAlignment(JLabel.CENTER);
		this.trophy.setBounds(610, 340, this.trophy.getFactor() * 34, this.trophy.getFactor() * 48);
		
		//players
		//player1
		this.player1 = new JLabel("Player 1") {
					protected void paintComponent(Graphics g) {
					Graphics2D g2 = (Graphics2D)g;
					g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
					AffineTransform aT = g2.getTransform();
					Shape oldshape = g2.getClip();
					double x = getWidth()/2.0;
					double y = getHeight()/2.0;
					aT.rotate(Math.toRadians(90), x, y);
					g2.setTransform(aT);
					g2.setClip(oldshape);
					super.paintComponent(g);
				    }
				};
		this.player1.setHorizontalAlignment(JLabel.CENTER);
		this.player1.setFont(comics20);
		this.player1.setBounds(0, 350, 100, 50);
		this.panel.add(this.player1);
		
		this.hand1p1 = new CardLabel("pictures/CardsPng/back.jpg"){
			public void paint(Graphics g) {
			Graphics2D g2 = (Graphics2D)g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
			AffineTransform aT = g2.getTransform();
			Shape oldshape = g2.getClip();
			double x = getWidth()/2.0;
			double y = getHeight()/2.0;
			aT.rotate(Math.toRadians(90), x, y);
			g2.setTransform(aT);
			g2.setClip(oldshape);
			super.paint(g);
		    }
		};
		this.hand1p1.setFactor(handSize);
		this.hand1p1.setPreferredSize(new Dimension(this.hand1p1.getFactor() * 34,this.hand1p1.getFactor() * 48));
		this.hand1p1.setVerticalAlignment(JLabel.CENTER);
		this.hand1p1.setBounds(300, 275, this.hand1p1.getFactor() * 34, this.hand1p1.getFactor() * 48);
		this.panel.add(this.hand1p1);
		this.hand1p1.addMouseListener(new MouseAdapter()  
		{  
		    public void mouseClicked(MouseEvent e)  
		    {  
		       //Put action after clicking the card here
		       GameView frame = new GameView(gvc);
		       frame.setVisible(true);

		    }  
		});
		this.hand2p1 = new CardLabel("pictures/CardsPng/back.jpg"){
			public void paint(Graphics g) {
			Graphics2D g2 = (Graphics2D)g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
			AffineTransform aT = g2.getTransform();
			Shape oldshape = g2.getClip();
			double x = getWidth()/2.0;
			double y = getHeight()/2.0;
			aT.rotate(Math.toRadians(90), x, y);
			g2.setTransform(aT);
			g2.setClip(oldshape);
			super.paint(g);
		    }
		};
		this.hand2p1.setFactor(handSize);
		this.hand2p1.setPreferredSize(new Dimension(this.hand1p1.getFactor() * 34,this.hand1p1.getFactor() * 48));
		this.hand2p1.setVerticalAlignment(JLabel.CENTER);
		this.hand2p1.setBounds(300, 375, this.hand1p1.getFactor() * 34, this.hand1p1.getFactor() * 48);
		this.panel.add(this.hand2p1);
		this.hand2p1.addMouseListener(new MouseAdapter()  
		{  
		    public void mouseClicked(MouseEvent e)  
		    {  
		       //Put action after clicking the card here
		       GameView frame = new GameView(gvc);
		       frame.setVisible(true);

		    }  
		}); 
		
		for(int i = 1; i < 8; i++) {
			CardLabel jestCard = new CardLabel("pictures/CardsPng/back.jpg"){
				public void paint(Graphics g) {
					Graphics2D g2 = (Graphics2D)g;
					g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
					AffineTransform aT = g2.getTransform();
					Shape oldshape = g2.getClip();
					double x = getWidth()/2.0;
					double y = getHeight()/2.0;
					aT.rotate(Math.toRadians(90), x, y);
					g2.setTransform(aT);
					g2.setClip(oldshape);
					super.paint(g);
				    }
				};
			jestCard.setFactor(trophysSize);
			jestCard.setPreferredSize(new Dimension(jestCard.getFactor() * 34,jestCard.getFactor() * 48));
			jestCard.setVerticalAlignment(JLabel.CENTER);
			jestCard.setBounds(150, 50+i*100, jestCard.getFactor() * 34, jestCard.getFactor() * 48);
			jestCard.setVisible(false);
			this.jestp1.add(jestCard);
		}
		for(CardLabel j:jestp1){
		    this.panel.add(j);
		}
		this.hand1p1.setVisible(false);
		this.hand2p1.setVisible(false);
		
		//player2
		this.player2 = new JLabel("Player 2");
		this.player2.setHorizontalAlignment(JLabel.CENTER);
		this.player2.setFont(comics20);
		this.player2.setBounds(600, 730, 100, 50);
		this.panel.add(this.player2);
		
		this.hand1p2 = new CardLabel("pictures/CardsPng/back.jpg");
		this.hand1p2.setFactor(handSize);
		this.hand1p2.setPreferredSize(new Dimension(this.hand1p1.getFactor() * 34,this.hand1p1.getFactor() * 48));
		this.hand1p2.setVerticalAlignment(JLabel.CENTER);
		this.hand1p2.setBounds(550, 500, this.hand1p1.getFactor() * 34, this.hand1p1.getFactor() * 48);
		this.panel.add(this.hand1p2);
		this.hand1p2.addMouseListener(new MouseAdapter()  
		{  
		    public void mouseClicked(MouseEvent e)  
		    {  
		       //Put action after clicking the card here
		    	GameView frame = new GameView(gvc);
		       frame.setVisible(true);
			}  
		}); 
		
		this.hand2p2 = new CardLabel("pictures/CardsPng/back.jpg");
		this.hand2p2.setFactor(handSize);
		this.hand2p2.setPreferredSize(new Dimension(this.hand2p1.getFactor() * 34,this.hand2p1.getFactor() * 48));
		this.hand2p2.setVerticalAlignment(JLabel.CENTER);
		this.hand2p2.setBounds(650, 500, this.hand2p1.getFactor() * 34, this.hand2p1.getFactor() * 48);
		this.panel.add(this.hand2p2);
		this.hand2p2.addMouseListener(new MouseAdapter()  
		{  
		    public void mouseClicked(MouseEvent e)  
		    {  
		       //Put action after clicking the card here
		    	GameView frame = new GameView(gvc);
		       frame.setVisible(true);
			}  
		}); 
				
		for(int i = 1; i < 8; i++) {
			CardLabel jestCard = new CardLabel("pictures/CardsPng/back.jpg");
			jestCard.setFactor(trophysSize);
			jestCard.setPreferredSize(new Dimension(jestCard.getFactor() * 34,jestCard.getFactor() * 48));
			jestCard.setVerticalAlignment(JLabel.CENTER);
			jestCard.setBounds(350+i*100, 650, jestCard.getFactor() * 34, jestCard.getFactor() * 48);
			jestCard.setVisible(false);
			this.jestp2.add(jestCard);
			}
		for(CardLabel j:jestp2) {
			this.panel.add(j);
				}
		this.hand1p2.setVisible(false);
		this.hand2p2.setVisible(false);
		
		//player3
		this.player3 = new JLabel("Player 3") {
			protected void paintComponent(Graphics g) {
					Graphics2D g2 = (Graphics2D)g;
					g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
											RenderingHints.VALUE_ANTIALIAS_ON);
					AffineTransform aT = g2.getTransform();
					Shape oldshape = g2.getClip();
					double x = getWidth()/2.0;
					double y = getHeight()/2.0;
					aT.rotate(Math.toRadians(-90), x, y);
					g2.setTransform(aT);
					g2.setClip(oldshape);
					super.paintComponent(g);
				    }
		};
		this.player3.setHorizontalAlignment(JLabel.CENTER);
		this.player3.setFont(comics20);
		this.player3.setBounds(1200, 350, 100, 50);
		this.panel.add(this.player3);
		
		this.hand1p3 = new CardLabel("pictures/CardsPng/back.jpg"){
			public void paint(Graphics g) {
			Graphics2D g2 = (Graphics2D)g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
			AffineTransform aT = g2.getTransform();
			Shape oldshape = g2.getClip();
			double x = getWidth()/2.0;
			double y = getHeight()/2.0;
			aT.rotate(Math.toRadians(-90), x, y);
			g2.setTransform(aT);
			g2.setClip(oldshape);
			super.paint(g);
		    }
		};
		this.hand1p3.setFactor(handSize);
		this.hand1p3.setPreferredSize(new Dimension(this.hand1p1.getFactor() * 34,this.hand1p1.getFactor() * 48));
		this.hand1p3.setVerticalAlignment(JLabel.CENTER);
		this.hand1p3.setBounds(900, 275, this.hand1p1.getFactor() * 34, this.hand1p1.getFactor() * 48);
		this.panel.add(this.hand1p3);
		this.hand1p3.addMouseListener(new MouseAdapter()  
		{  
		    public void mouseClicked(MouseEvent e)  
		    {  
		       //Put action after clicking the card here
		       GameView frame = new GameView(gvc);
		       frame.setVisible(true);

		    }  
		});
		this.hand2p3 = new CardLabel("pictures/CardsPng/back.jpg"){
			public void paint(Graphics g) {
			Graphics2D g2 = (Graphics2D)g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
			AffineTransform aT = g2.getTransform();
			Shape oldshape = g2.getClip();
			double x = getWidth()/2.0;
			double y = getHeight()/2.0;
			aT.rotate(Math.toRadians(-90), x, y);
			g2.setTransform(aT);
			g2.setClip(oldshape);
			super.paint(g);
		    }
		};
		this.hand2p3.setFactor(handSize);
		this.hand2p3.setPreferredSize(new Dimension(this.hand1p1.getFactor() * 34,this.hand1p1.getFactor() * 48));
		this.hand2p3.setVerticalAlignment(JLabel.CENTER);
		this.hand2p3.setBounds(900, 375, this.hand1p1.getFactor() * 34, this.hand1p1.getFactor() * 48);
		this.panel.add(this.hand2p3);
		this.hand2p3.addMouseListener(new MouseAdapter()  
		{  
		    public void mouseClicked(MouseEvent e)  
		    {  
		       //Put action after clicking the card here
		       GameView frame = new GameView(gvc);
		       frame.setVisible(true);

		    }  
		});
		
		for(int i = 1; i < 8; i++) {
			CardLabel jestCard = new CardLabel("pictures/CardsPng/back.jpg"){
				public void paint(Graphics g) {
					Graphics2D g2 = (Graphics2D)g;
					g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
					AffineTransform aT = g2.getTransform();
					Shape oldshape = g2.getClip();
					double x = getWidth()/2.0;
					double y = getHeight()/2.0;
					aT.rotate(Math.toRadians(-90), x, y);
					g2.setTransform(aT);
					g2.setClip(oldshape);
					super.paint(g);
				    }
				};
			jestCard.setFactor(trophysSize);
			jestCard.setPreferredSize(new Dimension(jestCard.getFactor() * 34,jestCard.getFactor() * 48));
			jestCard.setVerticalAlignment(JLabel.CENTER);
			jestCard.setBounds(1100, 50+i*100, jestCard.getFactor() * 34, jestCard.getFactor() * 48);
			jestCard.setVisible(false);
			this.jestp3.add(jestCard);
		}
		for(CardLabel j:jestp3){
		    this.panel.add(j);
		}
		this.hand1p3.setVisible(false);
		this.hand2p3.setVisible(false);
		
		//player4
		this.player4 = new JLabel("Player 4");
		this.player4.setHorizontalAlignment(JLabel.CENTER);
		this.player4.setFont(comics20);
		this.player4.setBounds(600, 0, 100, 50);
		
		this.hand1p4 = new CardLabel("pictures/CardsPng/back.jpg");
		this.hand1p4.setFactor(handSize);
		this.hand1p4.setPreferredSize(new Dimension(this.hand1p1.getFactor() * 34,this.hand1p1.getFactor() * 48));
		this.hand1p4.setVerticalAlignment(JLabel.CENTER);
		this.hand1p4.setBounds(550, 150, this.hand1p1.getFactor() * 34, this.hand1p1.getFactor() * 48);
		this.hand1p4.addMouseListener(new MouseAdapter()  
		{  
		    public void mouseClicked(MouseEvent e)  
		    {  
		       //Put action after clicking the card here
		       GameView frame = new GameView(gvc);
		       frame.setVisible(true);

		    }  
		}); 
		
		this.hand2p4 = new CardLabel("pictures/CardsPng/back.jpg");
		this.hand2p4.setFactor(handSize);
		this.hand2p4.setPreferredSize(new Dimension(this.hand2p1.getFactor() * 34,this.hand2p1.getFactor() * 48));
		this.hand2p4.setVerticalAlignment(JLabel.CENTER);
		this.hand2p4.setBounds(650, 150, this.hand2p1.getFactor() * 34, this.hand2p1.getFactor() * 48);	
		this.hand2p4.addMouseListener(new MouseAdapter()  
		{  
		    public void mouseClicked(MouseEvent e)  
		    {  
		       //Put action after clicking the card here
		       GameView frame = new GameView(gvc);
		       frame.setVisible(true);

		    }  
		}); 
		
		for(int i = 1; i < 8; i++) {
			CardLabel jestCard = new CardLabel("pictures/CardsPng/back.jpg");
			jestCard.setFactor(trophysSize);
			jestCard.setPreferredSize(new Dimension(jestCard.getFactor() * 34,jestCard.getFactor() * 48));
			jestCard.setVerticalAlignment(JLabel.CENTER);
			jestCard.setBounds(350+i*100, 50, jestCard.getFactor() * 34, jestCard.getFactor() * 48);
			jestCard.setVisible(false);
			this.jestp4.add(jestCard);
		}
		this.panel.add(this.hand1p4);
		this.panel.add(this.hand2p4);
		this.panel.add(this.player4);
		this.player4.setVisible(false);
		this.hand1p4.setVisible(false);
		this.hand2p4.setVisible(false);
		for(CardLabel j:this.jestp4){
		    this.panel.add(j);
		}
	}
	

	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof GameBoard) {
			if(OptionsData.getNbPlayer() == 3) {
				this.panel.add(trophy1);
				this.trophy1.setImagePath(((GameBoard) o).getTrophys().get(0).getImagePath());
				this.panel.add(trophy2);
				this.trophy2.setImagePath(((GameBoard) o).getTrophys().get(1).getImagePath());
			}
			else if(OptionsData.getNbPlayer() == 4) {
				this.panel.add(trophy);
				this.trophy.setImagePath(((GameBoard) o).getTrophys().get(0).getImagePath());
			}
		}
		else if(o instanceof RoundsManager) {
			if(OptionsData.getNbPlayer() == 3) {
				this.player1.setText(((RoundsManager) o).getListPlayers().get(0).getName());
				
				this.player2.setText(((RoundsManager) o).getListPlayers().get(1).getName());
				
				this.player3.setText(((RoundsManager) o).getListPlayers().get(2).getName());
			}
			else if(OptionsData.getNbPlayer() == 4) {
				this.player1.setText(((RoundsManager) o).getListPlayers().get(0).getName());
				
				this.player2.setText(((RoundsManager) o).getListPlayers().get(1).getName());
				
				this.player3.setText(((RoundsManager) o).getListPlayers().get(2).getName());
				
				this.player4.setText(((RoundsManager) o).getListPlayers().get(3).getName());
				this.player4.setVisible(true);
			}
		}
		else if(o instanceof Player) {
			if(((Player) o).getNb() == 1) {
				if(arg instanceof Card) {
					if(((Card) arg).equals(((Player) o).getHand().get(0))) {
						this.hand1p1.setImagePath(((Player) o).getHand().get(0).getImagePath());
						this.hand1p1.setVisible(true);
					}
					else if(((Card) arg).equals(((Player) o).getHand().get(1))) {
						this.hand2p1.setImagePath(((Player) o).getHand().get(1).getImagePath());
						this.hand2p1.setVisible(true);
					}
				}
				else if(arg instanceof Jest) {
					for(int i=0; i < ((Jest) arg).getJestCards().size();i++) {
						this.jestp1.get(i).setImagePath(((Jest) arg).getJestCards().get(i).getImagePath());
						this.jestp1.get(i).setVisible(true);
					}
				}
			}
			if(((Player) o).getNb() == 2) {
				if(arg instanceof Card) {
					if(((Card) arg).equals(((Player) o).getHand().get(0))) {
						this.hand1p2.setImagePath(((Player) o).getHand().get(0).getImagePath());
						this.hand1p2.setVisible(true);
					}
					else if(((Card) arg).equals(((Player) o).getHand().get(1))) {
						this.hand2p2.setImagePath(((Player) o).getHand().get(1).getImagePath());
						this.hand2p2.setVisible(true);
					}
				}
				else if(arg instanceof Jest) {
					for(int i=0; i < ((Jest) arg).getJestCards().size();i++) {
						this.jestp2.get(i).setImagePath(((Jest) arg).getJestCards().get(i).getImagePath());
						this.jestp2.get(i).setVisible(true);
					}
				}
			}
			if(((Player) o).getNb() == 3) {
				if(arg instanceof Card) {
					if(((Card) arg).equals(((Player) o).getHand().get(0))) {
						this.hand1p3.setImagePath(((Player) o).getHand().get(0).getImagePath());
						this.hand1p3.setVisible(true);
					}
					else if(((Card) arg).equals(((Player) o).getHand().get(1))) {
						this.hand2p3.setImagePath(((Player) o).getHand().get(1).getImagePath());
						this.hand2p3.setVisible(true);
					}
				}
				else if(arg instanceof Jest) {
					for(int i=0; i < ((Jest) arg).getJestCards().size();i++) {
						this.jestp3.get(i).setImagePath(((Jest) arg).getJestCards().get(i).getImagePath());
						this.jestp3.get(i).setVisible(true);
					}
				}
			}
			if(((Player) o).getNb() == 4) {
				if(arg instanceof Card) {
					if(((Card) arg).equals(((Player) o).getHand().get(0))) {
						this.hand1p4.setImagePath(((Player) o).getHand().get(0).getImagePath());
						this.hand1p4.setVisible(true);
					}
					else if(((Card) arg).equals(((Player) o).getHand().get(1))) {
						this.hand2p4.setImagePath(((Player) o).getHand().get(1).getImagePath());
						this.hand2p4.setVisible(true);
					}
				}
				else if(arg instanceof Jest) {
					for(int i=0; i < ((Jest) arg).getJestCards().size();i++) {
						this.jestp4.get(i).setImagePath(((Jest) arg).getJestCards().get(i).getImagePath());
						this.jestp4.get(i).setVisible(true);
					}
				}
			}
		}
	}

}
