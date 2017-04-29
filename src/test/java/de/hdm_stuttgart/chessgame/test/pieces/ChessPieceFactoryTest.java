package de.hdm_stuttgart.chessgame.test.pieces;

import static org.junit.Assert.*;
import org.junit.Test;
import de.hdm_stuttgart.chessgame.pieces.*;

public class ChessPieceFactoryTest
{
	@Test
	public void testPositive()
	{
		for (EnumPieceType t : EnumPieceType.values())
		{
			ChessPiece cp = ChessPieceFactory.getInstance(EnumPieceColor.BLACK, 0, 0, t);
			assertTrue(cp.getClass().getSimpleName().equalsIgnoreCase(t.name()));
		}
	}
	
	@Test(expected = NullPieceTypeException.class)
	public void testNull()
	{
		ChessPieceFactory.getInstance(EnumPieceColor.BLACK, 0, 0, null);
	}
	
	@Test
	public void testNegative()
	{
		ChessPiece cp = ChessPieceFactory.getInstance(EnumPieceColor.BLACK, 0, 0, EnumPieceType.BISHOP);
		assertFalse(cp.getClass().getSimpleName().equalsIgnoreCase(EnumPieceType.PAWN.name()));
	}
}