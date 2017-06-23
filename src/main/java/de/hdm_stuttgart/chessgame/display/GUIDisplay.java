package de.hdm_stuttgart.chessgame.display;

import de.hdm_stuttgart.chessgame.Game;
import de.hdm_stuttgart.chessgame.Main;
import de.hdm_stuttgart.chessgame.pieces.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
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

/**
 * Class which visualizes the game (Game.class) via JavaFX
 * Implements the IDisplay Interface
 */
@SuppressWarnings("restriction")
public class GUIDisplay extends Application implements IDisplay
{
	private Game game; //Game instance which is shown
	private StringProperty statusString; //Feedback status for the player
	private StringProperty currentString; //Shows the current player
	private StringProperty moveString; //Shows the number of moves
	private Stage gameStage; //Stage of the main game
	private GridPane board; //Pane of the board
	
	/**
	 * Entry point of the GUIDisplay class
	 * Startmenu of the game is visualized
	 * A new game can be started in this stage 
	 * You can quit the application in this stage
	 */
	@Override
	public void start(Stage primaryStage)
	{			
		Main.setCurrentDisplay(this);
		AnchorPane menuPane = new AnchorPane();
		menuPane.setId("startscreen");
		
		/////STARTBUTTON/////////////////////////
        Button buttonStart = new Button("Start new Game");
        buttonStart.setAlignment(Pos.TOP_LEFT);
        buttonStart.setPrefWidth(150);
        AnchorPane.setLeftAnchor(buttonStart, 10.0);
        AnchorPane.setBottomAnchor(buttonStart, 90.0);
        buttonStart.getStyleClass().add("StartButton");   
		buttonStart.setOnAction(event -> {
			primaryStage.close(); // start menu window is closed
			game = new Game(this); // new game is instantiated
			game(); // start Stage for game visualization
		});
		/////OPTIONSBUTTON/////////////////////////      
        Button buttonOptionen = new Button("Options");
        buttonOptionen.setAlignment(Pos.TOP_LEFT);
        buttonOptionen.setPrefWidth(150);
		AnchorPane.setLeftAnchor(buttonOptionen, 10.0);
		AnchorPane.setBottomAnchor(buttonOptionen, 50.0);
		buttonOptionen.getStyleClass().add("StartButton");
		final ImageView gif = new ImageView("de/hdm_stuttgart/chessgame/GUIresources/banana.gif");
		gif.setFitWidth(280);
		gif.setFitHeight(280);
		buttonOptionen.setOnAction(event -> {
			for (Node n : menuPane.getChildren()) {
				if (n == gif) {
					menuPane.getChildren().remove(n);
					return;
				}
			}
			menuPane.getChildren().add(gif);
		});
		/////QUITBUTTON///////////////////////////          
        Button buttonEnd = new Button("Quit");
        buttonEnd.setAlignment(Pos.TOP_LEFT);
        buttonEnd.setPrefWidth(150);
        AnchorPane.setLeftAnchor(buttonEnd, 10.0);
        AnchorPane.setBottomAnchor(buttonEnd, 10.0);
        buttonEnd.getStyleClass().add("StartButton");
		buttonEnd.setOnAction(event -> {
		        Platform.exit(); // Close Application
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
	 * This method visualizes the game logic of chess game with a new stage
	 * On the top are option buttons
	 * In the middle is the game board
	 * On the bottom are some status information
	 */
	private void game() {
		gameStage = new Stage(); //Stage of the main game

		BorderPane canvas = new BorderPane();	

		//Toolbar//////////////////////
		Button buttonFile = new Button("File");
		buttonFile.getStyleClass().add("toolbar");
		buttonFile.setAlignment(Pos.TOP_LEFT);
		
		Button buttonOptions = new Button("Options");
		buttonOptions.getStyleClass().add("toolbar");
		buttonOptions.setAlignment(Pos.TOP_LEFT);
		
		Button buttonHelp = new Button("Help");
		buttonHelp.getStyleClass().add("toolbar");
		buttonHelp.setAlignment(Pos.TOP_LEFT);
		
		ToolBar top = new ToolBar(buttonFile/*, buttonOptions, buttonHelp*/);
		
		//Chessboard///////////////////
		BorderPane boardWithBorders = new BorderPane();
		board = new GridPane(); // actual board
		board.setId("board");
		
		HBox topSide = new HBox(); //top border
		topSide.setPrefHeight(20);
		topSide.setId("top");
		
		VBox leftSide = new VBox(); // left border
		leftSide.setId("left");
		leftSide.setPrefWidth(20);
		
		boardWithBorders.setTop(topSide);
		boardWithBorders.setLeft(leftSide);
		boardWithBorders.setCenter(board);
		
		//Bottom////////////////////
		VBox bottom = new VBox(); // Bottom info space

		statusString = new SimpleStringProperty();
		currentString = new SimpleStringProperty();
		moveString = new SimpleStringProperty();

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
		
		
		//add cells to the board
		for (int row = 0; row < 8; row++) {
			for (int column = 0; column < 8; column++) {
				Button button = new Button();
				button.setPrefWidth(75);
				button.setPrefHeight(75);
				button.setStyle("-fx-background-color: transparent;");
				int x = column;
				int y = row;

				// everytime a cell is clicked, the board is updated
				button.setOnAction(mouseClick -> {
					// calculate game logic
					game.select(x, y);
					// process visual updated
					processUpdate(game.getBoard(), game.getCurrentTeam());
				});

				board.add(button, row, column); // Insert cells in the board
			}
		}

		canvas.setTop(top);
		canvas.setCenter(boardWithBorders);
		canvas.setBottom(bottom);

		// first visual update of the game
		processUpdate(game.getBoard(), game.getCurrentTeam());
		
		Scene scene = new Scene(canvas);
		scene.getStylesheets().addAll(this.getClass().getResource("/de/hdm_stuttgart/chessgame/GUIresources/style.css").toExternalForm());
		gameStage.setTitle("Chess");
		gameStage.setScene(scene);
		gameStage.setWidth(620);
		gameStage.setHeight(800);
		gameStage.setResizable(false);
		gameStage.show();
	}
		
	/**
	 * This method is executed after every single mouseclick on the chessboard
	 * The textures of the board get rearranged
	 * Furthermore the status string are updated
	 */
	@Override
	public void processUpdate(ChessPiece[][] gameBoard, EnumPieceColor currentTeam) 
	{
		// Game Object created after "start"
		if (game == null) {
			return;
		}

		// Status strings are updated
		currentString.set("Current Team: " + game.getCurrentTeam().toString());
		moveString.set("Move counter: " + game.getMove());

		// Clear all textures of the buttons
		for (Node b : board.getChildren()) { 
				b.setStyle("-fx-background-image: none;" 
						 + "-fx-background-color: transparent;");
				((Button) b).setBorder(null);
		}
		

		// Add the new Texture according to their position and type
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

			int index = p.getY() * 8 + p.getX(); // evaluate position
			int size = 50;
			board.getChildren().get(index).setStyle(
							"-fx-background-color: transparent;" 
							+ "-fx-background-image: url('" + s + "'); "
							+ "-fx-background-size: "+ size + "px;" 
							+ "-fx-background-repeat: no-repeat;"
							+ "-fx-background-position: center;");
		}

		synchronized (game.threadLock)
		{
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
				board.getChildren().get(index)
						.setStyle("-fx-background-color: transparent;" + "-fx-background-image: url('" + s + "'); "
								+ "-fx-background-size: 50px;" + "-fx-background-repeat: no-repeat;"
								+ "-fx-background-position: center;");
			}
		}


		if (game.selectedPiece != null) {
			  // add border for the selected piece
			int indexSelected = game.selectedPiece.getY() * 8 + game.selectedPiece.getX();
			((Button) board.getChildren().get(indexSelected))
					.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
							new BorderWidths(6, 6, 6, 6, false, false, false, false))));
		}
	}

	/**
	 * If the black or white king is beaten this method is executed
	 * Depending on the winning team a window appears with the winner
	 * If you press 'ok' the game window closes and you get back to main menu
	 */
	@Override
	public void processCheckmate(EnumPieceColor winner)
	{
		Stage winStage = new Stage();
		VBox root = new VBox();
		root.setAlignment(Pos.CENTER);
		root.setSpacing(10);
		
		//Label for the winning team
		Label winLabel = new Label(winner+ " Wins!");
		winLabel.setStyle("-fx-font-family: 'didot'; -fx-font-size: 3em;");
		
		//back to menu button
		Button winButton = new Button("Back to menu");
		winButton.setOnAction(event -> {
			gameStage.close(); //close old game stage
			winStage.close(); //close 'game over' stage
			start(new Stage()); //main menu with new stage 
		});
		
		root.getChildren().addAll(winLabel, winButton);

		Scene scene = new Scene(root, 270, 100);
		winStage.setScene(scene);
		winStage.setResizable(false);
		winStage.show();
	}
		
	@Override
	public void processCheckPreupdate(boolean whiteInCheck, boolean blackInCheck)
	{
		// Check status
	}
	
	@Override
	public void processInvalidAction(String message)
	{
		// Problem Exists Between Keyboard And Chair
		Main.logInfo(message);
	}

	@Override
	public void processLogMessage(String message)
	{
		statusString.set(message);
	}

}