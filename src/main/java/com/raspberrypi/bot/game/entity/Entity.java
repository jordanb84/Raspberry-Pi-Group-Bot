package com.raspberrypi.bot.game.entity;

public abstract class Entity {
	
	private int positionSlot;
	
	protected final String name;
	
	public Entity(String name){
		this.name = name;
	}
	
	public abstract void update();
	
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
