package es.practicando.apirest.tortucata.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.practicando.apirest.tortucata.model.Tortuga;
import es.practicando.apirest.tortucata.repository.TortugaRepository;

@Service
public class TortugaServiceImpl implements TortugaService{
	
	@Autowired
	private TortugaRepository tortugaRepo;

	@Override
	@Transactional(readOnly = true)
	public Iterable<Tortuga> findAll() {
		
		return tortugaRepo.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Tortuga> findAll(Pageable paginado) {
		
		return tortugaRepo.findAll(paginado);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Tortuga> findById(Long id) {
		
		return tortugaRepo.findById(id);
	}

	@Override
	@Transactional
	public Tortuga save(Tortuga tortuga) {
		
		return tortugaRepo.save(tortuga);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		
		tortugaRepo.deleteById(id);
	}
	
	
	

}
