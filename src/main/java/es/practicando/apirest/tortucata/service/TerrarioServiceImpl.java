package es.practicando.apirest.tortucata.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.practicando.apirest.tortucata.model.Terrario;
import es.practicando.apirest.tortucata.repository.TerrarioRepository;

@Service
public class TerrarioServiceImpl implements TerrarioService{
	
	@Autowired
	private TerrarioRepository terrarioRepo;

	@Override
	@Transactional(readOnly = true)
	public Iterable<Terrario> findAll() {
		
		return terrarioRepo.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Terrario> findAll(Pageable paginado) {
		
		return terrarioRepo.findAll(paginado);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Terrario> findById(Long id) {
		
		return terrarioRepo.findById(id);
	}

	@Override
	@Transactional
	public Terrario save(Terrario terrario) {
		
		return terrarioRepo.save(terrario);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		
		terrarioRepo.deleteById(id);
	}
	
	
	

}
