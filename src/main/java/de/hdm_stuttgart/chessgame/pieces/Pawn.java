package de.hdm_stuttgart.chessgame.pieces;

// claimed by Markus
public class Pawn extends ChessPiece
{
	public Pawn(EnumPieceColor color, int x, int y) {
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
		return "p";
	}
}
