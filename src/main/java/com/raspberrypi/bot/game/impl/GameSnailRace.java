package com.raspberrypi.bot.game.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Future;

import com.raspberrypi.bot.game.Game;
import com.raspberrypi.bot.game.entity.impl.EntitySnail;

import de.btobastian.javacord.entities.message.Message;

public class GameSnailRace extends Game {
	
	private List<EntitySnail> snails = new ArrayList<EntitySnail>();
	
	private final int raceEndPosition;
	
	private final int extraRacers;
	
	private EntitySnail raceWinner;
	
	public GameSnailRace(Message callMessage, int raceEndPosition, int extraRacers) {
		super("Snail Races", callMessage, 1000);
		this.raceEndPosition = raceEndPosition;
		this.extraRacers = extraRacers;
	}

	@Override
	public void update() throws Exception{
		for(EntitySnail snail : this.snails){
			snail.update();
			
			if(snail.getPositionSlot() >= this.raceEndPosition){
				this.setCompleted(true);
				this.raceWinner = snail;
				break;
			}
		}
	}

	@Override
	public void draw(Future<Message> gameDisplayMessage) throws Exception{
		String map = ("**THE SNAIL RACES HAVE BEGUN** : TRACK 0");
		for(EntitySnail snail : this.snails){
			map += ("\n");
			for(int spaceForPosition = 0; spaceForPosition < snail.getPositionSlot(); spaceForPosition++){
				map += (" ");
			}
			map += (":snail: -> :shrimp: **" + snail.getName() + "**(score " + snail.getScore() + ")\n");
			
			for(int spaceForPosition = 0; spaceForPosition < this.raceEndPosition; spaceForPosition++){
				map += (" ");
			}
			map += (":flag_black:");
		}
		
		gameDisplayMessage.get().edit(map);
	}

	@Override
	protected void create(Future<Message> gameDisplayMessage) {
		this.snails.add(new EntitySnail(this.getCallMessage().getAuthor().getName()));
		
		for(int extraRacersAdded = 0; extraRacersAdded < extraRacers; extraRacersAdded++){
			this.snails.add(new EntitySnail("Racer #" + new Random().nextInt(100)));
		}
		
		this.getCallMessage().reply("The snail races have begun!" + "\nThis race ends at position " + this.raceEndPosition);
	}

	@Override
	public void endGame(Future<Message> gameDisplayMessage) {
		this.getCallMessage().reply("The race has ended! The winner: **" + this.raceWinner.getName() +"**! Here's your prize: :love_letter: (non-openable)");
	}

}