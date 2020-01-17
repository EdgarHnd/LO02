package fr.utt.classes.controler;

import fr.utt.classes.model.GameManager;
import fr.utt.classes.view.setupException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The class for the controler of the <code>GameView</code> graphical interface.
 * This is where all the ActionListener for the buttons are created and linked to the model.
 *
 * @author Edgar
 */
public class GameViewControler extends AbstractControler {
    /**
     * Create an <code>GameViewControler</code> object
     *
     * @param gm GameManager the name of the main class of our model{@link fr.utt.classes.model.GameManager}
     */
    public GameViewControler(GameManager gm) {
        super(gm);
    }

    /**
     * Gets the ActionListener options.
     *
     * @return this.options()
     */
    public ActionListener getOptions() {
        return options;
    }

    /**
     * Gets the ActionListener rules.
     *
     * @return this.rules()
     */
    public ActionListener getRules() {
        return rules;
    }

    /**
     * Gets the ActionListener quit.
     *
     * @return this.quit()
     */
    public ActionListener getQuit() {
        return quit;
    }

    /**
     * Gets the ActionListener back.
     *
     * @return this.back()
     */
    public ActionListener getBack() {
        return back;
    }

    /**
     * Gets the ActionListener start.
     *
     * @return this.start()
     */
    public ActionListener getStart() {
        return start;
    }

    /**
     * Create an ActionListener for the button options and link it to the model.
     */
    private ActionListener options = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Thread options = new Thread(new Runnable() {
                public void run() {
                    try {
                        gm.executeUserChoice(4);
                    } catch (setupException e) {
                        e.printStackTrace();
                    }
                    System.out.println("button options pressed");
                }
            });
            options.start();
        }

    };

    /**
     * Create an ActionListener for the button rules and link it to the model.
     */
    private ActionListener rules = e -> {
        Thread rules = new Thread(new Runnable() {
            public void run() {
                try {
                    gm.executeUserChoice(2);
                } catch (setupException e) {
                    e.printStackTrace();
                }
                System.out.println("button rules pressed");
            }
        });
        rules.start();
    };

    /**
     * Create an ActionListener for the button back and link it to the model.
     */
    private ActionListener back = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Thread back = new Thread(new Runnable() {
                public void run() {
                    try {
                        gm.mainMenu();
                    } catch (setupException e) {
                        e.printStackTrace();
                    }
                    System.out.println("button rules pressed");
                }
            });
            back.start();
        }
    };

    /**
     * Create an ActionListener for the button start and link it to the model.
     */
    private ActionListener start = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Thread start = new Thread(new Runnable() {
                public void run() {
                    gm.play();
                    System.out.println("button start pressed");
                }
            });
            start.start();
        }
    };

    /**
     * Create an ActionListener for the button quit and link it to the model.
     */
    private ActionListener quit = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    };
}
