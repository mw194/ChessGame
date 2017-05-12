package de.hdm_stuttgart.chessgame.test.pieces;

import static org.junit.Assert.*;
import org.junit.Test;

import de.hdm_stuttgart.chessgame.pieces.ChessPiece;
import de.hdm_stuttgart.chessgame.pieces.ChessPieceFactory;
import de.hdm_stuttgart.chessgame.pieces.EnumPieceColor;
import de.hdm_stuttgart.chessgame.pieces.EnumPieceType;

public class KnightTest
{
	@Test
	public void testValidMovement() { 
		ChessPiece[][] board = new ChessPiece[8][8]; // Dummy Board 
		// Fill with pieces 
		ChessPiece dummyKnight = ChessPieceFactory.getInstance(EnumPieceColor.WHITE, 4, 4, EnumPieceType.KNIGHT); 
		board[4][4] = dummyKnight; 
		//board[4][2] = dummyKnight; 


		assertTrue(dummyKnight.canMove(6, 5, board)); // rechts oben 
		assertTrue(dummyKnight.canMove(6, 3, board)); // rechts unten 
		assertTrue(dummyKnight.canMove(2, 5, board)); // links oben 
		assertTrue(dummyKnight.canMove(2, 3, board)); // rechts unten 
		assertTrue(dummyKnight.canMove(5, 6, board)); // oben rechts 
		assertTrue(dummyKnight.canMove(3, 6, board)); // oben links 
		assertTrue(dummyKnight.canMove(5, 2, board)); // unten rechts 
		assertTrue(dummyKnight.canMove(3, 2, board)); // rechts oben 

		}
	
	@Test
	public void testInvalidMovement()
	{
		
		ChessPiece[][] board = new ChessPiece[8][8]; // Dummy Board 
		// Fill with pieces 
		ChessPiece dummyKnight = ChessPieceFactory.getInstance(EnumPieceColor.WHITE, 2, 2, EnumPieceType.KNIGHT); 
		
		board[2][2] = dummyKnight;
		board[4][3] = ChessPieceFactory.getInstance(EnumPieceColor.WHITE, 4, 3, EnumPieceType.KNIGHT); 
		assertFalse(dummyKnight.canMove(7, 5, board));
		assertFalse(dummyKnight.canMove(4, 3, board));
		assertFalse(dummyKnight.canMove(2, 6, board));
		
		
		
	}
	

}