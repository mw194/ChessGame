package de.hdm_stuttgart.chessgame;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import de.hdm_stuttgart.chessgame.display.IDisplay;
import de.hdm_stuttgart.chessgame.pieces.*;

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
	private final IDisplay display;

	public Game(IDisplay display)
	{
		this.display = display;
		currentInstance = this;
		fillFull();
		update();
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
		if (checkmate() == 1) {
			Main.getLog().info("Black wins the game");
			display.processCheckmate(EnumPieceColor.BLACK);
		}

		if (checkmate() == 2) {
			Main.getLog().info("White wins the game");
			display.processCheckmate(EnumPieceColor.WHITE);
		}
		
		if(check() == 1){ //If White King is in Check
			Main.getLog().info("White King is in Check");
		}
		
		if(check() == 2){ //If Black King is in Check
			Main.getLog().info("Black King is in Check");
		}
				
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
		
		display.processUpdate(board, getCurrentTeam());
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
				display.processInvalidAction("Keine passende Figur gefunden!");
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
				Main.getLog().info("Figur geschlagen.");
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
	
	/**
	 * Checks if the game is over
	 * 3 := both kings alive
	 * 2 := black king dead -> white wins
	 * 1 := white king dead -> black wins
	 * @return code for different options
	 */
	private int checkmate() //DE: Schachmatt
	{
		final AtomicInteger atInt = new AtomicInteger(0); // Have to use AtomicInteger because this object has to be final
		
		// To meet the project requirements:
		// Filtering the collection for condition "piece instanceof King"
		// then for every piece that matches the condition, change the value of the atInt
		blackPieces.stream().filter((piece) -> piece instanceof King).forEach((piece) -> {
			atInt.getAndIncrement();
		});
		
		whitePieces.stream().filter((piece) -> piece instanceof King).forEach((piece) -> {
			atInt.getAndAdd(2);
		});
		
//		for(ChessPiece pivot : blackPieces){
//			if(pivot instanceof King){
//				win += 1;
//			}
//		}
//		
//		for(ChessPiece pivot : whitePieces){
//			if (pivot instanceof King) {
//				win += 2;
//			}
//		}
		return atInt.get();
	}
	
	/**
	 * Checks if a King can be beaten/is in check
	 * 0 := No King is in check
	 * 1 := White King is in check
	 * 2 := Black King is in check
	 * @return code for different options
	 */
	private int check() //DE: Im Schach stehen
	{
		for (ChessPiece pivot : whitePieces) {
			if (pivot instanceof King) {
				for (ChessPiece enemy : blackPieces) {
					if (enemy.canMove(pivot.getX(), pivot.getY(), board)) {
						System.out.println(enemy);
						return 1;
					}
				}
				break;
			}		
		}

				
		for (ChessPiece pivot : blackPieces) {
			if (pivot instanceof King) {
				for (ChessPiece enemy : whitePieces) {
					if (enemy.canMove(pivot.getX(), pivot.getY(), board)) {
						return 2;
					}
				}
				break;
			}
		}
	
		return 0;
	}
}
