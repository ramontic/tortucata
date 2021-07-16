package es.practicando.apirest.tortucata.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.practicando.apirest.tortucata.model.Especie;
import es.practicando.apirest.tortucata.service.EspecieService;

@RestController
@RequestMapping("/api/especies")
public class EspecieController {
	
	@Autowired 
	private EspecieService especieService;
	
	
	//Crear una especie
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Especie especie) {
		
		return ResponseEntity.status(HttpStatus.CREATED).body(especieService.save(especie));
		
	}
	
	
	//Visualizar una especie
	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable(value= "id") Long id){
		
		Optional<Especie> oEspecie = especieService.findById(id);
		
		if (!oEspecie.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(oEspecie);
	}
	
	//Actualizar una especie
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Especie especieDetalles, @PathVariable Long id){
		
		Optional<Especie> oEspecie = especieService.findById(id);
		
		if (!oEspecie.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		BeanUtils.copyProperties(especieDetalles, oEspecie.get(), "id");
			
		return ResponseEntity.status(HttpStatus.CREATED).body(especieService.save(oEspecie.get()));
	}
	
	//Borrar una especie
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		
		if (!especieService.findById(id).isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		especieService.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
	
	//Visualizar todas los especies
		@GetMapping
		public List<Especie> readAll(){
			
			List<Especie> especies = StreamSupport
					.stream(especieService.findAll().spliterator(), false)
					.collect(Collectors.toList());
			
			return especies;
			
		}
	
	
		

}
