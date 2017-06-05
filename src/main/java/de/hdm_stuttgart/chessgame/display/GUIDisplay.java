package de.hdm_stuttgart.chessgame.display;

import de.hdm_stuttgart.chessgame.Game;
import de.hdm_stuttgart.chessgame.pieces.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GUIDisplay extends Application implements IDisplay
{
	Game game;

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
		
	}

	@Override
	public void processCheckPreupdate(boolean whiteInCheck, boolean blackInCheck)
	{
		// Check status
	}

	private void checkCheck(Stage gameStage) {
		Stage winStage = new Stage();
		VBox root = new VBox();
		root.setAlignment(Pos.CENTER);
		root.setSpacing(10);
		
		Label winLabel = new Label("Black Wins!");
		if (game.checkmate() == 2) {
			winLabel.setText("White Wins!");
		}
		winLabel.setStyle("-fx-font-family: 'didot'; -fx-font-size: 3em;");
		Button winButton = new Button("Back to menu");
		root.getChildren().addAll(winLabel, winButton);

		winButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				gameStage.close();
				winStage.close();
				try {
					start(new Stage());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		Scene scene = new Scene(root, 250, 120);
		winStage.setScene(scene);
		winStage.setResizable(false);
		winStage.show();
	}
	
	/**
	 *Startmenu
	 */
	@Override
	public void start(Stage primaryStage) throws Exception
	{			
		AnchorPane menuPane = new AnchorPane();
		menuPane.setId("startscreen");
		
		/////STARTBUTTON/////////////////////////
        Button buttonStart = new Button("Start new Game");
        buttonStart.setAlignment(Pos.TOP_LEFT);
        buttonStart.setPrefWidth(150);
        AnchorPane.setLeftAnchor(buttonStart, 10.0);
        AnchorPane.setBottomAnchor(buttonStart, 90.0);
        buttonStart.getStyleClass().add("StartButton");   
		buttonStart.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				primaryStage.close();
					try {
						game = new Game(new GUIDisplay());
						game();
					} catch (Exception e) {
						e.printStackTrace();
					}
			}
		});       
		/////OPTIONSBUTTON/////////////////////////      
        Button buttonOptionen = new Button("Options");
        buttonOptionen.setAlignment(Pos.TOP_LEFT);
        buttonOptionen.setPrefWidth(150);
        AnchorPane.setLeftAnchor(buttonOptionen, 10.0);
        AnchorPane.setBottomAnchor(buttonOptionen, 50.0);
        buttonOptionen.getStyleClass().add("StartButton");
		buttonOptionen.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				//TO DO
			}
		});  
		/////QUITBUTTON///////////////////////// //          
        Button buttonEnd = new Button("Quit");
        buttonEnd.setAlignment(Pos.TOP_LEFT);
        buttonEnd.setPrefWidth(150);
        AnchorPane.setLeftAnchor(buttonEnd, 10.0);
        AnchorPane.setBottomAnchor(buttonEnd, 10.0);
        buttonEnd.getStyleClass().add("StartButton");
		buttonEnd.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
		        Platform.exit();
				}
		});
		//////////////////////////////////////////   
		

		Label title = new Label("Chess");
		title.setId("title");
		AnchorPane.setLeftAnchor(title, 10.0);
		
        menuPane.getChildren().addAll(title, buttonStart, buttonOptionen, buttonEnd);
        
		Scene scene = new Scene(menuPane, 280, 280);     	
        scene.getStylesheets().addAll(this.getClass().getResource("/de/hdm_stuttgart/chessgame/GUIresources/style.css").toExternalForm());   
        primaryStage.setTitle("Chess");
        primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/**
	 * Chess Game
	 * @throws Exception
	 */
	public void game() throws Exception {
		Stage gameStage = new Stage();

		BorderPane canvas = new BorderPane();	

		//Toolbar////////////////
		Button buttonFile = new Button("File");
		buttonFile.getStyleClass().add("toolbar");
		buttonFile.setAlignment(Pos.TOP_LEFT);
		
		Button buttonOptions = new Button("Options");
		buttonOptions.getStyleClass().add("toolbar");
		buttonOptions.setAlignment(Pos.TOP_LEFT);
		
		Button buttonHelp = new Button("Help");
		buttonHelp.getStyleClass().add("toolbar");
		buttonHelp.setAlignment(Pos.TOP_LEFT);
		
		ToolBar top = new ToolBar(buttonFile, buttonOptions, buttonHelp);
		//Chessboard/////////////
		BorderPane board = new BorderPane();
		GridPane boardPane = new GridPane();
		boardPane.setId("board");
		
		HBox topSide = new HBox();
		topSide.setPrefHeight(20);
		topSide.setId("top");
		
		VBox leftSide = new VBox();
		leftSide.setId("left");
		leftSide.setPrefWidth(20);
		
		board.setTop(topSide);
		board.setLeft(leftSide);
		board.setCenter(boardPane);
		//Bottom//////////////
		VBox bottom = new VBox();

		StringProperty statusString = new SimpleStringProperty();
		StringProperty currentString = new SimpleStringProperty();
		StringProperty moveString = new SimpleStringProperty();

		Label statusLabel = new Label();
		statusLabel.textProperty().bind(statusString);
		Label currentLabel = new Label("Current Player: ");
		currentLabel.textProperty().bind(currentString);
		Label moveLabel = new Label("Move: ");
		moveLabel.textProperty().bind(moveString);
	
		bottom.getChildren().addAll(statusLabel, currentLabel, moveLabel);
		bottom.setPrefHeight(126);
		bottom.setPadding(new Insets(0, 0, 0, 20));
		/////////////////////////
		
		
		for (int row = 0; row < 8; row++) {
			for (int column = 0; column < 8; column++) {
				Button button = new Button();
				button.setPrefWidth(75);
				button.setPrefHeight(75);
				button.setStyle("-fx-background-color: transparent;");
				int x = column;
		        int y = row;
		        
				button.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
						game.select(x, y);
						update(boardPane, gameStage, statusString, currentString, moveString);
					}
				});			
				boardPane.add(button, row, column);
			}
		}


		canvas.setTop(top);
		canvas.setCenter(board);
		canvas.setBottom(bottom);

		update(boardPane, gameStage, statusString, currentString, moveString);
		
		Scene scene = new Scene(canvas);
		scene.getStylesheets().addAll(this.getClass().getResource("/de/hdm_stuttgart/chessgame/GUIresources/style.css").toExternalForm());
		gameStage.setTitle("Chess");
		gameStage.setScene(scene);
		gameStage.setWidth(620);
		gameStage.setHeight(800);
		gameStage.setResizable(false);
		gameStage.show();
	}
	
	public void update(GridPane center, Stage gameStage, StringProperty statusString, StringProperty currentString, StringProperty moveString ) {
		if (game.checkmate() != 3) {
			checkCheck(gameStage);
		}

		statusString.set("Hier muss der Status stehen");
		currentString.set("Current Team: " + game.getCurrentTeam().toString());
		moveString.set("Move counter: " + game.getMove());

		for (Node b : center.getChildren()) { // Clear all textures
				b.setStyle("-fx-background-image: none;" 
						 + "-fx-background-color: transparent;");
				((Button) b).setBorder(null);
		}
		

		for (ChessPiece p : game.whitePieces) {
			String s = "";
			
			if (p instanceof Pawn) 
				s = "/de/hdm_stuttgart/chessgame/GUIresources/Pawn_white.png";
			if (p instanceof Queen) 
				s = "/de/hdm_stuttgart/chessgame/GUIresources/Queen_white.png";
			if (p instanceof King) 
				s = "/de/hdm_stuttgart/chessgame/GUIresources/King_white.png";
			if (p instanceof Bishop) 
				s = "/de/hdm_stuttgart/chessgame/GUIresources/Bishop_white.png";
			if (p instanceof Knight) 
				s = "/de/hdm_stuttgart/chessgame/GUIresources/Knight_white.png";
			if (p instanceof Rook) 
				s = "/de/hdm_stuttgart/chessgame/GUIresources/Rook_white.png";

			int index = p.getY() * 8 + p.getX();
			int size = 50;
			center.getChildren().get(index).setStyle(
							"-fx-background-color: transparent;" 
							+ "-fx-background-image: url('" + s + "'); "
							+ "-fx-background-size: "+ size + "px;" 
							+ "-fx-background-repeat: no-repeat;"
							+ "-fx-background-position: center;");
		}

		for (ChessPiece p : game.blackPieces) {
			String s = "";

			if (p instanceof Pawn) 
				s = "/de/hdm_stuttgart/chessgame/GUIresources/Pawn_black.png";
			if (p instanceof Queen) 
				s = "/de/hdm_stuttgart/chessgame/GUIresources/Queen_black.png";
			if (p instanceof King)
				s = "/de/hdm_stuttgart/chessgame/GUIresources/King_black.png";
			if (p instanceof Bishop)
				s = "/de/hdm_stuttgart/chessgame/GUIresources/Bishop_black.png";
			if (p instanceof Knight)
				s = "/de/hdm_stuttgart/chessgame/GUIresources/Knight_black.png";
			if (p instanceof Rook) 	
				s = "/de/hdm_stuttgart/chessgame/GUIresources/Rook_black.png";

			int index = p.getY() * 8 + p.getX();
			center.getChildren().get(index)
					.setStyle("-fx-background-color: transparent;" + "-fx-background-image: url('" + s + "'); "
							+ "-fx-background-size: 50px;" + "-fx-background-repeat: no-repeat;"
							+ "-fx-background-position: center;");
		}

		if (game.selectedPiece != null) {
			int indexSelected = game.selectedPiece.getY() * 8 + game.selectedPiece.getX();
			((Button) center.getChildren().get(indexSelected))
					.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
							new BorderWidths(6, 6, 6, 6, false, false, false, false))));
		}		
	}
}