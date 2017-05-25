package de.hdm_stuttgart.chessgame.display;

import de.hdm_stuttgart.chessgame.Game;
import de.hdm_stuttgart.chessgame.pieces.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

@SuppressWarnings("restriction")
public class GUIDisplay extends Application implements IDisplay
{
	@Override
	public void processUpdate(ChessPiece[][] gameBoard, EnumPieceColor currentTeam)
	{
		// Game Board Update
	}

	@Override
	public void processInvalidAction(String message)
	{
		// Problem Exists Between Keyboard And Chair
	}

	@Override
	public void processCheckmate(EnumPieceColor winner)
	{
		// End game
	}

	@Override
	public void processCheckPreupdate(boolean whiteInCheck, boolean blackInCheck)
	{
		// Check status
	}

	@Override
	public void start(Stage primaryStage) throws Exception
	{
		Game game = new Game(this);
		
		VBox root = new VBox();
		Scene scene = new Scene(root, 500, 500);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
}