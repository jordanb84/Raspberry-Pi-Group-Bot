package com.raspberrypi.bot.events;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.listener.message.MessageCreateListener;

public class MyMessageListener implements MessageCreateListener {

	  @Override
	  public void onMessageCreate(DiscordAPI api, Message message) {
	    System.out.println(message.getAuthor().getName() + ": " + message.getContent());
	  }


	}
