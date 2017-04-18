package de.hdm_stuttgart.chessgame.pieces;

public abstract class ChessPiece
{
	protected EnumPieceColor color;	//color of Piece
	protected int x;			//Current x coordinate
	protected int y;			//Current y coordinate
	
	public ChessPiece(EnumPieceColor color, int x, int y)
	{
		this.color = color;
		this.x = x;
		this.y = y;
	}

	//Move Piece to new coords
	public void move(int x, int y) { 
		this.x = x;
		this.y = y;
	}
	
	public EnumPieceColor getColor()
	{
		return color;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	//check if the piece is able to move to the new location 
	public abstract boolean canMove(int nextX, int nextY, ChessPiece[][] board);
}