package de.hdm_stuttgart.chessgame.test.pieces;

import static org.junit.Assert.*;
import org.junit.Test;

import de.hdm_stuttgart.chessgame.pieces.ChessPiece;
import de.hdm_stuttgart.chessgame.pieces.ChessPieceFactory;
import de.hdm_stuttgart.chessgame.pieces.EnumPieceColor;
import de.hdm_stuttgart.chessgame.pieces.EnumPieceType;

public class BishopTest
{
	@Test
	public void testValidMovement()
	{
		ChessPiece[][] board = new ChessPiece[8][8]; // Dummy Board
		// Fill with pieces
		ChessPiece dummyBishop = ChessPieceFactory.getInstance(EnumPieceColor.WHITE, 4, 4, EnumPieceType.BISHOP);
		board[4][4] = dummyBishop;
		board[1][1] = ChessPieceFactory.getInstance(EnumPieceColor.BLACK, 1, 1, EnumPieceType.BISHOP);
		
		assertTrue(dummyBishop.canMove(3, 5, board)); // Up right
		assertTrue(dummyBishop.canMove(2, 6, board)); // Up right
		assertTrue(dummyBishop.canMove(3, 3, board)); // Up left
		assertTrue(dummyBishop.canMove(2, 2, board)); // Up left
		assertTrue(dummyBishop.canMove(5, 5, board)); // Down right
		assertTrue(dummyBishop.canMove(7, 7, board)); // Down right
		assertTrue(dummyBishop.canMove(5, 3, board)); // Down left
		assertTrue(dummyBishop.canMove(6, 2, board)); // Down left
		assertTrue(dummyBishop.canMove(1, 1, board)); // Beats enemy
	}
	
	@Test
	public void testInvalidMovement() 
	{
		ChessPiece[][] board = new ChessPiece[8][8]; // Dummy Board
		// Fill with pieces
		ChessPiece dummyBishop = ChessPieceFactory.getInstance(EnumPieceColor.WHITE, 4, 4, EnumPieceType.BISHOP);
		board[4][4] = dummyBishop;
		board[3][3] = dummyBishop;

		assertFalse(dummyBishop.canMove(2, 2, board)); // Jumps over piece
		assertFalse(dummyBishop.canMove(3, 3, board)); // Kill own color
		assertFalse(dummyBishop.canMove(5, 7, board)); // Movement not possible
		assertFalse(dummyBishop.canMove(1, 0, board)); // Movement not possible
	}
}