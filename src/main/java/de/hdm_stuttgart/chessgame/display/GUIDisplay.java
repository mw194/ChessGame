package de.hdm_stuttgart.chessgame.display;

import javax.swing.JLabel;

import de.hdm_stuttgart.chessgame.Game;
import de.hdm_stuttgart.chessgame.pieces.*;
import de.hdm_stuttgart.chessgame.GUIresources.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

@SuppressWarnings("restriction")
public class GUIDisplay extends Application implements IDisplay
{
	Game game = new Game(this);

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
		AnchorPane root = new AnchorPane();
		root.setId("startscreen");
		Scene scene = new Scene(root, 280, 280);

		Label title = new Label("Chess");
		title.setId("title");
		AnchorPane.setLeftAnchor(title, 10.0);
		root.getChildren().add(title);
		
        Button buttonStart = new Button("Start new Game");
        buttonStart.setAlignment(Pos.TOP_LEFT);
        buttonStart.setPrefWidth(150);
        AnchorPane.setLeftAnchor(buttonStart, 10.0);
        AnchorPane.setBottomAnchor(buttonStart, 90.0);
        buttonStart.getStyleClass().add("StartButton");   
		buttonStart.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				Stage gameStage = new Stage();
				primaryStage.close();
				try {
					game(gameStage, game);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});       
        root.getChildren().add(buttonStart);
        
        
        Button buttonOptionen = new Button("Options");
        buttonOptionen.setAlignment(Pos.TOP_LEFT);
        buttonOptionen.setPrefWidth(150);
        AnchorPane.setLeftAnchor(buttonOptionen, 10.0);
        AnchorPane.setBottomAnchor(buttonOptionen, 50.0);
        buttonOptionen.getStyleClass().add("StartButton");
        root.getChildren().add(buttonOptionen);
        
        
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
        root.getChildren().add(buttonEnd);
        
        	
        scene.getStylesheets().addAll(this.getClass().getResource("/de/hdm_stuttgart/chessgame/GUIresources/style.css").toExternalForm());   
        primaryStage.setTitle("Chess");
        primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public void game(Stage gameStage, Game game) throws Exception {
		BorderPane canvas = new BorderPane();	

		//Toolbar////////////////
		Button file = new Button("File");
		file.getStyleClass().add("toolbar");
		file.setAlignment(Pos.TOP_LEFT);
		Button options = new Button("Options");
		options.getStyleClass().add("toolbar");
		options.setAlignment(Pos.TOP_LEFT);
		Button help = new Button("Help");
		help.getStyleClass().add("toolbar");
		help.setAlignment(Pos.TOP_LEFT);
		ToolBar top = new ToolBar(file, options, help);
		//Chessboard/////////////
		GridPane center = new GridPane();
		center.setId("board");
		//Bottombar//////////////
		Label bot = new Label("v. 2.405.2a alpha");
		bot.setStyle("-fx-font: .7em courier;");

		
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
						update(center, game);
					}
				});			
				center.add(button, row, column);
			}
		}
		
		canvas.setTop(top);
		canvas.setCenter(center);
		canvas.setBottom(new ToolBar(bot));

		update(center, game);
		
		Scene scene = new Scene(canvas);
		scene.getStylesheets().addAll(
		this.getClass().getResource("/de/hdm_stuttgart/chessgame/GUIresources/style.css").toExternalForm());
		gameStage.setTitle("Chess");
		gameStage.setScene(scene);
		gameStage.setWidth(600);
		gameStage.setHeight(800);
		gameStage.setResizable(true);
		gameStage.show();
	}
	
	public void update(GridPane center, Game game) {
		for (Node button : center.getChildren()) { //Clear all textures
			button.setStyle("-fx-background-image: none;"
						  + "-fx-background-color: transparent;");
			((Button)button).setBorder(null);
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
			center.getChildren().get(index).setStyle(
							"-fx-background-color: transparent;" 
							+ "-fx-background-image: url('" + s + "'); "
							+ "-fx-background-size: 50px;" 
							+ "-fx-background-repeat: no-repeat;"
							+ "-fx-background-position: center;");
			
			
			
		}
		
		if (game.selectedPiece != null) {
			int indexSelected = game.selectedPiece.getY() * 8 + game.selectedPiece.getX();
			((Button)center.getChildren().get(indexSelected)).setBorder(new Border(new BorderStroke(Color.BLACK, 
		            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(5, 5, 5, 5, false, false, false, false))));
		}
	}

}