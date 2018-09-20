package ar.edu.um.programacion2_2018.tp10.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ar.edu.um.programacion2_2018.tp10.exception.ResourceNotFoundException;
import ar.edu.um.programacion2_2018.tp10.model.Tarea;
import ar.edu.um.programacion2_2018.tp10.repository.TareaRepository;

@RestController
@RequestMapping("/api")
public class TareaController {

	@Autowired
	TareaRepository tareaRepository;
	
	// Traer Todas las tareas
	@GetMapping("/tareas")
	public List<Tarea> getAllTareas() {
		return tareaRepository.findAll();
	}
	
	// Traer una tarea especifica por ID
	@GetMapping("/tareas/{id}")
	public Tarea getTareaById(@PathVariable(value = "id") Long tareaId) {
		return tareaRepository.findById(tareaId)
				.orElseThrow(() -> new ResourceNotFoundException("Tarea", "id", tareaId));
	}
	
	// Insertar Tarea
	@PostMapping("/tareas")
	public Tarea createTarea(@Valid @RequestBody Tarea tarea) {
		return tareaRepository.save(tarea);
	}
	
	// Eliminar Tarea
	@DeleteMapping("/tareas/{id}")
	public ResponseEntity<?> deleteTarea(@PathVariable(value = "id") Long tareaId){
		Tarea tarea = tareaRepository.findById(tareaId)
                .orElseThrow(() -> new ResourceNotFoundException("Tarea", "id", tareaId));

		tareaRepository.delete(tarea);

        return ResponseEntity.ok().build();
	}
	
	// Actualizar Tarea
	@PutMapping("/tareas/{id}")
	public Tarea updateTarea(	@PathVariable(value = "id") Long tareaId, 
								@Valid @RequestBody Tarea detallesTarea) {
		
		Tarea tarea = tareaRepository.findById(tareaId)
				.orElseThrow(() -> new ResourceNotFoundException("Tarea", "id", tareaId));
		
		tarea.setDescripcion(detallesTarea.getDescripcion());
		tarea.setResponsable(detallesTarea.getResponsable());
		
		Tarea actualizarTarea = tareaRepository.save(tarea);
		return actualizarTarea;
		
	}
	
}
