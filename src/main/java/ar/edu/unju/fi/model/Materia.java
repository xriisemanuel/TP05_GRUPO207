package ar.edu.unju.fi.model;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
@Component
@Data
@Entity()

public class Materia {
	@Id
	@Size(min=4, message="El nombre debe contener como mínimo 4 caracteres")
	@Pattern(regexp = "^cod[0-9]+$", message = "El legajo debe iniciar con cod y número de codigo. Ejemplo: cod1,cod2")
	private String codigo;
	
	@Size(min=3, max=40, message="El nombre debe contener como mínimo 3 caracteres y como máximo 20 caracteres")
	@Pattern(regexp= "[a-z A-Z]*", message="Debe ingresar únicamente letras")
	private String nombre;
	
	@Size(min=3, max=20, message="El nombre debe contener como mínimo 3 caracteres y como máximo 20 caracteres")
	@Pattern(regexp = "^[a-z A-Z0-9]*$", message="Debe ingresar únicamente letras")
	private String curso;
	
	@Min(value = 50, message = "La cantidad de horas mínimas son 50")
	@Max(value = 200, message = "La cantidad de horas maximas son 200")
	private Integer cantidadHoras;
	
	@Enumerated(EnumType.STRING)
	private Modalidad modalidad;
	private Boolean estado;
	
	@OneToOne
	@JoinColumn(name = "legajo")
	private Docente docente;
	

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(
	    name = "materia_alumno",
	    joinColumns = @JoinColumn(name = "codigo"),
	    inverseJoinColumns = @JoinColumn(name = "dni")
	)
	private List<Alumno> alumnos;
	
	 @ManyToOne()
	 @JoinColumn(name = "carrera_id")
    private Carrera carrera;
	
}