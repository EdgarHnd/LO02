package fr.utt.jestcardgame.view;

import fr.utt.jestcardgame.controler.GameViewControler;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
/**
 * Class representing a panel to show the rules of the game
 * This class extends from the class <code>Panel</code> in order to set basic attributes
 * @author Edgar
 */
public class Rules extends Panel{
	
	private String imagePath = "pictures/Other/rules.png";
	private Image im;
	private JButton back;
	private JLabel rul;
	/**
     * Constructor for this class
     * @param dim Dimension for the panel
     * @param gvc GameViewController to control the players input
     */
	public Rules(Dimension dim, GameViewControler gvc) {
		super(dim, gvc);
		this.initPanel();
	}
	/**
     * Method use to print out the image corresponding to the rules
     * Create and place the back button needed to go back to the main menu
     */
	@Override
	protected void initPanel() {
		try {
			this.im = ImageIO.read(new File(this.imagePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Image scaledImage = this.im.getScaledInstance(1200, 700, Image.SCALE_AREA_AVERAGING);

		this.back = new JButton("BACK");
		this.back.setBounds(10, 0, 50, 50);
		this.back.addActionListener(this.gvc.getBack());
		this.panel.add(back);
		this.rul = new JLabel(new ImageIcon(scaledImage));
		this.rul.setBounds(0, 0, 1200, 700);
		this.panel.add(rul);
	}
	
}
