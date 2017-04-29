package de.hdm_stuttgart.chessgame.pieces;

import static org.junit.Assert.*;
import org.junit.Test;

public class RookTest
{
	@Test
	public void testValidMovement()
	{
		ChessPiece[][] board = new ChessPiece[8][8]; // Dummy Board
		// Fill with pieces
		ChessPiece dummyRook = ChessPieceFactory.getInstance(EnumPieceColor.WHITE, 4, 4, EnumPieceType.ROOK);
		board[4][4] = dummyRook;
		board[4][2] = ChessPieceFactory.getInstance(EnumPieceColor.BLACK, 4, 4, EnumPieceType.ROOK);;
		
		assertTrue(dummyRook.canMove(2, 4, board)); // Up
		assertTrue(dummyRook.canMove(5, 4, board)); // Down
		assertTrue(dummyRook.canMove(4, 2, board)); // Left, Beats enemy
		assertTrue(dummyRook.canMove(4, 5, board)); // Right
	}
	
	@Test
	public void testInvalidMovement()
	{
		ChessPiece[][] board = new ChessPiece[8][8]; // Dummy Board
		// Fill with pieces
		ChessPiece dummyRook = ChessPieceFactory.getInstance(EnumPieceColor.WHITE, 4, 4, EnumPieceType.ROOK);
		board[4][4] = dummyRook;
		board[4][2] = dummyRook;
		
		assertFalse(dummyRook.canMove(4, 1, board)); // Jumps over piece
		assertFalse(dummyRook.canMove(4, 2, board)); // Beats own
		assertFalse(dummyRook.canMove(6, 6, board)); // Diagonal movement
	}
}