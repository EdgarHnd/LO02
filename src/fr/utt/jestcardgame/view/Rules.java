package fr.utt.jestcardgame.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class Rules extends Panel{
	
	private String imagePath = "pictures/Other/rules.png";
	private Image im;
	private JButton back;

	public Rules(Dimension dim) {
		super(dim);
		this.initPanel();
	}

	@Override
	protected void initPanel() {
		try {
			this.im = ImageIO.read(new File(this.imagePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Image scaledImage = this.im.getScaledInstance(1200, 700, Image.SCALE_AREA_AVERAGING);
		
		this.panel.add(new JLabel(new ImageIcon(scaledImage)), BorderLayout.CENTER);
		this.back = new JButton("BACK");
		this.panel.add(back);
	}
	
}
