package fr.utt.jestcardgame.view;

import fr.utt.jestcardgame.controler.GameViewControler;
import fr.utt.jestcardgame.model.*;
import fr.utt.jestcardgame.observer.Observable;
import fr.utt.jestcardgame.observer.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

/**
 * Class representing the panel where the players will be playing
 * This class extends from the class <code>Panel</code> in order to set basic attributes
 * This class implements the Observer design pattern since this is where most of the updates will be made
 *
 * @author Edgar
 */
public class Board extends Panel implements Observer {

    private final int trophysSize = 2;
    private final int handSize = 3;
    private CardLabel trophy1;
    private CardLabel trophy2;
    private CardLabel trophy;
    private JLabel player1;
    private JLabel player2;
    private JLabel player3;
    private JLabel player4;
    private JLabel p1;
    private JLabel p2;
    private JLabel p3;
    private JLabel p4;
    private JLabel s1;
    private JLabel s2;
    private JLabel s3;
    private JLabel s4;
    private JLabel winner;
    private JLabel state;
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

    /**
     * Constructor for this class
     *
     * @param dim Dimension for the panel
     * @param gvc GameViewController to control the players input (not yet implemented)
     */
    public Board(Dimension dim, GameViewControler gvc) {
        super(dim, gvc);
        this.initPanel();
    }

