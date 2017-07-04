package de.hdm_stuttgart.chessgame;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.logging.log4j.Level;

/**
 * Logs the current state of the game to console.
 */
public class GameStateLoggerTask implements Runnable
{
	private static AtomicInteger taskIDCounter = new AtomicInteger(1);
	private final long startTime = System.currentTimeMillis();
	private final Game game;
	
	/**
	 * @param parent The game to log
	 */
	public GameStateLoggerTask(Game parent)
	{
		this.game = parent;
	}
	
	@Override
	public void run()
	{
		Thread.currentThread().setName("GameStateLoggerTask-" +  taskIDCounter.getAndIncrement());
		
		while (!game.isFinished())
		{
			Main.getLog().info("Current game state: ");
			synchronized (game.threadLock)
			{
				Main.getLog().info("Turn: " + game.getMove());
				Main.getLog().info("White pieces left - " + game.whitePieces.size()); // "Gleichzeitiger Zugriff"
				Main.getLog().info("Black pieces left - " + game.blackPieces.size());
			}
			Main.getLog().info("Seconds elapsed since game start - " + ((System.currentTimeMillis() - startTime) / 1000.0D));
			
			try
			{
				Thread.sleep(10000L);
			} catch (InterruptedException e)
			{
				Main.getLog().fatal("GameStateLoggerTask was interrupted.", e);
			}
		}
	}
}