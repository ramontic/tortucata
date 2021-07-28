package es.practicando.apirest.tortucata.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.validation.Valid;

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
	
	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody Especie especie) {
		
		return ResponseEntity.status(HttpStatus.CREATED).body(especieService.save(especie));
		
	}
	
	@GetMapping("/{genero},{especie},{subespecie}")
	public ResponseEntity<?> read(
			@PathVariable(value= "genero") String genero,
			@PathVariable(value= "especie") String especie,
			@PathVariable(value= "subespecie") String subespecie){
		
		Optional<Especie> oEspecie = especieService.findByGeneroAndEspecieAndSubespecie(genero, especie, subespecie);
		
		if (!oEspecie.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(oEspecie);
	}
	
	@PutMapping("/{genero},{especie},{subespecie}")
	public ResponseEntity<?> update(@Valid @RequestBody Especie especieDetalles, 
			@PathVariable(value= "genero") String genero,
			@PathVariable(value= "especie") String especie,
			@PathVariable(value= "subespecie") String subespecie){
		
		Optional<Especie> oEspecie = especieService.findByGeneroAndEspecieAndSubespecie(genero, especie, subespecie);
		
		if (!oEspecie.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		BeanUtils.copyProperties(especieDetalles, oEspecie.get(), "id");
			
		return ResponseEntity.status(HttpStatus.CREATED).body(especieService.save(oEspecie.get()));
	}
	
	@DeleteMapping("/{genero},{especie},{subespecie}")
	public ResponseEntity<?> delete(
			@PathVariable(value= "genero") String genero,
			@PathVariable(value= "especie") String especie,
			@PathVariable(value= "subespecie") String subespecie){
		
		if (!especieService.findByGeneroAndEspecieAndSubespecie(genero, especie, subespecie).isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		especieService.deleteByGeneroAndEspecieAndSubespecie(genero, especie, subespecie);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping
	public List<Especie> readAll(){
		
		List<Especie> especies = StreamSupport
				.stream(especieService.findAll().spliterator(), false)
				.collect(Collectors.toList());
		
		return especies;
	}
}
