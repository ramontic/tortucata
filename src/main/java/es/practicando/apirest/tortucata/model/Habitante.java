package es.practicando.apirest.tortucata.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import lombok.Data;

/**
 * 
 * Class in order to implement @ManyToMany with extra attributes (fecha_entrada, fecha_salida) between Terrario and Tortuga.
 *
 */
@Entity
@Data
@Table(name = "habitante")
public class Habitante {
	
	@EmbeddedId
	private Habitante_Id id;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @MapsId("terrarioId")
    private Terrario terrario;
 
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("tortugaId")
    private Tortuga tortuga;
    
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	@Column(name = "fecha_entrada", nullable = false)
	private LocalDate fechaEntrada;
    
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	@Column(name = "fecha_salida", nullable = false)
	private LocalDate fechaSalida;

}
