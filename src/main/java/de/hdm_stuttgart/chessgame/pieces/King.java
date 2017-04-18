package de.hdm_stuttgart.chessgame.pieces;


public class King extends ChessPiece
{

	public King(EnumPieceColor color, int x, int y) {
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
		

		if (nextX != x) // Movement in x direction
		{
			if (nextX > x) //Movement to bottom
			{
				if((nextX - x) > 1) {
					return false;
				}
			} else //Movement to top
			{
				if((x - nextX) > 1) {
					return false;
				}
			}
		} else // Movement in y direction
		{
			if (nextY > y) //Movement to right
			{
				if((nextY - y) > 1) {
					return false;
				}
			}
			if(nextY < y)//Movement to left
			{
				if((y - nextY) > 1) {
					return false;
				}
			}
		}
		
		return true;
		
		
		
		
		
	}

	@Override
	public String toString() {
		return "O";
	}
}