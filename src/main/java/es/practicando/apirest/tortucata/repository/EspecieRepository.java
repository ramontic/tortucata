package es.practicando.apirest.tortucata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.practicando.apirest.tortucata.model.Especie;

@Repository
public interface EspecieRepository extends JpaRepository<Especie, Long>{

}
