package com.raspberrypi.bot.command.impl;

import java.util.Arrays;

import com.raspberrypi.bot.command.Command;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;

/**
 * Bare bones example command
 * @author oprsec
 *
 */
public class CommandExample extends Command {

	public CommandExample() {
		super("ping", new String[] {}, "Example ping command");
	}

	@Override
	public void onUse(DiscordAPI api, String[] arguments, Message callMessage) {
		callMessage.reply("Pong! Arguments: " + Arrays.toString(arguments));
	}

}
