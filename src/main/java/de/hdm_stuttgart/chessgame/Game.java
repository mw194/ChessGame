package de.hdm_stuttgart.chessgame;

import java.util.ArrayList;

import de.hdm_stuttgart.chessgame.pieces.ChessPiece;
import de.hdm_stuttgart.chessgame.pieces.ChessPieceFactory;
import de.hdm_stuttgart.chessgame.pieces.EnumPieceColor;
import de.hdm_stuttgart.chessgame.pieces.EnumPieceType;
import de.hdm_stuttgart.chessgame.test.pieces.*;

/**
 * Represents a single game.
 */
public class Game
{
	private static Game currentInstance;
	
	ChessPiece[][] board = new ChessPiece[8][8]; // Board
	ArrayList<ChessPiece> whitePieces = new ArrayList<>(); // List of white pieces
	ArrayList<ChessPiece> blackPieces = new ArrayList<>(); // List of black piece
	private ChessPiece selectedPiece;
	private int turn = 0;

	public Game()
	{
		currentInstance = this;
		// fillFull();
		fillDebug();
		update();
	}

	/**
	 * For debugging use instead of {@link #fillFull()}.
	 */
	private void fillDebug() {
		whitePieces.add(ChessPieceFactory.getInstance(EnumPieceColor.WHITE, 6, 6, EnumPieceType.KING));
		blackPieces.add(ChessPieceFactory.getInstance(EnumPieceColor.BLACK, 5, 5, EnumPieceType.ROOK));
	}

	/**
	 * Populates the board with a standard chess game.
	 */
	private void fillFull()
	{
		for (int i = 0; i < 8; i++) { // Pawns can be abbreviated
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

	/**
	 * @return The current player based on turns
	 */
	private EnumPieceColor getCurrentTeam()
	{
		if (turn % 2 == 0)
		{
			return EnumPieceColor.WHITE;
		} else
		{
			return EnumPieceColor.BLACK;
		}
	}

	/**
	 * Repopulates the game board with all chess pieces
	 */
	private void update()
	{
		// Clear board
		for (int i = 0; i < board.length; i++)
		{
			for (int j = 0; j < board.length; j++)
			{
				board[i][j] = null;
			}
		}
		
		// Re-insert all pieces
		
		whitePieces.forEach((piece) -> {
			board[piece.getX()][piece.getY()] = piece;
		});
		
		blackPieces.forEach((piece) -> {
			board[piece.getX()][piece.getY()] = piece;
		});
	}

	/**
	 * Handles mouse input.
	 * @param currentX Vertical coordinate of the field clicked
	 * @param currentY Horizontal coordinate of the field clicked
	 */
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

	/**
	 * Only for internal use.
	 * Tries to perform a piece move with the current selected piece.
	 * @param xNew The vertical coordinate to move to
	 * @param yNew The horizontal coordinate to move to
	 */
	private void move(int xNew, int yNew) {
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

	/**
	 * For debugging use.
	 * Prints the current game board to the console.
	 */
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
	
	/**
	 * Removes a {@link de.hdm_stuttgart.chessgame.pieces.ChessPiece} from the board.
	 * @param chessPiece The piece to remove.
	 */
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

	/**
	 * @return The current active game instance
	 */
	public static Game getCurrentInstance()
	{
		return currentInstance;
	}
}
