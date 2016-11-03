package com.raspberrypi.bot;

public class Launcher {
	
	public static void main(String[] args){
		System.out.println("Connecting with token " + args[0]);
		Bot discordBot = new Bot(args[0]);
	}

}
