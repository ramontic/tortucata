package es.practicando.apirest.tortucata.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "tortuga")
@AllArgsConstructor
@NoArgsConstructor
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
	
	@Column(name = "fechaNacimiento")
	private LocalDate fechaNacimiento;
	
	@Transient
	private Integer edad;
	

	public Integer getEdad() {
		return LocalDate.now().getYear() - this.fechaNacimiento.getYear() ;
	}
	
	@Column(name = "taxonomia")
	private String taxonomia;
	
	@Column(name = "foto" ,nullable = true)
    @Basic(optional = true, fetch = FetchType.LAZY)
    @Lob()
    private byte[] foto;

	
	public enum Sexo {
		MACHO,HEMBRA,DESCONOCIDO
	}
}
