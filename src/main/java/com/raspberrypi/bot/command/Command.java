package com.raspberrypi.bot.command;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;

public abstract class Command {

	private final String identifier;
	
	private final String[] arguments;
	
	private final String description;
	
	private final String usage;
	
	private CommandSection section;
	
	public Command(String identifier, String[] arguments, String description, String usage){
		this.identifier = identifier;
		this.arguments = arguments;
		this.description = description;
		this.usage = usage;
		this.section = CommandSection.General;
	}

	/**
	 * Called when the command is executed
	 * @param api API instance by which the command was executed
	 * @param arguments Arguments passed for the command usage
	 * @param callMessage The message by which the command was called
	 * @throws For all exceptions that the command won't deal with
	 */
	public abstract void onUse(DiscordAPI api, String[] arguments, Message callMessage) throws Exception;
	
	public String getIdentifier() {
		return identifier;
	}

	public String getDescription() {
		return description;
	}

	public String[] getArguments() {
		return arguments;
	}
	
	protected void setSection(CommandSection section){
		this.section = section;
	}
	
	public CommandSection getSection(){
		return this.section;
	}

	public String getUsage() {
		return usage;
	}
	
	public static String addElementsTogether(String[] list){
		String finalstr = "";
		
		for(int i = 1; i <= list.length; i++){
			if(i != list.length){
				finalstr += list[i-1] + ", ";
			} else {
				finalstr += list[i-1];
			}
		}
		
		return finalstr;
	}
}
