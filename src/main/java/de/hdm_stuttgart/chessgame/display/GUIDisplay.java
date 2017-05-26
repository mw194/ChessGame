package de.hdm_stuttgart.chessgame.display;

import javax.swing.JLabel;

import de.hdm_stuttgart.chessgame.Game;
import de.hdm_stuttgart.chessgame.pieces.*;
import de.hdm_stuttgart.chessgame.GUIresources.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
        buttonOptionen.autosize();
        buttonOptionen.setLayoutX(250-75);
        buttonOptionen.setLayoutY(230);
        buttonOptionen.setId("button");
        root.getChildren().add(buttonOptionen);
        
        Button buttonEnd = new Button("Quit");
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
		
		Stage gameStage = new Stage();
		primaryStage.close();
		try {
			game(gameStage, game);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void game(Stage gameStage, Game game) throws Exception {
		Pane canvas = new Pane();
		Group pieces = new Group();
		
		Image board = new Image("/de/hdm_stuttgart/chessgame/GUIresources/chessboard.png");
		ImageView iv1 = new ImageView();
		iv1.setImage(board);
		canvas.getChildren().add(iv1);

		update(canvas, game, pieces);


		for (int row = 0; row < 8; row++) {
			for (int column = 0; column < 8; column++) {
				Button button = new Button();
				button.setLayoutX(column * 75);
				button.setLayoutY(row * 75);
				button.setPrefWidth(75);
				button.setPrefHeight(75);
				button.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
						game.select((int) button.getLayoutY() / 75, (int) button.getLayoutX() / 75);
						pieces.getChildren().clear();
						update(canvas, game, pieces);
					}
				});
				canvas.getChildren().add(button);
			}
		}

         Scene scene = new Scene(canvas);

         scene.getStylesheets().addAll(this.getClass().getResource("/de/hdm_stuttgart/chessgame/GUIresources/style.css").toExternalForm());      

         gameStage.setTitle("Chess");
         gameStage.setScene(scene); 
         gameStage.setWidth(600);
         gameStage.setHeight(800);
         gameStage.show(); 
	}
	
	public void update(Pane canvas, Game game, Group pieces) {
	
		Label current = new Label("Current player: " + game.getCurrentTeam().toString());
		current.setTranslateY(600);
		pieces.getChildren().add(current);

		for (ChessPiece p : game.whitePieces) {
			Image piece = null;
			ImageView x = new ImageView();

			if (p instanceof Pawn) {
				piece = new Image("/de/hdm_stuttgart/chessgame/GUIresources/Pawn_white.png");
			}
			if (p instanceof Queen) {
				piece = new Image("/de/hdm_stuttgart/chessgame/GUIresources/Queen_white.png");
			}
			if (p instanceof King) {
				piece = new Image("/de/hdm_stuttgart/chessgame/GUIresources/King_white.png");
			}
			if (p instanceof Bishop) {
				piece = new Image("/de/hdm_stuttgart/chessgame/GUIresources/Bishop_white.png");
			}
			if (p instanceof Knight) {
				piece = new Image("/de/hdm_stuttgart/chessgame/GUIresources/Knight_white.png");
			}
			if (p instanceof Rook) {
				piece = new Image("/de/hdm_stuttgart/chessgame/GUIresources/Rook_white.png");
			}
			
			x.setLayoutX(p.getY()*75);
			x.setLayoutY(p.getX()*75);
			x.setFitHeight(75);
			x.setFitWidth(75);
			x.setImage(piece);
			pieces.getChildren().add(x);
		}

		for (ChessPiece p : game.blackPieces) {
			Image piece = null;
			ImageView x = new ImageView();

			if (p instanceof Pawn) {
				piece = new Image("/de/hdm_stuttgart/chessgame/GUIresources/Pawn_black.png");
			}
			if (p instanceof Queen) {
				piece = new Image("/de/hdm_stuttgart/chessgame/GUIresources/Queen_black.png");
			}
			if (p instanceof King) {
				piece = new Image("/de/hdm_stuttgart/chessgame/GUIresources/King_black.png");
			}
			if (p instanceof Bishop) {
				piece = new Image("/de/hdm_stuttgart/chessgame/GUIresources/Bishop_black.png");
			}
			if (p instanceof Knight) {
				piece = new Image("/de/hdm_stuttgart/chessgame/GUIresources/Knight_black.png");
			}
			if (p instanceof Rook) {
				piece = new Image("/de/hdm_stuttgart/chessgame/GUIresources/Rook_black.png");
			}
			
			x.setLayoutX(p.getY()*75);
			x.setLayoutY(p.getX()*75);
			x.setFitHeight(75);
			x.setFitWidth(75);
			x.setImage(piece);
			pieces.getChildren().add(x);
		}
		canvas.getChildren().add(pieces);
	}

}