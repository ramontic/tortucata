package es.practicando.apirest.tortucata.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


import lombok.Data;

@Data
@Entity
@Table(name = "sensor")
public class Sensor implements Serializable{
	
	private static final long serialVersionUID = -6345838945800734626L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "modelo", length = 30, nullable = false)
	private String modelo;
	
	@Column(name = "estado", nullable = false)
	@Enumerated(value = EnumType.STRING)
	private Estado estado;

	public enum Estado {
		ACTIVO,
		INACTIVO
	}
	
	// DB Relationships
	@JsonIgnore
	@OneToMany(
			mappedBy = "sensor",
			cascade = CascadeType.ALL
	)		
    private List<Medicion> mediciones;
	
	@JsonIgnore
	@OneToMany(mappedBy = "sensor",
			cascade = CascadeType.ALL)
    private List<Uso> terrarios;
	
	
	
}
