package es.practicando.apirest.tortucata.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 *  
 * Esta clase hace referencia a cualquier sensor que está en algún terrario, siendo propiedad de un usuario, 
 * y pudiendo estar en diferentes terrarios a lo largo del tiempo pudiendo devolver valores de temperatura y humedad,
 * horas de Luz tanto actuales como máximas y mínimas en un periodo. 
 *
 */
@Data
@Entity
@Table(name = "sensor")
public class Sensor implements Serializable{
	
	private static final long serialVersionUID = -6345838945800734626L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idSensor;
	
	@Column(name = "modelo", length = 30, nullable = false)
	private String modelo;
	
	@Column(name = "estado", nullable = false)
	@Enumerated(value = EnumType.STRING)
	private Estado estado;
	
	@Column(name = "temperatura")
	private Integer temperatura;
	
	@Column(name = "humedad")
	private Integer humedad;
	
	public enum Estado {
		ACTIVO,
		INACTIVO
	}
	
	

}
