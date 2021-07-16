package es.practicando.apirest.tortucata.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.practicando.apirest.tortucata.model.Role;

public interface RoleService {
	
	public Iterable<Role> findAll();
	
	public Page<Role> findAll(Pageable pageable);
	
	public Optional<Role> findById(Long id);
	
	public Role save(Role role);
	
	public void deleteById(Long id);
}
