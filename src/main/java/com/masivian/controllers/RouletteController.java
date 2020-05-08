package com.masivian.controllers;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import com.masivian.models.Bet;
import com.masivian.models.Roulette;
import com.masivian.repositories.RouletteRepository;

@Controller
public class RouletteController{
	
	@Autowired(required = true)
    private RouletteRepository rouletteRepository;
	
	public Roulette create() throws Exception{
		try {
			Roulette roulette = new Roulette(System.nanoTime(),false);
			return rouletteRepository.save(roulette);
		}
		catch (Exception e) {
			throw e;
		}	
	}
	
	public boolean openRoulette(long idRoulette) {
		try {
			Roulette roulette = rouletteRepository.findById(idRoulette).get();
			if(!roulette.isOpen()){
				roulette.setOpen(true);
				rouletteRepository.save(roulette);
				return true;
			}
			return false;
		}
		catch (Exception e) {
			throw e;
		}	
	}
	
	public Bet createBet(long idRoulette, long idClient, String color, int number, BigDecimal amount) throws Exception {
		if(color != null)
			if(isValidColor(color))
				return betOnColor(idRoulette, idClient, color, amount);
			else
				throw new Exception("Invalid color");
		else
			if(isValidNumber(number))
				return betOnNumber(idRoulette, idClient, number, amount);
			else
				throw new Exception("Invalid number");
	}

	private Bet betOnColor(long idRoulette, long idClient, String color, BigDecimal amount) throws Exception {
		try {
			if(amount.compareTo(Bet.MAX_AMOUNT) == 1)
				throw new Exception("Amount cannot exceed $"+Bet.MAX_AMOUNT);
			
			Roulette roulette = rouletteRepository.findById(idRoulette).get();
			if(roulette.isOpen()){
				Bet bet = new Bet(idRoulette, idClient, amount, color, -1);
				ArrayList<Bet> bets = roulette.getBets();
				bets.add(bet);
				roulette.setBets(bets);
			    return bet;
			}
			throw new Exception("The roulette is closed");
		}
		catch (Exception e) {
			throw e;
		}	
	}

	private Bet betOnNumber(long idRoulette, long idClient, int number, BigDecimal amount) throws Exception {
		try {
			if(amount.compareTo(Bet.MAX_AMOUNT) == 1)
				throw new Exception("Amount cannot exceed $"+Bet.MAX_AMOUNT);
			
			Roulette roulette = rouletteRepository.findById(idRoulette).get();
			if(roulette.isOpen()){
				Bet bet = new Bet(System.nanoTime(), idClient, amount, null, number);
				ArrayList<Bet> bets = roulette.getBets();
				bets.add(bet);
				roulette.setBets(bets);
			    return bet;
			}
			throw new Exception("The roulette is closed");
		}
		catch (Exception e) {
			throw e;
		}	
	}

	public boolean isValidColor(String color) {
		if(color.equals("rojo") || color.equals("negro")) 
			return true;
		return false;
	}
	
	public boolean isValidNumber(int number) {
		return ((number != -1) && (number >= 0 && number <= 36));
	}
}
