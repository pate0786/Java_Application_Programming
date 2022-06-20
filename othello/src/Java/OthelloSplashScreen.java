package othello;

/**
 * File name: OthelloSplashScreen.java
 * Author: Vandankumar Patel, Student ID: 040976978
 * Assignment: 1 Part 2
 * Date: 2021/07/18 
 * Professor: Daniel Cormier
 * Purpose: This class is to display SplashScreen before the launch of Othello Application
 * References: Lab codes& hybrids provided by Professor Daniel Cormier, https://www.javatpoint.com/java-swing, https://docs.oracle.com/javase/7/docs/api/java/awt/GridLayout.html
*/

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;

/**
 * Splash Screen
 * 
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
		setSize(600, 350);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		setBackground(Color.WHITE);
		JPanel panel = new JPanel();
		Toolkit t = Toolkit.getDefaultToolkit();


		/*Set an image for SplashScreen*/
		Image i = t.getImage("logo.png");
		
		/* To create the splash screen */
		panel.add(new JLabel(new ImageIcon(i)));
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(3,1));		
		JLabel nameLabel = new JLabel("                             Developed by: Vandankumar Patel");
		JLabel loadingLabel = new JLabel("Loading Board");
		JProgressBar progressBar = new JProgressBar();
		progressBar.setMinimum(0);
		progressBar.setMaximum(100);
		nameLabel.setFont(new Font("Arial", Font.BOLD, 18));
		add(panel, BorderLayout.CENTER);
		bottomPanel.add(nameLabel);
		bottomPanel.add(progressBar);
		bottomPanel.add(loadingLabel);
		add(bottomPanel, BorderLayout.SOUTH);
		
		/*This makes frame visible*/
		setVisible(true);

		/* Waits for ten seconds on Splash Screen */
		try {
			int value = 0;
			for(int j=1;j<=100;j++){				
				if(j==20){
					loadingLabel.setText("Loading Black Pieces");
				}
				if(j==40){
					loadingLabel.setText("Loading White Pieces");
				}
				if(j==60){
					loadingLabel.setText("Generating Score Area");
				}
				if(j==80){
					loadingLabel.setText("Finalizing");
				}				
				Thread.sleep(100);
				value++;
				progressBar.setValue(value);				
			}
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}

		/* Below lines launches the main JFrame */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new OthelloViewController().setVisible(true);
			}
		});

		/* Close the Splash Screen */
		dispose();
	}

}//End of file
