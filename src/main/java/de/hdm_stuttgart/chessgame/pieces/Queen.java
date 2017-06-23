package de.hdm_stuttgart.chessgame.pieces;

/**
 * Represents a queen piece.
 * @author da031
 */
public class Queen extends ChessPiece
{
	/**
	 * creates a new bishop
	 * @param color of the team (black or white)
	 * @param x coordinate
	 * @param y coordinate
	 */
	public Queen(EnumPieceColor color, int x, int y) {
		super(color, x, y);
	}

	@Override
	public boolean canMove(int nextX, int nextY, ChessPiece[][] board)
	{
		if (nextX == x && nextY == y) // No movement
		{
			return false;
		}
		if (board[nextX][nextY] != null && board[nextX][nextY].getColor() == color) // Own piece on place
		{
			return false;
		}

		//The Following code is basically the canMove methods from rook/bishop combined
		if (x == nextX || y == nextY) { //starts with the rook movement style check
			if (nextX != x) // Movement in x direction
			{
				if (nextX > x) // Movement to bottom
				{
					for (int i = nextX - 1; i > x; i--) {
						if (board[i][y] != null)
							return false;
					}
					return true;
				} else // Movement to top
				{
					for (int i = nextX + 1; i < x; i++) {
						if (board[i][y] != null)
							return false;
					}
					return true;
				}
			} else // Movement in y direction
			{
				if (nextY > y) // Movement to right
				{
					for (int i = nextY - 1; i > y; i--) {
						if (board[x][i] != null)
							return false;
					}
					return true;
				} else // Movement to left
				{
					for (int i = nextY + 1; i < y; i++) {
						if (board[x][i] != null)
							return false;
					}
					return true;
				}
			}
		} else { //Here starts the bishop movement style check
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

		}
		return false;
	}

	@Override
	public String toString() {
		return "Q";
	}
}
