package de.hdm_stuttgart.chessgame.test.pieces;

import static org.junit.Assert.*;
import org.junit.Test;
import de.hdm_stuttgart.chessgame.pieces.*;

public class PawnTest
{
	@Test
	public void testValid()
	{
		ChessPiece[][] board = new ChessPiece[8][8]; // Dummy Board
		// Fill with pieces
		Pawn dummyPawn = (Pawn) ChessPieceFactory.getInstance(EnumPieceColor.WHITE, 4, 4, EnumPieceType.PAWN);
		board[4][4] = dummyPawn;
		board[1][3] = ChessPieceFactory.getInstance(EnumPieceColor.BLACK, 4, 4, EnumPieceType.ROOK);
		
		assertTrue(dummyPawn.canMove(2, 4, board)); // Up, Double
		dummyPawn.move(2, 4);
		board[2][4] = dummyPawn;
		board[4][4] = null;
		assertTrue(dummyPawn.canMove(1, 3, board)); // Diagonal, beats enemy
		
		dummyPawn = (Pawn) ChessPieceFactory.getInstance(EnumPieceColor.BLACK, 4, 4, EnumPieceType.PAWN);
		board[4][4] = dummyPawn;
		board[7][3] = ChessPieceFactory.getInstance(EnumPieceColor.WHITE, 4, 4, EnumPieceType.ROOK);
		assertTrue(dummyPawn.canMove(6, 4, board)); // Black has different orientation, aside from that, see above
		dummyPawn.move(6, 4);
		board[6][4] = dummyPawn;
		board[4][4] = null;
		assertTrue(dummyPawn.canMove(7, 3, board));
	}
	
	@Test
	public void testInvalid()
	{
		ChessPiece[][] board = new ChessPiece[8][8];
		Pawn dummyPawn = (Pawn) ChessPieceFactory.getInstance(EnumPieceColor.WHITE, 4, 4, EnumPieceType.PAWN);
		board[4][4] = dummyPawn;
		
		assertFalse(dummyPawn.canMove(6, 4, board));
		assertFalse(dummyPawn.canMove(5, 4, board));
		
		dummyPawn = (Pawn) ChessPieceFactory.getInstance(EnumPieceColor.WHITE, 4, 4, EnumPieceType.PAWN);
		board[4][4] = dummyPawn;
		assertFalse(dummyPawn.canMove(5, 4, board)); // Need to revalidate because of move tracker
		
		assertTrue(dummyPawn.canMove(2, 4, board));
		dummyPawn.move(2, 4);
		board[2][4] = dummyPawn;
		board[4][4] = null;
		assertFalse(dummyPawn.canMove(0, 4, board)); // goes double twice
		
		assertFalse(dummyPawn.canMove(2, 3, board));
		assertFalse(dummyPawn.canMove(2, 5, board)); // walks sideways
		board[2][4] = null;
		
		dummyPawn = (Pawn) ChessPieceFactory.getInstance(EnumPieceColor.WHITE, 4, 4, EnumPieceType.PAWN);
		board[4][4] = dummyPawn;
		assertFalse(dummyPawn.canMove(0, 4, board)); // walks more than two
		
		board[3][4] = (Pawn) ChessPieceFactory.getInstance(EnumPieceColor.BLACK, 3, 4, EnumPieceType.PAWN);
		assertFalse(dummyPawn.canMove(3, 4, board)); // beats enemy directly
		assertFalse(dummyPawn.canMove(2, 4, board)); // walks over enemy
		
		// black
		
		board = new ChessPiece[8][8];
		dummyPawn = (Pawn) ChessPieceFactory.getInstance(EnumPieceColor.BLACK, 4, 4, EnumPieceType.PAWN);
		board[4][4] = dummyPawn;
		
		assertFalse(dummyPawn.canMove(3, 4, board));
		assertFalse(dummyPawn.canMove(2, 4, board));
		
		dummyPawn = (Pawn) ChessPieceFactory.getInstance(EnumPieceColor.BLACK, 4, 4, EnumPieceType.PAWN);
		board[4][4] = dummyPawn;
		assertFalse(dummyPawn.canMove(2, 4, board)); // Need to revalidate because of move tracker
		
		assertTrue(dummyPawn.canMove(5, 4, board));
		dummyPawn.move(5, 4);
		board[5][4] = dummyPawn;
		board[4][4] = null;
		assertFalse(dummyPawn.canMove(7, 4, board)); // goes double twice
		
		assertFalse(dummyPawn.canMove(5, 3, board));
		assertFalse(dummyPawn.canMove(5, 5, board)); // walks sideways
		board[5][4] = null;
		
		dummyPawn = (Pawn) ChessPieceFactory.getInstance(EnumPieceColor.BLACK, 4, 4, EnumPieceType.PAWN);
		board[4][4] = dummyPawn;
		assertFalse(dummyPawn.canMove(7, 4, board)); // walks more than two
		
		board[5][4] = (Pawn) ChessPieceFactory.getInstance(EnumPieceColor.WHITE, 5, 4, EnumPieceType.PAWN);
		assertFalse(dummyPawn.canMove(5, 4, board)); // beats enemy directly
		assertFalse(dummyPawn.canMove(6, 4, board)); // walks over enemy
	}
	
