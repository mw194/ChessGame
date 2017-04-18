package de.hdm_stuttgart.chessgame.pieces;

public class King extends ChessPiece
{

	public King(EnumPieceColor color, int x, int y) {
		super(color, x, y);
	}

	@Override
	public boolean canMove(int nextX, int nextY, ChessPiece[][] board)
	{
		//implementier mich
		return false;
	}

	@Override
	public String toString() {
		return "O";
	}
}