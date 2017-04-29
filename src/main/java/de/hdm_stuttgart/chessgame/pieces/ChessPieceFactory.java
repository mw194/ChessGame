package de.hdm_stuttgart.chessgame.pieces;

public class ChessPieceFactory
{
	private ChessPieceFactory() {}
	
	public static ChessPiece getInstance(EnumPieceColor team, int coordX, int coordY, EnumPieceType type)
	{
		if (type == null)
		{
			throw new NullPieceTypeException("Piece type cannot be null!");
		}
		switch (type)
		{
			case ROOK:
				return new Rook(team, coordX, coordY);
			case KNIGHT:
				return new Knight(team, coordX, coordY);
			case KING:
				return new King(team, coordX, coordY);
			case QUEEN:
				return new Queen(team, coordX, coordY);
			case BISHOP:
				return new Bishop(team, coordX, coordY);
			case PAWN:
				return new Pawn(team, coordX, coordY);
			default:
				throw new NullPieceTypeException("Piece type cannot be null!"); // Doesn't fire, but is needed.
		}
	}
}