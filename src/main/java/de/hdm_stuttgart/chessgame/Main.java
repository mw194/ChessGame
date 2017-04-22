package de.hdm_stuttgart.chessgame;

public class Main {
	public static void main(String[] args) {
		Game chess = new Game();

		chess.printBoard();
		chess.select(6, 6);
		chess.select(5, 5);
		chess.printBoard();


	}
}
