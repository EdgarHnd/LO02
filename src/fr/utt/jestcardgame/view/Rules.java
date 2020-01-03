package fr.utt.jestcardgame.view;

import fr.utt.jestcardgame.controler.GameViewControler;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Rules extends Panel{
	
	private String imagePath = "pictures/Other/rules.png";
	private Image im;
	private JButton back;
	private JLabel rul;

	public Rules(Dimension dim, GameViewControler gvc) {
		super(dim, gvc);
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

		this.back = new JButton("BACK");
		this.back.setBounds(10, 0, 50, 50);
		this.back.addActionListener(this.gvc.getBack());
		this.panel.add(back);
		this.rul = new JLabel(new ImageIcon(scaledImage));
		this.rul.setBounds(0, 0, 1200, 700);
		this.panel.add(rul);
	}
	
}
