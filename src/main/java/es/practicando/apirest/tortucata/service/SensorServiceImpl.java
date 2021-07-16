package es.practicando.apirest.tortucata.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.practicando.apirest.tortucata.model.Sensor;
import es.practicando.apirest.tortucata.repository.SensorRepository;

@Service
public class SensorServiceImpl implements SensorService{
	
	@Autowired
	private SensorRepository sensorRepo;

	@Override
	@Transactional(readOnly = true)
	public Iterable<Sensor> findAll() {
		
		return sensorRepo.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Sensor> findAll(Pageable paginado) {
		
		return sensorRepo.findAll(paginado);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Sensor> findById(Long id) {
		
		return sensorRepo.findById(id);
	}

	@Override
	@Transactional
	public Sensor save(Sensor sensor) {
		
		return sensorRepo.save(sensor);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		
		sensorRepo.deleteById(id);
	}
	
	
	

}
