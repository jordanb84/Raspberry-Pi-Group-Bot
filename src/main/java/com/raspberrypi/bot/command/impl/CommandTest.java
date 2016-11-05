package com.raspberrypi.bot.command.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.raspberrypi.bot.command.Command;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;

public class CommandTest extends Command {

	public CommandTest() {
		super("test", new String[] {}, "Test command", "test");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onUse(DiscordAPI api, String[] arguments, Message callMessage) throws Exception {
		SnailRace snailRace = new SnailRace(callMessage, 1000, 10, 3);
		snailRace.start();
	}

}

class SnailRace extends Thread {
	
	private final Message callMessage;
	private final Future<Message> raceMessage;
	
	private List<EntitySnail> snails = new ArrayList<EntitySnail>();
	
	private final int tickInterval;
	
	private boolean raceFinished;
	private final int raceEndPosition;
	
	private EntitySnail raceWinner;
	
	SnailRace(Message callMessage, int tickInterval, int raceEndPosition, int extraRacers){
		this.callMessage = callMessage;
		this.tickInterval = tickInterval;
		this.raceEndPosition = raceEndPosition;
		
		this.snails.add(new EntitySnail(callMessage.getAuthor().getName()));
		
		for(int extraRacersAdded = 0; extraRacersAdded < extraRacers; extraRacersAdded++){
			this.snails.add(new EntitySnail("Racer #" + new Random().nextInt(100)));
		}
		
		callMessage.reply("The snail races have begun!" + "\nThis race ends at position " + this.raceEndPosition);
		this.raceMessage = callMessage.reply("|||RACE TRACK INITIATING|||");
	}
	
	@Override
	public void run(){
		while(!this.raceFinished){
			try {
				Thread.sleep(this.tickInterval);
				
				this.tick();
				this.draw();
			} catch (InterruptedException | ExecutionException e) {
				this.callMessage.reply("Oops, there was an error performing the snail race, and it has stopped! :slight_frown:");
				e.printStackTrace();
			}
		}
		
		this.callMessage.reply("The race has ended! The winner: **" + this.raceWinner.getName() +"**! Here's your prize: :love_letter: (non-openable)");
	}
	
	public void tick(){
		for(EntitySnail snail : this.snails){
			snail.tick();
			
			if(snail.getPositionSlot() >= this.raceEndPosition){
				this.raceFinished = true;
				this.raceWinner = snail;
				break;
			}
		}
	}
	
	public void draw() throws InterruptedException, ExecutionException{
		String map = ("**THE SNAIL RACES HAVE BEGUN** : TRACK 0");
		for(EntitySnail snail : this.snails){
			map += ("\n");
			for(int spaceForPosition = 0; spaceForPosition < snail.getPositionSlot(); spaceForPosition++){
				map += (" ");
			}
			map += (":snail: **" + snail.getName() + "**");
		}
		
		this.raceMessage.get().edit(map);
	}
}

abstract class Entity {
	
	private int positionSlot;
	
	protected final String name;
	
	Entity(String name){
		this.name = name;
	}
	
	abstract void tick();
	
	public int getPositionSlot(){
		return this.positionSlot;
	}
	
	public String getName(){
		return this.name;
	}
	
	protected void move(int moveSlots){
		this.positionSlot += (moveSlots);
	}
}

class EntitySnail extends Entity {

	EntitySnail(String name) {
		super(name);
	}

	@Override
	void tick() {
		this.move(new Random().nextInt(3));
	}
	
}
