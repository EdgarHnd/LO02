package fr.utt.classes.view;

import fr.utt.classes.controler.GameViewControler;

import javax.swing.*;
import java.awt.*;

/**
 * Abstract class from where all of our panel inherit in order to have basic settings
 * This class countain the Java element JPanel
 *
 * @author Edgar
 */
public abstract class Panel {
    protected JPanel panel;
    protected GameViewControler gvc;

    protected Font comics30 = new Font("Comics Sans MS", Font.BOLD, 30);
    protected Font comics40 = new Font("Comics Sans MS", Font.BOLD, 40);
    protected Font comics20 = new Font("Comics Sans MS", Font.BOLD, 20);
    protected Font arial = new Font("Arial", Font.BOLD, 15);
    protected Font dialog = new Font("Dialog", Font.BOLD + Font.ITALIC, 15);

    /**
     * Constructor of the class never used directly because this is an abstract class but it
     * sets all the basic attributes needed when contructing other panels
     *
     * @param dim Dimension the size of the panel
     * @param gvc GameViewControler use by the real panel to control their inputs
     */
    public Panel(Dimension dim, GameViewControler gvc) {
        this.panel = new JPanel();
        this.panel.setPreferredSize(dim);
        this.panel.setBackground(Color.white);
        this.gvc = gvc;
    }

    /**
     * Getter to return the panel
     *
     * @return this.panel
     */
    protected JPanel getPanel() {
        return this.panel;
    }

    /**
     * Abstract method that is implemented in the class extending this one
     */
    protected abstract void initPanel();

}
