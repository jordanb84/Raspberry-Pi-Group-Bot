package com.raspberrypi.bot.command.impl;
import java.util.Collection;

import com.raspberrypi.bot.command.Command;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.entities.permissions.Role;
import de.btobastian.javacord.entities.Server;

public class CommandRoles extends Command {
	public CommandRoles(){
		super("role", new String[] {}, "Adds/deletes you to a specifc role.");
	}
	
	@Override
	public void onUse(DiscordAPI api, String[] arguments, Message callMessage) throws Exception {
	
		Server s;
		
		String[] listofRoles = {"C++", "Java", "JavaScript", "Python"};
		
		String progRole = arguments[2];
		
		s = callMessage.getChannelReceiver().getServer();
		
		Collection<Role> roles = s.getRoles();
		
		System.out.println(arguments[1].equals("add"));
		
		if(arguments[1].equals("add")){
		
			for(String roleinList: listofRoles){
				if(roleinList.equals(progRole)){
					System.out.println("Found something");
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
			for(String roleinList: listofRoles){
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
		
	
	}
}
