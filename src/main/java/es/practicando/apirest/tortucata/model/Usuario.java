package es.practicando.apirest.tortucata.model;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;


import lombok.Data;

@Data
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable{

	private static final long serialVersionUID = 7155583754420389385L;

	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO, generator="native")
	//@GenericGenerator(name="native",strategy="native")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 50, nullable = false)
	private String nombre;
	
	@Column(length = 50)
	private String apellidoPaterno;
	
	@Column(name = "email", nullable= false, length = 50, unique = true)
	private String email;
	
	@Column(name = "telefono", nullable= false, unique = true)
	private Long telefono;
	
	@Column(name = "fechaAlta", updatable = false, nullable = false)
	private LocalDate fechaAlta;
	
	private Boolean enabled;
	
	@Column(name = "username", nullable = false)
	private String username;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Transient 
	private String confirmPassword;
	

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="user_roles"
		,joinColumns=@JoinColumn(name="user_id")
		,inverseJoinColumns=@JoinColumn(name="role_id"))
	private Set<Role> roles;

}
