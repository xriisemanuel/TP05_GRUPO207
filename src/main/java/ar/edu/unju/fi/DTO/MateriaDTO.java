package ar.edu.unju.fi.DTO;

import org.springframework.stereotype.Component;

import ar.edu.unju.fi.model.Docente;
import ar.edu.unju.fi.model.Modalidad;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@Component
public class MateriaDTO {
	
		@Size(min=4, message="El nombre debe contener como mínimo 4 caracteres")
		@Pattern(regexp = "^cod[0-9]+$", message = "El legajo debe iniciar con cod y número de codigo. Ejemplo: cod1,cod2")
		private String codigo;
		
		@Size(min=3, max=40, message="El nombre debe contener como mínimo 3 caracteres y como máximo 20 caracteres")
		@Pattern(regexp= "[a-z A-Z]*", message="Debe ingresar únicamente letras")
		private String nombre; 
		
		@Size(min=3, max=20, message="El nombre debe contener como mínimo 3 caracteres y como máximo 20 caracteres")
		@Pattern(regexp = "^[a-z A-Z0-9]*$", message="Debe ingresar únicamente letras y numeros")
		private String curso;
		
		@Min(value = 50, message = "La cantidad de horas mínimas son 50")
		@Max(value = 200, message = "La cantidad de horas maximas son 200")
		private Integer cantidadHoras;	
		
		private Modalidad modalidad;
		private Boolean estado;
		private Docente docente;
}
