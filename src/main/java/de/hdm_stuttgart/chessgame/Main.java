package de.hdm_stuttgart.chessgame;

public class Main {
	public static void main(String[] args) {
		Game chess = new Game();

		chess.printBoard();
		chess.select(6, 0);
		chess.select(4, 0);
		chess.printBoard();
		System.out.println("NEW PIECE");
		chess.select(4, 1);
		chess.select(5, 0);
		chess.printBoard();

	}
}
