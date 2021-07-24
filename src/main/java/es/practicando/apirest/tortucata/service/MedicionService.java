package es.practicando.apirest.tortucata.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.practicando.apirest.tortucata.model.Medicion;


public interface MedicionService {
	
	public Iterable<Medicion> findAll();
	
	public Page<Medicion> findAll(Pageable pageable);
	
	public Optional<Medicion> findById(Long id);
	
	public Medicion save(Medicion medicion);
	
	public void deleteById(Long id);
}
