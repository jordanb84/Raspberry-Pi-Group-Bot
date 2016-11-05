package com.raspberrypi.bot.command.impl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.raspberrypi.bot.command.Command;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;

public class CommandStallman extends Command {

	public CommandStallman() {
		super("stallman", new String[] {}, "Provides a sexy, beautiful Stallman", "stallman");
	}

	@Override
	public void onUse(DiscordAPI api, String[] arguments, Message callMessage) throws Exception {
		Document stallmanWebsite = Jsoup.connect("https://rms.sexy").get();
		
		Element sexyStallmanImage = stallmanWebsite.getElementsByTag("img").get(1);
		
		callMessage.reply(sexyStallmanImage.absUrl("src"));
	}

}
