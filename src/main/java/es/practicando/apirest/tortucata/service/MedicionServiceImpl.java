package es.practicando.apirest.tortucata.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.practicando.apirest.tortucata.model.Medicion;
import es.practicando.apirest.tortucata.repository.MedicionRepository;

@Service
public class MedicionServiceImpl implements MedicionService{
	
	@Autowired
	private MedicionRepository medicionRepo;

	@Override
	@Transactional(readOnly = true)
	public Iterable<Medicion> findAll() {
		
		return medicionRepo.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Medicion> findAll(Pageable paginado) {
		
		return medicionRepo.findAll(paginado);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Medicion> findById(Long id) {
		
		return medicionRepo.findById(id);
	}

	@Override
	@Transactional
	public Medicion save(Medicion medicion) {
		
		return medicionRepo.save(medicion);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		
		medicionRepo.deleteById(id);
	}
	
	
	

}
