package ar.edu.um.programacion2_2018.tp10.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ar.edu.um.programacion2_2018.tp10.model.Tarea;

@Repository
public interface TareaRepository extends JpaRepository<Tarea, Long> {

}
