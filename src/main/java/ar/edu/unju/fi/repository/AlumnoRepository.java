package ar.edu.unju.fi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ar.edu.unju.fi.model.Alumno;
import ar.edu.unju.fi.model.Carrera;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno,String>{
	List<Alumno> findAlumnoByEstado(Boolean estado);
	Optional<Alumno> findByDni(String dni);
	List<Alumno> findByCarreras(Carrera carrera);
}
