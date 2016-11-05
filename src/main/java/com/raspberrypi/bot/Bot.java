package com.raspberrypi.bot;

import com.google.common.util.concurrent.FutureCallback;
import com.raspberrypi.bot.command.CommandManager;
import com.raspberrypi.bot.command.impl.CommandExample;
import com.raspberrypi.bot.command.impl.CommandHelp;
import com.raspberrypi.bot.command.impl.CommandInvites;
import com.raspberrypi.bot.command.impl.CommandRoles;
import com.raspberrypi.bot.command.impl.CommandStallman;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.Javacord;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.listener.message.MessageCreateListener;

/**
 * Represents an instance of a bot. Used to manage and create a bot
 * @author oprsec
 *
 */
public class Bot {

	private DiscordAPI api;
	
	private CommandManager commandManager;
	
	public Bot(String token){
		this.api = (Javacord.getApi(token, true));
		
		this.commandManager = new CommandManager(",");
		this.registerCommands();
		
		this.connect();
	}
	
	public void registerCommands(){
		this.commandManager.registerCommand(new CommandExample());
		this.commandManager.registerCommand(new CommandInvites());
		this.commandManager.registerCommand(new CommandRoles());
		this.commandManager.registerCommand(new CommandHelp(this.commandManager));
		this.commandManager.registerCommand(new CommandStallman());
	}
	
	/**
	 * Connect the bot to the API
	 */
	private void connect(){
		this.api.connect(new FutureCallback<DiscordAPI>(){

			public void onFailure(Throwable failureException) {
				
			}

			public void onSuccess(DiscordAPI api) {
				api.registerListener(new MessageCreateListener(){

					public void onMessageCreate(DiscordAPI api, Message queryMessage) {
						System.out.println("Executing for command " + queryMessage.getContent());
						commandManager.executeQuery(api, queryMessage);
					}
					
				});
			}

		});
	}
}
