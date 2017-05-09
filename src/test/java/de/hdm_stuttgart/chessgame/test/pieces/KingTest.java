package de.hdm_stuttgart.chessgame.test.pieces;

import static org.junit.Assert.*;
import org.junit.Test;

import de.hdm_stuttgart.chessgame.pieces.ChessPiece;
import de.hdm_stuttgart.chessgame.pieces.ChessPieceFactory;
import de.hdm_stuttgart.chessgame.pieces.EnumPieceColor;
import de.hdm_stuttgart.chessgame.pieces.EnumPieceType;

public class KingTest
{
	@Test
	public void testValidMovement()
	{
		ChessPiece[][] board = new ChessPiece[8][8]; // Dummy Board
		// Fill with pieces
		ChessPiece dummyKing = ChessPieceFactory.getInstance(EnumPieceColor.WHITE, 4, 4, EnumPieceType.KING);
		board[4][4] = dummyKing;
		board[2][2] = ChessPieceFactory.getInstance(EnumPieceColor.BLACK, 2, 2, EnumPieceType.KING);
		board[4][3] = ChessPieceFactory.getInstance(EnumPieceColor.BLACK, 4, 3, EnumPieceType.BISHOP);

		assertTrue(dummyKing.canMove(3, 4, board)); // Up
		assertTrue(dummyKing.canMove(5, 4, board)); // Down
		assertTrue(dummyKing.canMove(4, 5, board)); // Right
		assertTrue(dummyKing.canMove(4, 3, board)); // Left kill enemy

	}
	
	@Test
	public void testInvalidMovement()
	{
		ChessPiece[][] board = new ChessPiece[8][8]; // Dummy Board
		// Fill with pieces
		ChessPiece dummyKing = ChessPieceFactory.getInstance(EnumPieceColor.WHITE, 4, 4, EnumPieceType.KING);
		board[4][4] = dummyKing;
		board[4][3] = ChessPieceFactory.getInstance(EnumPieceColor.WHITE, 4, 3, EnumPieceType.BISHOP);
		board[4][6] = ChessPieceFactory.getInstance(EnumPieceColor.BLACK, 2, 2, EnumPieceType.KING);

		assertFalse(dummyKing.canMove(4, 1, board)); // Impossible Movement
		assertFalse(dummyKing.canMove(6, 6, board)); // Impossible Movement
		assertFalse(dummyKing.canMove(4, 3, board)); // Beats own
		assertFalse(dummyKing.canMove(4, 5, board)); // Distance to enemy King

	}
}