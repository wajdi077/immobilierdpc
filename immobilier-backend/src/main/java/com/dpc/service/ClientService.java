package com.dpc.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dpc.domain.Artisant;
import com.dpc.domain.Client;
import com.dpc.dto.ClientArtisanDto;
import com.dpc.repository.ClientRepository;

@Service
public class ClientService {
	@Autowired 
	
	private ClientRepository clientRepository;

    public List<Client> getAllclient(){
  
    return (List<Client>) clientRepository.findAll();

}
    public Client createClient(Client client) {
        return this.clientRepository.save(client);
	}
    public Client getclientById(Long id) {
		return this.clientRepository.findById(id);
		
	}
    public void deleteClient(Long id) {
        this.clientRepository.delete(id);
    }	
    public Client updateClient(Client client) {
        return this.clientRepository.save(client);
    }
    public boolean clientExist( Long id) {
        return clientRepository.exists(id);
}
	public Client findByEmail(String email) {
		return this.clientRepository.findByEmail(email);
	}
    
}