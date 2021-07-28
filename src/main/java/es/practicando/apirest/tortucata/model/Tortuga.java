package es.practicando.apirest.tortucata.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import es.practicando.apirest.tortucata.utils.ValueOfEnum;

import lombok.Data;


@Data
@Entity
@Table(name = "tortuga",
		uniqueConstraints = {
				@UniqueConstraint(name="tortuga_numChip_unique", columnNames = "num_chip")
		})
public class Tortuga implements Serializable{
	
	private static final long serialVersionUID = 3517591061181504089L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "{tortuga.sexo.notNull}")
	@Column(name = "sexo", nullable = false, updatable = true)
	@ValueOfEnum(enumClass = Sexo.class, message = "{tortuga.sexo.valueOfEnum}")
	private String sexo; 
	
	@Size(min = 3, message = "{tortuga.nombre.size}")
	@NotBlank(message = "{tortuga.nombre.notBlank}")
	@Column(length = 50, name = "nombre", nullable = false)
	private String nombre;
	
	@JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	@Past(message = "{tortuga.fechaNacimiento.past}")
	@Column(name = "fecha_nacimiento", nullable = false, updatable = false)
	private LocalDate fechaNacimiento;
	
	@Column(length = 30, name = "num_chip", nullable = true, updatable = true)  // It should be nullable = false, updatable = false. In working progress
	private String numChip;
	
	@Transient
	private Integer edad;

	@Column(name = "foto",nullable = true, updatable = true)
    @Basic(optional = true, fetch = FetchType.LAZY)
    @Lob()
    private byte[] foto;

	public enum Sexo {
		MACHO,HEMBRA,DESCONOCIDO
	}
	
	
	// DB Relationships
	
	@ManyToOne(optional = false)
    @JoinColumn(name="usuario_id", referencedColumnName = "id", nullable = false, updatable = true, foreignKey=@ForeignKey(name = "Fk_tortuga_usuarioId"))
	@Valid
    private Usuario usuario;	
	
	@ManyToOne(optional = false)
    @JoinColumn(name="terrario_id", referencedColumnName = "id", nullable = false, updatable = true, foreignKey=@ForeignKey(name = "Fk_tortuga_terrarioId"))
	@Valid
    private Terrario terrario;
	
	@ManyToOne(optional = false)
    @JoinColumn(name="genero_id", referencedColumnName = "genero", nullable = false)
	@JoinColumn(name="especie_id", referencedColumnName = "especie", nullable = false)
	@JoinColumn(name="subespecie_id", referencedColumnName = "subespecie", nullable = false)
	@Valid
    private Especie especie;
	
	
	// Methods
	
	public Integer getEdad() {
		
		if (fechaNacimiento != null) {
			return LocalDate.now().getYear() - this.fechaNacimiento.getYear() ;
		}
		
		return null;
	}
}
