package es.practicando.apirest.tortucata.model;

import java.io.Serializable;

import lombok.Data;

/**
 * Class to support Especie.class with its composite key
 * 
 *
 */
@Data
public class EspecieId implements Serializable{

	private static final long serialVersionUID = 3874363626263247990L;
	
	private String genero;
	private String especie;
	private String subespecie; 
}
