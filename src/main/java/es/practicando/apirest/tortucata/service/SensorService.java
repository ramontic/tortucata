package es.practicando.apirest.tortucata.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.practicando.apirest.tortucata.model.Sensor;


public interface SensorService {
	
	public Iterable<Sensor> findAll();
	
	public Page<Sensor> findAll(Pageable pageable);
	
	public Optional<Sensor> findById(Long id);
	
	public Sensor save(Sensor sensor);
	
	public void deleteById(Long id);
}
