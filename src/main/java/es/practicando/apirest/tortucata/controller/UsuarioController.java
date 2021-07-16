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

import es.practicando.apirest.tortucata.model.Usuario;
import es.practicando.apirest.tortucata.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
	
	@Autowired 
	private UsuarioService usuarioService;
	
	
	//Crear un usuario
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Usuario usuario) {
		
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuario));
		
	}
	
	
	//Visualizar un usuario
	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable(value= "id") Long idUsuario){
		
		Optional<Usuario> oUsuario = usuarioService.findById(idUsuario);
		
		if (!oUsuario.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(oUsuario);
	}
	
	//Actualizar un usuario
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Usuario usuarioDetalles, @PathVariable Long id){
		
		Optional<Usuario> oUsuario = usuarioService.findById(id);
		
		if (!oUsuario.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		BeanUtils.copyProperties(usuarioDetalles, oUsuario.get(), "id");
		
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(oUsuario.get()));
	}
	
	//Borrar un usuario
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		
		if (!usuarioService.findById(id).isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		usuarioService.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
	
	//Visualizar todos los usuarios
	@GetMapping
	public List<Usuario> readAll() {

		List<Usuario> usuarios = StreamSupport.stream(usuarioService.findAll().spliterator(), false)
				.collect(Collectors.toList());

		return usuarios;

	}
	
	
		

}
