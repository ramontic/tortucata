package es.practicando.apirest.tortucata.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.practicando.apirest.tortucata.model.Tortuga;

@Repository
public interface TortugaRepository extends JpaRepository<Tortuga, Long>{

	Optional<Tortuga> findByNombre(String nameTortuga);}
