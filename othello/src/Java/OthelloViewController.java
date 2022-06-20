package othello;

/**
 * File name: OthelloViewController.java
 * Author: Vandankumar Patel, Student ID: 040976978
 * Assignment: 1 Part 2 
 * Date: 2021/07/18 
 * Professor: Daniel Cormier
 * Purpose: The class OthelloViewController is responsible for building UI of the Othello Game Application along with evrey component
 * Class List: OthelloViewController, Controller
 * References: Lab codes and hybrids provided by Professor Daniel Cormier, https://www.javatpoint.com/java-swing, https://docs.oracle.com/javase/7/docs/api/java/awt/GridLayout.html
*/

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

/**
 * OthelloViewController
 * 
 * @author Vandankumar Patel
 *
 */
public class OthelloViewController extends JFrame {

	private static final long serialVersionUID = 4353953521279957365L;

	// Various JPanels on the screen
	// bottomPanel will contain the textfield and submit button
	// mainPanel will contain actual 8x8 gaming board at left side
	// midPanel will contain move buttons and player 1 and player 2 pieces
	// information
	// rightPanel will contain show valid moves checkbox, midPanel and blue
	// output area
	JPanel bottomPanel, mainPanel, rightPanel, midPanel;

	// JPanels to contain right hand side move buttons and player 1 and player 2
	// pieces information
	JPanel buttonsPanel, playerInfoPanel, player1Panel, player2Panel;

	// The JTextfield at bottom just before the submit button
	JTextField textField;

	// The submit button at the bottom
	JButton submitButton;

	// The show valid moves checkbox at the right top
	JCheckBox showValidMovesCheckbox;

	// JTextarea to show Blue output area
	JTextArea textArea;

	// JButtons for various arrow move buttons
	JButton moveButton, upButton, leftButton, rightButton, downButton;

	// The JLabels to display number of player 1 and player 2 pieces at right
	// hand side of move buttons
	JLabel player1NoOfPieces, player2NoOfPieces;

	// The 8x8 board
	JPanel[][] board;

	// The menu bar at top
	JMenuBar menuBar;

	// File Menu
	JMenu fileMenu;

	// Game Menu
	JMenu gameMenu;

	// Help Menu
	JMenu helpMenu;

	// New Game menu item inside the File Menu
	JMenuItem newGame;

	// Load menu item inside the File Menu
	JMenuItem load;
	
	// Save menu item inside the File menu
	JMenuItem save;
	
	//Exit menu item inside the File menu
	JMenuItem exit;
	
	//About menu item inside the Help menu
	JMenuItem about;
	
	// The three different themes menu items
	JMenuItem redAndWhite, blueAndWhite, blackAndWhite;
	
	//Menu for board colours
	JMenu boardColours;
	
	//Menu for debug scenarios
	JMenu debugScenarios;
	
	//Score zone labels for player 1 and player 2
	JLabel player1PiecesLabel, player2PiecesLabel;
	
	//Menu items for various debug scenarios
	JMenuItem normalGame, cornerTest, sideTests, oneXCaptueTest, twoXCaptureTest, emptyBoard, innerSquareTest,
			upArrowOrientationTest;
	
	// Scroll pane to make textarea scrollable
	JScrollPane scrollBar;
	
	// Radio buttons group for various debug scenarios
	ButtonGroup debugScenariosGroup;
	
	//Controller object
	Controller controller;
	
	//Model object
	OthelloModel model;
	
	//These two fields represents the green cursor position
	int cursorX = 3, cursorY = 3;
	
	//This field represents the current player who needs to make a move
	int currentPlayer = 1;

