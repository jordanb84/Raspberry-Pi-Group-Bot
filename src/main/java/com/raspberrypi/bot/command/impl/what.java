package com.raspberrypi.bot.command.impl;

import com.raspberrypi.bot.command.Command;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.Invite;
import de.btobastian.javacord.entities.message.Message;

public class CommandInvites extends Command {

	public CommandInvites() {
		super("hi", new String[] {}, "bonjur");
	}
