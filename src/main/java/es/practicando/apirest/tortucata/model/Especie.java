package es.practicando.apirest.tortucata.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@IdClass(EspecieId.class)
@Table(name = "especie")
@SequenceGenerator(
	    name="EspecieSeq",
	    sequenceName = "Especies_Seq",
	    initialValue = 1, 
	    allocationSize = 10
	)
public class Especie implements Serializable{

	private static final long serialVersionUID = -6775359414880120322L;

	@Id
	@Column(length = 50, nullable = false)
	private String genero;
	
	@Id
	@Column(length = 50, nullable = false)
	private String especie;
	
	@Id
	@Column(length = 50, nullable = false)
	private String	subespecie; 
	
	@Column(length = 50)
	private String	nombreComun;
	
	@Column(length = 50)
	private String	distGeo;
	
	
	private Integer tempIdealMin, tempIdealMax, humIdealMin, humIdealMax, longEsperadaMacho, longEsperadaHembra,
	pesoEsperadoMacho, pesoEsperadoHembra;
	
	// DB Relationships
	@JsonIgnore
	@OneToMany(mappedBy = "especie")		
    private List<Tortuga> tortugas;
	
	//@JsonIgnore
	@ManyToMany(cascade = CascadeType.REMOVE)
	@JoinTable(
	        name = "rel_especies_compatibles",
	        joinColumns = {
	        		@JoinColumn(name = "genero", referencedColumnName = "genero"), 
	        		@JoinColumn(name = "especie", referencedColumnName = "especie"), 
	        		@JoinColumn(name = "subespecie", referencedColumnName = "subespecie")
	        },
	        inverseJoinColumns = {
	        		@JoinColumn(name = "generoB", referencedColumnName = "genero"), 
	        		@JoinColumn(name = "especieB", referencedColumnName = "especie"), 
	        		@JoinColumn(name = "subespecieB", referencedColumnName = "subespecie")
	        }
	)
	private List<Especie> especiesCompatibles;
}