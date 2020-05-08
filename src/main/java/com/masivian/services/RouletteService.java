package com.masivian.services;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.masivian.models.Bet;
import com.masivian.models.Roulette;
import com.masivian.repositories.RouletteRepository;

@Service
public class RouletteService{
	
	@Autowired(required = true)
    private RouletteRepository rouletteRepository;
	
	/**
	 * Create a new Roulette
	 * @return Roulette Object
	 * @throws Exception
	 */
	@Transactional
	public Roulette create() throws Exception{
		try {
			Roulette roulette = new Roulette(System.nanoTime(),false);
			return rouletteRepository.save(roulette);
		}
		catch (Exception e) {
			throw e;
		}	
	}
	
	/**
	 * Return list of Roulettes
	 * @return List
	 */
	public Iterable<Roulette> list() {
		try {
			return rouletteRepository.findAll();
		}
		catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * Change state to open of a Roulette
	 * @param idRoulette 
	 * @return Boolean if transaction is success
	 */
	@Transactional
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
	
	/**
	 * Change to state to close of a Roulette 
	 * @param idRoulette
	 * @return Roulette Object
	 */
	@Transactional
	public Roulette closeRoulette(long idRoulette) {
		try {
			Roulette roulette = rouletteRepository.findById(idRoulette).get();
			roulette.setOpen(false);
			return rouletteRepository.save(roulette);
		}
		catch (Exception e) {
			throw e;
		}	
	}
	
	/**
	 * Create a new Bet
	 * @param idRoulette
	 * @param idClient
	 * @param color (Rojo o negro)
	 * @param number (0 to 36)
	 * @param amount (<10000)
	 * @return Bet Object
	 * @throws Exception
	 */
	@Transactional
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

	/**
	 * Create a Bet - Option Color
	 */
	private Bet betOnColor(long idRoulette, long idClient, String color, BigDecimal amount) throws Exception {
		try {
			if(amount.compareTo(Bet.MAX_AMOUNT) == 1)
				throw new Exception("Amount cannot exceed $"+Bet.MAX_AMOUNT);
			
			Roulette roulette = rouletteRepository.findById(idRoulette).get();
			if(roulette.isOpen()){
				Bet bet = new Bet(System.nanoTime(), idClient, amount, color, -1);
				ArrayList<Bet> bets = roulette.getBets();
				bets.add(bet);
				roulette.setBets(bets);
				rouletteRepository.save(roulette);
			    return bet;
			}
			throw new Exception("The roulette is closed");
		}
		catch (Exception e) {
			throw e;
		}	
	}

	/**
	 * Create a Bet - Option Number
	 */
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
				rouletteRepository.save(roulette);
			    return bet;
			}
			throw new Exception("The roulette is closed");
		}
		catch (Exception e) {
			throw e;
		}	
	}

	/**
	 * Return true if color is rojo or negro
	 * @param color
	 * @return Boolean
	 */
	public boolean isValidColor(String color) {
		if(color.equals("rojo") || color.equals("negro")) 
			return true;
		return false;
	}
	
	/**
	 * Return true if number is diferent to -1 and is between 0 and 36
	 * @param number
	 * @return Boolean
	 */
	public boolean isValidNumber(int number) {
		return ((number != -1) && (number >= 0 && number <= 36));
	}
}
