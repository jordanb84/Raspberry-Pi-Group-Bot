package com.raspberrypi.bot.command;

public enum CommandSection {
	General, Misc
	;
	
	public static String nameFor(CommandSection section){
		return section.name();
	}
}
