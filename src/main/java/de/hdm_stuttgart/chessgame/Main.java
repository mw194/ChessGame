package de.hdm_stuttgart.chessgame;

import org.apache.logging.log4j.*;
import de.hdm_stuttgart.chessgame.display.GUIDisplay;
import de.hdm_stuttgart.chessgame.display.IDisplay;
import javafx.application.Application;

public class Main 
{
	private static Logger log = LogManager.getLogger(Main.class);
	
	@SuppressWarnings("restriction")
	public static void main(String[] args)
	{
		Application.launch(GUIDisplay.class, args);
	}

	public static Logger getLog()
	{
		return log;
	}
}