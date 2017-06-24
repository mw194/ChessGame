package de.hdm_stuttgart.chessgame;

import org.apache.logging.log4j.*;
import de.hdm_stuttgart.chessgame.display.GUIDisplay;
import javafx.application.Application;

/**
 * Entry point for the program
 */
@SuppressWarnings("restriction")
public class Main 
{
	private static Logger log = LogManager.getLogger(Main.class);

	public static void main(String[] args)
	{
		Application.launch(GUIDisplay.class, args);
	}

	/**
	 * @return The global logger
	 */
	public static Logger getLog()
	{
		return log;
	}
}