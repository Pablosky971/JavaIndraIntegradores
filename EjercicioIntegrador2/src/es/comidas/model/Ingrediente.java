package es.comidas.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Ingrediente extends Alimento {
	private String cantidad;

	public Ingrediente(String nombre, String cantidad) {
		super(nombre);
		this.cantidad = cantidad;
	}
	
	
}
