package de.hdm_stuttgart.chessgame.pieces;

/**
 * Represents a king piece.
 * @author Erik
 */
public class King extends ChessPiece
{
	public King(EnumPieceColor color, int x, int y) {
		super(color, x, y);
	}

	@Override
	public boolean canMove(int nextX, int nextY, ChessPiece[][] board) {
		if(!distanceKing(nextX, nextY, board)){ //Distatnce to enemy King
			return false;
		}

		if (nextX == x && nextY == y) { // same position
			return false;
		}

		if (nextX < (x - 1) || nextX > (x + 1) || nextY < (y - 1) || nextY > (y + 1)){ // Move possible
			return false;
		}

		if (board[nextX][nextY] != null) {
			if (board[nextX][nextY].getColor() == color) { // Same color
				return false;
			}
		}

		return true;
	}

	/**
	 * Checks if the Distance to the enemy King is ok
	 * @param nextX x Coord of next Field
	 * @param nextY y Coord of next Field
	 * @param board the board
	 * @return true if distance is ok
	 */
	boolean distanceKing(int nextX, int nextY, ChessPiece[][] board) {
		if (nextX + 1 <= 7) {
			if (board[nextX + 1][nextY] instanceof King && color != board[nextX + 1][nextY].getColor())
				return false;
		}
		if (nextX - 1 >= 0) {
			if (board[nextX - 1][nextY] instanceof King && color != board[nextX - 1][nextY].getColor())
				return false;
		}
		if (nextY + 1 <= 7) {
			if (board[nextX][nextY + 1] instanceof King && color != board[nextX][nextY + 1].getColor())
				return false;
		}
		if (nextY - 1 >= 0) {
			if (board[nextX][nextY - 1] instanceof King && color != board[nextX][nextY - 1].getColor())
				return false;
		}
		if (nextX + 1 <= 7 && nextY + 1 <= 7) {
			if (board[nextX + 1][nextY + 1] instanceof King && color != board[nextX + 1][nextY + 1].getColor())
				return false;
		}
		if (nextX + 1 <= 7 && nextY - 1 >= 0) {
			if (board[nextX + 1][nextY - 1] instanceof King && color != board[nextX + 1][nextY - 1].getColor())
				return false;
		}
		if (nextX - 1 >= 0 && nextY + 1 <= 7) {
			if (board[nextX - 1][nextY + 1] instanceof King && color != board[nextX - 1][nextY + 1].getColor())
				return false;
		}
		if (nextX - 1 >= 0 && nextY - 1 >= 0) {
			if (board[nextX - 1][nextY - 1] instanceof King && color != board[nextX - 1][nextY - 1].getColor())
				return false;
		}
		return true;
	}


	@Override
	public String toString() {
		return "O";
	}
}