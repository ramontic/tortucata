package es.practicando.apirest.tortucata.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MiService <T> {
	
	public Iterable<?> findAll();
	
	public Page<?> findAll(Pageable pageable);
	
	public Optional<?> findById(Long id);
	
	public T save(T t);
	
	public void deleteById(Long id);

}