	@Test
	public void testEnPassantValidBlack()
	{
		ChessPiece[][] board = new ChessPiece[8][8];
		Pawn beatable = (Pawn) ChessPieceFactory.getInstance(EnumPieceColor.BLACK, 4, 4, EnumPieceType.PAWN);
		board[4][4] = beatable;
		assertTrue(beatable.canMove(6, 4, board));
		beatable.move(6, 4);
		board[6][4] = beatable;
		board[4][4] = null;
		Pawn dummyPawn = (Pawn) ChessPieceFactory.getInstance(EnumPieceColor.WHITE, 6, 3, EnumPieceType.PAWN);
		board[6][3] = dummyPawn;
		assertTrue(dummyPawn.canMove(5, 4, board));
	}
	
	@Test
	public void testEnPassantInvalidBlack()
	{
		ChessPiece[][] board = new ChessPiece[8][8];
		Pawn beatable = (Pawn) ChessPieceFactory.getInstance(EnumPieceColor.BLACK, 6, 4, EnumPieceType.PAWN);
		board[6][4] = beatable;
		Pawn dummyPawn = (Pawn) ChessPieceFactory.getInstance(EnumPieceColor.WHITE, 6, 3, EnumPieceType.PAWN);
		board[6][3] = dummyPawn;
		assertFalse(dummyPawn.canMove(5, 4, board));
	}
	
	@Test
	public void testEnPassantValidWhite()
	{
		ChessPiece[][] board = new ChessPiece[8][8];
		Pawn beatable = (Pawn) ChessPieceFactory.getInstance(EnumPieceColor.WHITE, 4, 4, EnumPieceType.PAWN);
		board[4][4] = beatable;
		assertTrue(beatable.canMove(2, 4, board));
		beatable.move(2, 4);
		board[2][4] = beatable;
		board[4][4] = null;
		Pawn dummyPawn = (Pawn) ChessPieceFactory.getInstance(EnumPieceColor.BLACK, 2, 3, EnumPieceType.PAWN);
		board[2][3] = dummyPawn;
		assertTrue(dummyPawn.canMove(3, 4, board));
	}
	
	@Test
	public void testEnPassantInvalidWhite()
	{
		ChessPiece[][] board = new ChessPiece[8][8];
		Pawn beatable = (Pawn) ChessPieceFactory.getInstance(EnumPieceColor.WHITE, 2, 4, EnumPieceType.PAWN);
		board[2][4] = beatable;
		Pawn dummyPawn = (Pawn) ChessPieceFactory.getInstance(EnumPieceColor.WHITE, 3, 3, EnumPieceType.PAWN);
		board[2][3] = dummyPawn;
		assertFalse(dummyPawn.canMove(3, 4, board));
	}
}