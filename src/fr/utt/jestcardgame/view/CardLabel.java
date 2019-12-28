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
	public CardLabel(){}
	public CardLabel(String path){
		this.imagePath = path;
	}
	
	public void paint(Graphics g){
			g.setColor(Color.red);
			g.drawRect(0, 0, this.getWidth(), this.getHeight());
			try {
				Image img = ImageIO.read(new File(this.imagePath));
				g.drawImage(img, 0, 0, this);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	public void setImagePath(String path){
		this.imagePath = path;
		repaint();
	}
}
