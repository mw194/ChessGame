package de.hdm_stuttgart.chessgame.pieces;

import de.hdm_stuttgart.chessgame.Game;

/**
 * Represents a pawn piece.
 */
public class Pawn extends ChessPiece
{
	public Pawn(EnumPieceColor color, int x, int y)
	{
		super(color, x, y);
	}
	
	private int firstMove = 0; // 0-Not moved yet / 1 en passant / 2 
	
	@Override
	public boolean canMove(int nextX, int nextY, ChessPiece[][] board)
	{
		if (nextX == x && nextY == y) // No movement
		{
			return false;
		}
		
		if (color == EnumPieceColor.WHITE && nextX >= x)
		{
			return false;
		}
		
		if (color == EnumPieceColor.BLACK && nextX <= x)
		{
			return false;
		}
		
		if (board[nextX][nextY] != null && board[nextX][nextY].getColor() == color) // Own piece on place
		{
			return false;
		}

		if (firstMove == 0 && color == EnumPieceColor.WHITE){
			if(x-2 == nextX && nextY == y && board[nextX][nextY] == null && board[nextX +1][nextY] == null){
				firstMove++;
				return true;
			}
		}
		
		if(firstMove == 0 && color == EnumPieceColor.BLACK){
			if(x+2 == nextX && nextY == y && board[nextX][nextY] == null && board[nextX -1][nextY] == null){
				firstMove++;
				return true;
			}
		}
		if(Math.abs(nextX - x) > 1){
			return false;
		}
		if(y != nextY){ //Beat enemy piece
			
			if(Math.abs(nextY - y) != 1){
				return false;
			}
			if (board[nextX][nextY] != null && board[nextX][nextY].getColor() != color) {
				firstMove = 2;
				return true;
			}
					//En Passant
				if(board[x][nextY] != null && board[x][nextY].getColor() != color && board[x][nextY] instanceof Pawn ){
					if(((Pawn)board[x][nextY]).firstMove == 1 ){
						Game.getCurrentInstance().removePiece(board[x][nextY]); // TODO: This is kind of bad design, try to find a better solution.
						firstMove++;
						return true;
					}
				}
			return false;
		}
		
		
		if (board[nextX][nextY] == null && nextX != x && nextY != y) // Filed is empty and chessPiece is moving diagonal
		{  
			return false;
		}
		
		return true;
	}

	@Override
	public String toString() {
		return "p";
	}
}
