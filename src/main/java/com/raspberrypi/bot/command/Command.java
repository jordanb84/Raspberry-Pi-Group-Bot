package com.raspberrypi.bot.command;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;

public abstract class Command {

	private final String identifier;
	
	private final String[] arguments;
	
	private final String description;
	
	public Command(String identifier, String[] arguments, String description){
		this.identifier = identifier;
		this.arguments = arguments;
		this.description = description;
	}

	/**
	 * Called when the command is executed
	 * @param api API instance by which the command was executed
	 * @param arguments Arguments passed for the command usage
	 * @param callMessage The message by which the command was called
	 */
	public abstract void onUse(DiscordAPI api, String[] arguments, Message callMessage);
	
	public String getIdentifier() {
		return identifier;
	}

	public String getDescription() {
		return description;
	}

	public String[] getArguments() {
		return arguments;
	}
	
}
