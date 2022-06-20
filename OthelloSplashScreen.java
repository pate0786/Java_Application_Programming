package othello;

/**
 * File name: OthelloSplashScreen.java
 * Author: Vandankumar Patel, Student ID: 040976978
 * Assignment: 1 Part 1 
 * Date: 2021/06/26 
 * Professor: Daniel Cormier
 * Purpose: This class is to display SplashScreen before the launch of Othello Application
 * References: Lab codes provided Daniel Cormier, https://www.javatpoint.com/java-swing, https://docs.oracle.com/javase/7/docs/api/java/awt/GridLayout.html
*/

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;

/**
 * This class is to display Splash Screen
 * @author Vandankumar Patel
 * @version 1
 * @see othello 
 * @since 1.8.0_291
 */



public class OthelloSplashScreen extends JWindow {

	private static final long serialVersionUID = -2761985962067338151L;

	/**
	 * Constructor for OthelloSplashScreen
	 */
	 
	public OthelloSplashScreen() {
		setSize(600, 300);//sets the frame size
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		setBackground(Color.WHITE);
		JPanel panel = new JPanel();
		Toolkit t = Toolkit.getDefaultToolkit();

		/*Set an image for SplashScreen*/
		Image i = t.getImage("logo.png");
		
		/* To create the splash screen */
		panel.add(new JLabel(new ImageIcon(i)));
		JLabel nameLabel = new JLabel("                             Developed by: Vandankumar Patel");
		nameLabel.setFont(new Font("Arial", Font.BOLD, 18));
		add(panel, BorderLayout.CENTER);
		add(nameLabel, BorderLayout.SOUTH);
		
		/*This makes frame visible*/
		setVisible(true);

		/* Waits for 5 seconds on Splash Screen */
		try {
			Thread.sleep(5000);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}

		/* Below lines launches the main JFrame */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new OthelloViewController().setVisible(true);
			}
		});

		/* Closes the Splash Screen*/
		dispose();
	}

}
//End of file
 