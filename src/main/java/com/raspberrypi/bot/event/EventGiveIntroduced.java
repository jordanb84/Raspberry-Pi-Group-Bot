package com.raspberrypi.bot.event;


import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.entities.permissions.Role;
import de.btobastian.javacord.listener.message.MessageCreateListener;

public class EventGiveIntroduced implements MessageCreateListener {
    private static String introChannelID = "217477797319409676"; //RPI channel
    //private static String introChannelID = "244594423906697217"; //test channel
    
    Role introducedRole;

	@Override
	public void onMessageCreate(DiscordAPI API, Message message) {
		if (message.getChannelReceiver().getId().equals(introChannelID)){
			// Get introduced role object
			for(Role role: message.getChannelReceiver().getServer().getRoles()){
				if (role.getName().equals("Introduced")){
					introducedRole = role;
				}
	          	
	        } 
			// assign role to message author
			System.out.println(introducedRole.getName());
			introducedRole.addUser(message.getAuthor());
		}
		
	}

}
