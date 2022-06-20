package othello;

/**
 * File name: OthelloModel.java
 * Author: Vandankumar Patel, Student ID: 040976978
 * Assignment: 1 Part 2 
 * Date: 2021/07/18
 * Professor: Daniel Cormier
 * Purpose: OthelloModel represents the model layer of MVC architecture. This contains the real data part i.e. the internal double dimensional array of integeres on which full game logic is implemented.
 *          It contains one 8x8 two-dimensional array that represents the board.
 *          In that array, 0 represents an empty square, 1 is for black, and 2 is for white
 * References: Lab codes and Hybrids provided by Professor Daniel Cormier, https://www.javatpoint.com/java-swing, https://docs.oracle.com/javase/7/docs/api/java/awt/GridLayout.html
 * 
*/
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * 
 * @author Vandankumar Patel
 * OthelloModel should contain one 8x8 two-dimensional array that represents the board. In that array, 0 represents an empty square, 1 is for black, and 2 is for white.
 */
public class OthelloModel {

	private int[][] board;

	// Some class constants for your use:
	public static final int NORMAL = 0;
	public static final int CORNER_TEST = 1;
	public static final int OUTER_TEST = 2;
	public static final int TEST_CAPTURE = 3;
	public static final int TEST_CAPTURE2 = 4;
	public static final int CANNOTWIN = 5;
	public static final int INNER_TEST = 6;
	public static final int ARROW = 7;

	public static final int EMPTY = 0;
	public static final int BLACK = 1;
	public static final int WHITE = 2;

