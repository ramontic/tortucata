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

import es.practicando.apirest.tortucata.exception.customized.MyNotFoundException;
import es.practicando.apirest.tortucata.model.Tortuga;
import es.practicando.apirest.tortucata.service.TortugaService;

@RestController
@RequestMapping("/api/tortugas")
public class TortugaController {
	
	@Autowired 
	private TortugaService tortugaService;
	
	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody Tortuga tortuga) {
		
		return ResponseEntity.status(HttpStatus.CREATED).body(tortugaService.save(tortuga));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable(value= "id") Long idTortuga){
		
		Optional<Tortuga> oTortuga = tortugaService.findById(idTortuga);
		
		if (!oTortuga.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(oTortuga);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Tortuga tortugaDetalles, @PathVariable Long id){
		
		Optional<Tortuga> oTortuga = tortugaService.findById(id);
		
		if (!oTortuga.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		BeanUtils.copyProperties(tortugaDetalles, oTortuga.get(), "id");
		
		return ResponseEntity.status(HttpStatus.CREATED).body(tortugaService.save(oTortuga.get()));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		
		if (!tortugaService.findById(id).isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		tortugaService.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping
	public ResponseEntity<?> readAll(){
		
		List<Tortuga> tortugas = StreamSupport
				.stream(tortugaService.findAll().spliterator(), false)
				.collect(Collectors.toList());
		
		return ResponseEntity.ok(tortugas);
	}
	
	@GetMapping("/byName/{nameTortuga}")
	public ResponseEntity<?> read(@PathVariable(value= "nameTortuga") String nameTortuga){
		
		Tortuga tortuga = tortugaService.findByNombre(nameTortuga)
				.orElseThrow(() -> new  MyNotFoundException(nameTortuga));
	
		return ResponseEntity.status(HttpStatus.OK).body(tortuga);
	}
}
