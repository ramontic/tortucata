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

import es.practicando.apirest.tortucata.model.Sensor;
import es.practicando.apirest.tortucata.service.SensorService;

@RestController
@RequestMapping("/api/sensores")
public class SensorController {
	
	@Autowired 
	private SensorService sensorService;
	
	
	//Crear un usuario
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Sensor sensor) {
		
		return ResponseEntity.status(HttpStatus.CREATED).body(sensorService.save(sensor));
		
	}
	
	
	//Visualizar un sensor
	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable(value= "id") Long idSensor){
		
		Optional<Sensor> oSensor = sensorService.findById(idSensor);
		
		if (!oSensor.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(oSensor);
	}
	
	//Actualizar un sensor
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Sensor sensorDetalles, @PathVariable Long id){
		
		Optional<Sensor> oSensor = sensorService.findById(id);
		
		if (!oSensor.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		
		BeanUtils.copyProperties(sensorDetalles, oSensor.get(), "id");
		
		return ResponseEntity.status(HttpStatus.CREATED).body(sensorService.save(oSensor.get()));
	}
	
	//Borrar un sensor
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		
		if (!sensorService.findById(id).isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		sensorService.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
	
	// Visualizar todos los sensores
	@GetMapping
	public List<Sensor> readAll() {

		List<Sensor> sensores = StreamSupport.stream(sensorService.findAll().spliterator(), false)
				.collect(Collectors.toList());

		return sensores;

	}

	/*
	 * // Visualizar todos los sensores
	 * 
	 * @GetMapping public ResponseEntity<List<Sensor>> readAll() {
	 * 
	 * List<Sensor> sensores =
	 * StreamSupport.stream(sensorService.findAll().spliterator(), false)
	 * .collect(Collectors.toList());
	 * 
	 * return ResponseEntity.ok(sensores);
	 * 
	 * }
	 */

	
	
		

}
