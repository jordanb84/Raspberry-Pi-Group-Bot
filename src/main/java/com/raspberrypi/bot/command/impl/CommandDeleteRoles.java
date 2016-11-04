package com.raspberrypi.bot.command.impl;
import java.util.Collection;

import com.raspberrypi.bot.command.Command;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.entities.permissions.Role;
import de.btobastian.javacord.entities.Server;

public class CommandDeleteRoles extends Command {
	public CommandDeleteRoles(){
		super("roledelete", new String[] {}, "Deletes you from a specifc role.");
	}
	
	@Override
	public void onUse(DiscordAPI api, String[] arguments, Message callMessage) throws Exception{
		
		//support for multi-word commands
		
		Server s;
		s = callMessage.getChannelReceiver().getServer();
		String role = "";
		
		int i = 0;
		
		for(String a: arguments){
			//we don't want to include the command itself
			if(i != 0){
				role = role + a + " ";
			}
			i++;
		}
		
		//remove the extra space at the end
		role = role.substring(0, role.length()-1);
		
		Collection<Role> roles = s.getRoles();
		for(Role b : roles ){
			if(b.getName().equals("C++")){
				b.addUser(callMessage.getAuthor());
				callMessage.reply("Success! You were removed from " + role + ".");
			}else if(b.getName().equals("Java")){
				b.addUser(callMessage.getAuthor());
				callMessage.reply("Success! You were removed from " + role + ".");
				break;
			}else if(b.getName().equals("JavaScript")){
				b.addUser(callMessage.getAuthor());
				callMessage.reply("Success! You were removed from " + role + ".");
				break;
			}else if(b.getName().equals("Python")){
				b.addUser(callMessage.getAuthor());
				callMessage.reply("Success! You were removed from " + role + ".");
				break;
			}
		}
		
	
	}
}
