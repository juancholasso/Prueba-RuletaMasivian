package com.masivian.models;

import java.io.Serializable;
import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("Roulette")
public class Roulette implements Serializable{

	@Id
	private long id;
    private boolean isOpen;
    private ArrayList<Bet> bets;
    
	public Roulette(long id, boolean isOpen) {
		this.id = id;
		this.isOpen = isOpen;
		this.bets = new ArrayList<Bet>();
	}

	public long getId() {
		return id;
	}

	public boolean isOpen() {
		return isOpen;
	}

	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}

	public ArrayList<Bet> getBets() {
		return bets;
	}

	public void setBets(ArrayList<Bet> bets) {
		this.bets = bets;
	}
   
}
