package ar.edu.unju.fi.DTO;

import java.util.List;

import org.springframework.stereotype.Component;

import ar.edu.unju.fi.model.Materia;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Component
@NoArgsConstructor
public class CarreraDTO {
	@Id
	@NotBlank(message="Debe ingresar un codigo de la carrera")
	@Size(min=3,max=30,message="El codigo debe contener como minimo 3 caracteres y como maximo 30")
	//@Pattern(regexp="[a-zA-Z]*", message="Debe ingresar unicamente letras")
	private String codigo;
	
	//@NonNull
	@NotBlank(message="Debe ingresar el nombre de la carrera")
	@Size(min=3,max=30,message="El nombre debe contener como minimo 3 caracteres y como maximo 30")
	@Pattern(regexp="[a-zA-Z]*", message="Debe ingresar unicamente letras")
	private String nombre;
	
	//@NonNull
	 @NotNull(message = "La cantidad de años no puede ser nula")
	 @Digits(integer = 1, fraction = 0, message = "Ingrese un valor numérico válido")
	 @Min(value = 1, message = "La cantidad de años debe ser como mínimo 1")
	 @Max(value = 6, message = "La cantidad de años debe ser como máximo 6")
	private Integer cantidadAnios;

	private boolean estado;
	private List<AlumnoDTO> alumnos;
	
}