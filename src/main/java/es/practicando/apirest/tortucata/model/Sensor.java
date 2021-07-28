package es.practicando.apirest.tortucata.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import es.practicando.apirest.tortucata.utils.ValueOfEnum;
import lombok.Data;

@Data
@Entity
@Table(name = "sensor")
public class Sensor implements Serializable{
	
	private static final long serialVersionUID = -6345838945800734626L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "{sensor.modelo.notBlanck}")
	@Column(name = "modelo", length = 30, nullable = false)
	private String modelo;
	
	@NotNull(message = "{sensor.estado.notNull}")
	@Column(name = "estado", nullable = false)
	@ValueOfEnum(enumClass = Estado.class, message = "{sensor.estado.valueOfEnum}")
	private String estado;

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
    private List<@Valid Medicion> mediciones;
	
	@ManyToOne
    @JoinColumn(name="terrario_id", referencedColumnName = "id", nullable = true, foreignKey=@ForeignKey(name = "Fk_sensor_terrarioId"))
	@Valid
    private Terrario terrario;
	
	
	
}
