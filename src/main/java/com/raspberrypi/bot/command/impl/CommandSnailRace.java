package com.raspberrypi.bot.command.impl;

import com.raspberrypi.bot.command.Command;
import com.raspberrypi.bot.game.impl.GameSnailRace;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;

public class CommandSnailRace extends Command {

	public CommandSnailRace() {
		super("race", new String[] {}, "Run a snail race against bots", "race");
	}

	@Override
	public void onUse(DiscordAPI api, String[] arguments, Message callMessage) throws Exception {
		GameSnailRace snailRace = new GameSnailRace(callMessage, 35, 10);
		snailRace.start();
	}

}

