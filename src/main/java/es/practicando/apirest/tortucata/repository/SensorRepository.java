package es.practicando.apirest.tortucata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.practicando.apirest.tortucata.model.Sensor;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Long>{}
