package es.practicando.apirest.tortucata.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import lombok.Data;


@Data
@Entity
@Table(name = "tortuga",
		uniqueConstraints = {
				@UniqueConstraint(name="tortuga_usuario_unique",columnNames = "usuario_id")
		})
public class Tortuga implements Serializable{
	
	private static final long serialVersionUID = 3517591061181504089L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "sexo", nullable = false)
	@Enumerated(value = EnumType.STRING)
	private Sexo sexo; 
	
	@Column(length = 50, name = "nombre", nullable = false)
	private String nombre;
	
	@JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	@Column(name = "fecha_nacimiento", nullable = false)
	private LocalDate fechaNacimiento;
	
	@Column(length = 50, name = "num_chip", nullable = false)
	private Long numChip;
	
	@Transient
	private Integer edad;

	@Column(name = "foto",nullable = true)
    @Basic(optional = true, fetch = FetchType.LAZY)
    @Lob()
    private byte[] foto;

	public enum Sexo {
		MACHO,HEMBRA,DESCONOCIDO
	}
	
	
	// DB Relationships
	
	@ManyToOne(optional = false)
    @JoinColumn(name="usuario_id", referencedColumnName = "id", nullable = false)
    private Usuario usuario;	
	
	@JsonIgnore
	@OneToMany(mappedBy = "tortuga",
			cascade = CascadeType.ALL)
    private List<Habitante> terrarios;
	
	@ManyToOne(optional = false)
    @JoinColumn(name="genero_id", referencedColumnName = "genero", nullable = false)
	@JoinColumn(name="especie_id", referencedColumnName = "especie", nullable = false)
	@JoinColumn(name="subespecie_id", referencedColumnName = "subespecie", nullable = false)
    private Especie especie;
	
	
	// Methods
	
	public Integer getEdad() {
		
		if (fechaNacimiento != null) {
			return LocalDate.now().getYear() - this.fechaNacimiento.getYear() ;
		}
		
		return null;
	}
}