	public OthelloModel() {
		board = new int[8][8];

		// Initialize the board with all empty squares
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				board[i][j] = EMPTY;
			}
		}
		prepareBoard(NORMAL);
	}

	public void prepareBoard(int mode)
	{
		switch (mode)
		{
		case CORNER_TEST: 
			board=new int[][]{
				{2, 0, 0, 0, 0, 0, 0, 1},
				{0, 1, 0, 0, 0, 0, 2, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 1, 0, 0, 0, 0, 1, 0},
                {2, 0, 0, 0, 0, 0, 0, 2}};
            break;
                
		case OUTER_TEST:
			board = new int[][] {
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 2, 2, 2, 2, 2, 2, 0},
				{0, 2, 1, 1, 1, 1, 2, 0},
				{0, 2, 1, 0, 0, 1, 2, 0},
				{0, 2, 1, 0, 0, 1, 2, 0},
				{0, 2, 1, 1, 1, 1, 2, 0},
				{0, 2, 2, 2, 2, 2, 2, 0},
				{0, 0, 0, 0, 0, 0, 0, 0}};
			break;
                
		case INNER_TEST:
			board = new int[][] {
				{2, 2, 2, 2, 2, 2, 2, 2},
				{2, 0, 0, 0, 0, 0, 0, 2},
				{2, 0, 2, 2, 2, 2, 0, 2},
				{2, 0, 2, 1, 1, 2, 0, 2},
				{2, 0, 2, 1, 1, 2, 0, 2},
				{2, 0, 2, 2, 2, 2, 0, 2},
				{2, 0, 0, 0, 0, 0, 0, 2},
				{2, 2, 2, 2, 2, 2, 2, 2}};
			break;
                
		case CANNOTWIN:
			board = new int[][] {
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0}};
			break;
                
		case TEST_CAPTURE:
			board=new int[][]{
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 1, 1, 1, 1, 1, 1, 0},
				{0, 1, 1, 1, 1, 1, 1, 0},
				{0, 1, 2, 2, 2, 1, 1, 0},
				{0, 1, 2, 0, 2, 1, 1, 0},
				{0, 1, 2, 2, 2, 1, 1, 0},
				{0, 1, 1, 1, 1, 1, 1, 0},
				{0, 0, 0, 0, 0, 0, 0, 0}};
				break;
				
		case TEST_CAPTURE2:
			board=new int[][]{
				{1, 1, 1, 1, 1, 1, 1, 1},
				{1, 1, 1, 1, 1, 1, 1, 1},
				{1, 2, 2, 2, 1, 2, 1, 1},
				{1, 2, 2, 2, 2, 2, 1, 1},
				{1, 2, 2, 0, 2, 2, 1, 1},
				{1, 2, 2, 2, 2, 1, 1, 1},
				{1, 2, 1, 2, 2, 2, 1, 1},
				{1, 1, 1, 1, 1, 1, 1, 1}};
				break;
                
            case ARROW:
                board=new int[][]{
                {0, 0, 0, 1, 1, 0, 0, 0},
                {0, 0, 1, 1, 1, 1, 0, 0},
                {0, 1, 0, 1, 1, 0, 1, 0},
                {1, 0, 0, 1, 1, 0, 0, 1},
                {0, 0, 0, 1, 1, 0, 0, 0},
                {0, 0, 0, 1, 1, 0, 0, 0},
                {0, 0, 0, 1, 1, 0, 0, 0},
                {0, 0, 0, 1, 1, 0, 0, 0}};
                break;
                
		default:
			board = new int[][]{
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 2, 1, 0, 0, 0},
				{0, 0, 0, 1, 2, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0}};				
		}	
	}

	/**
	 * This method is used to get the square value based on row and col
	 * @param row
	 * @param col
	 * @return
	 */
	public int getSquare(int row, int col) {
		return board[row][col];
	}

	/**
	 * This method returns the opponent player of passed player
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
	 * This logic will check to see if a player (1 for black, 2 for white) may
	 * make a valid move at that particular square.
	 * 
	 * @param row
	 * @param col
	 * @param player
	 * @return True if yes, false if no.
	 */
	public boolean canMove(int row, int col, int player) {
		boolean canMove = false;
		if (board[row][col] != EMPTY) {
			return canMove;
		}
		ArrayList<Integer> rowToCaptureConsolidatedList = new ArrayList<Integer>();
		ArrayList<Integer> colToCaptureConsolidatedList = new ArrayList<Integer>();
		if (row > 1 && col > 1) {
			int leftUpDiagonalPiece = getSquare(row - 1, col - 1);
			if (leftUpDiagonalPiece == opponent(player)) {
				boolean result = keepGoingInLeftUpDiagonal(row - 2, col - 2, player, rowToCaptureConsolidatedList,
						colToCaptureConsolidatedList);
				if (result) {
					return true;
				}
			}
		}

		if (row < 6 && col < 6) {
			int rightBottomDiagonalPiece = getSquare(row + 1, col + 1);
			if (rightBottomDiagonalPiece == opponent(player)) {
				boolean result = keepGoingInRightBottomDiagonal(row + 2, col + 2, player, rowToCaptureConsolidatedList,
						colToCaptureConsolidatedList);
				if (result) {
					return true;
				}
			}
		}

		if (row > 1 && col < 6) {
			int rightUpDiagonalPiece = getSquare(row - 1, col + 1);
			if (rightUpDiagonalPiece == opponent(player)) {
				boolean result = keepGoingInRightUpDiagonal(row - 2, col + 2, player, rowToCaptureConsolidatedList,
						colToCaptureConsolidatedList);
				if (result) {
					return true;
				}
			}
		}

		if (row < 6 && col > 1) {
			int leftDownDiagonalPiece = getSquare(row + 1, col - 1);
			if (leftDownDiagonalPiece == opponent(player)) {
				boolean result = keepGoingInLeftDownDiagonal(row + 2, col - 2, player, rowToCaptureConsolidatedList,
						colToCaptureConsolidatedList);
				if (result) {
					return true;
				}
			}
		}

		if (row > 1) {
			int upPiece = getSquare(row - 1, col);
			if (upPiece == opponent(player)) {
				boolean result = keepGoingInUpDirection(row - 2, col, player, rowToCaptureConsolidatedList,
						colToCaptureConsolidatedList);
				if (result) {
					return true;
				}
			}
		}

		if (row < 6) {
			int downPiece = getSquare(row + 1, col);
			if (downPiece == opponent(player)) {
				boolean result = keepGoingInDownDirection(row + 2, col, player, rowToCaptureConsolidatedList,
						colToCaptureConsolidatedList);
				if (result) {
					return true;
				}
			}
		}

		// Right
		if (col < 6) {
			int rightPiece = getSquare(row, col + 1);
			if (rightPiece == opponent(player)) {
				boolean result = keepGoingInRightDirection(row, col + 2, player, rowToCaptureConsolidatedList,
						colToCaptureConsolidatedList);
				if (result) {
					return true;
				}
			}
		}

		// Left
		if (col > 1) {
			int leftPiece = getSquare(row, col - 1);
			if (leftPiece == opponent(player)) {
				boolean result = keepGoingInLeftDirection(row, col - 2, player, rowToCaptureConsolidatedList,
						colToCaptureConsolidatedList);
				if (result) {
					return true;
				}
			}
		}

		return canMove;
	}

	/**
	 * A recursive method to check the board in left up diagonal direction
	 * @param row
	 * @param col
	 * @param player
	 * @param rowToCapture
	 * @param colToCapture
	 * @return
	 */
	private boolean keepGoingInLeftUpDiagonal(int row, int col, int player, ArrayList<Integer> rowToCapture,
			ArrayList<Integer> colToCapture) {
		if (getSquare(row, col) == player) {
			return true;
		}
		if (getSquare(row, col) == EMPTY) {
			return false;
		}
		if (row > 0 && col > 0) {
			rowToCapture.add(row);
			colToCapture.add(col);
			return keepGoingInLeftUpDiagonal(row - 1, col - 1, player, rowToCapture, colToCapture);
		}
		return false;
	}

	/**
	 * A recursive method to check the board in right down diagonal direction
	 * @param row
	 * @param col
	 * @param player
	 * @param rowToCapture
	 * @param colToCapture
	 * @return
	 */
	private boolean keepGoingInRightBottomDiagonal(int row, int col, int player, ArrayList<Integer> rowToCapture,
			ArrayList<Integer> colToCapture) {
		if (getSquare(row, col) == player) {
			return true;
		}
		if (getSquare(row, col) == EMPTY) {
			return false;
		}
		if (row < 7 && col < 7) {
			rowToCapture.add(row);
			colToCapture.add(col);
			return keepGoingInRightBottomDiagonal(row + 1, col + 1, player, rowToCapture, colToCapture);
		}
		return false;
	}

	/**
	 * A recursive method to check the board in right up diagonal direction
	 * @param row
	 * @param col
	 * @param player
	 * @param rowToCapture
	 * @param colToCapture
	 * @return
	 */
	private boolean keepGoingInRightUpDiagonal(int row, int col, int player, ArrayList<Integer> rowToCapture,
			ArrayList<Integer> colToCapture) {
		if (getSquare(row, col) == player) {
			return true;
		}
		if (getSquare(row, col) == EMPTY) {
			return false;
		}
		if (row > 0 && col < 7) {
			rowToCapture.add(row);
			colToCapture.add(col);
			return keepGoingInRightUpDiagonal(row - 1, col + 1, player, rowToCapture, colToCapture);
		}
		return false;
	}

	/**
	 * A recursive method to check the board in left down diagonal direction
	 * @param row
	 * @param col
	 * @param player
	 * @param rowToCapture
	 * @param colToCapture
	 * @return
	 */
	private boolean keepGoingInLeftDownDiagonal(int row, int col, int player, ArrayList<Integer> rowToCapture,
			ArrayList<Integer> colToCapture) {
		if (getSquare(row, col) == player) {
			return true;
		}
		if (getSquare(row, col) == EMPTY) {
			return false;
		}
		if (row < 7 && col > 0) {
			rowToCapture.add(row);
			colToCapture.add(col);
			return keepGoingInLeftDownDiagonal(row + 1, col - 1, player, rowToCapture, colToCapture);
		}
		return false;
	}

	/**
	 * A recursive method to check the board in up direction
	 * @param row
	 * @param col
	 * @param player
	 * @param rowToCapture
	 * @param colToCapture
	 * @return
	 */
	private boolean keepGoingInUpDirection(int row, int col, int player, ArrayList<Integer> rowToCapture,
			ArrayList<Integer> colToCapture) {
		if (getSquare(row, col) == player) {
			return true;
		}
		if (getSquare(row, col) == EMPTY) {
			return false;
		}
		if (row > 0) {
			rowToCapture.add(row);
			colToCapture.add(col);
			return keepGoingInUpDirection(row - 1, col, player, rowToCapture, colToCapture);
		}
		return false;
	}

	/**
	 * A recursive method to check the board in down direction
	 * @param row
	 * @param col
	 * @param player
	 * @param rowToCapture
	 * @param colToCapture
	 * @return
	 */
	private boolean keepGoingInDownDirection(int row, int col, int player, ArrayList<Integer> rowToCapture,
			ArrayList<Integer> colToCapture) {
		if (getSquare(row, col) == player) {
			return true;
		}
		if (getSquare(row, col) == EMPTY) {
			return false;
		}
		if (row < 7) {
			rowToCapture.add(row);
			colToCapture.add(col);
			return keepGoingInDownDirection(row + 1, col, player, rowToCapture, colToCapture);
		}
		return false;
	}

	/**
	 * A recursive method to check the board in right direction
	 * @param row
	 * @param col
	 * @param player
	 * @param rowToCapture
	 * @param colToCapture
	 * @return
	 */
	private boolean keepGoingInRightDirection(int row, int col, int player, ArrayList<Integer> rowToCapture,
			ArrayList<Integer> colToCapture) {
		if (getSquare(row, col) == player) {
			return true;
		}
		if (getSquare(row, col) == EMPTY) {
			return false;
		}
		if (col < 7) {
			rowToCapture.add(row);
			colToCapture.add(col);
			return keepGoingInRightDirection(row, col + 1, player, rowToCapture, colToCapture);
		}
		return false;
	}

	/**
	 * A recursive method to check the board in left direction
	 * @param row
	 * @param col
	 * @param player
	 * @param rowToCapture
	 * @param colToCapture
	 * @return
	 */
	private boolean keepGoingInLeftDirection(int row, int col, int player, ArrayList<Integer> rowToCapture,
			ArrayList<Integer> colToCapture) {
		if (getSquare(row, col) == player) {
			return true;
		}
		if (getSquare(row, col) == EMPTY) {
			return false;
		}
		if (col > 0) {
			rowToCapture.add(row);
			colToCapture.add(col);
			return keepGoingInLeftDirection(row, col - 1, player, rowToCapture, colToCapture);
		}
		return false;
	}

	/**
	 * The player is attempting attempt to move here. It will also update its internal board model if the move is legal, flipping all appropriate chips to the new colour.
	 * @param row
	 * @param col
	 * @param player
	 * @return the number of chips captured; or 0 if the move is illegal
	 */
	public int tryMove(int row, int col, int player) {
		if (board[row][col] != EMPTY) {
			return 0;
		}
		ArrayList<Integer> rowToCaptureConsolidatedList = new ArrayList<Integer>();
		ArrayList<Integer> colToCaptureConsolidatedList = new ArrayList<Integer>();

		if (row > 1 && col > 1) {
			int leftUpDiagonalPiece = getSquare(row - 1, col - 1);
			if (leftUpDiagonalPiece == opponent(player)) {
				ArrayList<Integer> rowToCapture = new ArrayList<Integer>();
				ArrayList<Integer> colToCapture = new ArrayList<Integer>();
				rowToCapture.add(row - 1);
				colToCapture.add(col - 1);
				boolean result = keepGoingInLeftUpDiagonal(row - 2, col - 2, player, rowToCapture, colToCapture);
				if (result) {
					rowToCaptureConsolidatedList.addAll(rowToCapture);
					colToCaptureConsolidatedList.addAll(colToCapture);
				}
			}
		}

		if (row < 6 && col < 6) {
			int rightBottomDiagonalPiece = getSquare(row + 1, col + 1);
			if (rightBottomDiagonalPiece == opponent(player)) {
				ArrayList<Integer> rowToCapture = new ArrayList<Integer>();
				ArrayList<Integer> colToCapture = new ArrayList<Integer>();
				rowToCapture.add(row + 1);
				colToCapture.add(col + 1);
				boolean result = keepGoingInRightBottomDiagonal(row + 2, col + 2, player, rowToCapture, colToCapture);
				if (result) {
					rowToCaptureConsolidatedList.addAll(rowToCapture);
					colToCaptureConsolidatedList.addAll(colToCapture);
				}
			}
		}

		if (row > 1 && col < 6) {
			int rightUpDiagonalPiece = getSquare(row - 1, col + 1);
			if (rightUpDiagonalPiece == opponent(player)) {
				ArrayList<Integer> rowToCapture = new ArrayList<Integer>();
				ArrayList<Integer> colToCapture = new ArrayList<Integer>();
				rowToCapture.add(row - 1);
				colToCapture.add(col + 1);
				boolean result = keepGoingInRightUpDiagonal(row - 2, col + 2, player, rowToCapture, colToCapture);
				if (result) {
					rowToCaptureConsolidatedList.addAll(rowToCapture);
					colToCaptureConsolidatedList.addAll(colToCapture);
				}
			}
		}

		if (row < 6 && col > 1) {
			int leftDownDiagonalPiece = getSquare(row + 1, col - 1);
			if (leftDownDiagonalPiece == opponent(player)) {
				ArrayList<Integer> rowToCapture = new ArrayList<Integer>();
				ArrayList<Integer> colToCapture = new ArrayList<Integer>();
				rowToCapture.add(row + 1);
				colToCapture.add(col - 1);
				boolean result = keepGoingInLeftDownDiagonal(row + 2, col - 2, player, rowToCapture, colToCapture);
				if (result) {
					rowToCaptureConsolidatedList.addAll(rowToCapture);
					colToCaptureConsolidatedList.addAll(colToCapture);
				}
			}
		}

		if (row > 1) {
			int upPiece = getSquare(row - 1, col);
			if (upPiece == opponent(player)) {
				ArrayList<Integer> rowToCapture = new ArrayList<Integer>();
				ArrayList<Integer> colToCapture = new ArrayList<Integer>();
				rowToCapture.add(row - 1);
				colToCapture.add(col);
				boolean result = keepGoingInUpDirection(row - 2, col, player, rowToCapture, colToCapture);
				if (result) {
					rowToCaptureConsolidatedList.addAll(rowToCapture);
					colToCaptureConsolidatedList.addAll(colToCapture);
				}
			}
		}

		if (row < 6) {
			int downPiece = getSquare(row + 1, col);
			if (downPiece == opponent(player)) {
				ArrayList<Integer> rowToCapture = new ArrayList<Integer>();
				ArrayList<Integer> colToCapture = new ArrayList<Integer>();
				rowToCapture.add(row + 1);
				colToCapture.add(col);
				boolean result = keepGoingInDownDirection(row + 2, col, player, rowToCapture, colToCapture);
				if (result) {
					rowToCaptureConsolidatedList.addAll(rowToCapture);
					colToCaptureConsolidatedList.addAll(colToCapture);
				}
			}
		}

		// Right
		if (col < 6) {
			int rightPiece = getSquare(row, col + 1);
			if (rightPiece == opponent(player)) {
				ArrayList<Integer> rowToCapture = new ArrayList<Integer>();
				ArrayList<Integer> colToCapture = new ArrayList<Integer>();
				rowToCapture.add(row);
				colToCapture.add(col + 1);
				boolean result = keepGoingInRightDirection(row, col + 2, player, rowToCapture, colToCapture);
				if (result) {
					rowToCaptureConsolidatedList.addAll(rowToCapture);
					colToCaptureConsolidatedList.addAll(colToCapture);
				}
			}
		}

		// Left
		if (col > 1) {
			int leftPiece = getSquare(row, col - 1);
			if (leftPiece == opponent(player)) {
				ArrayList<Integer> rowToCapture = new ArrayList<Integer>();
				ArrayList<Integer> colToCapture = new ArrayList<Integer>();
				rowToCapture.add(row);
				colToCapture.add(col - 1);
				boolean result = keepGoingInLeftDirection(row, col - 2, player, rowToCapture, colToCapture);
				if (result) {
					rowToCaptureConsolidatedList.addAll(rowToCapture);
					colToCaptureConsolidatedList.addAll(colToCapture);
				}
			}
		}

		if (rowToCaptureConsolidatedList.size() > 0) {
			for (int i = 0; i < rowToCaptureConsolidatedList.size(); i++) {
				board[rowToCaptureConsolidatedList.get(i)][colToCaptureConsolidatedList.get(i)] = player;
			}
			board[row][col] = player;
		}

		return rowToCaptureConsolidatedList.size();

	}

	/**
	 * This method checks if player has any valid move available or not
	 * @param player
	 * @return true if the given player has a valid move they can do at all, anywhere on the board.
	 */
	public boolean moveTest(int player) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (canMove(i, j, player)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * This method finds the total number of pieces of a specific player
	 * @param player
	 * @return the total number of pieces the specified player has on the board at the time of calling.
	 */
	public int chipCount(int player) {
		int count = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (getSquare(i, j) == player) {
					count++;
				}
			}
		}
		return count;
	}

	/**
	 * This method is used to save the game
	 * @return True is saved successfully else false
	 */
	public boolean saveGame() {
		try {
			File file = new File("game.txt"); 
			FileWriter fw = new FileWriter(file);
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					fw.write(getSquare(i, j)+"");
				}
				fw.write("\n");
			}			
			fw.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * This method loads the last saved game.
	 * @return True if loaded successfully else false
	 */
	public boolean loadGame(){
		try{
			File file = new File("game.txt"); 
			FileReader fr=new FileReader(file);  
			BufferedReader br=new BufferedReader(fr);
			String line;  
			int[][] temp = new int[8][8];
			int i=0;
			while((line=br.readLine())!=null)  {
				if(line.length()!=8){
					br.close();
					fr.close();
					return false;
				}
				for(int j=0;j<8;j++){
					int x = Integer.parseInt(line.charAt(j)+"");
					if(x==0 || x==1 || x==2){
						temp[i][j] = x;
					}else{
						br.close();
						fr.close();						
						return false;
					}
				}				
				i++;
			}
			br.close();
			fr.close();
			for (i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					board[i][j] = temp[i][j];
				}
			}			
			return true;
		}catch(Exception ex){
			return false;
		}
	}

}//End of file
