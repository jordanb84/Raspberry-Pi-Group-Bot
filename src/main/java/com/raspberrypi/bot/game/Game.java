package com.raspberrypi.bot.game;

import java.util.concurrent.Future;

import de.btobastian.javacord.entities.message.Message;

public abstract class Game extends Thread {

	/** The message which initiated the game **/
	private Message callMessage;
	
	/** The frequency of game updates in milliseconds **/
	private int updateInterval;
	
	/** Whether the game has been completed **/
	private boolean completed;
	
	/** Message used to represent the game **/
	private Future<Message> gameDisplayMessage;
	
	/**
	 * @param callMessage {@link Game#callMessage}
	 * @param updateInterval {@link Game#updateInterval}
	 * 
	 */
	public Game(String gameName, Message callMessage, int updateInterval){
		this.callMessage = callMessage;
		this.updateInterval = updateInterval;
		
		this.gameDisplayMessage = callMessage.reply("Started game **" + gameName + "**!");
	}
	
	@Override
	public void run(){
		this.create(this.gameDisplayMessage);
		
		while(!this.isCompleted()){
			try {
				Thread.sleep(this.updateInterval);
				
				this.update();
				this.draw(this.gameDisplayMessage);
			} catch (Exception uncaughtGameBreakingException) {
				uncaughtGameBreakingException.printStackTrace();
				this.setCompleted(true);
			}
		}
		
		this.endGame(this.gameDisplayMessage);
	}
	
	/**
	 * Performs the game's update logic
	 * @throws For any game-breaking exceptions that should cause the game to end
	 */
	public abstract void update() throws Exception;
	
	/**
	 * Performs the game's drawing logic, eg generating or modifying
	 * a message to display to the user which represents the game
	 * @throws For any game-breaking exceptions that should cause the game to end
	 */
	public abstract void draw(Future<Message> gameDisplayMessage) throws Exception;

	/**
	 * Called when the game has been started
	 */
	protected abstract void create(Future<Message> gameDisplayMessage);
	
	/**
	 * Called when the game has been ended
	 */
	public abstract void endGame(Future<Message> gameDisplayMessage);
	
	public Message getCallMessage() {
		return callMessage;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	
}
