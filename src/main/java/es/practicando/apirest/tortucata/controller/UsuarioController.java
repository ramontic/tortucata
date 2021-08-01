package es.practicando.apirest.tortucata.controller;

import java.util.List;
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

import es.practicando.apirest.tortucata.exception.customized.MyConflictException;
import es.practicando.apirest.tortucata.exception.customized.MyNotFoundException;
import es.practicando.apirest.tortucata.model.Usuario;
import es.practicando.apirest.tortucata.service.UsuarioService;


@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
	
	@Autowired 
	private UsuarioService usuarioService;
	
	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody Usuario usuario) {
		
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuario));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable(value= "id") Long id){
	
		Usuario usuario = usuarioService.findById(id)
				.orElseThrow(() -> new  MyNotFoundException(id));
	
		return ResponseEntity.status(HttpStatus.OK).body(usuario);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Usuario usuarioDetalles, @PathVariable Long id){
		
		Usuario usuario = usuarioService.findById(id)
				.orElseThrow(() -> new  MyNotFoundException(id));
		
		// Handling non updatable fields of Usuario entity. Looking for better solution.
		if(!usuarioDetalles.getEmail().equals(usuario.getEmail()) || 
				!usuarioDetalles.getTelefono().equals(usuario.getTelefono())) {
					throw new MyConflictException("el email o teléfono del usuario");			
		}

		BeanUtils.copyProperties(usuarioDetalles, usuario, "id");
		
		return ResponseEntity.status(HttpStatus.OK).body(usuarioService.save(usuario));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		
		if (!usuarioService.findById(id).isPresent()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		
		usuarioService.deleteById(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@GetMapping
	public ResponseEntity<?> readAll() {

		List<Usuario> usuarios = StreamSupport.stream(usuarioService.findAll().spliterator(), false)
				.collect(Collectors.toList());

		return new ResponseEntity<>(usuarios, HttpStatus.OK);
	}
}
