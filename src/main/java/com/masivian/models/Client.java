package com.masivian.models;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("Client")
public class Client {
		
	@Id
	private long id;
	private BigDecimal money;
	
	public Client(long id, BigDecimal money) {
		this.id = id;
		this.money = money;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	
	public void addMoney(BigDecimal money){
		this.money = this.money.add(money);
	}

	public void subtractMoney(BigDecimal money){
		this.money = this.money.subtract(money);
	}
}
