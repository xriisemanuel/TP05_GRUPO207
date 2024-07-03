package ar.edu.unju.fi.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
//@Component
//@Data
//@Entity
//public class Alumno {
//	@Id
//	private String dni;
//	
//    private String nombre;
//    private String apellido;
//    private String email;
//    private String telefono;
//    
//    @DateTimeFormat(pattern = "dd/MM/yyyy")
//    private LocalDate fechaNacimiento;
//    private String domicilio;
//    private String LU;
//    private String foto;
//    private Boolean estado;
//}
import lombok.ToString;

@Component
@Data
@Entity
public class Alumno {
	@Id
	private String dni;

	@NotBlank(message = "Debe ingresar el nombre de la carrera")
	@Size(min = 3, max = 30, message = "El nombre debe contener como mínimo 3 caracteres y como máximo 30")
	@Pattern(regexp = "[a-zA-Z]*", message = "Debe ingresar únicamente letras")
	private String nombre;

	@NotBlank(message = "Debe ingresar el nombre de la carrera")
	@Size(min = 3, max = 30, message = "El nombre debe contener como mínimo 3 caracteres y como máximo 30")
	@Pattern(regexp = "[a-zA-Z]*", message = "Debe ingresar únicamente letras")
	private String apellido;

	@NotBlank(message = "Debe ingresar el correo electrónico")
	@Email(message = "Ingrese un correo electrónico válido")
	private String email;

	@NotNull(message = "El teléfono no puede ser nulo")
	//@Digits(integer = 1, fraction = 0, message = "Ingrese un valor numérico válido")
	@NotBlank(message = "Debe ingresar el teléfono celular")
	//@Pattern(regexp = "^[0-9]\\d{8,9,10}$", message = "El teléfono celular debe tener 10 dígitos y comenzar con un dígito del 0 al 9")
	private String telefono;

	@NotNull(message = "La fecha de nacimiento no puede ser nula")
	@Past(message = "La fecha de nacimiento debe ser anterior a la fecha actual")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate fechaNacimiento;

	@NotBlank(message = "Debe ingresar el nombre del domicilio")
	@Size(min = 3, max = 30, message = "El nombre debe contener como mínimo 3 caracteres y como máximo 30")
	@Pattern(regexp = "[a-zA-Z]*", message = "Debe ingresar únicamente letras")
	private String domicilio;

	@NotBlank(message = "Debe ingresar el nombre de la carrera")
	@Size(min = 3, max = 30, message = "El nombre debe contener como mínimo 3 caracteres y como máximo 30")
	@Pattern(regexp = "[0-9a-zA-Z]*", message = "Solo caracteres alfanuméricos")
	private String LU;

	private String foto;

	private Boolean estado;
	
	 @ManyToOne
	 @JoinColumn(name = "codigo")
	 private Carrera carrera;
	 
	
	 @ManyToMany(mappedBy = "alumnos")
	 @ToString.Exclude // Excluir materias del toString para evitar recursión infinitas
	 private List<Materia> materias;
}
