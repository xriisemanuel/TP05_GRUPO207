package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ar.edu.unju.fi.DTO.DocenteDTO;
import ar.edu.unju.fi.service.DocenteService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/docente")
public class DocenteController {

    @Autowired
    @Qualifier("docenteServiceImp")
    DocenteService docenteService;

    @Autowired
    DocenteDTO nuevoDocenteDTO;

    // Muestra la lista de docentes
    @GetMapping("/listadoDocente")
    public String getCarreras(Model model) {
        try {
            model.addAttribute("docente", docenteService.MostrarDocente()); // muestra la lista de docentes
        } catch (Exception e) {
            // Manejo de cualquier excepción que ocurra al obtener la lista de docentes
            model.addAttribute("error", "Ocurrió un error al obtener la lista de docentes.");
        }
        return "listadoDeDocentes";
    }

    // Muestra el formulario para agregar un nuevo docente
    @GetMapping("/nuevo")
    public String getVistaNuevaDocente(Model model) {
        boolean edicion = false; // No quiero editar un objeto
        model.addAttribute("nuevoDocente", nuevoDocenteDTO);
        model.addAttribute("edicion", edicion);
        return "formDocente";
    }

    // Guarda un nuevo docente
    @PostMapping("/guardar")
    //@Valid @ModelAttribute("nuevoDocente") Tiene que devolver el mismo nombre que viene como objeto del formulario.html
    public String guardarDocente(@Valid @ModelAttribute("nuevoDocente") DocenteDTO docenteDTO, BindingResult resultado, Model model) { 
        if (resultado.hasErrors()) {
            model.addAttribute("nuevoDocente", docenteDTO);
            model.addAttribute("edicion", false);
            return "formDocente"; 
        }else {
			try {
            docenteService.save(docenteDTO);
	        } catch (Exception e) {
	            model.addAttribute("error", "Ocurrió un error al guardar el docente.");
	            return "formDocente"; // Mostrar el formulario con un mensaje de error
	        }
	        return "redirect:/docente/listadoDocente";
		}
        
    }


    // Muestra el formulario para modificar un docente existente
    @GetMapping("/modificar/{legajo}")
    public String getModificarDocentePage(Model model, @PathVariable(value = "legajo") String legajo) {
        try {
            DocenteDTO docenteEncontradoDTO = docenteService.findByLegajo(legajo);
            boolean edicion = true;
            model.addAttribute("nuevoDocente", docenteEncontradoDTO);
            model.addAttribute("edicion", edicion);
        } catch (Exception e) {
            // Manejo de cualquier excepción que ocurra al encontrar el docente por legajo
            return "redirect:/docente/listadoDocente?error=true";
        }
        return "formDocente";
    }

    // Guarda las modificaciones de un docente existente
    @PostMapping("/modificar")
    public String modificarDocente(@Valid @ModelAttribute("nuevoDocente") DocenteDTO docenteDTO,BindingResult resultado, Model model) {
    	if (resultado.hasErrors()) {
    		model.addAttribute("nuevoDocente", docenteDTO);
            return "formDocente";
		}
    	else {
			    try {
	            docenteService.edit(docenteDTO);
		        } catch (Exception e) {
		            // Manejo de cualquier excepción que ocurra al modificar el docente
		            return "redirect:/docente/modificar/" + docenteDTO.getLegajo() + "?error=true";
		        }
		        return "redirect:/docente/listadoDocente";
		}
       
    }

    // Elimina un docente por su legajo
    @GetMapping("/borrar/{legajo}")
    public String eliminarDocente(@PathVariable(value = "legajo") String legajo) {
        try {
            docenteService.deleteByLegajo(legajo);
        } catch (Exception e) {
            // Manejo de cualquier excepción que ocurra al borrar el docente
            return "redirect:/docente/listadoDocente?error=true";
        }
        return "redirect:/docente/listadoDocente";
    }
}
