package de.hdm_stuttgart.chessgame.pieces;

public class Queen extends ChessPiece{
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
		
		if (x == nextX || y == nextY) {
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
		} else {
			
			if(nextX != x && nextY != y)   // Check if chessPiece goes diagonal  
			{
				if(((x % 2) == 0 && (y % 2) == 0) || ((x % 2) != 0 && (y % 2) != 0)) // Check if current position both are even or odd
				{
					if(((nextX % 2) != 0 && (nextY % 2) == 0) || ((nextX % 2) == 0 && (nextY % 2) != 0)) // Check if next position both are not same (even/odd)
					{
						return false;
					}
				}
				
				if(((x % 2) != 0 && (y % 2) == 0) || ((x % 2) == 0 && (y % 2) != 0)) // Check if current position is even or odd
				{
					if(((nextX % 2) == 0 && (nextY % 2) == 0) || ((nextX % 2) != 0 && (nextY % 2) != 0)) // Check if next position both are even or odd
					{
						return false;
					}
				}
				
				if(nextY > y) // ChessPiece goes up
				{
					if(nextX > x ) // ChessPiece goes up right
					{
						for(int i = nextX - 1, j = nextY - 1; i > x; i--,j--) 
						{
							if(board[i][j] != null) {
								return false;
							}
						}
					}
					else if(nextX < x) // ChessPiece goes up left
					{
						for(int i = nextX + 1, j = nextY - 1; i < x; i++, j--) { 
							if(board[i][j] != null) {
								return false;
							}
						}
					}

				}

				if(nextY < y) // ChessPiece goes down
				{
					if(nextX < x) // ChessPiece goes down left
					{
						for(int i = nextX + 1, j = nextY + 1; i < x; i++, j++ ) {
							if(board[i][j] != null) {
								return false;
							}
						}
					}
					if(nextX > x) // ChessPiece goes down right
					{
						for(int i = nextX - 1, j = nextY + 1; i > x; i--,j++) {
							if(board[i][j] != null) {
								return false;
							}
						}
					}
				}
			}
			
		}		
		
		return true;
	}

	@Override
	public String toString() {
		return "Q";
	}
}
