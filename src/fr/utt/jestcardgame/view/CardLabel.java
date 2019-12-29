package fr.utt.jestcardgame.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

public class CardLabel extends JLabel{
	
	private String imagePath = "pictures/CardsPng/rulescard.png";
	private int factor;
	public CardLabel(){
		this.factor = 4;
	}
	public CardLabel(String path){
		this.imagePath = path;
		this.factor = 4;
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
