package es.comidas.model;

import java.util.List;

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
public class Receta implements IReceta {
	private String nombre;
	private List<Ingrediente> ingredientes;
	private TipoComida tipoComida;
	
	@Override
	public Integer getCantidadCalorias() {
		Integer calorias = 0;
		for(Ingrediente ingrediente: ingredientes) {
			calorias+=3;
		}
		return calorias;
	}

	@Override
	public Integer getTiempoCoccionEnMinutos() {
		
		Integer tiempoCoccion = 0;
		for(Ingrediente ingrediente: ingredientes) {
			tiempoCoccion++;
		}
		return tiempoCoccion;
	}
	
	

}
