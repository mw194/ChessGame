package de.hdm_stuttgart.chessgame.display;

import de.hdm_stuttgart.chessgame.pieces.*;

/**
 * Represents any form of display for the game data.
 */
public interface IDisplay
{
	/**
	 * Processes new game board data to be drawn
	 * @param gameBoard The new game board data
	 * @param currentTeam The player now able to play
	 */
	public void processUpdate(ChessPiece[][] gameBoard, EnumPieceColor currentTeam);
	
	/**
	 * Displays an error message
	 * @param message The error message
	 */
	public void processInvalidAction(String message);
	
	/**
	 * Processes the game ending
	 * @param winner The game's winner
	 */
	public void processCheckmate(EnumPieceColor winner);
}