    /**
     * Method where all the graphical components needed for the game will be created and placed on the panel
     * This is where all the parameters are set for our JLabels and CardLabels
     */
    @Override
    protected void initPanel() {
        this.panel.setLayout(null);

        //titre
        JLabel titre = new JLabel("GameBoard");
        titre.setHorizontalAlignment(JLabel.CENTER);
        titre.setFont(comics40);
        titre.setBounds(0, 0, 250, 50);
        this.panel.add(titre);

        this.winner = new JLabel("The winner is");
        this.winner.setHorizontalAlignment(JLabel.CENTER);
        this.winner.setFont(comics40);
        this.winner.setBounds(400, 300, 500, 50);
        this.panel.add(this.winner);
        this.winner.setVisible(false);

        this.state = new JLabel("Game started");
        this.state.setHorizontalAlignment(JLabel.CENTER);
        this.state.setFont(comics20);
        this.state.setBounds(1100, 0, 200, 50);
        this.panel.add(this.state);

        //trophys
        //trophy1
        this.trophy1 = new CardLabel("pictures/CardsPng/back.jpg");
        this.trophy1.setFactor(trophysSize);
        this.trophy1.setPreferredSize(new Dimension(this.trophy1.getFactor() * 34, this.trophy1.getFactor() * 48));
        this.trophy1.setVerticalAlignment(JLabel.CENTER);
        this.trophy1.setBounds(570, 340, this.trophy1.getFactor() * 34, this.trophy1.getFactor() * 48);


        //trophy2
        this.trophy2 = new CardLabel("pictures/CardsPng/back.jpg");
        this.trophy2.setFactor(trophysSize);
        this.trophy2.setPreferredSize(new Dimension(this.trophy1.getFactor() * 34, this.trophy2.getFactor() * 48));
        this.trophy2.setVerticalAlignment(JLabel.CENTER);
        this.trophy2.setBounds(670, 340, this.trophy2.getFactor() * 34, this.trophy2.getFactor() * 48);


        //aloneTrophy
        this.trophy = new CardLabel("pictures/CardsPng/back.jpg");
        this.trophy.setFactor(trophysSize);
        this.trophy.setPreferredSize(new Dimension(this.trophy.getFactor() * 34, this.trophy.getFactor() * 48));
        this.trophy.setVerticalAlignment(JLabel.CENTER);
        this.trophy.setBounds(610, 340, this.trophy.getFactor() * 34, this.trophy.getFactor() * 48);

        //players
        //player1
        this.player1 = new JLabel("Player 1");
        this.player1.setHorizontalAlignment(JLabel.CENTER);
        this.player1.setFont(comics20);
        this.player1.setBounds(0, 350, 100, 50);
        this.panel.add(this.player1);

        this.p1 = new JLabel("Playing");
        this.p1.setHorizontalAlignment(JLabel.CENTER);
        this.p1.setFont(comics20);
        this.p1.setBounds(0, 400, 100, 50);
        this.panel.add(this.p1);
        this.p1.setVisible(false);

        this.s1 = new JLabel("Score :");
        this.s1.setHorizontalAlignment(JLabel.CENTER);
        this.s1.setFont(comics20);
        this.s1.setBounds(0, 500, 110, 50);
        this.panel.add(this.s1);
        this.s1.setVisible(false);

        this.hand1p1 = new CardLabel("pictures/CardsPng/back.jpg");
        this.hand1p1.setFactor(handSize);
        this.hand1p1.setPreferredSize(new Dimension(this.hand1p1.getFactor() * 34, this.hand1p1.getFactor() * 48));
        this.hand1p1.setVerticalAlignment(JLabel.CENTER);
        this.hand1p1.setBounds(300, 275, this.hand1p1.getFactor() * 34, this.hand1p1.getFactor() * 48);
        this.panel.add(this.hand1p1);
        this.hand1p1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                //Put action after clicking the card here
                GameView frame = new GameView(gvc);
                frame.setVisible(true);

            }
        });
        this.hand2p1 = new CardLabel("pictures/CardsPng/back.jpg");
        this.hand2p1.setFactor(handSize);
        this.hand2p1.setPreferredSize(new Dimension(this.hand1p1.getFactor() * 34, this.hand1p1.getFactor() * 48));
        this.hand2p1.setVerticalAlignment(JLabel.CENTER);
        this.hand2p1.setBounds(400, 275, this.hand1p1.getFactor() * 34, this.hand1p1.getFactor() * 48);
        this.panel.add(this.hand2p1);
        this.hand2p1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                //Put action after clicking the card here
                GameView frame = new GameView(gvc);
                frame.setVisible(true);

            }
        });

        for (int i = 1; i < 8; i++) {
            CardLabel jestCard = new CardLabel("pictures/CardsPng/back.jpg") {
                public void paint(Graphics g) {
                    Graphics2D g2 = (Graphics2D) g;
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    AffineTransform aT = g2.getTransform();
                    Shape oldshape = g2.getClip();
                    double x = getWidth() / 2.0;
                    double y = getHeight() / 2.0;
                    aT.rotate(Math.toRadians(90), x, y);
                    g2.setTransform(aT);
                    g2.setClip(oldshape);
                    super.paint(g);
                }
            };
            jestCard.setFactor(trophysSize);
            jestCard.setPreferredSize(new Dimension(jestCard.getFactor() * 34, jestCard.getFactor() * 48));
            jestCard.setVerticalAlignment(JLabel.CENTER);
            jestCard.setBounds(150, 100 + i * 80, jestCard.getFactor() * 34, jestCard.getFactor() * 48);
            jestCard.setVisible(false);
            this.jestp1.add(jestCard);
        }
        for (CardLabel j : jestp1) {
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

        this.p2 = new JLabel("Playing");
        this.p2.setHorizontalAlignment(JLabel.CENTER);
        this.p2.setFont(comics20);
        this.p2.setBounds(700, 730, 100, 50);
        this.panel.add(this.p2);
        this.p2.setVisible(false);

        this.s2 = new JLabel("Score :");
        this.s2.setHorizontalAlignment(JLabel.CENTER);
        this.s2.setFont(comics20);
        this.s2.setBounds(800, 730, 120, 50);
        this.panel.add(this.s2);
        this.s2.setVisible(false);

        this.hand1p2 = new CardLabel("pictures/CardsPng/back.jpg");
        this.hand1p2.setFactor(handSize);
        this.hand1p2.setPreferredSize(new Dimension(this.hand1p1.getFactor() * 34, this.hand1p1.getFactor() * 48));
        this.hand1p2.setVerticalAlignment(JLabel.CENTER);
        this.hand1p2.setBounds(550, 500, this.hand1p1.getFactor() * 34, this.hand1p1.getFactor() * 48);
        this.panel.add(this.hand1p2);
        this.hand1p2.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                //Put action after clicking the card here
                GameView frame = new GameView(gvc);
                frame.setVisible(true);
            }
        });

        this.hand2p2 = new CardLabel("pictures/CardsPng/back.jpg");
        this.hand2p2.setFactor(handSize);
        this.hand2p2.setPreferredSize(new Dimension(this.hand2p1.getFactor() * 34, this.hand2p1.getFactor() * 48));
        this.hand2p2.setVerticalAlignment(JLabel.CENTER);
        this.hand2p2.setBounds(650, 500, this.hand2p1.getFactor() * 34, this.hand2p1.getFactor() * 48);
        this.panel.add(this.hand2p2);
        this.hand2p2.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                //Put action after clicking the card here
                GameView frame = new GameView(gvc);
                frame.setVisible(true);
            }
        });

        for (int i = 1; i < 8; i++) {
            CardLabel jestCard = new CardLabel("pictures/CardsPng/back.jpg");
            jestCard.setFactor(trophysSize);
            jestCard.setPreferredSize(new Dimension(jestCard.getFactor() * 34, jestCard.getFactor() * 48));
            jestCard.setVerticalAlignment(JLabel.CENTER);
            jestCard.setBounds(250 + i * 100, 650, jestCard.getFactor() * 34, jestCard.getFactor() * 48);
            jestCard.setVisible(false);
            this.jestp2.add(jestCard);
        }
        for (CardLabel j : jestp2) {
            this.panel.add(j);
        }
        this.hand1p2.setVisible(false);
        this.hand2p2.setVisible(false);

        //player3
        this.player3 = new JLabel("Player 3");
        this.player3.setHorizontalAlignment(JLabel.CENTER);
        this.player3.setFont(comics20);
        this.player3.setBounds(1200, 350, 100, 50);
        this.panel.add(this.player3);

        this.p3 = new JLabel("Playing");
        this.p3.setHorizontalAlignment(JLabel.CENTER);
        this.p3.setFont(comics20);
        this.p3.setBounds(1200, 400, 100, 50);
        this.panel.add(this.p3);
        this.p3.setVisible(false);

        this.s3 = new JLabel("Score :");
        this.s3.setHorizontalAlignment(JLabel.CENTER);
        this.s3.setFont(comics20);
        this.s3.setBounds(1170, 500, 150, 50);
        this.panel.add(this.s3);
        this.s3.setVisible(false);

        this.hand1p3 = new CardLabel("pictures/CardsPng/back.jpg");
        this.hand1p3.setFactor(handSize);
        this.hand1p3.setPreferredSize(new Dimension(this.hand1p1.getFactor() * 34, this.hand1p1.getFactor() * 48));
        this.hand1p3.setVerticalAlignment(JLabel.CENTER);
        this.hand1p3.setBounds(800, 275, this.hand1p1.getFactor() * 34, this.hand1p1.getFactor() * 48);
        this.panel.add(this.hand1p3);
        this.hand1p3.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                //Put action after clicking the card here
                GameView frame = new GameView(gvc);
                frame.setVisible(true);

            }
        });
        this.hand2p3 = new CardLabel("pictures/CardsPng/back.jpg");
        this.hand2p3.setFactor(handSize);
        this.hand2p3.setPreferredSize(new Dimension(this.hand1p1.getFactor() * 34, this.hand1p1.getFactor() * 48));
        this.hand2p3.setVerticalAlignment(JLabel.CENTER);
        this.hand2p3.setBounds(900, 275, this.hand1p1.getFactor() * 34, this.hand1p1.getFactor() * 48);
        this.panel.add(this.hand2p3);
        this.hand2p3.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                //Put action after clicking the card here
                GameView frame = new GameView(gvc);
                frame.setVisible(true);

            }
        });

        for (int i = 1; i < 8; i++) {
            CardLabel jestCard = new CardLabel("pictures/CardsPng/back.jpg") {
                public void paint(Graphics g) {
                    Graphics2D g2 = (Graphics2D) g;
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    AffineTransform aT = g2.getTransform();
                    Shape oldshape = g2.getClip();
                    double x = getWidth() / 2.0;
                    double y = getHeight() / 2.0;
                    aT.rotate(Math.toRadians(-90), x, y);
                    g2.setTransform(aT);
                    g2.setClip(oldshape);
                    super.paint(g);
                }
            };
            jestCard.setFactor(trophysSize);
            jestCard.setPreferredSize(new Dimension(jestCard.getFactor() * 34, jestCard.getFactor() * 48));
            jestCard.setVerticalAlignment(JLabel.CENTER);
            jestCard.setBounds(1100, 100 + i * 80, jestCard.getFactor() * 34, jestCard.getFactor() * 48);
            jestCard.setVisible(false);
            this.jestp3.add(jestCard);
        }
        for (CardLabel j : jestp3) {
            this.panel.add(j);
        }
        this.hand1p3.setVisible(false);
        this.hand2p3.setVisible(false);

        //player4
        this.player4 = new JLabel("Player 4");
        this.player4.setHorizontalAlignment(JLabel.CENTER);
        this.player4.setFont(comics20);
        this.player4.setBounds(600, 0, 100, 50);

        this.p4 = new JLabel("Playing");
        this.p4.setHorizontalAlignment(JLabel.CENTER);
        this.p4.setFont(comics20);
        this.p4.setBounds(700, 0, 100, 50);
        this.panel.add(p4);
        this.p4.setVisible(false);

        this.s4 = new JLabel("Score :");
        this.s4.setHorizontalAlignment(JLabel.CENTER);
        this.s4.setFont(comics20);
        this.s4.setBounds(750, 0, 120, 50);
        this.panel.add(s4);
        this.s4.setVisible(false);

        this.hand1p4 = new CardLabel("pictures/CardsPng/back.jpg");
        this.hand1p4.setFactor(handSize);
        this.hand1p4.setPreferredSize(new Dimension(this.hand1p1.getFactor() * 34, this.hand1p1.getFactor() * 48));
        this.hand1p4.setVerticalAlignment(JLabel.CENTER);
        this.hand1p4.setBounds(550, 150, this.hand1p1.getFactor() * 34, this.hand1p1.getFactor() * 48);
        this.hand1p4.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                //Put action after clicking the card here
                GameView frame = new GameView(gvc);
                frame.setVisible(true);

            }
        });

        this.hand2p4 = new CardLabel("pictures/CardsPng/back.jpg");
        this.hand2p4.setFactor(handSize);
        this.hand2p4.setPreferredSize(new Dimension(this.hand2p1.getFactor() * 34, this.hand2p1.getFactor() * 48));
        this.hand2p4.setVerticalAlignment(JLabel.CENTER);
        this.hand2p4.setBounds(650, 150, this.hand2p1.getFactor() * 34, this.hand2p1.getFactor() * 48);
        this.hand2p4.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                //Put action after clicking the card here
                GameView frame = new GameView(gvc);
                frame.setVisible(true);

            }
        });

        for (int i = 1; i < 8; i++) {
            CardLabel jestCard = new CardLabel("pictures/CardsPng/back.jpg");
            jestCard.setFactor(trophysSize);
            jestCard.setPreferredSize(new Dimension(jestCard.getFactor() * 34, jestCard.getFactor() * 48));
            jestCard.setVerticalAlignment(JLabel.CENTER);
            jestCard.setBounds(250 + i * 100, 50, jestCard.getFactor() * 34, jestCard.getFactor() * 48);
            jestCard.setVisible(false);
            this.jestp4.add(jestCard);
        }
        this.panel.add(this.hand1p4);
        this.panel.add(this.hand2p4);
        this.panel.add(this.player4);
        this.player4.setVisible(false);
        this.hand1p4.setVisible(false);
        this.hand2p4.setVisible(false);
        for (CardLabel j : this.jestp4) {
            this.panel.add(j);
        }
    }

    /**
     * Update method from the observer design pattern in order to change the graphical components
     * of the board based on what is happening in the game
     * The observed object can either be :
     * the <code>GameBoard</code> in order to update the trophys
     * the <code>RoundsManager</code> in order to set the board based on the number of player
     * or to update each player cards during the game
     *
     * @param o   Observable object that we are observing
     * @param arg Object send by the Observable object to the update method
     */
    @Override
    public void update(Observable o, Object arg) {
        //GameBoard
        if (o instanceof GameBoard) {
            if (OptionsData.getNbPlayer() == 3) {
                this.panel.add(trophy1);
                this.trophy1.setImagePath(((GameBoard) o).getTrophys().get(0).getImagePath());
                this.panel.add(trophy2);
                this.trophy2.setImagePath(((GameBoard) o).getTrophys().get(1).getImagePath());
            } else if (OptionsData.getNbPlayer() == 4) {
                this.panel.add(trophy);
                this.trophy.setImagePath(((GameBoard) o).getTrophys().get(0).getImagePath());
            }
        }
        //RoundsManager
        else if (o instanceof RoundsManager) {
            this.state.setText(((RoundsManager) o).getState());
            if (OptionsData.getNbPlayer() == 3) {
                this.player1.setText(((RoundsManager) o).getListPlayers().get(0).getName());

                this.player2.setText(((RoundsManager) o).getListPlayers().get(1).getName());

                this.player3.setText(((RoundsManager) o).getListPlayers().get(2).getName());
            } else if (OptionsData.getNbPlayer() == 4) {
                this.player1.setText(((RoundsManager) o).getListPlayers().get(0).getName());

                this.player2.setText(((RoundsManager) o).getListPlayers().get(1).getName());

                this.player3.setText(((RoundsManager) o).getListPlayers().get(2).getName());

                this.player4.setText(((RoundsManager) o).getListPlayers().get(3).getName());
                this.player4.setVisible(true);
            }
            if (((RoundsManager) o).getState() == "GameOver") {
                this.hand1p1.setVisible(false);
                this.hand2p1.setVisible(false);
                this.hand1p2.setVisible(false);
                this.hand2p2.setVisible(false);
                this.hand1p3.setVisible(false);
                this.hand2p3.setVisible(false);
                this.hand1p4.setVisible(false);
                this.hand2p4.setVisible(false);
                this.trophy.setVisible(false);
                this.trophy1.setVisible(false);
                this.trophy2.setVisible(false);
            }
            if (((RoundsManager) o).getState() == "Dealing" || ((RoundsManager) o).getState() == "Make offer") {
                for (Player p : ((RoundsManager) o).getListPlayers()) {
                    if (p.getHand().size() == 0) {
                        switch (p.getNb()) {
                            case 1:
                                this.hand1p1.setVisible(false);
                                this.hand2p1.setVisible(false);
                                break;
                            case 2:
                                this.hand1p2.setVisible(false);
                                this.hand2p2.setVisible(false);
                                break;
                            case 3:
                                this.hand1p3.setVisible(false);
                                this.hand2p3.setVisible(false);
                                break;
                            case 4:
                                this.hand1p4.setVisible(false);
                                this.hand2p4.setVisible(false);
                                break;
                        }
                    } else if (p.getHand().size() == 1) {
                        switch (p.getNb()) {
                            case 1:
                                this.hand1p1.setImagePath("pictures/CardsPng/back.jpg");
                                this.hand2p1.setImagePath("pictures/CardsPng/back.jpg");
                                this.hand1p1.setVisible(true);
                                this.hand2p1.setVisible(false);
                                break;
                            case 2:
                                this.hand1p2.setImagePath("pictures/CardsPng/back.jpg");
                                this.hand2p2.setImagePath("pictures/CardsPng/back.jpg");
                                this.hand1p2.setVisible(true);
                                this.hand2p2.setVisible(false);
                                break;
                            case 3:
                                this.hand1p3.setImagePath("pictures/CardsPng/back.jpg");
                                this.hand2p3.setImagePath("pictures/CardsPng/back.jpg");
                                this.hand1p3.setVisible(true);
                                this.hand2p3.setVisible(false);
                                break;
                            case 4:
                                this.hand1p4.setImagePath("pictures/CardsPng/back.jpg");
                                this.hand2p4.setImagePath("pictures/CardsPng/back.jpg");
                                this.hand1p4.setVisible(true);
                                this.hand2p4.setVisible(false);
                                break;
                        }
                    } else if (p.getHand().size() == 2) {
                        switch (p.getNb()) {
                            case 1:
                                this.hand1p1.setImagePath("pictures/CardsPng/back.jpg");
                                this.hand2p1.setImagePath("pictures/CardsPng/back.jpg");
                                this.hand1p1.setVisible(true);
                                this.hand2p1.setVisible(true);
                                break;
                            case 2:
                                this.hand1p2.setImagePath("pictures/CardsPng/back.jpg");
                                this.hand2p2.setImagePath("pictures/CardsPng/back.jpg");
                                this.hand1p2.setVisible(true);
                                this.hand2p2.setVisible(true);
                                break;
                            case 3:
                                this.hand1p3.setImagePath("pictures/CardsPng/back.jpg");
                                this.hand2p3.setImagePath("pictures/CardsPng/back.jpg");
                                this.hand1p3.setVisible(true);
                                this.hand2p3.setVisible(true);
                                break;
                            case 4:
                                this.hand1p4.setImagePath("pictures/CardsPng/back.jpg");
                                this.hand2p4.setImagePath("pictures/CardsPng/back.jpg");
                                this.hand1p4.setVisible(true);
                                this.hand2p4.setVisible(true);
                                break;
                        }
                    }
                }
            }
            if (((RoundsManager) o).getState() == "Pick offer") {
                for (Player p : ((RoundsManager) o).getListPlayers()) {
                    if (p.getOffer().size() == 0) {
                        switch (p.getNb()) {
                            case 1:
                                this.hand1p1.setVisible(false);
                                this.hand2p1.setVisible(false);
                                break;
                            case 2:
                                this.hand1p2.setVisible(false);
                                this.hand2p2.setVisible(false);
                                break;
                            case 3:
                                this.hand1p3.setVisible(false);
                                this.hand2p3.setVisible(false);
                                break;
                            case 4:
                                this.hand1p4.setVisible(false);
                                this.hand2p4.setVisible(false);
                                break;
                        }
                    } else if (p.getOffer().size() == 1) {
                        switch (p.getNb()) {
                            case 1:
                                this.hand1p1.setVisible(true);
                                this.hand2p1.setVisible(false);
                                break;
                            case 2:
                                this.hand1p2.setVisible(true);
                                this.hand2p2.setVisible(false);
                                break;
                            case 3:
                                this.hand1p3.setVisible(true);
                                this.hand2p3.setVisible(false);
                                break;
                            case 4:
                                this.hand1p4.setVisible(true);
                                this.hand2p4.setVisible(false);
                                break;
                        }
                    } else if (p.getOffer().size() == 2) {
                        switch (p.getNb()) {
                            case 1:
                                this.hand1p1.setVisible(true);
                                this.hand2p1.setVisible(true);
                                break;
                            case 2:
                                this.hand1p2.setVisible(true);
                                this.hand2p2.setVisible(true);
                                break;
                            case 3:
                                this.hand1p3.setVisible(true);
                                this.hand2p3.setVisible(true);
                                break;
                            case 4:
                                this.hand1p4.setVisible(true);
                                this.hand2p4.setVisible(true);
                                break;
                        }
                    }
                }
            }
            if (arg instanceof Player) {
                if (arg.equals(((RoundsManager) o).getFinalWinner())) {
                    this.winner.setText("The winner is " + ((Player) arg).getName());
                    this.winner.setVisible(true);
                }
            }
        }
        //Player
        else if (o instanceof Player) {
            this.panel.repaint();
            //Player1
            if (((Player) o).getNb() == 1) {
                if (arg instanceof Jest) {
                    for (int i = 0; i < ((Jest) arg).getJestCards().size(); i++) {
                        this.jestp1.get(i).setImagePath(((Jest) arg).getJestCards().get(i).getImagePath());
                        this.jestp1.get(i).setVisible(true);
                    }
                } else if (arg == null) {
                    if (((Player) o).isPlaying() == true) {
                        this.p1.setVisible(true);
                        this.hand1p1.setImagePath(((Player) o).getHand().get(0).getImagePath());
                        this.hand2p1.setImagePath(((Player) o).getHand().get(1).getImagePath());
                    } else if (((Player) o).isPlaying() == false) {
                        this.p1.setVisible(false);
                        if (((Player) o).getHand().get(0).isHidden()) {
                            this.hand1p1.setImagePath("pictures/CardsPng/back.jpg");
                        }
                        if (((Player) o).getHand().get(1).isHidden()) {
                            this.hand2p1.setImagePath("pictures/CardsPng/back.jpg");
                        }
                    }
                } else if (arg instanceof Score) {
                    this.s1.setText("Score : " + ((Score) arg).getScore());
                    this.s1.setVisible(true);
                }
            }
            //Player2
            if (((Player) o).getNb() == 2) {
                if (arg instanceof Jest) {
                    for (int i = 0; i < ((Jest) arg).getJestCards().size(); i++) {
                        this.jestp2.get(i).setImagePath(((Jest) arg).getJestCards().get(i).getImagePath());
                        this.jestp2.get(i).setVisible(true);
                    }
                } else if (arg == null) {
                    if (((Player) o).isPlaying() == true) {
                        this.p2.setVisible(true);
                        this.hand1p2.setImagePath(((Player) o).getHand().get(0).getImagePath());
                        this.hand2p2.setImagePath(((Player) o).getHand().get(1).getImagePath());
                    } else if (((Player) o).isPlaying() == false) {
                        this.p2.setVisible(false);
                        if (((Player) o).getHand().get(0).isHidden()) {
                            this.hand1p2.setImagePath("pictures/CardsPng/back.jpg");
                        }
                        if (((Player) o).getHand().get(1).isHidden()) {
                            this.hand2p2.setImagePath("pictures/CardsPng/back.jpg");
                        }
                    }
                } else if (arg instanceof Score) {
                    this.s2.setText("Score : " + ((Score) arg).getScore());
                    this.s2.setVisible(true);
                }
            }
            //Player3
            if (((Player) o).getNb() == 3) {
                if (arg instanceof Jest) {
                    for (int i = 0; i < ((Jest) arg).getJestCards().size(); i++) {
                        this.jestp3.get(i).setImagePath(((Jest) arg).getJestCards().get(i).getImagePath());
                        this.jestp3.get(i).setVisible(true);
                    }
                } else if (arg == null) {
                    if (((Player) o).isPlaying() == true) {
                        this.p3.setVisible(true);
                        this.hand1p3.setImagePath(((Player) o).getHand().get(0).getImagePath());
                        this.hand2p3.setImagePath(((Player) o).getHand().get(1).getImagePath());
                    } else if (((Player) o).isPlaying() == false) {
                        this.p3.setVisible(false);
                        if (((Player) o).getHand().get(0).isHidden()) {
                            this.hand1p3.setImagePath("pictures/CardsPng/back.jpg");
                        }
                        if (((Player) o).getHand().get(1).isHidden()) {
                            this.hand2p3.setImagePath("pictures/CardsPng/back.jpg");
                        }
                    }
                } else if (arg instanceof Score) {
                    this.s3.setText("Score : " + ((Score) arg).getScore());
                    this.s3.setVisible(true);
                }
            }
            //Player4
            if (((Player) o).getNb() == 4) {
                if (arg instanceof Jest) {
                    for (int i = 0; i < ((Jest) arg).getJestCards().size(); i++) {
                        this.jestp4.get(i).setImagePath(((Jest) arg).getJestCards().get(i).getImagePath());
                        this.jestp4.get(i).setVisible(true);
                    }
                } else if (arg == null) {
                    if (((Player) o).isPlaying() == true) {
                        this.p4.setVisible(true);
                        this.hand1p4.setImagePath(((Player) o).getHand().get(0).getImagePath());
                        this.hand2p4.setImagePath(((Player) o).getHand().get(1).getImagePath());
                    } else if (((Player) o).isPlaying() == false) {
                        this.p4.setVisible(false);
                        if (((Player) o).getHand().get(0).isHidden()) {
                            this.hand1p4.setImagePath("pictures/CardsPng/back.jpg");
                        }
                        if (((Player) o).getHand().get(1).isHidden()) {
                            this.hand2p4.setImagePath("pictures/CardsPng/back.jpg");
                        }
                    }
                } else if (arg instanceof Score) {
                    this.s4.setText("Score : " + ((Score) arg).getScore());
                    this.s4.setVisible(true);
                }
            }
        }
    }

}
