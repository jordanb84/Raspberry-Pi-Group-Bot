package com.raspberrypi.bot.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;

/**
 * Manages a given set of commands
 * @author oprsec
 *
 */
public class CommandManager {

	private List<Command> commands = new ArrayList<Command>();
	
	private final String COMMAND_PREFIX;
	
	public CommandManager(String commandPrefix){
		this.COMMAND_PREFIX = commandPrefix;
	}

	/**
	 * Executes the command called by the given query if there is a matching command
	 */
	public void executeQuery(DiscordAPI api, Message queryMessage){
		String content = (queryMessage.getContent());
		
		if(content.startsWith(this.COMMAND_PREFIX)){
			content = content.replaceFirst(this.COMMAND_PREFIX, "");
			
			String[] argumentsIncludingCommand = (content.split(" "));
			
			for(Command command : this.commands){
				if(command.getIdentifier().equals(argumentsIncludingCommand[0])){
					try {
						command.onUse(api, argumentsIncludingCommand, queryMessage);
					} catch (Exception e) {
						queryMessage.reply("Oops, there was an error! Arguments: " + Arrays.toString(command.getArguments()));
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	/**
	 * Registers a new command for the command manager
	 */
	public void registerCommand(Command command){
		this.commands.add(command);
	}
	
	public List<Command> getCommands(){
		return this.commands;
	}
	
}
