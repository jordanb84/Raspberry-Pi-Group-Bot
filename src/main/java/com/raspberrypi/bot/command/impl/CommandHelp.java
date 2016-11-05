package com.raspberrypi.bot.command.impl;

import java.util.Arrays;

import com.raspberrypi.bot.command.Command;
import com.raspberrypi.bot.command.CommandManager;
import com.raspberrypi.bot.command.CommandSection;

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
		String helpMessage = ("```Markdown\n");
		
		for(CommandSection section : CommandSection.values()){
			helpMessage += ("#in " + section.name() + ":\n\n");
			
			for(Command command : this.commandManager.getCommandsForSection(section)){
				helpMessage += (command.getIdentifier() + ": " + command.getDescription());
				
				if(command.getArguments().length > 0){
					helpMessage += ("\nArguments: " + Arrays.toString(command.getArguments()));
				}
				
				helpMessage += ("\n\n");
			}
			
			helpMessage += ("\n\n");
		}
		
		helpMessage += ("```\n");
		
		callMessage.reply(helpMessage);
	}

}
