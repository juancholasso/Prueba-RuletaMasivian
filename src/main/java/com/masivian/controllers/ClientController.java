package com.masivian.controllers;

import java.math.BigDecimal;
import java.text.Bidi;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masivian.models.Client;
import com.masivian.models.Roulette;
import com.masivian.services.ClientService;
import com.masivian.services.RouletteService;

@Service
@RestController
@RequestMapping("/api/client")
public class ClientController {
	
	@Autowired
	private ClientService clientController;
	
	@PostMapping("/create")
	public ResponseEntity<JSONObject> createClient(@RequestBody JSONObject body)  {
		JSONObject jsonResponse = new JSONObject();
		try {
			long idClient = Long.parseLong(body.get("id").toString());
			BigDecimal money = new BigDecimal(body.get("money").toString());
			Client client = this.clientController.create(idClient, money);
			jsonResponse.put("client", client);
			return new ResponseEntity<JSONObject>(jsonResponse, HttpStatus.CREATED);
		} 
		catch (Exception e) {
			jsonResponse.put("error", e.getMessage());
			return new ResponseEntity<JSONObject>(jsonResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<JSONObject> getClient(@PathVariable("id") Long id)  {
		JSONObject jsonResponse = new JSONObject();
		try {
			Client client = this.clientController.find(id);
			jsonResponse.put("client", client);
			return new ResponseEntity<JSONObject>(jsonResponse, HttpStatus.OK);
		} 
		catch (Exception e) {
			jsonResponse.put("error", e.getMessage());
			return new ResponseEntity<JSONObject>(jsonResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/{id}/addmoney")
	public ResponseEntity<JSONObject> addMoney(@PathVariable("id") Long id, @RequestBody JSONObject body)  {
		JSONObject jsonResponse = new JSONObject();
		try {
			BigDecimal money = new BigDecimal(body.get("money").toString());
			Client client = this.clientController.addMoney(id, money);
			jsonResponse.put("client", client);
			return new ResponseEntity<JSONObject>(jsonResponse, HttpStatus.OK);
		} 
		catch (Exception e) {
			jsonResponse.put("error", e.getMessage());
			return new ResponseEntity<JSONObject>(jsonResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
