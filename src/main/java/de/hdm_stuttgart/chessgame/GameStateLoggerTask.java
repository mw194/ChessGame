package de.hdm_stuttgart.chessgame;

public class GameStateLoggerTask implements Runnable
{
	private static int taskIDCounter = 1;
	private final long startTime = System.currentTimeMillis();
	private final Game game;
	
	public GameStateLoggerTask(Game parent)
	{
		this.game = parent;
	}
	
	@Override
	public void run()
	{
		Thread.currentThread().setName("GameStateLoggerTask-" + taskIDCounter++);
		
		while (!game.isFinished())
		{
			Main.getLog().info("Current game state: ");
			synchronized (game.threadLock)
			{
				Main.getLog().info("Turn: " + game.getMove());
				Main.getLog().info("White pieces left - " + game.whitePieces.size());
				Main.getLog().info("Black pieces left - " + game.blackPieces.size());
			}
			Main.getLog().info("Seconds elapsed since game start - " + ((System.currentTimeMillis() - startTime) / 1000.0D));
			
			try
			{
				Thread.sleep(10000L);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
}