package ar.edu.unju.fi.controller;

import java.util.List;

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

import com.github.mustachejava.Binding;

import ar.edu.unju.fi.DTO.AlumnoDTO;
import ar.edu.unju.fi.DTO.CarreraDTO;
import ar.edu.unju.fi.DTO.DocenteDTO;
import ar.edu.unju.fi.DTO.MateriaDTO;
import ar.edu.unju.fi.service.AlumnoService;
import ar.edu.unju.fi.service.CarreraService;
import ar.edu.unju.fi.service.DocenteService;
import ar.edu.unju.fi.service.MateriaService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/alumno")
public class AlumnoController {

    @Autowired
    @Qualifier("alumnoServiceImp")
    AlumnoService alumnoService;

    @Autowired
    AlumnoDTO nuevoAlumnoDTO;
    
    
    @Autowired
    @Qualifier("carreraServiceImp")
    CarreraService carreraService;
    
    @Autowired
    @Qualifier("materiaServiceImp")
    MateriaService materiaService;

    
    @Autowired
    CarreraDTO carreraDTO;
    
    @Autowired
    MateriaDTO materiaDTO;
    
    //AGREGAR ATRUBUTOS AL DTO 
    //AGREGAR ATRIBUTOS A LOS MAPER

    // Muestra la lista de alumnos
    @GetMapping("/listadoAlumno")
    public String getAlumno(Model model) {
        try {
            model.addAttribute("alumno", alumnoService.MostrarAlumno());
        } catch (Exception e) {
            // Manejo de cualquier excepción que ocurra al obtener la lista de alumnos
            model.addAttribute("error", "Ocurrió un error al obtener la lista de alumnos.");
        }
        return "listaDeAlumnos";
    }

    // Muestra el formulario para agregar un nuevo alumno
    @GetMapping("/nuevo")
    public String getVistaNuevoAlumno(Model model) {
        model.addAttribute("nuevoAlumno", nuevoAlumnoDTO);
        model.addAttribute("edicion", false);
        //-----------------CAMBIOS PARA MOSTRAR EN LA VISTA------------------
//        // Añadir la lista de carreras al modelo

        List<CarreraDTO> carreras = carreraService.MostrarCarrera();
        model.addAttribute("carreras", carreras);
        return "formAlumno";
    }

    // Guarda un nuevo alumno
    @PostMapping("/guardar")
    public String guardarAlumno(@Valid @ModelAttribute("nuevoAlumno") AlumnoDTO alumnoDTO, BindingResult resultado, Model model) {
        
    	if (resultado.hasErrors()) {
    		model.addAttribute("nuevoAlumno",alumnoDTO);
    		model.addAttribute("edicion",false);
    		//-----------------CAMBIOS PARA MOSTRAR EN LA VISTA------------------
//          // Añadir la lista de carreras al modelo

          List<CarreraDTO> carreras = carreraService.MostrarCarrera();
          model.addAttribute("carreras", carreras);
         
    		return "formAlumno";
    	} else {
    		
    		//try {
                alumnoService.save(alumnoDTO);
//            } catch (Exception e) {
//                // Manejo de cualquier excepción que ocurra al guardar el alumno
//                return "redirect:/alumno/nuevo?error=true";
//            }
            return "redirect:/alumno/listadoAlumno";
    	}
    	
    	
    }

    // Muestra el formulario para modificar un alumno existente
    @GetMapping("/modificar/{dni}")
    public String getModificarAlumnoPague(Model model, @PathVariable(value = "dni") String dni) {
        try {
            AlumnoDTO alumnoEncontradoDTO = alumnoService.findByDni(dni);
            model.addAttribute("nuevoAlumno", alumnoEncontradoDTO);
            model.addAttribute("edicion", true);
          //-----------------CAMBIOS PARA MOSTRAR EN LA VISTA------------------
//          // Añadir la lista de carreras al modelo

          List<CarreraDTO> carreras = carreraService.MostrarCarrera();
          model.addAttribute("carreras", carreras);
        } catch (Exception e) {
            // Manejo de cualquier excepción que ocurra al encontrar el alumno por DNI
            return "redirect:/alumno/listadoAlumno?error=true";
        }
        return "formAlumno";
    }

    // Guarda las modificaciones de un alumno existente
    @PostMapping("/modificar")
    public String modificarAlumno(@Valid @ModelAttribute("nuevoAlumno") AlumnoDTO alumnoDTO, BindingResult resultado, Model model) {
        if (resultado.hasErrors()) {
        	model.addAttribute("nuevoAlumno", alumnoDTO);
        	return "formAlumno";
        } else {
        	try {
                alumnoService.edit(alumnoDTO);
            } catch (Exception e) {
                // Manejo de cualquier excepción que ocurra al modificar el alumno
                return "redirect:/alumno/modificar/" + alumnoDTO.getDni() + "?error=true";
            }
            return "redirect:/alumno/listadoAlumno";
        }
    }

    // Elimina un alumno por su DNI
    @GetMapping("/borrar/{dni}")
    public String eliminarAlumno(@PathVariable(value = "dni") String dni) {
        try {
            alumnoService.deleteByDni(dni);
        } catch (Exception e) {
            // Manejo de cualquier excepción que ocurra al borrar el alumno
            return "redirect:/alumno/listadoAlumno?error=true";
        }
        return "redirect:/alumno/listadoAlumno";
    }
    
    //Incriocion a materia
    @GetMapping("/inscribir/{dni}")
    
    public String getVistaInscripcionAlumno(Model model, @PathVariable(value = "dni")String dni) {
    	AlumnoDTO alumnoEncontradoDTO = alumnoService.findByDni(dni);
		System.out.println(alumnoEncontradoDTO);
        //-----------------CAMBIOS PARA MOSTRAR EN LA VISTA------------------
//        // Añadir la lista de carreras al modelo
        
		 List<MateriaDTO> materiaDTOs = materiaService.MostrarMateria(); 
         model.addAttribute("materia", materiaDTOs);
         model.addAttribute("nuevoAlumno", alumnoEncontradoDTO);
         model.addAttribute("edicion", true);
       // model.addAttribute("carreras", carreras);
        return "incripcionMateria";  
    }

    // guardar INCRIPCION
    @PostMapping("/inscripcion")
    public String inscribirMateria(@ModelAttribute("nuevoAlumno") AlumnoDTO alumnoDTO,Model model) {
        //boolean isSaved = alumnoService.save(alumnoDTO.getDni(), codigoMateria);
       // System.out.println(codigoMateria);
        System.out.println("AlumnoDTO:   "+ alumnoDTO.getDni());
        boolean isSaved=true;
        if (isSaved) {
            model.addAttribute("mensaje", "Inscripción exitosa");
        } else {
            model.addAttribute("mensaje", "Error al inscribir la materia");
        }

        model.addAttribute("alumnoDTO", new AlumnoDTO());
        model.addAttribute("materias", materiaService.MostrarMateria());
        return "redirect:/alumno/listadoAlumno";
    }
    
    
}
