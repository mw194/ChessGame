package de.hdm_stuttgart.chessgame.pieces;

public class NullPieceTypeException extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	public NullPieceTypeException() { super(); }
	
	public NullPieceTypeException(String msg) { super(msg); }
}