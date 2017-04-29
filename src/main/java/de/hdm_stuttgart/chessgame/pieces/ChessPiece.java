package de.hdm_stuttgart.chessgame.pieces;

public abstract class ChessPiece
{
	protected EnumPieceColor color;	//color of Piece
	protected int x;			//Current x coordinate
	protected int y;			//Current y coordinate
	
	/**
	 * @param color This piece's team
	 * @param x Starting position vertical coordinate
	 * @param y Starting position horizontal coordinate
	 */
	public ChessPiece(EnumPieceColor color, int x, int y)
	{
		this.color = color;
		this.x = x;
		this.y = y;
	}

	/**
	 * Sets new coordinates.
	 * @param x New vertical coordinate
	 * @param y New horizontal coordinate
	 */
	public void move(int x, int y) { 
		this.x = x;
		this.y = y;
	}
	
	/**
	 * @return This piece's team
	 */
	public EnumPieceColor getColor()
	{
		return color;
	}
	
	/**
	 * @return Current vertical coordinate
	 */
	public int getX()
	{
		return x;
	}
	
	/**
	 * @return Current horizontal coordinate
	 */
	public int getY()
	{
		return y;
	}
	
	//check if the piece is able to move to the new location 
	public abstract boolean canMove(int nextX, int nextY, ChessPiece[][] board);
}