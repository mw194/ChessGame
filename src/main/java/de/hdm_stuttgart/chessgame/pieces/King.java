package de.hdm_stuttgart.chessgame.pieces;


public class King extends ChessPiece
{

	public King(EnumPieceColor color, int x, int y) {
		super(color, x, y);
	}

	@Override
	public boolean canMove(int nextX, int nextY, ChessPiece[][] board) {
		if(!distanceKing(nextX, nextY, board)){ //Distatnce to enemy King
			System.out.println("Distanz zum gegnerischen König nicht berücksichtig");
			return false;
		}
		
		if (nextX == x && nextY == y) { // same position
			System.out.println("Gleiche Position");
			return false;
		}

		if (nextX < (x - 1) || nextX > (x + 1) || nextY < (y - 1) || nextY > (y + 1)){ // Move possible
			System.out.println("Zug nicht möglich");
			return false;
		}

		if (board[nextX][nextY] != null) {
			if (board[nextX][nextY].getColor() == color) { // Same color
				System.out.println("gleiche Farbe");
				return false;
			}
		}
		
		return true;
	}

	boolean distanceKing(int nextX, int nextY, ChessPiece[][] board) { //Returns true if distance is ok
		if (board[nextX + 1][nextY] instanceof King && color != board[nextX + 1][nextY].getColor())
			return false;
		if (board[nextX - 1][nextY] instanceof King && color != board[nextX - 1][nextY].getColor())
			return false;
		if (board[nextX][nextY + 1] instanceof King && color != board[nextX][nextY + 1].getColor())
			return false;
		if (board[nextX][nextY - 1] instanceof King && color != board[nextX][nextY - 1].getColor())
			return false;
		if (board[nextX + 1][nextY + 1] instanceof King && color != board[nextX + 1][nextY + 1].getColor())
			return false;
		if (board[nextX + 1][nextY - 1] instanceof King && color != board[nextX + 1][nextY - 1].getColor())
			return false;
		if (board[nextX - 1][nextY + 1] instanceof King && color != board[nextX - 1][nextY + 1].getColor())
			return false;
		if (board[nextX - 1][nextY - 1] instanceof King && color != board[nextX - 1][nextY - 1].getColor())
			return false;

		return true;
	}


	@Override
	public String toString() {
		return "O";
	}
}