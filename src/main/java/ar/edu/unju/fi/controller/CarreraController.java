package ar.edu.unju.fi.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ar.edu.unju.fi.DTO.AlumnoDTO;
import ar.edu.unju.fi.DTO.CarreraDTO;
import ar.edu.unju.fi.service.AlumnoService;
import ar.edu.unju.fi.service.CarreraService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/carrera")
public class CarreraController {
	
	@Autowired
	CarreraService carreraService;
	
	@Autowired
	CarreraDTO nuevoCarreraDTO;
	
	@Autowired
    private AlumnoService alumnoService;
	
	@GetMapping("/listado")
	public String getCarreras(Model model) {
		try {
			model.addAttribute("carrera",carreraService.MostrarCarrera());
		} catch (Exception e) {
			model.addAttribute("carrera", carreraService.MostrarCarrera());
		}
		return "listaDeCarreras";
	}
	
	@GetMapping("/nuevo")
	public String getVistaNuevaCarrera(Model model) {
		model.addAttribute("nuevaCarrera",nuevoCarreraDTO);
		model.addAttribute("edicion",false);
		return "formCarrera";
	}
	
	@PostMapping("/guardar")
	public String guardarCarrera(@Valid @ModelAttribute("nuevaCarrera") CarreraDTO carreraDTO, BindingResult resultado, Model model) {
		
		if (resultado.hasErrors()) {
			model.addAttribute("nuevaCarrera", carreraDTO);
			model.addAttribute("edicion",false);
			return "formCarrera";
		} else {
			try {
				carreraService.save(carreraDTO);
			} catch(Exception e) {
				return "redirect:/carrera/nuevo?error=true";
			}
			
			return "redirect:/carrera/listado";
		}
	}
	
	@GetMapping("/modificarCarrera/{codigo}")
	public String getModificarCarreraPage(Model model, @PathVariable(value = "codigo") String codigo) {
		try {
			boolean edicion = true;
			CarreraDTO carreraEncontradaDTO = carreraService.findByCodigo(codigo);
			model.addAttribute("nuevaCarrera",carreraEncontradaDTO);
			model.addAttribute("edicion",edicion);
		} catch (Exception e) {
			return "redirect:/carrera/listado?error=true";
		}
		return "formCarrera";
	}
	
	@PostMapping("/modificar")
	public String modificarCarrera(@Valid @ModelAttribute("nuevaCarrera") CarreraDTO carreraDTO, BindingResult resultado, Model model) {
		if (resultado.hasErrors()) {
			model.addAttribute("nuevaCarrera", carreraDTO);
			model.addAttribute("edicion",false);
			return "formCarrera";
		} else {
			try {
				carreraService.save(carreraDTO);
			} catch(Exception e) {
				 return "redirect:/carrera/modificar/" + carreraDTO.getCodigo() + "?error=true";
			}
			return "redirect:/carrera/listado";
		}
	}
	
	@GetMapping("/borrarCarrera/{codigo}")
	public String eliminarCarrera(@PathVariable(value = "codigo") String codigo) {
		
		try {
			carreraService.deleteByCodigo(codigo);
		} catch (Exception e) {
			return "redirect:/carrera/listado?error=true";
		}
		
		return "redirect:/carrera/listado";
	}
	
	@GetMapping("/alumnosPorCarrera")
	public String getAlumnosPorCarrera(@RequestParam("carreraId") String carreraId, Model model) {
	    try {
	        List<AlumnoDTO> alumnos = alumnoService.findByCarrera(carreraId);
	        System.out.println("Alumnos encontrados: " + alumnos);  // Línea de depuración
	        model.addAttribute("alumnos", alumnos);
	        
	    } catch (Exception e) {
	        // Manejo de errores, por ejemplo:
	        model.addAttribute("error", "Error al buscar alumnos por carrera");
	        return "errorPage"; // Asegúrate de tener una vista 'errorPage.html'
	    }
	    return "alumnosPorCarrera"; // Asegúrate de tener una vista 'alumnosPorCarrera.html'
	}

	
	
	
//NO ELIMINAR ESTA PARTE
  //-------------------------------------------------------------------------------------------------------------------
//    @Autowired
//    private Carrera nuevaCarrera; // inyección de dependencias
//    @Autowired
//    private CarreraService cs;
//    
//    // Muestra Carreras
//    @GetMapping("/listado")
//    public String getCarreras(Model model) {
//       model.addAttribute("carrera", cs.MostrarCarrera());
//        return "listaDeCarreras";
//    }
//    
//    @GetMapping("/nuevo")
//    public String getVistaNuevaCarrera(Model model) {
//        boolean edicion = false; // No quiero editar un objeto
//        model.addAttribute("nuevaCarrera", nuevaCarrera);
//        model.addAttribute("edicion", edicion);
//        return "formCarrera";
//    }
//    
//    @PostMapping("/guardar")
//    public String guardarCarrera(@ModelAttribute("nuevaCarrera") Carrera carrera) {
//    	carrera.setEstado(true);
//        cs.guardarCarrera(carrera);
//        //System.out.println("Carrera en el metodo Guardar" + carrera.getCodigo());
//        return "redirect:/carrera/listado";
//    }
//    
//    @GetMapping("/modificarCarrera/{codigo}")
//    public String getModificarCarreraPague(Model model, @PathVariable(value = "codigo") String codigo) {
//        Carrera nuevaCarrera = cs.buscaCarrera(codigo);
//        boolean edicion = true;
//        model.addAttribute("nuevaCarrera", nuevaCarrera);
//        model.addAttribute("edicion", edicion);
//        return "formCarrera";
//    }
//    
//    @PostMapping("/modificar")
//    public String modificarCarrera(@ModelAttribute("nuevaCarrera") Carrera carrera) {
//       cs.modificarCarrera(carrera);
//        return "redirect:/carrera/listado";
//    }
//    
//    @GetMapping("/borrarCarrera/{codigo}")
//    public String eliminarCarrera(@PathVariable(value = "codigo") String codigo) {
//    	cs.borrarCarrera(codigo);
//    	return "redirect:/carrera/listado";
//    }
//    
}
