package es.practicando.apirest.tortucata.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.practicando.apirest.tortucata.model.Especie;
import es.practicando.apirest.tortucata.repository.EspecieRepository;

@Service
public class EspecieServiceImpl implements EspecieService {
	
	@Autowired
	private EspecieRepository especieRepo;

	@Override
	@Transactional(readOnly = true)
	public Iterable<Especie> findAll() {
		
		return especieRepo.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Especie> findAll(Pageable paginado) {
		
		return especieRepo.findAll(paginado);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Especie> findById(Long id) {
		
		return especieRepo.findById(id);
	}

	@Override
	@Transactional
	public Especie save(Especie especie) {
		
		return especieRepo.save(especie);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		
		especieRepo.deleteById(id);
	}

	
	
	
	

}
