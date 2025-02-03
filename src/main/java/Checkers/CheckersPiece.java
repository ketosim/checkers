package Checkers;

import java.util.HashSet;
import java.util.Set;

public class CheckersPiece {

	private char colour;
	private Cell position;
	private boolean isKing = false;
	public static final int BOARD_WIDTH = 8;

	
	public CheckersPiece(char c) {
		this.colour = c;
	}
	
	public char getColour() {
		return this.colour;
	}
	
	public void setPosition(Cell p) {
		this.position = p;
	}
	
	public Cell getPosition() {
		return this.position;
	}

	public boolean isKing() {
		return isKing;
	}
	
	public void setKing(boolean king) {
		isKing = king;
	}
	
	public Set<Move> getAvailableMoves(Cell[][] board) {

		Set<Move> moves = new HashSet<>();

		if(!isKing){

			int up_or_down = (colour == 'w') ? 1 : -1; // White moves down (1), Black moves up (-1)
			int[] dx = {1, -1}; // Changes in x-coordinate for a standard left or right move
			int[] dy = {up_or_down, up_or_down}; // Changes in y-coordinate for standard one or two moves

			for (int i = 0; i < dx.length; i++) {
				int newX = position.getX() + dx[i];
				int newY = position.getY() + dy[i];

				// Rule 1: the move is within bounds of the board based on task 1 solution
				if (newX >= 0 && newX < board.length && newY >= 0 && newY < board.length) {
					Cell potentialMove = board[newY][newX];

					// Rule 2: it is an empty cell
					if (potentialMove.getPiece() == null) {
						moves.add(new Move(this.position, potentialMove, null));

					} else if (potentialMove.getPiece().getColour() != this.colour) { 
						// Rule 3: it is a potential jump and capture
						int jumpX = newX + dx[i];
						int jumpY = newY + dy[i];

						if (jumpX >= 0 && jumpX < board.length && jumpY >= 0 && jumpY < board.length) {
							Cell jumpCell = board[jumpY][jumpX];
							if (jumpCell.getPiece() == null) {
								moves.add(new Move(this.position, jumpCell, potentialMove.getPiece()));
							}
						}
					}
				}
			}
		} else {
			//Rules only for the kings

			int[] dx = {1, -1, -1, 1}; // Diagonal directions
			int[] dy = {1, 1, -1, -1}; // Diagonal directions

			for (int i = 0; i < dx.length; i++) {
				boolean potentialCapture = false;
				
				for (int j = 1; j < BOARD_WIDTH; j++){ 
					int newX = position.getX() + j * dx[i];
					int newY = position.getY() + j * dy[i];

					// Rule 1: Check if the move is within bounds
					if (newX < 0 || newX >= board.length || newY < 0 || newY >= board.length) {
						break; 
					}

					Cell potentialMove = board[newY][newX];

					if (potentialMove.getPiece() == null) {
						if (!potentialCapture) {
							// Rule 2: it's an empty cell and no capture is being made
							moves.add(new Move(this.position, potentialMove, null));
						} else {
							// A jump over an opponent piece into an empty cell
							moves.add(new Move(this.position, potentialMove, board[newY - dy[i]][newX - dx[i]].getPiece()));
							break; 
						}
					} else if (potentialMove.getPiece().getColour() != this.colour && !potentialCapture) {
						// Rule 3: first piece encountered in this direction is an opponent's piece, mark potential capture
						potentialCapture = true;
					} else {
						break; // move to next quadrant
					}
				}
			}
		}


		return moves;
	}
	
	
	//draw the piece
	public void draw(App app) {
		app.strokeWeight(5.0f);
		if (colour == 'w') {
			app.fill(255);
			app.stroke(0);
		} else if (colour == 'b') {
			app.fill(0);
			app.stroke(255);
		}
		app.ellipse(position.getX()*App.CELLSIZE + App.CELLSIZE/2, 
		position.getY()*App.CELLSIZE + App.CELLSIZE/2, 
		App.CELLSIZE*0.8f, 
		App.CELLSIZE*0.8f);

		if (isKing) {
			if (colour == 'w') {
				app.fill(255);
			} else {
				app.fill(0);
			} 
			app.ellipse(position.getX()*App.CELLSIZE + App.CELLSIZE/2, 
			position.getY()*App.CELLSIZE + App.CELLSIZE/2, 
			App.CELLSIZE*0.4f, 
			App.CELLSIZE*0.4f);
		}
		app.noStroke();
	}
}