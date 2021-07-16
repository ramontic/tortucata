package es.practicando.apirest.tortucata.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
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
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EspecieSeq")  
	private Long id;

	@Column(length = 50, nullable = false)
	private String genero;
	
	@Column(length = 50, nullable = false)
	private String especie;
	
	@Column(length = 50, nullable = false)
	private String	subespecie; 
	
	@Column(length = 50)
	private String	nComun;
	
	@Column(length = 50)
	private String	distGeog;
	
	@Column(length = 50)
	private String	taxon;
	
	private int tempIdealMin, tempIdealMax, humIdealMin, humIdealMax, longEsperadaMacho, longEsperadaHembra,
	pesoEsperadoMacho, pesoEsperadoHembra;
	


}