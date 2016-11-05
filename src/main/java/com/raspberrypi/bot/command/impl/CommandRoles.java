package com.raspberrypi.bot.command.impl;
import java.util.Arrays;
import java.util.Collection;

import com.raspberrypi.bot.command.Command;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.entities.permissions.Role;
import de.btobastian.javacord.entities.Server;

public class CommandRoles extends Command {
	protected static String[] roles = {"C++", "Java", "JavaScript", "Python"};
	
	public CommandRoles(){
		super("role", roles, "Adds/deletes you to a specific role.", "role add <role> or role delete <role>");
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
	
		Server s;
		
		String progRole = arguments[2];
		
		s = callMessage.getChannelReceiver().getServer();
		
		Collection<Role> roles = s.getRoles();
		
		if(arguments[1].equals("add")){
		
			for(String roleinList: this.roles){
				
				if(roleinList.equals(progRole)){
					for(Role role: roles){
						if(role.getName().equals(roleinList)){
							role.addUser(callMessage.getAuthor());
							callMessage.reply("Successfully added to: " + roleinList);
							break;
						}
					}
					
					break;
				}
				
			}
			
		} else if(arguments[1].equals("delete")){
			for(String roleinList: this.roles){
				if(roleinList.equals(progRole)){
					for(Role role: roles){
						if(role.getName().equals(roleinList)){
							role.removeUser(callMessage.getAuthor());
							callMessage.reply("Successfully removed from: " + roleinList);
							break;
						}
					}
					break;
				}
				
			}
		}
		
		if(!contain(this.roles, progRole)) callMessage.reply("You can't do that with this role.");
	
	}
}
