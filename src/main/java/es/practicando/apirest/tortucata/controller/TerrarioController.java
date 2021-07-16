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

import es.practicando.apirest.tortucata.model.Terrario;
import es.practicando.apirest.tortucata.service.TerrarioService;

@RestController
@RequestMapping("/api/terrarios")
public class TerrarioController {
	
	@Autowired 
	private TerrarioService terrarioService;
	
	
	//Crear un usuario
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Terrario usuario) {
		
		return ResponseEntity.status(HttpStatus.CREATED).body(terrarioService.save(usuario));
		
	}
	
	
	//Visualizar un usuario
	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable(value= "id") Long idTerrario){
		
		Optional<Terrario> oTerrario = terrarioService.findById(idTerrario);
		
		if (!oTerrario.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(oTerrario);
	}
	
	//Actualizar un usuario
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Terrario terrarioDetalles, @PathVariable Long id){
		
		Optional<Terrario> oTerrario = terrarioService.findById(id);
		
		if (!oTerrario.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		BeanUtils.copyProperties(terrarioDetalles, oTerrario.get(), "id");
		
		
		return ResponseEntity.status(HttpStatus.CREATED).body(terrarioService.save(oTerrario.get()));
	}
	
	//Borrar un usuario
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		
		if (!terrarioService.findById(id).isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		terrarioService.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
	
	//Visualizar todos los usuarios
		@GetMapping
		public List<Terrario> readAll(){
			
			List<Terrario> usuarios = StreamSupport
					.stream(terrarioService.findAll().spliterator(), false)
					.collect(Collectors.toList());
			
			return usuarios;
			
		}
	
	
		

}
