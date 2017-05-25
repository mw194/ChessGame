package de.hdm_stuttgart.chessgame;

import org.apache.logging.log4j.*;

import de.hdm_stuttgart.chessgame.display.ConsoleDisplay;
import de.hdm_stuttgart.chessgame.display.GUIDisplay;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main 
{
	private static Logger log = LogManager.getLogger(Main.class);

	public static void main(String[] args)
	{
		//log.info("Test log message. Use this instead Sysout");
		GUIDisplay javaFX = new GUIDisplay();
		Game chess = new Game(javaFX);
		Application.launch(javaFX.getClass(), args);
	}


	public static Logger getLog()
	{
		return log;
	}

}