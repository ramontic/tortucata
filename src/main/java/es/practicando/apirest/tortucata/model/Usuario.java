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
	
	@Column(name = "nombre", length = 50, nullable = false, updatable = true)
	private String nombre;
	
	@Column(name = "apellido_paterno", length = 50, nullable = true, updatable = true)
	private String apellidoPaterno;
	
	@Column(name = "email", nullable= false, updatable = false)
	private String email;
	
	@Column(name = "ciudad", nullable= true, updatable = true)
	private String ciudad;
	
	@Column(name = "telefono", nullable= false, updatable = false)
	private Long telefono;
	
	@Column(name = "fecha_alta", updatable = false, nullable = false)
	private LocalDate fechaAlta = LocalDate.now();
	
	private Boolean enabled = true;
	
	
	// DB Relationships
	
	@JsonIgnore
	@OneToMany(
			mappedBy = "usuario", 
			cascade = CascadeType.ALL
	)		
    private List<Terrario> terrarios;
	
	@JsonIgnore
	@OneToMany(
			mappedBy = "usuario", 
			cascade = CascadeType.ALL			
	)		
    private List<Tortuga> tortugas;
}
