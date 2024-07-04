package ar.edu.unju.fi.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.DTO.MateriaDTO;
import ar.edu.unju.fi.map.MateriaMapDTO;
import ar.edu.unju.fi.model.Materia;
import ar.edu.unju.fi.repository.MateriaRepository;
import ar.edu.unju.fi.service.MateriaService;
import lombok.extern.slf4j.Slf4j;

@Service("materiaServiceImp")
@Slf4j
public class MateriaServiceImp implements MateriaService {

    @Autowired
    MateriaMapDTO materiaMapDTO;

    @Autowired
    MateriaRepository materiaRepository;

    @Override
    public List<MateriaDTO> MostrarMateria() {
        log.info("Mostrando listado de materias activas");
        List<MateriaDTO> materiaDTOs = materiaMapDTO.toMateriaDTOList(materiaRepository.findMateriaByEstado(true));
        log.info("Cantidad de materias recuperadas: {}", materiaDTOs.size());
        return materiaDTOs;
    }

    @Override
    public MateriaDTO findByCodigo(String codigo) {
        Optional<Materia> materiaOpt = materiaRepository.findByCodigo(codigo);

        if (materiaOpt.isPresent()) {
            log.info("Materia encontrada por código: {}", codigo);
            return materiaOpt.map(materiaMapDTO::toMateriaDTO).orElse(null);
        } else {
            log.warn("Materia no encontrada por código: {}", codigo);
            return null;
        }
    }

    @Override
    public boolean save(MateriaDTO materiaDTO) {
        Materia materia = materiaMapDTO.toMateria(materiaDTO);
        materiaRepository.save(materia);
        log.info("Materia guardada correctamente: {}", materiaDTO.getCodigo());
        return true;
    }

    @Override
    public void deleteByCodigo(String codigo) {
        List<Materia> todasLasMaterias = materiaRepository.findAll();
        for (int i = 0; i < todasLasMaterias.size(); i++) {
            Materia materia = todasLasMaterias.get(i);
            if (materia.getCodigo().equals(codigo)) {
                materia.setEstado(false);
                materiaRepository.save(materia);
                log.info("Materia eliminada lógicamente por código: {}", codigo);
                break;
            }
        }
    }

    @Override
    public void edit(MateriaDTO materiaDTO) {
        Materia materia = materiaMapDTO.toMateria(materiaDTO);
        materiaRepository.save(materia);
        log.info("Materia editada correctamente: {}", materiaDTO.getCodigo());
    }

    @Override
    public Materia buscaMateria(String codigo) {
        return materiaRepository.findById(codigo).orElse(null);
    }
}



//package ar.edu.unju.fi.service.imp;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import ar.edu.unju.fi.DTO.MateriaDTO;
//import ar.edu.unju.fi.map.MateriaMapDTO;
////import ar.edu.unju.fi.map.MateriaMapDTO;
//import ar.edu.unju.fi.model.Materia;
//import ar.edu.unju.fi.repository.MateriaRepository;
//import ar.edu.unju.fi.service.MateriaService;
//
//@Service("materiaServiceImp")
//public class MateriaServiceImp implements MateriaService {
//	
//	@Autowired MateriaMapDTO materiaMapDTO;
//		
//	@Autowired
//	MateriaRepository materiaRepository;
//	
//	@Override
//	public List<MateriaDTO> MostrarMateria() {
//		// TODO Auto-generated method stub
//		List<MateriaDTO>materiaDTOs=materiaMapDTO.toMateriaDTOList(materiaRepository.findMateriaByEstado(true));
//		System.out.println("Materias recuperadas: " + materiaDTOs.size()); // Verifica si se están recuperando datos
//		return materiaDTOs;
//	}
//
//	@Override
//	public MateriaDTO findByCodigo(String codigo) {
//		// TODO Auto-generated method stub
//		Optional<Materia> materiaOpt = materiaRepository.findByCodigo(codigo);
//		return materiaOpt.map(materiaMapDTO::toMateriaDTO).orElse(null);
//	}
//
//	@Override
//	public boolean save(MateriaDTO materiaDTO) {
//		// TODO Auto-generated method stub
//		Materia materia = materiaMapDTO.toMateria(materiaDTO);
//		materiaRepository.save(materia);
//		return true;
//		//Siempre devuelve true, indicando que el guardado fue exitoso.
//	}
//
//	@Override
//	public void deleteByCodigo(String codigo) {
//		// TODO Auto-generated method stub
//		List<Materia> todasLasMaterias = materiaRepository.findAll();
//		for (int i = 0; i < todasLasMaterias.size(); i++) {
//		      Materia materia = todasLasMaterias.get(i);
//		      if (materia.getCodigo().equals(codigo)) {
//		    	  materia.setEstado(false);
//		    	  materiaRepository.save(materia);
//		        break;
//		      }
//		    }
//		
//	}
//
//	@Override
//	public void edit(MateriaDTO materiaDTO) {
//		// TODO Auto-generated method stub
//		Materia materia = materiaMapDTO.toMateria(materiaDTO);
//		materiaRepository.save(materia);
//	}
//
//	@Override
//	public Materia buscaMateria(String codigo) {
//		// TODO Auto-generated method stub
//		return materiaRepository.findById(codigo).orElse(null);
//	}
//
//}
