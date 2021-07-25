package es.practicando.apirest.tortucata.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data 
public class Habitante_Id implements Serializable{
	
	private static final long serialVersionUID = -828309689393417086L;
	
	@Column(name = "terrario_id")
    private Long terrarioId;
	
	@Column(name = "tortuga_id")
    private Long tortugaId;

}
