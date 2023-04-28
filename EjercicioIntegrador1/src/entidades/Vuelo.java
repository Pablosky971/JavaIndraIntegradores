package entidades;

import java.time.LocalDate;
import java.time.Period;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Vuelo extends EmpresaAeronautica implements iVuelo{
	
	private String nombreVuelo;
	private Integer cantidadPasajeros;
	private TipoPasaje tipoPasaje;
	private Double valorUnitarioPasaje;
	private LocalDate fechaVuelo;
	private Rentabilidad rentabilidad;
	
	public Vuelo(String nombreEmpresa,String nombreVuelo,
			Integer cantidadPasajeros,TipoPasaje tipoPasaje,
			Double valorUnitarioPasaje, LocalDate fechaVuelo) {
		super(nombreEmpresa);
		
		this.nombreEmpresa=nombreEmpresa;
		this.nombreVuelo = nombreVuelo;
		this.cantidadPasajeros = cantidadPasajeros;
		this.tipoPasaje = tipoPasaje;
		this.valorUnitarioPasaje = valorUnitarioPasaje;
		this.fechaVuelo = fechaVuelo;
		segmentacion();
		
		
		
		
	}
	
	@Override
	public Double valorFinalVuelo() {
		
		return valorUnitarioPasaje * cantidadPasajeros;
	}
	@Override
	public Integer diasDiferencia() {
		
		Integer diasRestantes  = Period.between(fechaVuelo, LocalDate.now()).getDays();
		return diasRestantes;
		
	}
	@Override
	public void segmentacion() {
		Double valorFinalVuelo = valorFinalVuelo();
		switch(tipoPasaje) {
		case ECONOMICO:
			if(valorFinalVuelo < 100.0) {
				rentabilidad=Rentabilidad.NO_RENTABLE;
			} else {
				rentabilidad=Rentabilidad.A_CONFIRMAR;
			}
			break;
		
		case PREMIER:
			if(valorFinalVuelo==1500.0) {
				rentabilidad=Rentabilidad.A_CONFIRMAR;
			} else {
				rentabilidad=Rentabilidad.RENTABLE;
			}
			
		}
		
		
		
			
		}



}
