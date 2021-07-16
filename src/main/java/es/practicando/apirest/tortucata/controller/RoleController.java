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

import es.practicando.apirest.tortucata.model.Role;
import es.practicando.apirest.tortucata.service.RoleService;

@RestController
@RequestMapping("/api/roles")
public class RoleController {
	
	@Autowired 
	private RoleService roleService;
	
	
	//Crear un role
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Role role) {
		
		return ResponseEntity.status(HttpStatus.CREATED).body(roleService.save(role));
		
	}
	
	
	//Visualizar un role
	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable(value= "id") Long idRole){
		
		Optional<Role> oRole = roleService.findById(idRole);
		
		if (!oRole.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(oRole);
	}
	
	//Actualizar un role
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Role roleDetalles, @PathVariable Long id){
		
		Optional<Role> oRole = roleService.findById(id);
		
		if (!oRole.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		BeanUtils.copyProperties(roleDetalles, oRole.get(), "id");

		return ResponseEntity.status(HttpStatus.CREATED).body(roleService.save(oRole.get()));
	}
	
	//Borrar un role
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		
		if (!roleService.findById(id).isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		roleService.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
	
	//Visualizar todos los roles
		@GetMapping
		public List<Role> readAll(){
			
			List<Role> roles = StreamSupport
					.stream(roleService.findAll().spliterator(), false)
					.collect(Collectors.toList());
			
			return roles;
			
		}
	
	
		

}
