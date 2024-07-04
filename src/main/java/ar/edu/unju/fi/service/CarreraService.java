package ar.edu.unju.fi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.unju.fi.DTO.CarreraDTO;
import ar.edu.unju.fi.model.Carrera;

@Service
public interface CarreraService {

	public List<CarreraDTO> MostrarCarrera();
	CarreraDTO findByCodigo(String codigo);
	boolean save (CarreraDTO carreraDTO);
	void deleteByCodigo(String codigo);
	void edit(CarreraDTO carreraDTO);
	public Carrera buscarCarrera(String codigo);
		
}
