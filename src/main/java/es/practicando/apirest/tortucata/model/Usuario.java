package es.practicando.apirest.tortucata.model;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "usuario",
		uniqueConstraints = {
				@UniqueConstraint(name="usuario_email_unique",columnNames = "email"),
				@UniqueConstraint(name="usuario_telefono_unique",columnNames = "telefono")
		})
public class Usuario implements Serializable{

	private static final long serialVersionUID = 7155583754420389385L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "{usuario.nombre.empty}")
	@Size(min = 3, max = 50, message = "{usuario.nombre.size}")
	@Column(name = "nombre", length = 50, nullable = false, updatable = true)
	private String nombre;
	
	@Column(name = "apellido_paterno", length = 50, nullable = true, updatable = true)
	private String apellidoPaterno;
	
	
	@Email
	@NotBlank(message = "{usuario.email.notBlank}")
	@Column(name = "email", nullable= false, updatable = false)
	private String email;
	
	@Column(name = "ciudad", nullable= true, updatable = true)
	private String ciudad;
	
	@NotBlank(message = "{usuario.telefono.notBlank}")
	@Pattern(regexp = "[6][0-9]{8}", message = "{usuario.telefono.pattern}")
	@Column(name = "telefono", nullable= false, updatable = false)
	private String telefono;  
	
	@NotNull(message = "{usuario.fechaAlta.notEmpty}")
	@Column(name = "fecha_alta", nullable = false, updatable = false)
	private LocalDate fechaAlta = LocalDate.now();
	
	
	
	private Boolean enabled = true;
	
	
	// DB Relationships
	
	@JsonIgnore
	@OneToMany(
			mappedBy = "usuario", 
			cascade = CascadeType.ALL
	)		
    private List<@Valid Terrario> terrarios;
	
	@JsonIgnore
	@OneToMany(
			mappedBy = "usuario", 
			cascade = CascadeType.ALL			
	)		
    private List<@Valid Tortuga> tortugas;
}
