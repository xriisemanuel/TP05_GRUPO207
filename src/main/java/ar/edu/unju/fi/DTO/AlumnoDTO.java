package ar.edu.unju.fi.DTO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Component
public class AlumnoDTO {
	@Id
	@NotBlank(message="Debe ingresar un codigo de la carrera")
	@Size(min=3,max=30,message="El codigo debe contener como minimo 3 caracteres y como maximo 30")
	//@Pattern(regexp="[a-zA-Z]*", message="Debe ingresar unicamente letras")
	private String dni;

	@NotBlank(message = "Debe ingresar el nombre de la carrera")
	@Size(min = 3, max = 30, message = "El nombre debe contener como minimo 3 caracteres y como maximo 30")
	@Pattern(regexp = "[a-zA-Z]*", message = "Debe ingresar unicamente letras")
	private String nombre;

	@NotBlank(message = "Debe ingresar el nombre de la carrera")
	@Size(min = 3, max = 30, message = "El nombre debe contener como minimo 3 caracteres y como maximo 30")
	@Pattern(regexp = "[a-zA-Z]*", message = "Debe ingresar unicamente letras")
	private String apellido;

	@NotBlank(message = "Debe ingresar el correo electrónico")
	@Email(message = "Ingrese un correo electrónico válido")
	private String email;

	@NotNull(message = "El teléfono no puede ser nulo")
	@Digits(integer = 1, fraction = 0, message = "Ingrese un valor numérico válido")
//	 @Min(value = 1000000000, message = "Numero ingresado es inválido")
//	 @Max(value = 9999999999, message = "Numero ingresado es invalido")
	@NotBlank(message = "Debe ingresar el teléfono celular")
	@Pattern(regexp = "^[0-9]\\d{9}$", message = "El teléfono celular debe tener 10 dígitos y comenzar con un dígito del 0 al 9")
	private String telefono;

	@NotNull(message = "La fecha de nacimiento no puede ser nula")
	@Past(message = "La fecha de nacimiento debe ser anterior a la fecha actual")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate fechaNacimiento;
	
	@NotBlank(message = "Debe ingresar el nombre del domicilio")
	@Size(min = 3, max = 30, message = "El nombre debe contener como minimo 3 caracteres y como maximo 30")
	@Pattern(regexp = "[a-zA-Z]*", message = "Debe ingresar unicamente letras")
	private String domicilio;
	
	@NotBlank(message = "Debe ingresar el nombre de la carrera")
	@Size(min = 3, max = 30, message = "El nombre debe contener como minimo 3 caracteres y como maximo 30")
	@Pattern(regexp = "[0-9a-zA-Z]*", message = "Solo caracteres alfanumericos")
	private String LU;
	
	//es un select y no es necesario
	private String foto;
	
	private Boolean estado;
    public String getFechaNacimientoForHtml() {
        return fechaNacimiento != null ? fechaNacimiento.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) : "";
        
    }
    
    
}