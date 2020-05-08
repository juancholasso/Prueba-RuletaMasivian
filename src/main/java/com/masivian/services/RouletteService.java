package com.masivian.services;

import java.math.BigDecimal;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masivian.controllers.ClientController;
import com.masivian.controllers.RouletteController;
import com.masivian.models.Bet;
import com.masivian.models.Client;
import com.masivian.models.Roulette;

@Service
@RestController
@RequestMapping("/api/roulette")
public class RouletteService {
	
	@Autowired
	private RouletteController rouletteController;
	@Autowired
	private ClientController clientController;

	@GetMapping("/create")
	public ResponseEntity<JSONObject> createRoulette()  {
		JSONObject jsonResponse = new JSONObject();
		try {
			Roulette roulette = this.rouletteController.create();
			jsonResponse.put("id", roulette.getId());
			return new ResponseEntity<JSONObject>(jsonResponse, HttpStatus.CREATED);
		} 
		catch (Exception e) {
			jsonResponse.put("error", e.getMessage());
			return new ResponseEntity<JSONObject>(jsonResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{id}/open")
	public ResponseEntity<JSONObject> openRoulette(@PathVariable("id") Long id){
		JSONObject jsonResponse = new JSONObject();
		try {
			long idRoulette = id;
			if(rouletteController.openRoulette(idRoulette)) {
				jsonResponse.put("status", "success");
				return new ResponseEntity<JSONObject>(jsonResponse, HttpStatus.OK);
			}
			jsonResponse.put("status", "denied");
			return new ResponseEntity<JSONObject>(jsonResponse, HttpStatus.BAD_REQUEST);
			
		} catch (Exception e) {
			jsonResponse.put("error", e.getMessage());
			return new ResponseEntity<JSONObject>(jsonResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{id}/close")
	public ResponseEntity<JSONObject> closeRoulette(@PathVariable("id") Long id){
		JSONObject jsonResponse = new JSONObject();
		try {
			long idRoulette = id;
			Roulette roulette = rouletteController.closeRoulette(idRoulette);
			jsonResponse.put("roulette", roulette);
			return new ResponseEntity<JSONObject>(jsonResponse, HttpStatus.OK);
			
		} catch (Exception e) {
			jsonResponse.put("error", e.getMessage());
			return new ResponseEntity<JSONObject>(jsonResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/{id}/bet")
	public ResponseEntity<JSONObject> createBet(@PathVariable("id") Long id, @RequestBody JSONObject body, @RequestHeader Map<String, String> headers)  {
		JSONObject jsonResponse = new JSONObject();
		try {
			long idRoulette = id;
			long idClient = Long.parseLong(headers.get("idclient"));
			String color = (body.get("color")!=null) ? body.get("color").toString() : null;
			int number = (body.get("number")!=null) ? Integer.parseInt(body.get("number").toString()) : -1;
			BigDecimal amount = new BigDecimal(body.get("amount").toString());
			
			clientController.clientExist(idClient);
			if(!clientController.hasMoney(idClient, amount)) {
				jsonResponse.put("error", "The client does not have sufficient money");
				return new ResponseEntity<JSONObject>(jsonResponse, HttpStatus.BAD_REQUEST);
			}
			
			Bet bet = rouletteController.createBet(idRoulette, idClient, color, number, amount);
			clientController.subtractMoney(idClient, amount);
			jsonResponse.put("bet", bet);
			jsonResponse.put("idRoulette", id);
			return new ResponseEntity<JSONObject>(jsonResponse, HttpStatus.CREATED);
		} 
		catch (Exception e) {
			jsonResponse.put("error", e.getMessage());
			return new ResponseEntity<JSONObject>(jsonResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/list")
	public ResponseEntity<JSONObject> listRoulettes()  {
		JSONObject jsonResponse = new JSONObject();
		try {
			Iterable<Roulette> roulette = this.rouletteController.list();
			jsonResponse.put("id", roulette);
			return new ResponseEntity<JSONObject>(jsonResponse, HttpStatus.CREATED);
		} 
		catch (Exception e) {
			jsonResponse.put("error", e.getMessage());
			return new ResponseEntity<JSONObject>(jsonResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
