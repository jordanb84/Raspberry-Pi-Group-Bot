package com.raspberrypi.bot.command.impl;

import com.raspberrypi.bot.command.Command;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.Invite;
import de.btobastian.javacord.entities.message.Message;

public class CommandInvites extends Command {

	public CommandInvites() {
		super("invites", new String[] {}, "Displays the amount of people you've invited to the server via your permanent invitation link");
	}

	@Override
	public void onUse(DiscordAPI api, String[] arguments, Message callMessage) throws Exception {
		Invite[] serverInvites = (callMessage.getChannelReceiver().getServer().getInvites().get());
		
		int invitedUsers = 0;
		for(Invite invite : serverInvites){
			if(invite.getCreator().getId().equals(callMessage.getAuthor().getId())){
				invitedUsers += invite.getUses();
			}
		}
		
		callMessage.reply("You have invited **" + invitedUsers + "** people! :smile:");
	}

}
