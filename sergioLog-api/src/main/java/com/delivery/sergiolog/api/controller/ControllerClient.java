package com.delivery.sergiolog.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.delivery.sergiolog.domain.model.Client;
import com.delivery.sergiolog.domain.repository.ClientRepository;
import com.delivery.sergiolog.domain.service.CatalogClientService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/clients")
public class ControllerClient {
	

	private ClientRepository clientRepository;
	private CatalogClientService catalogClientService;
	
	@GetMapping
	public List<Client> list() {
		return clientRepository.findAll();
		
	}
	
	@GetMapping("/{clientId}")
	public ResponseEntity<Client> listClientById(@PathVariable Long clientId) {
		
		return clientRepository.findById(clientId)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
		

	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Client createClient(@Valid @RequestBody Client client) {
		//return clientRepository.save(client);
		return catalogClientService.saveClient(client);

	}
	
	@PutMapping("/{clientId}")
	public ResponseEntity<Client> updateClient(@PathVariable Long clientId, @Valid @RequestBody Client client){
		
		if(!clientRepository.existsById(clientId)) {
			return ResponseEntity.notFound().build();
		}
		
		client.setId(clientId);
		
		//client = clientRepository.save(client);
		
		client = catalogClientService.saveClient(client);
		
		return ResponseEntity.ok(client);
		
	}
	
	@DeleteMapping("/{clientId}")
	public ResponseEntity<Void> deleteClient(@PathVariable Long clientId){
		
		if(!clientRepository.existsById(clientId)) {
			return ResponseEntity.notFound().build();
		}
		
		catalogClientService.deleteClient(clientId);
		
		return ResponseEntity.noContent().build();
				
	}

}
