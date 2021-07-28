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
	
	@NotNull(message = "{terrario.tipoTerrario.notNull}")
	@Column(name = "tipoTerrario", nullable = false)
	@ValueOfEnum(enumClass = TipoTerrario.class, message = "{terrario.tipoTerrario.valueOfEnum}")
	private String tipoTerrario;
	
	@NotBlank(message = "{terrario.infoTecnica.notBlanck}")
	@Column(length = 100, name = "info_tecnica", nullable = false)
	private String infoTecnica;
	
	@Column(name = "esAtivo", nullable = false)
	private Boolean esActivo = true;
	
	private enum TipoTerrario {
		INTERIOR_CERRADO,INTERIOR_ABIERTO,EXTERIOR,CUARENTENA
	}
	
	// DB Relationships
	
	@ManyToOne(optional = false)
    @JoinColumn(name="usuario_id", referencedColumnName = "id", nullable = false, foreignKey=@ForeignKey(name = "Fk_terrario_usuarioId"))
	@Valid
    private Usuario usuario;
	
	@JsonIgnore
	@OneToMany(
			mappedBy = "terrario", 
			cascade = CascadeType.ALL
	)		
    private List<@Valid Sensor> sensores;
	
	@JsonIgnore
	@OneToMany(
			mappedBy = "terrario",
			cascade = CascadeType.ALL
	)		
    private List<@Valid Tortuga> tortugas;
}
