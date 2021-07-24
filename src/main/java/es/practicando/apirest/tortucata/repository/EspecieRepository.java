package es.practicando.apirest.tortucata.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.practicando.apirest.tortucata.model.Especie;
import es.practicando.apirest.tortucata.model.EspecieId;

@Repository
public interface EspecieRepository extends JpaRepository<Especie, EspecieId>{
	
	public Optional<Especie> findByGeneroAndEspecieAndSubespecie(String genero, String especie, String subespecie);

	public void deleteByGeneroAndEspecieAndSubespecie(String genero, String especie, String subespecie);
}
