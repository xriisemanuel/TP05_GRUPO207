package ar.edu.unju.fi.model;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
//import lombok.Data;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.NonNull;
//import lombok.RequiredArgsConstructor;
//import lombok.Setter;

//@NoArgsConstructor
//@RequiredArgsConstructor
@Data
@Entity
@Component
public class Carrera {
	
	@Id
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
	
	@OneToMany(mappedBy = "carrera")
    private List<Alumno> alumnos;
	
}

//@Getter
//@Setter
//@NoArgsConstructor
//@RequiredArgsConstructor
//@Entity
//@Component
//public class Carrera {
//	
////	@Id
////	private String codigo;
////	@NonNull
////	private String nombre;
////	@NonNull
////	private Integer cantidadAnios;
////
////	private boolean estado;
////}
	
//@Getter
//@Setter
