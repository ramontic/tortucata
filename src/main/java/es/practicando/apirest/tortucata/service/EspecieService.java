package es.practicando.apirest.tortucata.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.practicando.apirest.tortucata.model.Especie;

public interface EspecieService {
	
	public Iterable<Especie> findAll();
	
	public Page<Especie> findAll(Pageable pageable);
	
	public Optional<Especie> findById(Long id);
	
	public Especie save(Especie especie);
	
	public void deleteById(Long id);
}
