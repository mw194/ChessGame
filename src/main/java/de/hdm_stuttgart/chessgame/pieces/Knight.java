package de.hdm_stuttgart.chessgame.pieces;


public class Knight extends ChessPiece{
	public Knight(EnumPieceColor color, int x, int y) {
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
		
		int [][] position = new int [8][2];
		
		position[0][0] = x + 2;
		position[0][1] = y + 1;
		position[1][0] = x + 2;
		position[1][1] = y - 1;
		position[2][0] = x - 2;
		position[2][1] = y + 1;
		position[3][0] = x - 2;
		position[3][1] = y - 1;
		position[4][0] = x + 1;
		position[4][1] = y + 2;
		position[5][0] = x - 1;
		position[5][1] = y + 2;
		position[6][0] = x + 1;
		position[6][1] = y - 2;
		position[7][0] = x - 1;
		position[7][1] = y - 2;
		
		boolean foundX = false;
		boolean foundY = false;
		
		for(int i = 0; i < position.length; i++){
			if(nextX == position[i][0])	{
				foundX = true;
			}
			if(nextY == position[i][1])	{
				foundY = true;
			}
		}
		
		if(foundX == false || foundY == false)	{
			return false;
		}
		
		
		return true;
	}

	@Override
	public String toString() {
		return "k";
	}
}
