package es.practicando.apirest.tortucata.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.practicando.apirest.tortucata.model.Usuario;
import es.practicando.apirest.tortucata.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService{
	
	@Autowired
	private UsuarioRepository usuarioRepo;

	@Override
	@Transactional(readOnly = true)
	public Iterable<Usuario> findAll() {
		
		return usuarioRepo.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Usuario> findAll(Pageable paginado) {
		
		return usuarioRepo.findAll(paginado);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Usuario> findById(Long id) {
		
		return usuarioRepo.findById(id);
	}

	@Override
	@Transactional
	public Usuario save(Usuario usuario) {
		
		return usuarioRepo.save(usuario);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		
		usuarioRepo.deleteById(id);
	}
}
