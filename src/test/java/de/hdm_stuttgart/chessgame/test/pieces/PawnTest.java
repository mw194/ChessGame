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
		board[1][4] = ChessPieceFactory.getInstance(EnumPieceColor.BLACK, 4, 4, EnumPieceType.ROOK);
		
		assertTrue(dummyPawn.canMove(2, 4, board)); // Up, Double
		dummyPawn.move(2, 4);
		assertTrue(dummyPawn.canMove(1, 4, board)); // Up, beats enemy
		
		dummyPawn = (Pawn) ChessPieceFactory.getInstance(EnumPieceColor.BLACK, 4, 4, EnumPieceType.PAWN);
		board[4][4] = dummyPawn;
		assertTrue(dummyPawn.canMove(6, 4, board)); // Black has different orientation, aside from that, see above
		dummyPawn.move(6, 4);
		assertTrue(dummyPawn.canMove(7, 4, board));
	}
	
	@Test
	public void testInvalid()
	{
		// TODO: Walks backwards (color!), goes double twice, walks sideways, walks more than two, jumps over enemy
	}
	
	@Test
	public void testEnPassantValid()
	{
		// TODO: This is where it gets complicated: Need restructure in Pawn to make this test possible
		// (Explanation: Game.getInstance().removePiece() will throw NullPointerException
		// 	we need to get rid of actual board modification in canMove)
	}
	
	@Test
	public void testEnPassantInvalid()
	{
		// TODO: Random diagonal movement, movement behind non-enpassant-pawn
	}
}