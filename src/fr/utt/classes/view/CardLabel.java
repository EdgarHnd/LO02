package fr.utt.classes.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * This class extends the java JLabel class in order to create a custom JLabel to represent our card objects
 *
 * @author Edgar
 */
public class CardLabel extends JLabel {

    private String imagePath;
    private int factor = 4;

    /**
     * Create a CardLabel with the path to its matching image in the project files
     *
     * @param path String representing the path in the program data to the card image
     */
    public CardLabel(String path) {
        this.imagePath = path;
    }

    /**
     * Method use to paint the image from the file onto the Label
     *
     * @param g Graphics component needed for java to display images
     */
    public void paint(Graphics g) {
        g.setColor(Color.white);
        g.drawRect(0, 0, 33 * this.factor, 47 * this.factor);
        try {
            Image img = ImageIO.read(new File(this.imagePath));
            Image scdImg = img.getScaledInstance(33 * this.factor, 47 * this.factor, Image.SCALE_AREA_AVERAGING);
            g.drawImage(scdImg, 1, 1, this);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * Return the factor use to calculate the size of the label
     *
     * @return this.factor
     */
    public int getFactor() {
        return this.factor;
    }

    /**
     * Setter for the factor attribute
     *
     * @param factor integer  use to calculate the new size of the label
     */
    public void setFactor(int factor) {
        this.factor = factor;
    }

    /**
     * Setter for the ImagePath attribute used to change the card image at any moment
     *
     * @param path String representing the new path of the image
     */
    public void setImagePath(String path) {
        this.imagePath = path;
        repaint();
    }
}
