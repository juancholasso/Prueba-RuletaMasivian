package com.masivian.services;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.masivian.models.Client;
import com.masivian.repositories.ClientRepository;

@Service
public class ClientService{
	
	@Autowired(required = true)
    private ClientRepository clientRepository;
	
	public Client create(long id, BigDecimal money) throws Exception{
		try {
			Client client = new Client(id, money);
			return clientRepository.save(client);
		}
		catch (Exception e) {
			throw e;
		}	
	}
	
	public Client addMoney(long id, BigDecimal money) throws Exception {
		try {
			clientExist(id);
			Client client = clientRepository.findById(id).get();
			client.addMoney(money);
			return clientRepository.save(client);
		}
		catch (Exception e) {
			throw e;
		}	
	}

	public Client subtractMoney(long id, BigDecimal money) throws Exception {
		try {
			clientExist(id);
			Client client = clientRepository.findById(id).get();
			client.subtractMoney(money);
			return clientRepository.save(client);
		}
		catch (Exception e) {
			throw e;
		}	
	}
	
	public Client find(long id) throws Exception {
		try {
			clientExist(id);
			return clientRepository.findById(id).get();
		}
		catch (Exception e) {
			throw e;
		}	
	}
	
	public boolean hasMoney(long id, BigDecimal amount) throws Exception {
		try {
			clientExist(id);
			Client client = clientRepository.findById(id).get();
			if(client.getMoney().compareTo(amount) == 1 || client.getMoney().compareTo(amount) == 0) 
				return true;
			return false;
		}
		catch(Exception e) {
			throw e;
		}
	}
	
	public boolean clientExist(long id) throws Exception {
		if(clientRepository.existsById(id))
			return true;
		throw new Exception("The client don't exist");
	}
}
