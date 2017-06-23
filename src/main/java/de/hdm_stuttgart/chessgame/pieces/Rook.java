package de.hdm_stuttgart.chessgame.pieces;

/**
 * Represents a rook piece.
 */
public class Rook extends ChessPiece {

	/**
	 * creates a new bishop
	 * @param color of the team (black or white)
	 * @param x coordinate
	 * @param y coordinate
	 */
	public Rook(EnumPieceColor color, int coordX, int coordY) {
		super(color, coordX, coordY);
	}

	@Override
	public String toString() {
		return "r";
	}

	@Override
	public boolean canMove(int nextX, int nextY, ChessPiece[][] board)
	{
		if (nextX == x && nextY == y) // No movement
		{
			return false;
		}

		if (nextX != x && nextY != y) // Diagaonal movement
		{
			return false;
		}

		if (board[nextX][nextY] != null && board[nextX][nextY].getColor() == color) // Own piece on place
		{
			return false;
		}

		if (nextX != x) // Movement in x direction
		{
			if (nextX > x) //Movement to bottom
			{
				for (int i = nextX - 1; i > x; i--)
				{
					if (board[i][y] != null) return false;
				}
			} else //Movement to top
			{
				for (int i = nextX + 1; i < x; i++)
				{
					if (board[i][y] != null) return false;
				}
			}
		} else // Movement in y direction
		{
			if (nextY > y) //Movement to right
			{
				for (int i = nextY - 1; i > y; i--)
				{
					if (board[x][i] != null) return false;
				}
			} else //Movement to left
			{
				for (int i = nextY + 1; i < y; i++)
				{
					if (board[x][i] != null) return false;
				}
			}
		}

		return true;
	}
}