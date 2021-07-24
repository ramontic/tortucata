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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "terrario")
public class Terrario implements Serializable{

	private static final long serialVersionUID = 4665468467024909189L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 3, name = "max_miembros", nullable = true)
	private int maxMiembros;
	
	private int tempMin, tempMax, humMin, humMax, horasLuzDirecta;
	
	//private int tempRegMin, tempRegMax, humRegMin, humRegMax, horasRegLuzDirecta; // Variables resultado de cálculos entre Registros de parámetro en el tiempo en un determinado objeto Terrario
	
	@Column(name = "tipoTerrario", nullable = false)
	@Enumerated(value = EnumType.STRING)
	private TipoTerrario tipoTerrario;
	
	@Column(length = 100, name = "info_tecnica", nullable = true)
	private String infoTecnica;
	
	@Column(name = "esAtivo", nullable = false)
	private Boolean esActivo = true;
	
	private enum TipoTerrario {
		INTERIOR_CERRADO,INTERIOR_ABIERTO,EXTERIOR,CUARENTENA
	}
	
	// DB Relationships
	
	@ManyToOne(optional = false)
    @JoinColumn(name="usuario_id", referencedColumnName = "id", nullable = false)
    private Usuario usuario;
	
	@JsonIgnore
	@OneToMany(
			mappedBy = "terrario", 
			cascade = CascadeType.ALL
	)		
    private List<Sensor> sensores;
	
	@JsonIgnore
	@OneToMany(
			mappedBy = "terrario"
	)		
    private List<Tortuga> tortugas;
}
