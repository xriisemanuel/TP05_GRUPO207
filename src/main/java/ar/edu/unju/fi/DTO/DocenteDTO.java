package ar.edu.unju.fi.DTO;

import org.springframework.stereotype.Component;

import ar.edu.unju.fi.model.Materia;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter 
@NoArgsConstructor
@Component

public class DocenteDTO {
	@Size(min=8, max=9, message="El nombre debe contener como mínimo 8 caracteres y como máximo 9 caracteres")
	//@Pattern(regexp = "[A-Z]*+[0-9]*", message = "El legajo debe contener únicamente letras y números. Ejemplo: APU004236")
	@Pattern(regexp = "^[A-Z]{3}+[0-9]{5,6}$", message = "El legajo debe contener únicamente letras y números. Ejemplo: APU004236")
	private String legajo;
	@Size(min=3, max=20, message="El nombre debe contener como mínimo 3 caracteres y como máximo 20 caracteres")
	@Pattern(regexp= "[a-z A-Z]*", message="Debe ingresar únicamente letras")
	private String nombre;
	@Size(min=3, max=20, message="El nombre debe contener como mínimo 3 caracteres y como máximo 20 caracteres")
	@Pattern(regexp= "[a-z A-Z]*", message="Debe ingresar únicamente letras")
	private String apellido;
	@Email(message = "Debe ingresar un correo electrónico válido")	
    private String email;
	@NotBlank(message = "Debe ingresar un número de teléfono")
	@Pattern(regexp = "^[1-9]\\d{9}$", message = "El número de teléfono debe contener 10 dígitos, sin 0 y sin 15: ejemplo 3884605499")
    private String telefono;
    private Boolean estado;
    private String foto;
    private Materia materia;
}
