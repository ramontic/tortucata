package es.practicando.apirest.tortucata.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.practicando.apirest.tortucata.model.Tortuga;

public interface TortugaService {
	
	public Iterable<Tortuga> findAll();
	
	public Page<Tortuga> findAll(Pageable pageable);
	
	public Optional<Tortuga> findById(Long id);
	
	public Optional<Tortuga> findByNombre(String name);
	
	public Tortuga save(Tortuga tortuga);
	
	public void deleteById(Long id);
}
