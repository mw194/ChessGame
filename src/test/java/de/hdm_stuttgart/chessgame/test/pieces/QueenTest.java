package de.hdm_stuttgart.chessgame.test.pieces;

import static org.junit.Assert.*;
import org.junit.Test;

import de.hdm_stuttgart.chessgame.pieces.ChessPiece;
import de.hdm_stuttgart.chessgame.pieces.ChessPieceFactory;
import de.hdm_stuttgart.chessgame.pieces.EnumPieceColor;
import de.hdm_stuttgart.chessgame.pieces.EnumPieceType;

public class QueenTest
{
	// Implementier mich.

	@Test
	public void testValidMovement() { 

		ChessPiece[][] board = new ChessPiece[8][8]; // Dummy Board 
		// Fill with pieces 
		ChessPiece dummyQueen = ChessPieceFactory.getInstance(EnumPieceColor.WHITE, 5, 5, EnumPieceType.QUEEN); 
		board[5][5] = dummyQueen;


		assertTrue(dummyQueen.canMove(7, 7, board));
		assertTrue(dummyQueen.canMove(5, 7, board));
		assertTrue(dummyQueen.canMove(5, 2, board));
		assertTrue(dummyQueen.canMove(2, 2, board));

	}
	
	@Test
	public void testInvalidMovement() {
		ChessPiece[][] board = new ChessPiece[8][8]; // Dummy Board 
		// Fill with pieces 
		ChessPiece dummyQueen = ChessPieceFactory.getInstance(EnumPieceColor.WHITE, 5, 5, EnumPieceType.QUEEN); 
		board[5][5] = dummyQueen;
		board[7][7] = ChessPieceFactory.getInstance(EnumPieceColor.WHITE, 7, 7, EnumPieceType.QUEEN); 

		assertFalse(dummyQueen.canMove(7, 7, board));
		assertFalse(dummyQueen.canMove(7, 4, board));
		assertFalse(dummyQueen.canMove(3, 2, board));

	}

}

