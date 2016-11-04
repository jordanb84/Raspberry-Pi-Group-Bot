package com.raspberrypi.bot.command.impl;
import com.raspberrypi.bot.command.Command;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;

public class CommandRoles extends Command {
	public CommandRoles(){
		super("roleadd", new String[] {}, "Adds you to a specifc role.");
	}
	
	@Override
	public void onUse(DiscordAPI api, String[] arguments, Message callMessage) throws Exception{
		String b = callMessage.getChannelReceiver().getName();
		
		callMessage.reply(b);
	
	}
}
