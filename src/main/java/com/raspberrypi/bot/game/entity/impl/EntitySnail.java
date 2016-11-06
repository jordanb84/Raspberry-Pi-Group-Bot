package com.raspberrypi.bot.game.entity.impl;

import java.util.concurrent.ThreadLocalRandom;

import com.raspberrypi.bot.game.entity.Entity;

public class EntitySnail extends Entity {

	protected int score;
	
	public EntitySnail(String name) {
		super(name);
	}

	@Override
	public void update() {
		int moveAmount = (ThreadLocalRandom.current().nextInt(-1, 4));
		this.score += moveAmount;
		
		this.move(moveAmount);
	}
	
	public int getScore(){
		return this.score;
	}
	
}