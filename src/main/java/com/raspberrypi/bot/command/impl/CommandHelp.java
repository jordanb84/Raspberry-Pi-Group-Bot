package com.raspberrypi.bot.command.impl;

import java.util.Arrays;

import com.raspberrypi.bot.command.Command;
import com.raspberrypi.bot.command.CommandManager;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;

public class CommandHelp extends Command {

	private final CommandManager commandManager;
	
	public CommandHelp(CommandManager commandManager) {
		super("help", new String[] {}, "Displays all commands");
		this.commandManager = commandManager;
	}

	@Override
	public void onUse(DiscordAPI api, String[] arguments, Message callMessage) throws Exception {
		String helpMessage = ("```\n");
		
		for(Command command : this.commandManager.getCommands()){
			helpMessage += (command.getIdentifier() + ": " + command.getDescription());
			
			if(command.getArguments().length > 0){
				helpMessage += ("\nArguments: " + Arrays.toString(command.getArguments()));
			}
			
			helpMessage += ("\n");
		}
		
		helpMessage += ("\n```");
		
		callMessage.reply(helpMessage);
	}

}
