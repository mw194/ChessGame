package de.hdm_stuttgart.chessgame.pieces;

/**
 * Represents a bishop piece.
 * @author Diana
 */
public class Bishop extends ChessPiece
{
	/**
	 * creates a new bishop
	 * @param color of the team (black or white)
	 * @param x coordinate
	 * @param y coordinate
	 */
	public Bishop(EnumPieceColor color, int x, int y)
	{
		super(color, x, y);
	}

	@Override
	public boolean canMove(int nextX, int nextY, ChessPiece[][] board)
	{
		if (nextX == x && nextY == y) // No movement -> not valid
		{
			return false;
		}
		if (board[nextX][nextY] != null && board[nextX][nextY].getColor() == color) // Own piece on place
		{
			return false;
		}
		if (nextX == x || nextY == y) // Is moving horizontal or vertical -> not valid
		{
			return false;
		}

		if (nextX < x) { // up
			if (nextY < y) { // up left
				for (int i = 1; x - i >= 0 && y - i >= 0; i++) {
					if (board[x - i][y - i] != null) {
						if (nextX == x - i && nextY == y - i && board[x - i][y - i].getColor() != color) {
							return true;
						}
						return false;
					}
					if (nextX == x - i && nextY == y - i) {
						return true;
					}
				}
			}
			if (nextY > y) { // up right
				for (int i = 1; x - i >= 0 && y + i <= 7; i++) {
					if (board[x - i][y + i] != null) {
						if (nextX == x - i && nextY == y + i && board[x - i][y + i].getColor() != color) {
							return true;
						}
						return false;
					}
					if (nextX == x - i && nextY == y + i) {
						return true;
					}
				}
			}
		}

		if (nextX > x) { // down
			if (nextY < y) { // down left
				for (int i = 1; x + i <= 7 && y - i >= 0; i++) {
					if (board[x + i][y - i] != null) {
						if (nextX == x + i && nextY == y - i && board[x + i][y - i].getColor() != color) {
							return true;
						}
						return false;
					}
					if (nextX == x + i && nextY == y - i) {
						return true;
					}
				}
			}
			if (nextY > y) { // down right
				for (int i = 1; x + i <= 7 && y + i <= 7; i++) {
					if (board[x + i][y + i] != null) {
						if (nextX == x + i && nextY == y + i && board[x + i][y + i].getColor() != color) {
							return true;
						}
						return false;
					}
					if (nextX == x + i && nextY == y + i) {
						return true;
					}
				}
			}
		}	
		return false;
	}

	@Override
	public String toString()
	{
		return "b";
	}
}
