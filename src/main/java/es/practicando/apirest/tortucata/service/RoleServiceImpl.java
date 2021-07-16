package es.practicando.apirest.tortucata.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.practicando.apirest.tortucata.model.Role;
import es.practicando.apirest.tortucata.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	private RoleRepository roleRepo;

	@Override
	@Transactional(readOnly = true)
	public Iterable<Role> findAll() {
		
		return roleRepo.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Role> findAll(Pageable paginado) {
		
		return roleRepo.findAll(paginado);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Role> findById(Long id) {
		
		return roleRepo.findById(id);
	}

	@Override
	@Transactional
	public Role save(Role role) {
		
		return roleRepo.save(role);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		
		roleRepo.deleteById(id);
	}
	
	
	

}
