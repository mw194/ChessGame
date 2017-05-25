package de.hdm_stuttgart.chessgame.display;

import de.hdm_stuttgart.chessgame.Game;
import de.hdm_stuttgart.chessgame.pieces.*;
import de.hdm_stuttgart.chessgame.GUIresources.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
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
		
		Pane root = new Pane();
		root.setId("pane");
		Scene scene = new Scene(root, 500, 500);
		
        Button buttonStart = new Button("Start new Game");
        buttonStart.autosize();
        buttonStart.setLayoutX(250-75);
        buttonStart.setLayoutY(180);
        buttonStart.setId("button");
        
		buttonStart.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				System.out.println("adasd");
			}
		});
        
        root.getChildren().add(buttonStart);
        
        Button buttonOptionen = new Button("Options");
        buttonOptionen.autosize();
        buttonOptionen.setLayoutX(250-75);
        buttonOptionen.setLayoutY(230);
        buttonOptionen.setId("button");
        root.getChildren().add(buttonOptionen);
        
        Button buttonEnd = new Button("Quit ");
        buttonEnd.autosize();
        buttonEnd.setLayoutX(250-75);
        buttonEnd.setLayoutY(280);
        buttonEnd.setId("button");
		buttonEnd.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
		        Platform.exit();
				}
		});
        root.getChildren().add(buttonEnd);
        
		
        scene.getStylesheets().addAll(this.getClass().getResource("/de/hdm_stuttgart/chessgame/GUIresources/style.css").toExternalForm());      
		primaryStage.setScene(scene);
		primaryStage.show();

	}
}