package com.raspberrypi.bot.command.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.raspberrypi.bot.command.Command;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;

public class CommandPlaying extends Command {
    
	public static final List<String> allowedUsers = 
		    Collections.unmodifiableList(Arrays.asList(
		    		"120193744707256320","191285845494333440",
		    		"135496683009081345","135483608491229184",
		    		"135521715378847744","206052435163086850"));
	
	public CommandPlaying() {
		super("playing", new String[] {}, "Sets the bot's playing message", "playing");
	}
	@Override
	public void onUse(DiscordAPI api, String[] arguments, Message callMessage) throws Exception {
	    if (allowedUsers.contains(callMessage.getAuthor().getId())) {
	        api.setGame(callMessage.getContent().replace(",playing ", ""));	
	    }
	}

}
