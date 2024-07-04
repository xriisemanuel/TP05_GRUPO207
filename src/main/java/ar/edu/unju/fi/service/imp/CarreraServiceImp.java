package ar.edu.unju.fi.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.DTO.CarreraDTO;
import ar.edu.unju.fi.map.CarreraMapDTO;
import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.repository.CarreraRepository;
import ar.edu.unju.fi.service.CarreraService;
import lombok.extern.slf4j.Slf4j;

@Service("carreraServiceImp")
@Slf4j
public class CarreraServiceImp implements CarreraService {

    @Autowired
    CarreraMapDTO carreraMapDTO;
    
    @Autowired
    CarreraRepository carreraRepository;
    
    @Override
    public List<CarreraDTO> MostrarCarrera() {
        log.info("Mostrando listado de carreras activas");
        List<CarreraDTO> carreraDTOs = carreraMapDTO.toCarreraDTOList(carreraRepository.findCarreraByEstado(true));
        return carreraDTOs;
    }

    @Override
    public CarreraDTO findByCodigo(String codigo) {
        Optional<Carrera> carreraOpt = carreraRepository.findByCodigo(codigo);
        
        if (carreraOpt.isPresent()) {
            log.info("Carrera encontrada por código: {}", codigo);
            return carreraOpt.map(carreraMapDTO::toCarreraDTO).orElse(null);
        } else {
            log.warn("Carrera no encontrada por código: {}", codigo);
            return null;
        }
    }

    @Override
    public boolean save(CarreraDTO carreraDTO) {
        Carrera carrera = carreraMapDTO.toCarrera(carreraDTO);
        carreraRepository.save(carrera);
        log.info("Carrera guardada correctamente: {}", carreraDTO.getCodigo());
        return true;
    }

    @Override
    public void deleteByCodigo(String codigo) {
        List<Carrera> todosLasCarrera = carreraRepository.findAll();
        for (int i = 0; i < todosLasCarrera.size(); i++) {
              Carrera carrera = todosLasCarrera.get(i);
              if (carrera.getCodigo().equals(codigo)) {
                carrera.setEstado(false);
                carreraRepository.save(carrera);
                log.info("Carrera eliminada lógicamente por código: {}", codigo);
                break;
              }
            }
    }

    @Override
    public void edit(CarreraDTO carreraDTO) {
        Carrera carrera = carreraMapDTO.toCarrera(carreraDTO);
        carreraRepository.save(carrera);
        log.info("Carrera editada correctamente: {}", carreraDTO.getCodigo());
    }

    @Override
    public Carrera buscarCarrera(String codigo) {
        return carreraRepository.findById(codigo).orElse(null);
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
//import ar.edu.unju.fi.DTO.CarreraDTO;
//import ar.edu.unju.fi.map.CarreraMapDTO;
////import ar.edu.unju.fi.collections.ListadoCarreras;
//import ar.edu.unju.fi.model.Carrera;
//import ar.edu.unju.fi.repository.CarreraRepository;



//import ar.edu.unju.fi.service.CarreraService;
//
//@Service("carreraServiceImp")
//public class CarreraServiceImp implements CarreraService {
//
//	@Autowired CarreraMapDTO carreraMapDTO;
//	
//	@Autowired 
//	CarreraRepository carreraRepository;
//	
//	@Override
//	public List<CarreraDTO> MostrarCarrera() {
//		List<CarreraDTO>carreraDTOs = carreraMapDTO.toCarreraDTOList(carreraRepository.findCarreraByEstado(true));
//		return carreraDTOs;
//	}
//
//	@Override
//	public CarreraDTO findByCodigo(String codigo) {
//		Optional<Carrera> carreraOpt = carreraRepository.findByCodigo(codigo);
//		return carreraOpt.map(carreraMapDTO::toCarreraDTO).orElse(null);
//	}
//
//	@Override
//	public boolean save(CarreraDTO carreraDTO) {
//		//boolean respuesta = ListadoCarreras.agregarCarrera(carreraMapDTO.toCarrera(carreraDTO));
//		Carrera carrera = carreraMapDTO.toCarrera(carreraDTO);
//		carreraRepository.save(carrera);
//		return true;
//	}
//
//	@Override
//	public void deleteByCodigo(String codigo) {
//		List<Carrera> todosLasCarrera = carreraRepository.findAll();
//		for (int i = 0; i < todosLasCarrera.size(); i++) {
//		      Carrera carrera = todosLasCarrera.get(i);
//		      if (carrera.getCodigo().equals(codigo)) {
//		        carrera.setEstado(false);
//		        carreraRepository.save(carrera);
//		        break;
//		      }
//		    }
//
//		
//	}
//
//	@Override
//	public void edit(CarreraDTO carreraDTO) {
//		//ListadoCarreras.modificarCarrera(carreraMapDTO.toCarrera(carreraDTO));
//		Carrera carrera = carreraMapDTO.toCarrera(carreraDTO);
//		carreraRepository.save(carrera);
//	}
//
//	@Override
//	public Carrera buscarCarrera(String codigo) {
//		// TODO Auto-generated method stub
//		return carreraRepository.findById(codigo).orElse(null);
//	}
//}
//
