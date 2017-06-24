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

	private ChessPiece[][] board = new ChessPiece[8][8]; // Board
	public ArrayList<ChessPiece> whitePieces = new ArrayList<>(); // List of white pieces
	public ArrayList<ChessPiece> blackPieces = new ArrayList<>(); // List of black piece
	public ChessPiece selectedPiece;
	private int move = 0;
	private final IDisplay display;
	private boolean finished;
	public Object threadLock = new Object();

	/**
	 * @param display This game's display
	 */
	public Game(IDisplay display)
	{
		this.display = display;
		currentInstance = this;
		fillFull();
		update();
		Thread thread = new Thread(new GameStateLoggerTask(this));
		thread.setDaemon(true);
		thread.start();
	}

	/**
	 * Populates the board with a standard chess game.
	 */
	private void fillFull()
	{
		synchronized (threadLock)
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
	}

	/**
	 * @return The current player based on turns
	 */
	public EnumPieceColor getCurrentTeam()
	{
		if (move % 2 == 0)
		{
			return EnumPieceColor.WHITE;
		} else
		{
			return EnumPieceColor.BLACK;
		}
	}

	/**
	 * @return The current turn
	 */
	public int getMove() {
		return move;
	}
	
	/**
	 * Repopulates the game board with all chess pieces
	 */
	private void update()
	{
		int checkmateStatus = checkmate();

		if (checkmateStatus == 1) {
			finished = true;
			display.processCheckmate(EnumPieceColor.BLACK);
		} else
			if (checkmateStatus == 2) {
				finished = true;
				display.processCheckmate(EnumPieceColor.WHITE);
			}

		display.processCheckPreupdate(checkCheck(EnumPieceColor.WHITE), checkCheck(EnumPieceColor.BLACK));

		// Clear board
		for (int i = 0; i < board.length; i++)
		{
			for (int j = 0; j < board.length; j++)
			{
				board[i][j] = null;
			}
		}

		// Re-insert all pieces	
		synchronized (threadLock)
		{
			whitePieces.forEach((piece) -> {
				board[piece.getX()][piece.getY()] = piece;
			});

			blackPieces.forEach((piece) -> {
				board[piece.getX()][piece.getY()] = piece;
			});

		}	

		display.processUpdate(board, getCurrentTeam());
	}

	/**
	 * Handles mouse input.
	 * @param currentX Vertical coordinate of the field clicked
	 * @param currentY Horizontal coordinate of the field clicked
	 */
	public void select(int currentX, int currentY) {
		if (selectedPiece == null) // no selected piece
		{
			if (board[currentX][currentY] == null || board[currentX][currentY].getColor() != getCurrentTeam()) {
				display.processInvalidAction("Keine passende Figur gefunden!");
				return;
			}
			selectedPiece = board[currentX][currentY]; // Piece gets selected
		} else {
			if(selectedPiece.getX() == currentX && selectedPiece.getY() == currentY){
				selectedPiece = null;
				return;
			}		
			if(board[currentX][currentY] != null){
				if(board[currentX][currentY].getColor() == selectedPiece.getColor()){
					selectedPiece = board[currentX][currentY];
				}
			}		
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
				display.processInvalidAction("Figur geschlagen.");
				synchronized (threadLock)
				{
					if (whitePieces.contains(board[xNew][yNew])) {
						whitePieces.remove(board[xNew][yNew]);
					}
					if (blackPieces.contains(board[xNew][yNew])) {
						blackPieces.remove(board[xNew][yNew]);
					}
				}
			}

			selectedPiece.move(xNew, yNew);
			selectedPiece = null;
			move++;
			update();
		} else {
			display.processInvalidAction("Kann nicht dorthin.");
			return;
		}
	}

	/**
	 * Removes a {@link de.hdm_stuttgart.chessgame.pieces.ChessPiece} from the board.
	 * @param x The piece's location on x axis
	 * @param y The piece's location on y axis
	 */
	public void removePiece(int x, int y)
	{
		synchronized (threadLock)
		{
			ChessPiece chessPiece = board[x][y];
			if (whitePieces.contains(chessPiece))
			{
				whitePieces.remove(chessPiece);
			} else
			{
				blackPieces.remove(chessPiece);
			}
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
	public int checkmate() //DE: Schachmatt
	{
		final AtomicInteger atInt = new AtomicInteger(0); // Have to use AtomicInteger because this object has to be final

		// To meet the project requirements:
		// Filtering the collection for condition "piece instanceof King"
		// then for every piece that matches the condition, change the value of the atInt
		synchronized (threadLock)
		{
			blackPieces.stream().filter((piece) -> piece instanceof King).forEach((piece) -> {
				atInt.getAndIncrement();
			});

			whitePieces.stream().filter((piece) -> piece instanceof King).forEach((piece) -> {
				atInt.getAndAdd(2);
			});
		}


		// This is the same as:

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
	 * @param color The king to check
	 * @return Is the king in check?
	 */
	public boolean checkCheck(EnumPieceColor color) //DE: Im Schach stehen
	{
		synchronized (threadLock)
		{
			if (color == EnumPieceColor.WHITE)
			{
				for (ChessPiece pivot : whitePieces) {
					if (pivot instanceof King) {
						for (ChessPiece enemy : blackPieces) {
							if (enemy.canMove(pivot.getX(), pivot.getY(), board)) {
								return true;
							}
						}
						break;
					}		
				}
			} else
			{
				for (ChessPiece pivot : blackPieces) {
					if (pivot instanceof King) {
						for (ChessPiece enemy : whitePieces) {
							if (enemy.canMove(pivot.getX(), pivot.getY(), board)) {
								return true;
							}
						}
						break;
					}
				}
			}
		}


		return false;
	}
	
	/**
	 * @return Is the game finished?
	 */
	public boolean isFinished()
	{
		return finished;
	}
	
	/**
	 * @return The current game board
	 */
	public ChessPiece[][] getBoard() {
		return board;
	}
}
