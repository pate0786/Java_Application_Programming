package othello;



/**
 * File name: OthelloViewController.java
 * Author: Vandankumar Patel, Student ID: 040976978
 * Assignment: 1 Part 1 
 * Date: 2021/06/26 
 * Professor: Daniel Cormier
 * Purpose: The class OthelloViewController is responsible for building UI of the Othello Game Application
 * Class List: OthelloViewController, Controller
 * References: Lab codes provided Daniel Cormier, https://www.javatpoint.com/java-swing, https://docs.oracle.com/javase/7/docs/api/java/awt/GridLayout.html
*/

/**
 * @version 2.5
 * @author Vandankumar Patel
 * @see othello
 * @since 1.8.0_291
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 * This class is responsible for Assembling UI
 * @author Vandankumar Patel
 * @version 2.3
 * @see othello 
 * @since 1.8.0_291
 */

   
public class OthelloViewController extends JFrame {

	private static final long serialVersionUID = 4353953521279957365L;
/**
 * Various JPanels on the screen
 * bottomPanel will contain the textfield and submit button
 * mainPanel will contain actual 8x8 gaming board at left side
 * midPanel will contain move buttons and player 1 and player 2 pieces
 * information
 * rightPanel will contain show valid moves checkbox, midPanel and blue
 * output area
 **/

	
	JPanel bottomPanel, mainPanel, rightPanel, midPanel;

	/* JPanels to contain right hand side move buttons and player 1 and player 2 */
	/* pieces information */
	JPanel buttonsPanel, playerInfoPanel, player1Panel, player2Panel;

	/* The JTextfield at bottom just before the submit button*/
	JTextField textField;

	/* The submit button at the bottom*/
	JButton submitButton;

	/* The show valid moves checkbox at the right top */
	JCheckBox showValidMovesCheckbox;

	/* JTextarea to show Blue output area */
	JTextArea textArea;

	/* JButtons for various arrow move buttons */
	JButton moveLabel, upButton, leftButton, rightButton, downButton;

	/* The JLabels to display number of player 1 and player 2 pieces at right*/
	/* hand side of move buttons*/
	JLabel player1NoOfPieces, player2NoOfPieces;

	/* The 8x8 board*/
	JPanel[][] board;

