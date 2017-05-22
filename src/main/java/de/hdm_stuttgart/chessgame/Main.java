package de.hdm_stuttgart.chessgame;

import org.apache.logging.log4j.*;

import de.hdm_stuttgart.chessgame.display.ConsoleDisplay;

public class Main
{
	private static Logger log = LogManager.getLogger(Main.class);

	public static void main(String[] args)
	{
		//log.info("Test log message. Use this instead Sysout");
		Game chess = new Game(new ConsoleDisplay());
		chess.select(6, 0); // Simulates mouse click
		chess.select(4, 0);
	}


	public static Logger getLog()
	{
		return log;
	}
}