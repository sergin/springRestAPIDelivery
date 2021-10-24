package com.delivery.sergiolog.domain.service;



import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.delivery.sergiolog.domain.exception.BusinessException;
import com.delivery.sergiolog.domain.model.Client;
import com.delivery.sergiolog.domain.repository.ClientRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CatalogClientService {

	private ClientRepository clientRepository;
	
	@Transactional
	public Client saveClient(Client client) {
		
		boolean emailAtUse = clientRepository.findByEmail(client.getEmail())
				.stream()
				.anyMatch(clientExistente -> !clientExistente.equals(client));
		
		if(emailAtUse) {
			throw new BusinessException("Já existe um client cadastrado com esse email.");
		}
		
		return clientRepository.save(client);
	}
	
	@Transactional
	public void deleteClient(Long idClient) {
		clientRepository.deleteById(idClient);
	}
	
}
