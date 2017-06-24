package de.hdm_stuttgart.chessgame;

import org.apache.logging.log4j.*;
import de.hdm_stuttgart.chessgame.display.GUIDisplay;
import de.hdm_stuttgart.chessgame.display.IDisplay;
import javafx.application.Application;

public class Main 
{
	private static Logger log = LogManager.getLogger(Main.class);

	private static IDisplay display;
	
	@SuppressWarnings("restriction")
	public static void main(String[] args)
	{
		Application.launch(GUIDisplay.class, args);
	}

	public static Logger getLog()
	{
		return log;
	}
	
	public static void setCurrentDisplay(IDisplay g)
	{
		display = g;
	}
	
//	public static void logInfo(String message)
//	{
//		// an gui übergeben
//		display.processLogMessage(message);
//		
//		log.info(message);
//	}

}