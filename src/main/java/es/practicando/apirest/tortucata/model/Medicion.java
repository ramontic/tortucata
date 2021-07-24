package es.practicando.apirest.tortucata.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;


@Data
@Entity
@Table(name="medicion")
public class Medicion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Double temperatura;
	
	private Double humedad;
	
	
	private LocalDateTime timeStamp = LocalDateTime.now();

	// DB Relationships
	@ManyToOne(optional = false)
    @JoinColumn(name="sensor_id", referencedColumnName = "id", nullable = false)
    private Sensor sensor;
}
