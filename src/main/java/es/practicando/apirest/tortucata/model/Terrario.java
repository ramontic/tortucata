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
 * Esta clase gestiona los objetos tipo Terrario (También puede ser lugar de hibernación, incubadora,...). Permite crear un Terrario, activarlo o desactivarlo, 
 * invitar y despedir Miembros, actualizar los recursos internos del terrario, Registrar tanto, los miembros actuales, como históricos que habitan el Terrario, 
 * monitorizar los parámetros como temperatura, Humedad, Horas de Luz tanto actuales como máximas y mínimas en un periodo. 
 *
 */
@Data
@Entity
@Table(name = "terrario")
public class Terrario implements Serializable{

	private static final long serialVersionUID = 4665468467024909189L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTerrario;
	
	@Column(length = 3, name = "maxMiembros", nullable = true)
	private int maxMiembros;
	
	private int tempMin, tempMax, humMin, humMax, horasLuzDirecta;
	private int tempRegMin, tempRegMax, humRegMin, humRegMax, horasRegLuzDirecta; // Variables resultado de cálculos entre Registros de parámetro en el tiempo en un determinado objeto Terrario
	
	@Column(name = "tipoTerrario", nullable = false)
	@Enumerated(value = EnumType.STRING)
	private TipoTerrario tipoTerrario;
	
	private String infoTecnica;
	
	@Column(name = "esAtivo", nullable = false)
	private boolean esActivo;
	
	public enum TipoTerrario {
		INTERIOR_CERRADO,INTERIOR_ABIERTO,EXTERIOR,CUARENTENA
	}

}
