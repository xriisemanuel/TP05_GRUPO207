package ar.edu.unju.fi.service;

import java.util.List;
import org.springframework.stereotype.Service;
import ar.edu.unju.fi.DTO.AlumnoDTO;
import ar.edu.unju.fi.model.Alumno;
//prueba
@Service
public interface AlumnoService {
	public List<AlumnoDTO> MostrarAlumno(); 
	AlumnoDTO findByDni(String dni); 
	boolean save (AlumnoDTO alumnoDTO); //d
	void deleteByDni(String dni);
	void edit(AlumnoDTO alumnoDTO);
	public Alumno buscaAlumno(String dni);	
	boolean save (String dni,String codigo);
	List<AlumnoDTO> findByCarrera(String carreraId);
}
