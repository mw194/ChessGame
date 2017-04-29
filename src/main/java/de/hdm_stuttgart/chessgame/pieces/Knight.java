package de.hdm_stuttgart.chessgame.pieces;

/**
 * Represents a knight piece.
 */
public class Knight extends ChessPiece
{
	public Knight(EnumPieceColor color, int x, int y)
	{
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
		
		int dX = Math.abs(nextX - x);
		int dY = Math.abs(nextY - y);
		
		if (dX == 2)
		{
			return dY == 1;
		} else
		if (dY == 2)
		{
			return dX == 1;
		} else
		{
			return false;
		}
	}

	@Override
	public String toString()
	{
		return "k";
	}
}