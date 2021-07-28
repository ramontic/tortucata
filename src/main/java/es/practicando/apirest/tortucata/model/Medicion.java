package es.practicando.apirest.tortucata.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.persistence.ForeignKey;

import lombok.Data;


@Data
@Entity
@Table(name="medicion")
public class Medicion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "temperatura", nullable = true)
	private Double temperatura;
	
	@Column(name = "humedad", nullable = true)
	private Double humedad;
	
	@NotNull(message = "{medicion.timeStamp.notEmpty}")
	@Column(name = "timeStamp", nullable = false)
	private LocalDateTime timeStamp = LocalDateTime.now();

	// DB Relationships
	@ManyToOne(optional = false)
    @JoinColumn(name="sensor_id", referencedColumnName = "id", nullable = false, foreignKey=@ForeignKey(name = "Fk_medicion_sensorId"))
	@Valid
    private Sensor sensor;
}
