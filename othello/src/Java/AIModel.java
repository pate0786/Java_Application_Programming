package othello;
/**
 * File name: AIModel.java
 * Author: Vandankumar Patel, Student ID: 040976978
 * Assignment: 1 Part 2 
 * Date: 2021/07/18
 * Professor: Daniel Cormier
 * Purpose: AIModel class contains the logic of AI opponent.Whenever in our game, we have player 2 turn,
 *          move method of this AIModel gets called which finds out the first available valid place for player 2 to move and make a turn there 
 * References: Lab codes and Hybrids provided by Professor Daniel Cormier, https://www.javatpoint.com/java-swing, https://docs.oracle.com/javase/7/docs/api/java/awt/GridLayout.html
 * 
*/

/**
 * This class represents the Aritifical Intelligence Player
 * @author Vandankumar Patel
 *
 */
public class AIModel {

	private OthelloModel model;
	
	//Below two attributes represents the last location where AI player made its turn
	private int lastMoveRow;
	private int lastMoveCol;
	
	/**
	 * AIModel constructor
	 * @param model
	 */
	public AIModel(OthelloModel model) {
		this.model = model;
	}

	/**
	 * This method is responsible to find a square to make a move and it also updates the model to make the move.
	 * @return
	 */
	public int move() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (model.canMove(i, j, OthelloModel.WHITE)) {
					lastMoveRow = i;
					lastMoveCol = j;
					return model.tryMove(i, j, OthelloModel.WHITE);
				}
			}
		}
		return 0;
	}	
	
	//getters and setters
	public int getLastMoveRow() {
		return lastMoveRow;
	}

	public void setLastMoveRow(int lastMoveRow) {
		this.lastMoveRow = lastMoveRow;
	}

	public int getLastMoveCol() {
		return lastMoveCol;
	}

	public void setLastMoveCol(int lastMoveCol) {
		this.lastMoveCol = lastMoveCol;
	}

	public OthelloModel getModel() {
		return model;
	}

	public void setModel(OthelloModel model) {
		this.model = model;
	}
	
}//End of file
