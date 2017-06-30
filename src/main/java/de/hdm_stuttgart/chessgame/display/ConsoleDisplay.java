package de.hdm_stuttgart.chessgame.display;

import de.hdm_stuttgart.chessgame.Main;
import de.hdm_stuttgart.chessgame.pieces.*;

/**
 * Prints the game board to the console after every update.
 * Unused in normal application.
 */
@Deprecated
public class ConsoleDisplay implements IDisplay
{	
	@Override
	public void processUpdate(ChessPiece[][] board, EnumPieceColor currentTeam)
	{
		System.out.println("current player= " + currentTeam);
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

	@Override
	public void processInvalidAction(String message)
	{
		Main.getLog().info(message);
	}

	@Override
	public void processCheckmate(EnumPieceColor winner)
	{
		Main.getLog().info(winner.name() + " wins!");
	}

	@Override
	public void processCheckPreupdate(boolean whiteInCheck,
			boolean blackInCheck)
	{
		if (whiteInCheck)
		{
			Main.getLog().info("White is in check");
		}

		if (blackInCheck)
		{
			Main.getLog().info("Black is in check");
		}
	}
}