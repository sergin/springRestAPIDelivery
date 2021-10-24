package com.delivery.sergiolog.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.delivery.sergiolog.domain.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

	List<Client> findByName(String name);
	List<Client> findByNameContaining(String name);
	Optional<Client> findByEmail(String email);
	
}