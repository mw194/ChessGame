package de.hdm_stuttgart.chessgame;

import java.util.ArrayList;

import de.hdm_stuttgart.chessgame.pieces.*;

/**
 * Represents a single game.
 */
public class Game {
	private static Game currentInstance;
	
	ChessPiece[][] board = new ChessPiece[8][8]; // Board
	ArrayList<ChessPiece> whitePieces = new ArrayList<>(); // List of white
															// pieces
	ArrayList<ChessPiece> blackPieces = new ArrayList<>(); // List of black
															// pieces

	private ChessPiece selectedPiece;
	private int turn = 0;

	public Game() { // Constructor
		currentInstance = this;
		// fillFull();
		fillDebug();
		update();
	}

	private void fillDebug() {
		whitePieces.add(ChessPieceFactory.getInstance(EnumPieceColor.WHITE, 6, 0, EnumPieceType.PAWN));
		blackPieces.add(ChessPieceFactory.getInstance(EnumPieceColor.BLACK, 4, 1, EnumPieceType.PAWN));
	}

	void fillFull() {
		for (int i = 0; i < 8; i++) {
			whitePieces.add(ChessPieceFactory.getInstance(EnumPieceColor.WHITE, 6, i, EnumPieceType.PAWN));
		}

		for (int i = 0; i < 8; i++) {
			blackPieces.add(ChessPieceFactory.getInstance(EnumPieceColor.BLACK, 1, i, EnumPieceType.PAWN));
		}

		whitePieces.add(ChessPieceFactory.getInstance(EnumPieceColor.WHITE, 7, 0, EnumPieceType.ROOK));
		whitePieces.add(ChessPieceFactory.getInstance(EnumPieceColor.WHITE, 7, 7, EnumPieceType.ROOK));
		blackPieces.add(ChessPieceFactory.getInstance(EnumPieceColor.BLACK, 0, 0, EnumPieceType.ROOK));
		blackPieces.add(ChessPieceFactory.getInstance(EnumPieceColor.BLACK, 0, 7, EnumPieceType.ROOK));

		whitePieces.add(ChessPieceFactory.getInstance(EnumPieceColor.WHITE, 7, 1, EnumPieceType.KNIGHT));
		whitePieces.add(ChessPieceFactory.getInstance(EnumPieceColor.WHITE, 7, 6, EnumPieceType.KNIGHT));
		blackPieces.add(ChessPieceFactory.getInstance(EnumPieceColor.BLACK, 0, 1, EnumPieceType.KNIGHT));
		blackPieces.add(ChessPieceFactory.getInstance(EnumPieceColor.BLACK, 0, 6, EnumPieceType.KNIGHT));

		whitePieces.add(ChessPieceFactory.getInstance(EnumPieceColor.WHITE, 7, 2, EnumPieceType.BISHOP));
		whitePieces.add(ChessPieceFactory.getInstance(EnumPieceColor.WHITE, 7, 5, EnumPieceType.BISHOP));
		blackPieces.add(ChessPieceFactory.getInstance(EnumPieceColor.BLACK, 0, 2, EnumPieceType.BISHOP));
		blackPieces.add(ChessPieceFactory.getInstance(EnumPieceColor.BLACK, 0, 5, EnumPieceType.BISHOP));

		whitePieces.add(ChessPieceFactory.getInstance(EnumPieceColor.WHITE, 7, 3, EnumPieceType.KING));
		blackPieces.add(ChessPieceFactory.getInstance(EnumPieceColor.BLACK, 0, 3, EnumPieceType.KING));

		whitePieces.add(ChessPieceFactory.getInstance(EnumPieceColor.WHITE, 7, 4, EnumPieceType.QUEEN));
		blackPieces.add(ChessPieceFactory.getInstance(EnumPieceColor.BLACK, 0, 4, EnumPieceType.QUEEN));

	}

	private EnumPieceColor getCurrentTeam() {
		if (turn % 2 == 0) {
			return EnumPieceColor.WHITE;
		} else {
			return EnumPieceColor.BLACK;
		}
	}

	void update() { // Updates the location of all Pieces
		// clear board
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				board[i][j] = null;
			}
		}

		// Insert all white Pieces
		for (ChessPiece piece : whitePieces) {
			board[piece.getX()][piece.getY()] = piece;
		}

		// Insert all white Pieces
		for (ChessPiece piece : blackPieces) {
			board[piece.getX()][piece.getY()] = piece;
		}
	}

	void select(int currentX, int currentY) {

		if (selectedPiece == null) // no selected piece
		{
			if (board[currentX][currentY] == null || board[currentX][currentY].getColor() != getCurrentTeam()) {
				System.out.println("Keine passende Figur gefunden!");
				return;
			}
			selectedPiece = board[currentX][currentY]; // Piece gets selected

		} else {
			if (board[currentX][currentY] == null || board[currentX][currentY].getColor() != getCurrentTeam()) {
				move(currentX, currentY);
			}
		}
	}

	void move(int xNew, int yNew) {
		if (selectedPiece.canMove(xNew, yNew, board)) {
			if (board[xNew][yNew] != null) {
				System.out.println("Figur geschalgen");
				if (whitePieces.contains(board[xNew][yNew])) {
					whitePieces.remove(board[xNew][yNew]);
				}
				if (blackPieces.contains(board[xNew][yNew])) {
					blackPieces.remove(board[xNew][yNew]);
				}
			}

			selectedPiece.move(xNew, yNew);
			selectedPiece = null;
			turn++;
			update();
		} else {
			System.out.println("Kann nicht dahin");
			return;
		}
	}

	void printBoard() { // Prints the board
		System.out.println("current player= " + getCurrentTeam());
		System.out.println(" |0 1 2 3 4 5 6 7\n" + "-+----------------");
		for (int i = 0; i < board.length; i++) {
			System.out.print(i + "|");
			for (int j = 0; j < board.length; j++) {
				if (board[i][j] == null) {
					System.out.print("  ");
				} else {
					System.out.print(board[i][j] + " ");
				}
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public void removePiece(ChessPiece chessPiece)
	{
		if (whitePieces.contains(chessPiece))
		{
			whitePieces.remove(chessPiece);
		} else
		{
			blackPieces.remove(chessPiece);
		}
		
	}

	
	public static Game getCurrentInstance()
	{
		return currentInstance;
	}
}
