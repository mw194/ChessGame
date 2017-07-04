package de.hdm_stuttgart.chessgame.pieces;

import de.hdm_stuttgart.chessgame.Game;

/**
 * Represents a pawn piece.
 */
public class Pawn extends ChessPiece
{
	/**
	 * creates a new bishop
	 * @param color of the team (black or white)
	 * @param x coordinate
	 * @param y coordinate
	 */
	protected Pawn(EnumPieceColor color, int x, int y)
	{
		super(color, x, y);
	}

	private int firstMove = 0; // 0-Not moved yet / 1 en passant / 2 
	private boolean enPassantFlagged = false;
	private int enPassantX = -1;
	private int enPassantY = -1;
	
	@Override
	public void move(int x, int y)
	{
		super.move(x, y);
		if (enPassantFlagged)
		{
			enPassantFlagged = false;
			Game.getCurrentInstance().removePiece(enPassantX, enPassantY);
		}
	}

	@Override
	public boolean canMove(int nextX, int nextY, ChessPiece[][] board)
	{
		enPassantFlagged = false;
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
		
		int dX = nextX - x;
		int dXa = Math.abs(dX);
		
		if (nextY == y)
		{
			if (board[nextX][nextY] != null)
			{
				return false;
			}
			
			if (firstMove == 0)
			{
				if (dXa == 2)
				{
					if (board[nextX - (dX / 2)][nextY] == null)
					{
						firstMove++;
						return true;
					}
				}
			}
			
			if (dXa == 1)
			{
				firstMove++;
				return true;
			} else
			{
				return false;
			}
		} else
		{
			int dY = nextY - y;
			int dYa = Math.abs(dY);
			
			if (dYa != 1 || dXa != 1) return false;
			
			if (board[x][nextY] != null)
			{
				if (board[x][nextY].getColor() != color)
				{
					if (board[x][nextY] instanceof Pawn)
					{
						if (((Pawn)board[x][nextY]).firstMove == 1)
						{
							enPassantFlagged = true;
							enPassantX = x;
							enPassantY = nextY;
							firstMove++;
							return true;
						}
					}
				}
			}
			
			if (board[nextX][nextY] != null && board[nextX][nextY].getColor() != color)
			{
				firstMove = 2;
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "p";
	}
}
