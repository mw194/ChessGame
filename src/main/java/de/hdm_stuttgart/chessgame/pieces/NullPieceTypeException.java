package de.hdm_stuttgart.chessgame.pieces;

/**
 * Utilized by the {@link de.hdm_stuttgart.chessgame.pieces.ChessPieceFactory}.
 * Throws on invalid {@link de.hdm_stuttgart.chessgame.pieces.EnumPieceType}.
 */
public class NullPieceTypeException extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	public NullPieceTypeException() { super(); }

	public NullPieceTypeException(String msg) { super(msg); }
}