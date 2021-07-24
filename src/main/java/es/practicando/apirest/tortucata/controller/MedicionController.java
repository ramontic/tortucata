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

import es.practicando.apirest.tortucata.model.Medicion;
import es.practicando.apirest.tortucata.service.MedicionService;
import es.practicando.apirest.tortucata.service.SensorService;

@RestController
@RequestMapping("/api/mediciones")
public class MedicionController {
	
	@Autowired 
	private MedicionService medicionService;
	
	@Autowired
	SensorService sensorService;
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Medicion medicion) {
		
		return ResponseEntity.status(HttpStatus.CREATED).body(medicionService.save(medicion));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable(value= "id") Long idMedicion){
		
		Optional<Medicion> oMedicion = medicionService.findById(idMedicion);
		
		if (!oMedicion.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(oMedicion);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Medicion medicionDetalles, @PathVariable Long id){
		
		Optional<Medicion> oMedicion = medicionService.findById(id);
		
		if (!oMedicion.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		BeanUtils.copyProperties(medicionDetalles, oMedicion.get(), "id");
		
		return ResponseEntity.status(HttpStatus.CREATED).body(medicionService.save(oMedicion.get()));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		
		if (!medicionService.findById(id).isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		medicionService.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping
	public List<Medicion> readAll() {

		List<Medicion> mediciones = StreamSupport.stream(medicionService.findAll().spliterator(), false)
				.collect(Collectors.toList());

		return mediciones;

	}
}
