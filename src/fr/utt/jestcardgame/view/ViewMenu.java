package fr.utt.jestcardgame.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.Font;

public class ViewMenu {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewMenu window = new ViewMenu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblJestGame = new JLabel("JEST GAME");
		lblJestGame.setFont(new Font("Marker Felt", Font.PLAIN, 20));
		lblJestGame.setBounds(248, 38, 111, 34);
		frame.getContentPane().add(lblJestGame);
		
		JMenuItem mntmStartGame = new JMenuItem("Start Game");
		mntmStartGame.setBounds(245, 160, 114, 19);
		frame.getContentPane().add(mntmStartGame);
		
		JMenuItem mntmQuit = new JMenuItem("Quit");
		mntmQuit.setBounds(268, 217, 65, 19);
		frame.getContentPane().add(mntmQuit);
	}
}