	/**
	 * Constructor for OthelloViewController
	 */
	public OthelloViewController() {
		setTitle("Vandan's Othello Client");
		setSize(1067, 670);
		setResizable(false);

		// Make sure that JFrame closes on clicking cross button on top right
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		// Change background color as requested in assignment
		setBackground(new Color(220, 220, 220));

		// Initialize the objects
		model = new OthelloModel();
		controller = new Controller();
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
		menuBar = new JMenuBar();
		fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		gameMenu = new JMenu("Game");
		gameMenu.setMnemonic(KeyEvent.VK_G);
		helpMenu = new JMenu("Help");
		helpMenu.setMnemonic(KeyEvent.VK_H);
		newGame = new JMenuItem("New Game");
		newGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.ALT_MASK));
		load = new JMenuItem("Load");
		load.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.ALT_MASK));
		save = new JMenuItem("Save");
		save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.ALT_MASK));
		exit = new JMenuItem("Exit");
		exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.ALT_MASK));
		about = new JMenuItem("About");
		about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.ALT_MASK));
		boardColours = new JMenu("Board Colours");
		redAndWhite = new JMenuItem("Red and White (Canadian theme)");
		blackAndWhite = new JMenuItem("Black and White (International theme)");
		blueAndWhite = new JMenuItem("Blue and White Theme");
		debugScenarios = new JMenu("Debug Scenarios");
		debugScenariosGroup = new ButtonGroup();
		normalGame = new JRadioButtonMenuItem("Normal Game", true);
		normalGame.setActionCommand("0");
		cornerTest = new JRadioButtonMenuItem("Corner Test");
		cornerTest.setActionCommand("1");
		sideTests = new JRadioButtonMenuItem("Side Tests");
		sideTests.setActionCommand("2");
		oneXCaptueTest = new JRadioButtonMenuItem("1x Capture Test");
		oneXCaptueTest.setActionCommand("3");
		twoXCaptureTest = new JRadioButtonMenuItem("2x Capture Test");
		twoXCaptureTest.setActionCommand("4");
		emptyBoard = new JRadioButtonMenuItem("Empty Board");
		emptyBoard.setActionCommand("5");
		innerSquareTest = new JRadioButtonMenuItem("Inner Square Test");
		innerSquareTest.setActionCommand("6");
		upArrowOrientationTest = new JRadioButtonMenuItem("Up Arrow Orientation Test");
		upArrowOrientationTest.setActionCommand("7");
		debugScenariosGroup.add(normalGame);
		debugScenariosGroup.add(cornerTest);
		debugScenariosGroup.add(sideTests);
		debugScenariosGroup.add(oneXCaptueTest);
		debugScenariosGroup.add(twoXCaptureTest);
		debugScenariosGroup.add(emptyBoard);
		debugScenariosGroup.add(innerSquareTest);
		debugScenariosGroup.add(upArrowOrientationTest);

		// load.setEnabled(false);
		// save.setEnabled(false);
		exit.addActionListener(controller);
		about.addActionListener(controller);
		blackAndWhite.addActionListener(controller);
		redAndWhite.addActionListener(controller);
		blueAndWhite.addActionListener(controller);
		newGame.addActionListener(controller);
		save.addActionListener(controller);
		load.addActionListener(controller);
		fileMenu.add(newGame);
		fileMenu.add(load);
		fileMenu.add(save);
		fileMenu.add(exit);
		debugScenarios.add(normalGame);
		debugScenarios.add(cornerTest);
		debugScenarios.add(sideTests);
		debugScenarios.add(oneXCaptueTest);
		debugScenarios.add(twoXCaptureTest);
		debugScenarios.add(emptyBoard);
		debugScenarios.add(innerSquareTest);
		debugScenarios.add(upArrowOrientationTest);
		boardColours.add(blackAndWhite);
		boardColours.add(redAndWhite);
		boardColours.add(blueAndWhite);
		gameMenu.add(boardColours);
		gameMenu.add(debugScenarios);
		helpMenu.add(about);
		menuBar.add(fileMenu);
		menuBar.add(gameMenu);
		menuBar.add(helpMenu);
		setJMenuBar(menuBar);

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

		// Added thickness as 2 intentionally as with thickness 1 it was not
		// clealry visible
		board[3][3].setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
		model.prepareBoard(0);
		displayBoard();
		textArea.setText("Player 1 initialized with " + model.chipCount(1) + " piece(s).\nPlayer 2 initialized with "
				+ model.chipCount(2) + " piece(s).");
		boolean isGameOver = checkWin();
		if (!isGameOver) {
			highlightCurrentPlayer();
		}
	}

	/**
	 * This method checks if game has been completed or not.
	 * @return true if game has been completed else false
	 */
	private boolean checkWin() {
		if (!model.moveTest(1) && !model.moveTest(2)) {
			moveButton.setEnabled(false);
			textArea.setText(textArea.getText() + "\nGAME OVER");
			if (model.chipCount(1) > model.chipCount(2)) {
				textArea.setText(textArea.getText() + "\nPlayer 1 wins!");
			} else if (model.chipCount(2) > model.chipCount(1)) {
				textArea.setText(textArea.getText() + "\nPlayer 2 wins!");
			} else {
				textArea.setText(textArea.getText() + "\nIt's a draw!");
			}
			textArea.setText(textArea.getText() + "\n\nSelect New Game to play again.");
			player1PiecesLabel.setForeground(Color.BLACK);
			player2PiecesLabel.setForeground(Color.BLACK);
			return true;
		}
		return false;
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
			jb.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
			jb.setOpaque(true);
			jb.setBackground(this.getBackground());
			JLabel jb2 = new JLabel(s1);
			jb2.setOpaque(true);
			jb2.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
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
			jb.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
			jb.setBackground(this.getBackground());
			jb2.setOpaque(true);
			jb2.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
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
				board[i][j].addMouseListener(controller);
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
	}

	/**
	 * This method displays the board based on the model
	 */
	private void displayBoard() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				board[i][j].removeAll();
				if (model.getSquare(i, j) == 1) {
					board[i][j].add(new JLabel(new ImageIcon("black.png")), BorderLayout.CENTER);
				} else if (model.getSquare(i, j) == 2) {
					board[i][j].add(new JLabel(new ImageIcon("white.png")), BorderLayout.CENTER);
				}
				board[i][j].revalidate();
				board[i][j].repaint();
			}
		}
		player1NoOfPieces.setText(model.chipCount(1) + "");
		player2NoOfPieces.setText(model.chipCount(2) + "");
		showValidMoves();
	}

	/**
	 * This method is reponsible to highlight the current player in score zone
	 */
	private void highlightCurrentPlayer() {
		textArea.setText(textArea.getText() + "\nCurrent Turn - Player " + currentPlayer);
		if (currentPlayer == 1) {
			player1PiecesLabel.setForeground(Color.RED);
			player2PiecesLabel.setForeground(Color.BLACK);
		} else {
			player2PiecesLabel.setForeground(Color.RED);
			player1PiecesLabel.setForeground(Color.BLACK);
		}
	}

	/**
	 * This method is responsible to draw the right Panel including checkbox at
	 * top, move button and blue output area
	 */
	public void initializeRightPanel() {
		rightPanel.setBorder(BorderFactory.createMatteBorder(5, 0, 5, 5, Color.GRAY));
		rightPanel.setLayout(new BorderLayout());
		rightPanel.setBackground(this.getBackground());
		showValidMovesCheckbox = new JCheckBox("Show Valid Moves");
		showValidMovesCheckbox.setBackground(this.getBackground());
		textArea = new JTextArea(25, 42);
		scrollBar = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollBar.setEnabled(true);
		scrollBar.setVerticalScrollBarPolicy(20);
		textArea.setEnabled(false);
		textArea.setDisabledTextColor(Color.BLACK);
		textArea.setBackground(new Color(175, 175, 255));
		textArea.setLineWrap(true);
		scrollBar.setViewportView(textArea);
		initializeRightMidPanel();
		showValidMovesCheckbox.addActionListener(controller);
		rightPanel.add(showValidMovesCheckbox, BorderLayout.NORTH);
		rightPanel.add(midPanel, BorderLayout.CENTER);
		rightPanel.add(scrollBar, BorderLayout.SOUTH);
	}

	/**
	 * This method is responsible to draw the panel where move buttons will be
	 * displayed along with the player pieces information
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
		moveButton = new JButton("move");
		moveButton.setBackground(Color.WHITE);
		buttonsPanel.setBackground(this.getBackground());

		// Add move buttons
		buttonsPanel.add(new JLabel());
		buttonsPanel.add(upButton);
		buttonsPanel.add(new JLabel());
		buttonsPanel.add(leftButton);
		buttonsPanel.add(moveButton);
		buttonsPanel.add(rightButton);
		buttonsPanel.add(new JLabel());
		buttonsPanel.add(downButton);
		buttonsPanel.add(new JLabel());

		// Add Action Listener to the move buttons
		upButton.addActionListener(controller);
		leftButton.addActionListener(controller);
		rightButton.addActionListener(controller);
		downButton.addActionListener(controller);
		moveButton.addActionListener(controller);

		playerInfoPanel.setLayout(new GridLayout(2, 1));
		playerInfoPanel.setBackground(this.getBackground());
		player1PiecesLabel = new JLabel("   Player 1 Pieces:      ");
		player1PiecesLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		player1Panel.add(player1PiecesLabel);
		player1Panel.add(new JLabel(new ImageIcon("black.png")));
		player1Panel.setBackground(this.getBackground());
		player2Panel.setBackground(this.getBackground());
		player1NoOfPieces = new JLabel(" 2");
		player1Panel.add(player1NoOfPieces);

		player2PiecesLabel = new JLabel("   Player 2 Pieces:      ");
		player2PiecesLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		player2Panel.add(player2PiecesLabel);
		player2Panel.add(new JLabel(new ImageIcon("white.png")));
		player2NoOfPieces = new JLabel(" 2");
		player2Panel.add(player2NoOfPieces);

		playerInfoPanel.add(player1Panel);
		playerInfoPanel.add(player2Panel);
		midPanel.add(buttonsPanel, BorderLayout.WEST);
		midPanel.add(playerInfoPanel, BorderLayout.CENTER);
	}

	/**
	 * This method removes the green color border from the passed square
	 * @param row
	 * @param col
	 */
	private void removeBorder(int row, int col) {
		board[row][col].setBorder(new EmptyBorder(0, 0, 0, 0));
	}

	/**
	 * This method adds a green color border on the passed square
	 * @param row
	 * @param col
	 */
	private void addBorder(int row, int col) {
		board[row][col].setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
	}

	/**
	 * This method changes color of board
	 * @param color
	 */
	private void updateTheme(Color color) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if ((i + j) % 2 != 0) {
					board[i][j].setBackground(color);
				}
			}
		}
	}

	/**
	 * This method is used to highlight the positions where a valid move is possible
	 */
	private void showValidMoves() {
		if (showValidMovesCheckbox.isSelected()) {
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (model.canMove(i, j, currentPlayer)) {
						board[i][j].add(new JLabel(new ImageIcon("checkmark.png")), BorderLayout.CENTER);
						board[i][j].revalidate();
						board[i][j].repaint();
					}
				}
			}
		}
	}

	/**
	 * This method finds the opponent of the passed player
	 * @param player
	 * @return
	 */
	private int opponent(int player) {
		if (player == 1) {
			return 2;
		} else {
			return 1;
		}
	}

	/**
	 * The Controller class will have very basic code. It reads the action
	 * command of the button that was pressed, and then prints it to the
	 * console. All buttons (and the checkbox) must have a unique action
	 * command.
	 * 
	 * @author Vandankumar Patel
	 *
	 */
	class Controller implements ActionListener, MouseListener {

		private AIModel aiModel;

		Controller() {
			aiModel = new AIModel(model);
		}

		/**
		 * This method resets the board based on the selected debug scenario
		 */
		private void resetModelBasedOnMode() {
			String scenatioStr = debugScenariosGroup.getSelection().getActionCommand();
			int mode = Integer.parseInt(scenatioStr);
			model.prepareBoard(mode);
			currentPlayer = 1;
			displayBoard();
		}

		/**
		 * This method returns a string representation of passed cell e.g. A1, D6 etc
		 * @param row
		 * @param col
		 * @return
		 */
		private String findPositionString(int row, int col) {
			return ((char) (65 + row)) + "" + (col + 1);
		}

		/**
		 * This method is reponsible to make a move at passed square and updates the various things in the board accordingly
		 * @param cursorX
		 * @param cursorY
		 */
		private void movePiece(int cursorX, int cursorY) {
			System.out.println("Move Button Pressed");
			boolean isGameOver = false;
			boolean hasCurrentPlayerChanged = false;
			if ("skip".equals(moveButton.getText())) {
				currentPlayer = opponent(currentPlayer);
				hasCurrentPlayerChanged = true;
				moveButton.setText("move");
			} else {
				int noOfCapturedPieces = model.tryMove(cursorX, cursorY, currentPlayer);
				if (noOfCapturedPieces > 0) {
					textArea.setText(textArea.getText() + "\n" + "Player " + currentPlayer + " has captured "
							+ noOfCapturedPieces + (noOfCapturedPieces > 1 ? " pieces." : " piece."));
					currentPlayer = opponent(currentPlayer);
					hasCurrentPlayerChanged = true;
					displayBoard();
					isGameOver = checkWin();
					if (!isGameOver) {
						if (!model.moveTest(currentPlayer)) {
							if (currentPlayer == 1) {
								moveButton.setText("skip");
								textArea.setText(textArea.getText() + "\nPlayer " + currentPlayer
										+ " has no valid moves. Press skip.");
							} else {
								textArea.setText(textArea.getText() + "\nPlayer AI has no valid moves.");

							}
						}
					}
				}
			}
			if (!isGameOver) {
				if (hasCurrentPlayerChanged) {
					highlightCurrentPlayer();
				}
				if (currentPlayer == 2) {
					int noOfCapturedPieces = aiModel.move();
					hasCurrentPlayerChanged = false;
					if (noOfCapturedPieces > 0) {
						textArea.setText(textArea.getText() + "\nAI opponent moves to "
								+ findPositionString(aiModel.getLastMoveRow(), aiModel.getLastMoveCol())
								+ ", capturing " + noOfCapturedPieces + " "
								+ (noOfCapturedPieces > 1 ? " pieces." : " piece."));
						currentPlayer = opponent(currentPlayer);
						hasCurrentPlayerChanged = true;
						displayBoard();
						isGameOver = checkWin();
						if (!isGameOver) {
							if (!model.moveTest(currentPlayer)) {
								moveButton.setText("skip");
								textArea.setText(textArea.getText() + "\nPlayer " + currentPlayer
										+ " has no valid moves. Press skip.");
							}
						}

					} else {
						currentPlayer = opponent(currentPlayer);
						hasCurrentPlayerChanged = true;
						textArea.setText(textArea.getText() + "\nAI skipped the turn.");
					}
					if (!isGameOver) {
						if (hasCurrentPlayerChanged) {
							highlightCurrentPlayer();
						}
					}
				}
			}
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == save) {
				boolean isSaved = model.saveGame();
				if (isSaved) {
					textArea.setText(textArea.getText() + "\nGame saved successfully!");
				} else {
					textArea.setText(textArea.getText() + "\nSave failed!");
				}
			}
			if (e.getSource() == load) {
				boolean isLoaded = model.loadGame();
				if (isLoaded) {
					textArea.setText("Game loaded successfully!");
					player1PiecesLabel.setForeground(Color.BLACK);
					player2PiecesLabel.setForeground(Color.BLACK);
					currentPlayer = 1;
					displayBoard();
					boolean isGameOver = checkWin();
					if (!isGameOver) {
						highlightCurrentPlayer();
						if (!model.moveTest(currentPlayer)) {
							moveButton.setText("skip");
							textArea.setText(textArea.getText() + "\nPlayer " + currentPlayer
									+ " has no valid moves. Press skip.");
						}
					}
				} else {
					textArea.setText(
							textArea.getText() + "\nLoading failed. Either game file is missing or corrupted!");
				}
			}
			if (e.getSource() == exit) {
				System.exit(0);
			}
			if (e.getSource() == about) {
				JOptionPane.showMessageDialog(null, "Othello Game\nby Vandankumar Patel\n\nJune 2021", "About",
						JOptionPane.INFORMATION_MESSAGE);
			}
			if (e.getSource() == redAndWhite) {
				updateTheme(Color.RED);
			}
			if (e.getSource() == blackAndWhite) {
				updateTheme(Color.BLACK);
			}
			if (e.getSource() == blueAndWhite) {
				updateTheme(Color.BLUE);
			}
			if (e.getSource() == newGame) {
				resetModelBasedOnMode();
				textArea.setText("Player 1 initialized with " + model.chipCount(1)
						+ " piece(s).\nPlayer 2 initialized with " + model.chipCount(2) + " piece(s).");
				moveButton.setEnabled(true);
				player1PiecesLabel.setForeground(Color.BLACK);
				player2PiecesLabel.setForeground(Color.BLACK);
				boolean isGameOver = checkWin();
				if (!isGameOver) {
					highlightCurrentPlayer();
				}
			}
			if (e.getSource() == upButton) {
				System.out.println("Up Button Pressed");
				if (cursorX > 0) {
					removeBorder(cursorX, cursorY);
					cursorX--;
					addBorder(cursorX, cursorY);
				}
			}
			if (e.getSource() == downButton) {
				System.out.println("Down Button Pressed");
				if (cursorX < 7) {
					removeBorder(cursorX, cursorY);
					cursorX++;
					addBorder(cursorX, cursorY);
				}
			}
			if (e.getSource() == leftButton) {
				System.out.println("Left Button Pressed");
				if (cursorY > 0) {
					removeBorder(cursorX, cursorY);
					cursorY--;
					addBorder(cursorX, cursorY);
				}
			}
			if (e.getSource() == rightButton) {
				System.out.println("Right Button Pressed");
				if (cursorY < 7) {
					removeBorder(cursorX, cursorY);
					cursorY++;
					addBorder(cursorX, cursorY);
				}
			}
			if (e.getSource() == moveButton) {
				movePiece(cursorX, cursorY);
			}
			if (e.getSource() == showValidMovesCheckbox) {
				if (showValidMovesCheckbox.isSelected()) {
					System.out.println("Started Showing valid moves");
					showValidMoves();
				} else {
					System.out.println("Stopped Showing valid moves");
					displayBoard();
				}
			}

		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (e.getSource() == board[i][j]) {
						movePiece(i, j);
						return;
					}
				}
			}
		}

	}

}//End of file
