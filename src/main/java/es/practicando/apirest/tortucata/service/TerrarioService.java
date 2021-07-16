package es.practicando.apirest.tortucata.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.practicando.apirest.tortucata.model.Terrario;

public interface TerrarioService {
	
	public Iterable<Terrario> findAll();
	
	public Page<Terrario> findAll(Pageable pageable);
	
	public Optional<Terrario> findById(Long id);
	
	public Terrario save(Terrario terrario);
	
	public void deleteById(Long id);
}
