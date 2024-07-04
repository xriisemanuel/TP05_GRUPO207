package ar.edu.unju.fi.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.DTO.AlumnoDTO;
import ar.edu.unju.fi.map.AlumnoMapDTO;
import ar.edu.unju.fi.model.Alumno;
import ar.edu.unju.fi.repository.AlumnoRepository;
import ar.edu.unju.fi.service.AlumnoService;
import lombok.extern.slf4j.Slf4j;

@Service("alumnoServiceImp")
@Slf4j
public class AlumnoServiceImp implements AlumnoService {

    @Autowired
    AlumnoMapDTO alumnoMapDTO;

    @Autowired
    AlumnoRepository alumnoRepository;

    @Override
    public List<AlumnoDTO> MostrarAlumno() {
        log.info("Mostrando listado de alumnos activos");
        List<AlumnoDTO> alumnoDTOs = alumnoMapDTO.toAlumnoDTOList(alumnoRepository.findAlumnoByEstado(true));
        return alumnoDTOs;
    }

    @Override
    public AlumnoDTO findByDni(String dni) {
        Optional<Alumno> alumnoOpt = alumnoRepository.findByDni(dni);

        if (alumnoOpt.isPresent()) {
            log.info("Alumno encontrado por DNI: {}", dni);
            return alumnoOpt.map(alumnoMapDTO::toAlumnoDTO).orElse(null);
        } else {
            log.warn("Alumno no encontrado por DNI: {}", dni);
            return null;
        }
    }

    @Override
    public boolean save(AlumnoDTO alumnoDTO) {
        Alumno alumno = alumnoMapDTO.toAlumno(alumnoDTO);
        alumnoRepository.save(alumno);
        log.info("Alumno guardado correctamente: {}", alumnoDTO.getDni());
        return true;
    }

    @Override
    public void deleteByDni(String dni) {
        List<Alumno> todosLosAlumnos = alumnoRepository.findAll();
        for (int i = 0; i < todosLosAlumnos.size(); i++) {
            Alumno alumno = todosLosAlumnos.get(i);
            if (alumno.getDni().equals(dni)) {
                alumno.setEstado(false);
                alumnoRepository.save(alumno);
                log.info("Alumno eliminado lógicamente por DNI: {}", dni);
                break;
            }
        }
    }

    @Override
    public void edit(AlumnoDTO alumnoDTO) {
        Alumno alumno = alumnoMapDTO.toAlumno(alumnoDTO);
        alumnoRepository.save(alumno);
        log.info("Alumno editado correctamente: {}", alumnoDTO.getDni());
    }

    @Override
    public Alumno buscaAlumno(String dni) {
        return alumnoRepository.findById(dni).orElse(null);
    }
}



//package ar.edu.unju.fi.service.imp;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//import org.springframework.stereotype.Service;
//
//import ar.edu.unju.fi.DTO.AlumnoDTO;
//import ar.edu.unju.fi.map.AlumnoMapDTO;
////import ar.edu.unju.fi.map.AlumnoMapDTO;
//import ar.edu.unju.fi.model.Alumno;
//import ar.edu.unju.fi.model.Materia;
//import ar.edu.unju.fi.repository.AlumnoRepository;
//import ar.edu.unju.fi.repository.MateriaRepository;
//import ar.edu.unju.fi.service.AlumnoService;
//import lombok.extern.slf4j.Slf4j;
//
//
//@Service("alumnoServiceImp")
//@Slf4j
//public class AlumnoServiceImp implements AlumnoService {
//	
//	@Autowired AlumnoMapDTO alumnoMapDTO;
//	@Autowired
//	AlumnoRepository alumnoRepository;
//	
//	@Autowired
//	MateriaRepository materiaRepository;
//	
//	@Override
//	public List<AlumnoDTO> MostrarAlumno() {
//		// TODO Auto-generated method stub
//		List<AlumnoDTO>alumnoDTOs=alumnoMapDTO.toAlumnoDTOList(alumnoRepository.findAlumnoByEstado(true));
//		
//		return alumnoDTOs;
//		
//	}
//
//	@Override
//	public AlumnoDTO findByDni(String dni) {
//		// TODO Auto-generated method stub
//		 Optional<Alumno> alumnoOpt = alumnoRepository.findByDni(dni);
//		 
//		 System.out.println("ALUMNO POR DNI:  "+alumnoOpt);
//	     return alumnoOpt.map(alumnoMapDTO::toAlumnoDTO).orElse(null);
//		
//	}
//
//	@Override
//	public boolean save(AlumnoDTO alumnoDTO) {
//		//boolean respuesta = ListadoAlumnos.agregarAlumno(alumnoMapDTO.toAlumno(alumnoDTO));
//		Alumno alumno = alumnoMapDTO.toAlumno(alumnoDTO);
//		alumnoRepository.save(alumno);
//		return true;
//	}
//
//	@Override
//	public void deleteByDni(String dni) {
//		// TODO Auto-generated method stub
//
//		List<Alumno> todosLosAlumnos = alumnoRepository.findAll();
//		for (int i = 0; i < todosLosAlumnos.size(); i++) {
//		      Alumno alumno = todosLosAlumnos.get(i);
//		      if (alumno.getDni().equals(dni)) {
//		    	  alumno.setEstado(false);
//		    	  alumnoRepository.save(alumno);
//		        break;
//		      }
//		    }
//	}
//
//	@Override
//	public void edit(AlumnoDTO alumnoDTO) {
//		// TODO Auto-generated method stub
//		//ListadoAlumnos.modificarAlumno(alumnoMapDTO.toAlumno(alumnoDTO));
//		Alumno alumno = alumnoMapDTO.toAlumno(alumnoDTO);
//		alumnoRepository.save(alumno);
//		
//	}
//
//	@Override
//	public Alumno buscaAlumno(String dni) {
//		return alumnoRepository.findById(dni).orElse(null);
//	}
//
//	@Override
//	public boolean save(String dni, String codigo) {
//	    // Buscar el alumno por su DNI
//	    Optional<Alumno> alumnoOpt = alumnoRepository.findById(dni);
//	    // Buscar la materia por su código
//	    Optional<Materia> materiaOpt = materiaRepository.findById(codigo);
//
//	    // Verificar si ambos Optional contienen valores
//	    if (alumnoOpt.isPresent() && materiaOpt.isPresent()) {
//	        // Obtener los valores desde los Optional
//	        Alumno alumno = alumnoOpt.get();
//	        Materia materia = materiaOpt.get();
//
//	        // Verificar si la materia no está ya en la lista del alumno
//	        if (!alumno.getMaterias().contains(materia)) {
//	            // Agregar la materia a la lista de materias del alumno
//	            alumno.getMaterias().add(materia);
//	            // Guardar el alumno actualizado en el repositorio
//	            alumnoRepository.save(alumno);
//	        }
//	        // Retornar verdadero indicando éxito
//	        return true;
//	    }
//	    // Retornar falso si el alumno o la materia no se encontraron
//	    return false;
//	}
//
//}