	/**
	 * This OthelloViewController constructor is used building the UI of Othello Game.
	*/
	public OthelloViewController() {
		setTitle("Vandan's Othello Client");
		setSize(1067, 670);
		setResizable(false);

		/* To Make sure that JFrame closes on clicking cross button on top right*/
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		/* Change background color as requested in assignment */
		setBackground(new Color(220, 220, 220));

		/* Initialize the objects*/
		board = new JPanel[8][8];
		bottomPanel = new JPanel();
		rightPanel = new JPanel();
		mainPanel = new JPanel();
		midPanel = new JPanel();
		playerInfoPanel = new JPanel();
		player1Panel = new JPanel();
		player2Panel = new JPanel();
		textField = new JTextField();
		submitButton = new JButton("Submit");


		bottomPanel.setLayout(new BorderLayout());
		bottomPanel.add(textField, BorderLayout.CENTER);
		submitButton.setBackground(Color.BLACK);
		submitButton.setForeground(Color.RED);
		bottomPanel.add(submitButton, BorderLayout.EAST);
		bottomPanel.setBorder(BorderFactory.createMatteBorder(0, 5, 5, 5, Color.GRAY));
		mainPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 5));
		initializeLeftPanel();
		initializeRightPanel();
		add(bottomPanel, BorderLayout.SOUTH);
		add(rightPanel, BorderLayout.EAST);
		add(mainPanel, BorderLayout.CENTER);
	}

	/**
	 * This method is responsible to draw the left Panel where the actual
	 * playing board will be displayed along with the four side numbering
	 */

	public void initializeLeftPanel() {
		mainPanel.setLayout(new BorderLayout());
		JPanel topNumbers = new JPanel();
		topNumbers.setLayout(new GridLayout(1, 10));
		JPanel bottomNumbers = new JPanel();
		bottomNumbers.setLayout(new GridLayout(1, 10));

		// Display numbers from 1 to 8 on top and bottom
		int a = 0;
		for (int i = 1; i <= 10; i++) {
			String s1 = a + "";
			if (a == 0 || a == 9) {
				s1 = "";
			}
			JLabel jb = new JLabel(s1);
			jb.setOpaque(true);
			jb.setBackground(this.getBackground());
			JLabel jb2 = new JLabel(s1);
			jb2.setOpaque(true);
			jb2.setBackground(this.getBackground());
			jb.setBorder(BorderFactory.createLineBorder(this.getBackground(), 18));
			jb2.setBorder(BorderFactory.createLineBorder(this.getBackground(), 18));
			a++;
			topNumbers.setBackground(this.getBackground());
			bottomNumbers.setBackground(this.getBackground());
			topNumbers.add(jb);
			bottomNumbers.add(jb2);
		}

		mainPanel.add(topNumbers, BorderLayout.NORTH);

		mainPanel.add(bottomNumbers, BorderLayout.SOUTH);

		JPanel leftNumbers = new JPanel();
		JPanel rightNumbers = new JPanel();
		leftNumbers.setLayout(new GridLayout(8, 1));
		rightNumbers.setLayout(new GridLayout(8, 1));

		// Display alphabets from A to H on left and right
		char c = 'A';
		for (int i = 1; i <= 8; i++) {
			JLabel jb = new JLabel(c + "");
			JLabel jb2 = new JLabel(c + "");
			jb.setOpaque(true);
			jb.setBackground(this.getBackground());
			jb2.setOpaque(true);
			jb2.setBackground(this.getBackground());
			jb.setBorder(BorderFactory.createLineBorder(this.getBackground(), 18));
			jb2.setBorder(BorderFactory.createLineBorder(this.getBackground(), 18));
			c++;
			leftNumbers.setBackground(this.getBackground());
			rightNumbers.setBackground(this.getBackground());
			leftNumbers.add(jb);
			rightNumbers.add(jb2);
		}
		mainPanel.add(leftNumbers, BorderLayout.WEST);
		mainPanel.add(rightNumbers, BorderLayout.EAST);

		JPanel boardPanel = new JPanel();
		boardPanel.setLayout(new GridLayout(8, 8));
		boardPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

		// Display the real 8x8 board
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				board[i][j] = new JPanel();
				board[i][j].setLayout(new BorderLayout());
				// Use alternate white and black color
				if ((i + j) % 2 == 0) {
					board[i][j].setBackground(Color.WHITE);
				} else {
					board[i][j].setBackground(Color.BLACK);
				}

				boardPanel.add(board[i][j]);
			}
		}
		mainPanel.add(boardPanel, BorderLayout.CENTER);

		/* Set 2 white and 2 black pieces in center of the board */
		board[3][3].add(new JLabel(new ImageIcon("white.png")), BorderLayout.CENTER);
		board[3][4].add(new JLabel(new ImageIcon("black.png")), BorderLayout.CENTER);
		board[4][3].add(new JLabel(new ImageIcon("black.png")), BorderLayout.CENTER);
		board[4][4].add(new JLabel(new ImageIcon("white.png")), BorderLayout.CENTER);
	}

	/**
	 * This method is responsible to draw the right Panel including checkbox at
	 * top, move button and blue output area
	 * 
	 * @author Vandankumar Patel
	 * 
	 */
	public void initializeRightPanel() {
		rightPanel.setBorder(BorderFactory.createMatteBorder(5, 0, 5, 5, Color.GRAY));
		rightPanel.setLayout(new BorderLayout());
		rightPanel.setBackground(this.getBackground());
		showValidMovesCheckbox = new JCheckBox("Show Valid Moves");
		showValidMovesCheckbox.setBackground(this.getBackground());
		textArea = new JTextArea(25, 42);
		textArea.setBackground(new Color(175, 175, 255));
		textArea.setText(
				"\n\n\n\n\n\n\n\n\n\n\nPlayer 1 initialized with 2 pieces.\nPlayer 2 initialized with 2 pieces.");
		initializeRightMidPanel();
		showValidMovesCheckbox.addActionListener(new Controller());
		rightPanel.add(showValidMovesCheckbox, BorderLayout.NORTH);
		rightPanel.add(midPanel, BorderLayout.CENTER);
		rightPanel.add(textArea, BorderLayout.SOUTH);
	}

	/**
	 * This method is responsible to draw the panel where move buttons will be
	 * displayed along with the player pieces information
	 * This method is called in initializeRightPanel().
	 * @author Vandankumar Patel
	 */

	public void initializeRightMidPanel() {
		midPanel.setBorder(BorderFactory.createMatteBorder(5, 0, 5, 0, Color.GRAY));
		midPanel.setLayout(new BorderLayout());
		buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridLayout(3, 3));
		upButton = new JButton(new ImageIcon("uparrow.png"));
		upButton.setBackground(Color.WHITE);
		downButton = new JButton(new ImageIcon("downarrow.png"));
		downButton.setBackground(Color.WHITE);
		leftButton = new JButton(new ImageIcon("leftarrow.png"));
		leftButton.setBackground(Color.WHITE);
		rightButton = new JButton(new ImageIcon("rightarrow.png"));
		rightButton.setBackground(Color.WHITE);
		/* The default value for MoveLabel ise set to false as this label will be used in part 2 only */
		moveLabel = new JButton("move");
		moveLabel.setEnabled(false);
		moveLabel.setBackground(Color.WHITE);
		buttonsPanel.setBackground(this.getBackground());

		/* Add move buttons */
 		buttonsPanel.add(new JLabel());
		buttonsPanel.add(upButton);
		buttonsPanel.add(new JLabel());
		buttonsPanel.add(leftButton);
		buttonsPanel.add(moveLabel);
		buttonsPanel.add(rightButton);
		buttonsPanel.add(new JLabel());
		buttonsPanel.add(downButton);
		buttonsPanel.add(new JLabel());

		/* Add Action Listener to the move buttons*/
		upButton.addActionListener(new Controller());
		leftButton.addActionListener(new Controller());
		rightButton.addActionListener(new Controller());
		downButton.addActionListener(new Controller());

		playerInfoPanel.setLayout(new GridLayout(2, 1));
		playerInfoPanel.setBackground(this.getBackground());
		player1Panel.add(new JLabel("   Player 1 Pieces:      "));
		player1Panel.add(new JLabel(new ImageIcon("white.png")));
		player1Panel.setBackground(this.getBackground());
		player2Panel.setBackground(this.getBackground());
		player1NoOfPieces = new JLabel(" 2");
		player1Panel.add(player1NoOfPieces);

		player2Panel.add(new JLabel("   Player 2 Pieces:      "));
		player2Panel.add(new JLabel(new ImageIcon("./.black.png")));
		player2NoOfPieces = new JLabel(" 2");
		player2Panel.add(player2NoOfPieces);

		playerInfoPanel.add(player1Panel);
		playerInfoPanel.add(player2Panel);
		midPanel.add(buttonsPanel, BorderLayout.WEST);
		midPanel.add(playerInfoPanel, BorderLayout.CENTER);
	}

	/**
	 * This inner class Controller will read the action
	 * command of the button that was pressed, and then prints it to the
	 * console. All buttons (and the checkbox) must have a unique action
	 * command.
	 * 
	 * @author Vandankumar Patel
	 * @param e 
	 */
	class Controller implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == upButton) {
				setTextInOutputArea("Up Button Pressed");
			}
			if (e.getSource() == downButton) {
				setTextInOutputArea("Down Button Pressed");
			}
			if (e.getSource() == leftButton) {
				setTextInOutputArea("Left Button Pressed");				
			}
			if (e.getSource() == rightButton) {
				setTextInOutputArea("Right Button Pressed");				
			}
			if (e.getSource() == showValidMovesCheckbox) {
				if (showValidMovesCheckbox.isSelected()) {
					setTextInOutputArea("Started Showing valid moves");					
				} else {
					setTextInOutputArea("Stopped Showing valid moves");					
				}
			}

		}
		
		/**
		 * This method is used to add new message in text area. 
		 * It makes sure that if text area is already full, 
		 * it deletes the first line and add message in last line
		 * 
		 * @author Vandankumar Patel
		 * @param message
		 * @return N/A
		 */
		private void setTextInOutputArea(String message){
			String s1 = textArea.getText();
			String s2 = "";
			String[] lines = s1.split("\n");			
			int startingIndex = 0;
			if(lines.length>=25){
				startingIndex++;
			}
			for(int i=startingIndex;i<lines.length;i++){
				s2 = s2 + lines[i] + "\n";
			}
			s2 = s2 + message;
			textArea.setText(s2);
		}

	}

}//End of file
