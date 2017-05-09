package de.hdm_stuttgart.chessgame;

import org.apache.logging.log4j.*;

public class Main
{
	private static Logger log = LogManager.getLogger(Main.class);
	
	public static void main(String[] args)
	{
		//log.info("Test log message. Use this instead Sysout");
		Game chess = new Game();

		chess.printBoard();
	}
}