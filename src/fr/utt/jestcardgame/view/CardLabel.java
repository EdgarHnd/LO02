package fr.utt.jestcardgame.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class CardLabel extends JLabel{
	
	private String imagePath;
	private int factor=4;

	public CardLabel(String path){
		this.imagePath = path;
	}
	
	public void paint(Graphics g){
			g.setColor(Color.black);
			g.drawRect(0, 0, 34*this.factor, 48*this.factor);
			try {
				Image img = ImageIO.read(new File(this.imagePath));
				Image scdImg = img.getScaledInstance(33*this.factor, 47*this.factor, Image.SCALE_AREA_AVERAGING);
				g.drawImage(scdImg, 1, 1, this);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	public int getFactor() {
		return factor;
	}
	public void setFactor(int factor) {
		this.factor = factor;
	}
	public void setImagePath(String path){
		this.imagePath = path;
		repaint();
	}
}
