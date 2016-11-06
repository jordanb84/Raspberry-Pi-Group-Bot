package com.raspberrypi.bot.command.impl;

import java.util.Collection;

import com.raspberrypi.bot.command.Command;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.entities.permissions.Role;




public class CommandShow extends Command {
	
	public CommandShow(){
		super("show", new String[] {}, "Lists people in a role.", "show");
	}
	
	private boolean contain(String[] str, String check){
		
		for(String strinArray : str){
			if(strinArray.equals(check)){
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public void onUse(DiscordAPI api, String[] arguments, Message callMessage) throws Exception {
    Collection<Role> list = callMessage.getChannelReceiver().getServer().getRoles();
	}
	

}
