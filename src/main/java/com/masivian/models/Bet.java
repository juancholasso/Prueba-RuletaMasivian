package com.masivian.models;

import java.math.BigDecimal;

public class Bet {

	public static BigDecimal MAX_AMOUNT = new BigDecimal("10000");

	private long id;
	private long idClient;
	private BigDecimal amount;
	private String color;
	private int number;

	public Bet(long id, long idClient, BigDecimal amount, String color, int number) {
		this.id = id;
		this.idClient = idClient;
		this.amount = amount;
		this.color = color;
		this.number = number;
	}
	
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}


	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public long getId() {
		return id;
	}

	public void setId(long idBet) {
		this.id = idBet;
	}

	public long getIdClient() {
		return idClient;
	}

	public void setIdClient(long idClient) {
		this.idClient = idClient;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) throws Exception {
		this.amount = amount;
		if(this.amount.compareTo(MAX_AMOUNT) == 1)
			throw new Exception("Amount cannot exceed $"+MAX_AMOUNT);
	}
}
