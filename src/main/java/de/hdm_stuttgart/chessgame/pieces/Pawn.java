package de.hdm_stuttgart.chessgame.pieces;

// claimed by Markus
public class Pawn extends ChessPiece
{
	public Pawn(EnumPieceColor color, int x, int y) {
		super(color, x, y);
	}
	
	private int firstMove = 0; // TODO - Implement in the CheesPiece's
	
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
		
		if (board[nextX][nextY] == null && nextX != x && nextY != y) // Filed is empty and chessPiece is moving diagonal
		{  
			return false;
		} 
		
		int m = 1;
		
		if(firstMove == 0) {
			m = 2;
		}
		
		if (nextX != x) // Movement in x direction
		{
			if (nextX > x) //Movement to bottom
			{
				if((nextX - x) == m) 
				{
					for (int i = nextX - 1; i > x; i--)
					{
						if (board[i][y] != null) return false;
					}
				}
				
			} else //Movement to top
			{
				for (int i = nextX + 1; i < x; i++)
				{
					if (board[i][y] != null) return false;
				}
			}
		} 
		
		return true;
	}

	@Override
	public String toString() {
		return "p";
	}
}
